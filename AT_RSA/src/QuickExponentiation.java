
public class QuickExponentiation {
   public static double pow(int a, Integer b, int n)
   {
       String bits=Integer.toBinaryString(b);
       int m=bits.length();
       a= a%n;
       double result=1;
       int x=a;
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
