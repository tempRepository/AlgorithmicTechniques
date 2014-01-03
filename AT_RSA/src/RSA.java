import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class RSA {

    private static BigInteger generateBigPrime() {
        Random rand = new Random();
        BigInteger p = new BigInteger("0", 10);
        do {
            StringBuilder tempBits = new StringBuilder("1");
            for (int i = 0; i < 30; i++) {
                if (rand.nextBoolean()) {
                    tempBits.append("1");
                } else {
                    tempBits.append("0");
                }
            }
            tempBits.append("1");
            p = new BigInteger(tempBits.toString(), 2);
        } while (!MillerRabin.isPrime(p, 100000000));
        return p;

    }

    private static BigInteger generateBigPrime(BigInteger differentFrom) {
        Random rand = new Random();
        BigInteger p = new BigInteger("0", 10);
        do {
            StringBuilder tempBits = new StringBuilder("1");
            for (int i = 0; i < 30; i++) {
                if (rand.nextBoolean()) {
                    tempBits.append("1");
                } else {
                    tempBits.append("0");
                }
            }
            tempBits.append("1");
            p = new BigInteger(tempBits.toString(), 2);
        } while (!MillerRabin.isPrime(p, 100000000) || p.equals(differentFrom));
        return p;

    }

    public static BigInteger decrypt(BigInteger encryptedData, BigInteger d,
            BigInteger n) {
        BigInteger result = QuickExponentiation.pow(encryptedData, d, n);
        return result;
    }

    public static BigInteger encrypt(BigInteger dateToEncrypt, BigInteger e,
            BigInteger n) {

        BigInteger result = QuickExponentiation.pow(dateToEncrypt, e, n);
        return result;

    }

    private static BigInteger generatePrime() {
        Random rand = new Random();
        BigInteger p = new BigInteger("0", 10);
        do {
            p = new BigInteger(
                    String.valueOf(rand.nextInt((int) Math.pow(2, 6))), 10);

        } while (!MillerRabin.isPrime(p, 100000000));
        return p;
    }

    public static void saveKeysToFile() {

        BigInteger p = generateBigPrime();
        BigInteger q = generateBigPrime(p);

/*        BigInteger p = new BigInteger("13", 10);
        BigInteger q = new BigInteger("11", 10);*/

        BigInteger pMinus1 = p.subtract(new BigInteger("1", 10));
        BigInteger qMinus1 = q.subtract(new BigInteger("1", 10));
        BigInteger phi = pMinus1.multiply(qMinus1);
        if (phi.compareTo(new BigInteger("0", 10)) < 0) {
            System.out.println("Phi is smaller than 0!ERROR!");
        }
        /* long n = (int) (p * q); */
        BigInteger n = (new BigInteger(p.toString(), 10))
                .multiply((new BigInteger(q.toString(), 10)));
        boolean appropriateE = false;
        int iterator = 0;
        BigInteger[] eCandidates = { new BigInteger("3", 10),
                new BigInteger("5", 10), new BigInteger("17", 10),
                new BigInteger("257", 10) };
        BigInteger e = new BigInteger("3", 10);

        while (!appropriateE) {
            // System.out.println(e);
            if (iterator < eCandidates.length) {
                e = eCandidates[iterator];
                iterator++;
            } else {
                e = e.add(new BigInteger("2", 10));
            }

            if (new BigInteger(e.toString(), 10).compareTo(n) < 0
                    && GCD.simpleGCD(e, phi).equals(new BigInteger("1", 10))) {
                appropriateE = true;
            } else {
                // e =(int)Math.pow(2, Math.pow(2, iterator))+1;
                e = e.add(new BigInteger("2", 10));
                iterator++;
            }

            if (new BigInteger(e.toString(), 10).compareTo(n) > 0) {
                System.out.println("ERROR");
                break;
            }

        }
        System.out.println("p=" + p + " q=" + q);
        System.out.println("e=" + e + " phi=" + phi + " n= " + n);
        // d × e mod Ø = 1
        BigInteger d = GCD.modularInversion(e, phi).mod(phi);
        System.out.println("d= " + d);
        // int data = 13;
        // long encryptedData = RSA.encrypt(data, e, n);
        // System.out.println("Encrypted 13: " + encryptedData);
        // System.out.println("Decrypted :" + RSA.decrypt(encryptedData, d, n));

        PrintWriter out;
        try {
            out = new PrintWriter("public_key.txt");
            out.println(e);
            out.println(n);
            out.close();
            out = new PrintWriter("private_key.txt");
            out.println(d);
            out.println(n);
            out.close();
        } catch (FileNotFoundException exception) {

            exception.printStackTrace();
        }

    }

    public static void createSignature(String fileName, BigInteger e,
            BigInteger n) throws FileNotFoundException {
        String myText = Signature.loadText(fileName);
        BigInteger fileHash = Signature.computeHash(myText, n);
        System.out.println("First calculated signature(decoded): " + fileHash);
        PrintWriter out = new PrintWriter("signature.txt");
        out.print(RSA.encrypt(new BigInteger(fileHash.toString(), 10), e, n));
        out.close();
    }

    public static void encryptFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("public_key.txt"));
        BigInteger e;
        BigInteger n;
        e = new BigInteger(br.readLine(), 10);
        n = new BigInteger(br.readLine(), 10);

        br.close();

        br = new BufferedReader(new FileReader(fileName));
        PrintWriter out;
        out = new PrintWriter("encrypted.txt");
        try {

            BigInteger line = new BigInteger("0", 10);

            while (!line.equals(new BigInteger("-1", 10))) {

                line = new BigInteger(String.valueOf(br.read()), 10);
                if (!line.equals(new BigInteger("-1", 10))) {// &&
                                                             // !line.equals(new
                                                             // BigInteger("10",
                                                             // 10))) {
                    String temp = (RSA.encrypt(line, e, n)).toString() + "|";
                    out.write(String.valueOf(RSA.encrypt(line, e, n)) + "|");
                }
            }

        } finally {
            br.close();
            out.close();
        }

        createSignature(fileName, e, n);
    }

    public static void decryptFile() throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(
                new FileReader("private_key.txt"));
        BigInteger d, n;
        d = new BigInteger(br.readLine(), 10);
        n = new BigInteger(br.readLine(), 10);
        br.close();

        Scanner scanner = new Scanner(new File("encrypted.txt"));
        scanner.useDelimiter("|");
        PrintWriter out;
        out = new PrintWriter("decrypted.txt");

        while (scanner.hasNext()) {
            String temp = " ";
            StringBuilder readNumber = new StringBuilder();
            while (!temp.equals("|") && scanner.hasNext()) {
                temp = scanner.next();
                if (!temp.equals("|")) {
                    readNumber.append(temp);
                } else {
                    out.print((char) (RSA.decrypt(
                            new BigInteger(readNumber.toString(), 10), d, n)
                            .intValue()));
                }

            }

        }
        out.close();

        // signature checking
        String myText = new Scanner(new File("decrypted.txt")).useDelimiter(
                "\\A").next();
        BigInteger computedSignature = Signature.computeHash(myText, n);
        System.out.println("Computed signature: " + computedSignature);

        BigInteger encodedSignature = new BigInteger((new Scanner(new File(
                "signature.txt")).useDelimiter("\\A").next()).replaceAll("\\n",
                ""), 10);
        BigInteger decodedSignature = RSA.decrypt(new BigInteger(
                encodedSignature.toString(), 10), d, n);

        if (computedSignature.equals(decodedSignature)) {
            System.out.println("Signature is valid!");
        } else {
            System.out.println("Signature is invalid");
        }

    }

    public static void main(String[] args) throws IOException {
        saveKeysToFile();
        encryptFile("data.txt");
        decryptFile();

    }

}
