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
        } while (MillerRabin.isPrime(p, 10));
        return p;
    }

    public static void main(String[] args) {

        Integer p = generateBigPrime();
        Integer temp = generateBigPrime();
        // switching second most important bit for more diversity between p and q
        Integer q = (int) (Long.parseLong(Integer.toBinaryString(temp)
                .replaceFirst("0", "1"), 2));
        Integer phi = (p - 1) * (q - 1);
        Integer n = p * q;
        int[] candidatesForE={3, 5, 17, 257};
        boolean appropriateE=false;
        int candidatesIterator=0;
        int e=-1;
        //checking if e is smaller than phi!!!
        while(!appropriateE)
        {
            if (candidatesIterator<candidatesForE.length) {
                e=candidatesForE[candidatesIterator];
                candidatesIterator++;
            } else {
                e=e+2;
            }
            if (GCD.simpleGCD(e, phi)==1) {
                appropriateE=true;
            }
        }
        System.out.println("e="+e+" phi="+phi);
    }

}
