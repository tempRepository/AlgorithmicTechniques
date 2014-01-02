import java.math.BigInteger;


public class QuickExponentiation {
   public static BigInteger pow(BigInteger dateToEncrypt, BigInteger e, BigInteger n)
   {
  /*     BigInteger temp=new BigInteger(dateToEncrypt.toString(), 10);
       while (!e.equals(new BigInteger("1", 10))) {
        e=e.subtract(BigInteger.ONE);
        dateToEncrypt=dateToEncrypt.multiply(temp);
    }
       
       return dateToEncrypt.mod(n);*/
       
       
      // String bitsA=Long.toBinaryString(e.longValue()).toString();
/*       byte[] bytes=e.toByteArray();
       StringBuilder bitsTemp=new StringBuilder();
       for (byte b : bytes) {
        String s =Integer.toBinaryString(0xFF & b);
        bitsTemp.append(s);
    }*/
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
   // System.out.println(Math.pow(20, 10)%7);
   // System.out.println(QuickExponentiation.pow(20, 10, 7));
}
}
