import java.math.BigInteger;

public class GCD {

    /**
     * @param args
     */

    public static BigInteger simpleGCD(BigInteger a, BigInteger phi) {
        BigInteger t;

        while (!phi.equals(new BigInteger("0"))) {
            t = new BigInteger(phi.toString());
            phi = a.mod(phi);
            a = new BigInteger(t.toString());
        }

        return a;
    }

    public static int odwr_mod(int a, int n) {
        int a0, n0, p0, p1, q, r, t;

        p0 = 0;
        p1 = 1;
        a0 = a;
        n0 = n;
        q = n0 / a0;
        r = n0 % a0;
        while (r > 0) {
            t = p0 - q * p1;
            if (t >= 0)
                t = t % n;
            else
                t = n - ((-t) % n);
            p0 = p1;
            p1 = t;
            n0 = a0;
            a0 = r;
            q = n0 / a0;
            r = n0 % a0;
        }
        return p1;
    }

    public static BigInteger modularInversion(BigInteger a, BigInteger n) {
        BigInteger t = new BigInteger("0");
        BigInteger newt = new BigInteger("1");
        BigInteger r = new BigInteger(n.toString());
        BigInteger newr = new BigInteger(a.toString());
        BigInteger quotient;
        while (!newr.equals(new BigInteger("0"))) {
            quotient = r.divide(newr);
            BigInteger tTemp=new BigInteger(t.toString());
            BigInteger newtTemp=new BigInteger(newt.toString());
            t = new BigInteger(newtTemp.toString());
            newt = tTemp.subtract(quotient.multiply(newtTemp));
          
            BigInteger rTemp=new BigInteger(r.toString());
            BigInteger newrTemp=new BigInteger(newr.toString());
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

    /*
     * public static int simpleGCD(int a, int b) { if (a < b) { int temp = a; a
     * = b; b = a; }
     * 
     * if (b == 0) { return a; } else { return simpleGCD(b, a % b); } }
     */

    // Extended gcd
    public static int[] extendedGCD(int a, int b) {
        int[] returnArray = new int[3];

        if (a < b) {
            int temp = a;
            a = b;
            b = a;
        }

        if (b == 0) {
            returnArray[0] = a;
            returnArray[1] = 1;
            returnArray[2] = 0;
            return returnArray;
        } else {
            int[] tempArray = extendedGCD(b, a % b);
            returnArray[0] = tempArray[0];
            returnArray[1] = tempArray[2];
            returnArray[2] = tempArray[1] - tempArray[2] * (a / b);
            return returnArray;

        }
    }

    public static void main(String[] args) {
        for (int number : extendedGCD(26, 15)) {
            System.out.println(number);
        }

    }

}
