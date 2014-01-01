import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class RSA {

    private static long generateBigPrime() {
        Random rand = new Random();
        long p = 0;
        do {
            StringBuilder tempBits = new StringBuilder("1");
            for (int i = 0; i < 3; i++) {
                if (rand.nextBoolean()) {
                    tempBits.append("1");
                } else {
                    tempBits.append("0");
                }
            }
            tempBits.append("1");
            p = Long.parseLong(tempBits.toString(), 2);
        } while (!MillerRabin.isPrime(p, 100000000));
        return p;

    }

    public static long decrypt(long encryptedData, long d, long n) {
        return (long) (QuickExponentiation.pow(encryptedData, d, n));
    }

    public static long encrypt(long dateToEncrypt, long e, long n) {

        return (long) QuickExponentiation.pow(dateToEncrypt, e, n);

    }

    private static int generatePrime() {
        Random rand = new Random();
        int p = 2;
        do {
            p = rand.nextInt((int) Math.pow(2, 6));

        } while (!MillerRabin.isPrime(p, 100000000));
        return p;
    }

    public static void saveKeysToFile() {
        long p = generateBigPrime();
        long q = generateBigPrime();

        /*
         * int p = 13; int q = 11;
         */

        long phi = ((p - 1) * (q - 1));
        if (phi < 0) {
            System.out.println("Phi is smaller than 0!ERROR!");
        }
        long n = (int) (p * q);
        boolean appropriateE = false;
        int iterator = 0;
        int[] eCandidates = { 3, 5, 17, 257 };
        int e = 3;

        while (!appropriateE) {
            // System.out.println(e);
            if (iterator < eCandidates.length) {
                e = eCandidates[iterator];
                iterator++;
            } else {
                e = e + 2;
            }

            if (e < n && GCD.simpleGCD(e, phi) == 1) {
                appropriateE = true;
            } else {
                // e =(int)Math.pow(2, Math.pow(2, iterator))+1;
                e += 2;
                iterator++;
            }

            if (e > n) {
                System.out.println("ERROR");
                break;
            }

        }
        System.out.println("p=" + p + " q=" + q);
        System.out.println("e=" + e + " phi=" + phi + " n= " + n);
        // d × e mod Ø = 1
        long d = GCD.modularInversion(e, phi) % phi;
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
            // TODO Auto-generated catch block
            exception.printStackTrace();
        }

    }

    public static void encryptFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("public_key.txt"));
        long e = -1, n = -1;
        e = Long.parseLong(br.readLine());
        n = Long.parseLong(br.readLine());

        br.close();

        br = new BufferedReader(new FileReader(fileName));
        PrintWriter out;
        out = new PrintWriter("encrypted.txt");
        try {

            Long line = new Long(br.read());

            while (line != -1) {

                line = new Long(br.read());
                if (line != -1) {
                    String temp = String.valueOf(RSA.encrypt(line, e, n)) + "|";
                    out.write(String.valueOf(RSA.encrypt(line, e, n)) + "|");
                }
            }

        } finally {
            br.close();
            out.close();
        }
    }

    public static void decryptFile() throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(
                new FileReader("private_key.txt"));
        long d = -1, n = -1;
        d = Long.parseLong(br.readLine());
        n = Long.parseLong(br.readLine());
        br.close();

        Scanner scanner = new Scanner(new File("encrypted.txt"));
       scanner.useDelimiter("|");
        PrintWriter out;
        out = new PrintWriter("decrypted.txt");
      
        while (scanner.hasNext()) {
            String temp = " ";
            StringBuilder readNumber = new StringBuilder();
            while (!temp.equals("|") && scanner.hasNext()) {
                temp=scanner.next();
                if (!temp.equals("|")) {
                    readNumber.append(temp);
                } else {
                    out.print((char) RSA.decrypt(
                            Long.parseLong(readNumber.toString()), d, n));
                }
                
            }

        }
        out.close();
    }

    public static void main(String[] args) throws IOException {
        saveKeysToFile();
         encryptFile("data.txt");
        decryptFile();

    }

}
