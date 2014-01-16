import java.math.BigInteger;


public class QuickExponentiation {
   public static BigInteger pow(BigInteger dateToEncrypt, BigInteger e, BigInteger n)
   {

       String bits=e.toString(2);
       
       int m=bits.length();
       dateToEncrypt= dateToEncrypt.mod(n);
       BigInteger result=new BigInteger("1", 10);
       BigInteger x=new BigInteger(dateToEncrypt.toString(), 10);
       for (int i = m-1; i >-1; i--) {
        if (bits.charAt(i)=='1') {
            result=result.multiply(x);
            result=result.mod(n);
        }
        x=x.multiply(x);
        x=x.mod(n);
    }
       return result;
   }
   
   public static void main(String[] args) {

}
}
