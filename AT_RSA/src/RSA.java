import java.math.BigInteger;
import java.util.Random;

public class RSA {

    private static Integer generateBigPrime() {
        Random rand = new Random();
        int keyLength = 16;
        Integer p = 2;
        do {
            p = rand.nextInt((int) Math.pow(2, keyLength / 2));
            // System.out.println(p+" -> "+Integer.toBinaryString(p));
            StringBuilder temp = new StringBuilder();
            temp.append("1");
            for (int j = 1; j < (keyLength)
                    - Integer.toBinaryString(p).length(); j++) {
                temp.append("0");
            }
            String tempString = Integer.toBinaryString(p);
            temp.append(tempString.substring(0, tempString.length() - 1));
            temp.append("1");

            // System.out.println("Result:  ->"+temp);
            Long decimal = Long.parseLong(temp.toString(), 2);
            p = decimal.intValue();
            /*
             * if (mode == 1) { p = (int)
             * (Long.parseLong(Integer.toBinaryString(p) .replaceFirst("0",
             * "1"), 2)); }
             */

        } while (MillerRabin.isPrime(p, 10));
        return p;
    }

    private static Integer generatePrime() {
        Random rand = new Random();
        Integer p = 2;
        do {
            p = rand.nextInt((int) Math.pow(2, 15));

        } while (!MillerRabin.isPrime(p, 1000000));
        return p;
    }

    public static void main(String[] args) {

       int p = generatePrime();
        int q = generatePrime();
        
      //  int p = 13;
      //  int q = 11;
        
        int phi =((p - 1) * (q - 1));
        if (phi<0) {
            System.out.println("Phi is smaller than 0!ERROR!");
        }
        Integer n = (int) (p * q);
        boolean appropriateE = false;
        int iterator=0;
        int[] eCandidates={3,5,17,257};
        int e =3;
        
        while (!appropriateE) {
           // System.out.println(e);
            if (iterator<eCandidates.length) {
                e=eCandidates[iterator];
                iterator++;
            } else {
                e=e+2;
            }
            
            if (e < n && GCD.simpleGCD(e, phi) == 1) {
                appropriateE = true;
            } else {
               // e =(int)Math.pow(2, Math.pow(2, iterator))+1;
                e+=2;
                iterator++;
            }
            
            if (e>n) {
                System.out.println("ERROR");
                break;
            }

        }
        System.out.println("p=" + p + " q=" + q);
        System.out.println("e=" + e + " phi=" + phi);
       // d × e  mod Ø = 1
        int d=GCD.modularInversion(e, phi)%phi;
        System.out.println("d= "+d);
    }

}
