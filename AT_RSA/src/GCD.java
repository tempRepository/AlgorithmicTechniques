public class GCD {

    /**
     * @param args
     */

    public static long simpleGCD(long a, long phi) {
        long t;

        while (phi != 0) {
            t = phi;
            phi = a % phi;
            a = t;
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

    public static long modularInversion(long a, long n) {
        long t = 0;
        long newt = 1;
        long r = n;
        long newr = a;
        long quotient;
        while (newr != 0) {
            quotient = r / newr;
            long tTemp=t;
            long newtTemp=newt;
            t = newtTemp;
            newt = tTemp - quotient * newtTemp;
          
            long rTemp=r;
            long newrTemp=newr;
            r = newrTemp;
            newr = rTemp - quotient * newrTemp;
        }
        if (r > 1) {
            return -1;
        }
        if (t < 0) {
            t = t + n;
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
