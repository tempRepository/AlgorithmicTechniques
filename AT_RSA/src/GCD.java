
public class GCD {

        /**
         * @param args
         */
      public static int simpleGCD(int a, int b)
        {
            if (a<b) {
                int temp=a;
                a=b;
                b=a;
            }
            
            if (b==0) {
                return a;
            } else {
                return simpleGCD(b, a%b);
            }
        }
        
        //Extended gcd
        public static int[] extendedGCD(int a, int b)
        {
            int[] returnArray=new int[3];
            
            if (a<b) {
                int temp=a;
                a=b;
                b=a;
            }
            
            if (b==0) {
                returnArray[0]=a;
                returnArray[1]=1;
                returnArray[2]=0;
                return returnArray;
            } else {
                int[] tempArray=extendedGCD(b, a%b);
                returnArray[0]=tempArray[0];
                returnArray[1]=tempArray[2];
                returnArray[2]=tempArray[1]-tempArray[2]*(a/b);
                return returnArray;
                
            }
        }
        
        public static void main(String[] args) {
            for (int number : extendedGCD(18,12)) {
                System.out.println(number);
            }

        }

    
}
