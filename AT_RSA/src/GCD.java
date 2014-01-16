import java.math.BigInteger;

public class GCD {

    /**
     * @param args
     */

    public static BigInteger simpleGCD2(BigInteger a, BigInteger b) {
        while (!a.equals(b)) {
            if (a.compareTo(b) > 0) {
                a = a.subtract(b);
            } else {
                b = b.subtract(a);
            }

        }
        return a;
    }

    public static BigInteger simpleGCD(BigInteger a, BigInteger phi) {
        BigInteger t;

        while (!phi.equals(new BigInteger("0"))) {
            t = new BigInteger(phi.toString());
            phi = a.mod(phi);
            a = new BigInteger(t.toString());
        }

        return a;
    }

    public static BigInteger modularInversion(BigInteger a, BigInteger n) {
        BigInteger t = new BigInteger("0");
        BigInteger newt = new BigInteger("1");
        BigInteger r = new BigInteger(n.toString());
        BigInteger newr = new BigInteger(a.toString());
        BigInteger quotient;
        while (!newr.equals(new BigInteger("0"))) {
            quotient = r.divide(newr);
            BigInteger tTemp = new BigInteger(t.toString());
            BigInteger newtTemp = new BigInteger(newt.toString());
            t = new BigInteger(newtTemp.toString());
            newt = tTemp.subtract(quotient.multiply(newtTemp));

            BigInteger rTemp = new BigInteger(r.toString());
            BigInteger newrTemp = new BigInteger(newr.toString());
            r = new BigInteger(newrTemp.toString());
            newr = rTemp.subtract(quotient.multiply(newrTemp));
        }
        if (r.compareTo(new BigInteger("1")) > 1) {
            return new BigInteger("-1");
        }
        if (t.compareTo(new BigInteger("0")) < 0) {
            t = t.add(n);
        }
        return t;
    }

    // Extended gcd
    /*
     * public static int[] extendedGCD(int a, int b) { int[] returnArray = new
     * int[3];
     * 
     * if (a < b) { int temp = a; a = b; b = a; }
     * 
     * if (b == 0) { returnArray[0] = a; returnArray[1] = 1; returnArray[2] = 0;
     * return returnArray; } else { int[] tempArray = extendedGCD(b, a % b);
     * returnArray[0] = tempArray[0]; returnArray[1] = tempArray[2];
     * returnArray[2] = tempArray[1] - tempArray[2] * (a / b); return
     * returnArray;
     * 
     * } }
     */

    public static void main(String[] args) {
        System.out.println(GCD.modularInversion(new BigInteger("3", 10),
                new BigInteger("7", 10)));

    }

}
