package pl.lodz.uni.math;

import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        ArrayList<Dwarf> myDwarfs = createDwarfes(10);
        for (Dwarf dwarf : myDwarfs) {
            System.out.println(dwarf);
        }
        
        while (myDwarfs.size()>2) {
            myDwarfs=split(myDwarfs);
            
        }

    }

    public static ArrayList<Dwarf> split(ArrayList<Dwarf> dwarfs) {
        ArrayList<Dwarf> tempDwarfs = new ArrayList<Dwarf>();
        for (int i = 0; i < dwarfs.size() / 2; i++) {
            if (dwarfs.get(i).areYouGood() && dwarfs.get(i + 1).areYouGood()) {
                tempDwarfs.add(dwarfs.get(i));
                tempDwarfs.add(dwarfs.get(i + 1));
            }
        }

        // odd case
        if (dwarfs.size() % 2 != 0) {
            if (dwarfs.get(dwarfs.size() - 1).areYouGood()
                    && dwarfs.get(0).areYouGood()) {
                tempDwarfs.add(dwarfs.get(0));
                tempDwarfs.add(dwarfs.get(dwarfs.size() - 1));
            }
        }
        return tempDwarfs;
    }

    public static ArrayList<Dwarf> createDwarfes(int howManyDwarfs) {
        ArrayList<Dwarf> dwarfs = new ArrayList<Dwarf>();

        for (int i = 0; i < (howManyDwarfs / 2) + 1; i++) {
            dwarfs.add(new Dwarf(true));
        }
        for (int i = 0; i < howManyDwarfs - ((howManyDwarfs / 2) + 1); i++) {
            dwarfs.add(new Dwarf(false));
        }
        Collections.shuffle(dwarfs);
        return dwarfs;
    }

}
