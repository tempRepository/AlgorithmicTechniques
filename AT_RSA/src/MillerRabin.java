import java.math.BigInteger;
import java.util.Random;

public class MillerRabin {

    
    public static Boolean isPrime(BigInteger p, int precision) {
        return p.isProbablePrime(precision);
       
    }
    
    

    public static void main(String[] args) {
       // System.out.println(MillerRabin.isPrime(17, 1000));
       System.out.println(MillerRabin.isPrime(new BigInteger("17", 10), 1000));
    }

}
