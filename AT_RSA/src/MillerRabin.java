import java.math.BigInteger;
import java.util.Random;

public class MillerRabin {

    public static Boolean isPrime(int number, int precision) {
        int maxPowerOf2 = -1;
        int powerIterator = 1;
        while (true) {
            if ((number - 1) % Math.pow(2, powerIterator) != 0) {
                break;
            } else {
                maxPowerOf2 = powerIterator;
                powerIterator++;
            }
        }
        int d = (int) ((double) number / Math.pow(2, maxPowerOf2));
        Random rand = new Random();
        for (int k = 0; k < precision; k++) {
            int randomNumber = 1;
            while (randomNumber <= 1 || randomNumber >= number) {
                randomNumber = rand.nextInt(number);
            }
            if (QuickExponentiation.pow(randomNumber, d, number) != 1) {
                for (int r = 0; r <= maxPowerOf2 - 1; r++) {
                    if (QuickExponentiation.pow(randomNumber,
                            (int) (d * Math.pow(2, r)), number) != number - 1) {
                        return true;
                    }
                }

            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(MillerRabin.isPrime(98, 10));
    }

}
