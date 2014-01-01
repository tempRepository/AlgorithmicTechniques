import java.math.BigInteger;
import java.util.Random;

public class MillerRabin {

    
    public static Boolean isPrime(long p, int precision) {
        BigInteger bigInteger=BigInteger.valueOf(p);
        return bigInteger.isProbablePrime(precision);
    }
    
   /* 
        if (number % 2 == 0) {
            return false;
        }

        int powerIterator = 1;
        while (true) {
            if ((number - 1) % Math.pow(2, powerIterator) != 0) {
                break;
            } else {

                powerIterator++;
            }
        }
        int maxPowerOf2 = powerIterator;
        int d = (int) ((double) number / Math.pow(2, maxPowerOf2));
        Random rand = new Random();
        for (int k = 0; k < precision; k++) {
            int randomNumber = 1;
            while (randomNumber <= 1 || randomNumber >= number) {
                randomNumber = rand.nextInt(number);
            }
            boolean flag=false;
            for (int r = 0; r <= maxPowerOf2 - 1; r++) {

                if (QuickExponentiation.pow(randomNumber, (int)(d * Math.pow(2, r)),
                        number) == number - 1) {
                   flag=true;
                    }

                }
            if (flag) {
                return false;
            }
            
        }
        return true;
    }*/
    
    

    public static void main(String[] args) {
        System.out.println(MillerRabin.isPrime(17, 1000));
    }

}
