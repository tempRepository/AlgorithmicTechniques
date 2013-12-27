import java.util.Random;

public class RSA {

    public static void main(String[] args) {
        // generating 2 prime numbers
        Random rand = new Random();
        int keyLength = 16;
        Integer p = 2;
        do {
            p = rand.nextInt((int) Math.pow(2, keyLength / 2));
            StringBuilder temp = new StringBuilder(keyLength / 2);
            temp.append("1");
            for (int j = 1; j < (keyLength / 2)
                    - Integer.toBinaryString(p).length(); j++) {
                temp.append("0");
            }
            String tempString = Integer.toBinaryString(p);
            temp.append(tempString.substring(0, tempString.length() - 1));
            temp.append("1");
            Long decimal = Long.parseLong(temp.toString(), 2);
            p = decimal.intValue();
        } while (MillerRabin.isPrime(p, 10));
        System.out.println(p);
    }
}
