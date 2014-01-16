

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.Scanner;

public class Signature {
    public static String loadText(String name) throws FileNotFoundException {
return new Scanner(new File(name)).useDelimiter(
                "\\A").next();
    }

public static BigInteger computeHash(String myText, BigInteger modulo) {
        BigInteger hash= new BigInteger("0", 10);
        for (int i = 0; i < myText.length(); i++) {
            
            hash =hash.add(new BigInteger(Long.valueOf((long)(myText.charAt(i)*Math.pow(257, (myText.length()-1)-i))).toString(), 10));
            hash= hash.mod(modulo);
        }
        
        return hash;
    }
}
