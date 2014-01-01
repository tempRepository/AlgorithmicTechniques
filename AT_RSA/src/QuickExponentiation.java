
public class QuickExponentiation {
   public static double pow(long dateToEncrypt, long e, long n)
   {
       String bits=Long.toBinaryString(e);
       int m=bits.length();
       dateToEncrypt= dateToEncrypt%n;
       double result=1;
       long x=dateToEncrypt;
       for (int i = m-1; i >-1; i--) {
        if (bits.charAt(i)=='1') {
            result=result*x;
            result=result % n;
        }
        x=x*x;
        x=x % n;
    }
       return result;
   }
   
   public static void main(String[] args) {
    System.out.println(Math.pow(20, 10)%7);
    System.out.println(QuickExponentiation.pow(20, 10, 7));
}
}
