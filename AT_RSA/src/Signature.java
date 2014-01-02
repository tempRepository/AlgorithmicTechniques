

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.Scanner;

public class Signature {
    public static String loadText(String name) throws FileNotFoundException {
 /*       Scanner in = null;
        String temp = "";

        try {
            in = new Scanner(new FileReader(name));
            while (in.hasNextLine()) {
                temp += in.nextLine();
            }
            in.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return temp;*/
        
        return new Scanner(new File(name)).useDelimiter(
                "\\A").next();
    }



    public static int computeHash(String myText, BigInteger modulo) {
        int hash = 0;
        for (int i = 0; i < myText.length(); i++) {
            hash += myText.charAt(i)*Math.pow(257, (myText.length()-1)-i);
            hash= (new BigInteger(Integer.valueOf(hash).toString(), 10).mod(modulo)).intValue();
        }
        
        return hash;
    }
}
