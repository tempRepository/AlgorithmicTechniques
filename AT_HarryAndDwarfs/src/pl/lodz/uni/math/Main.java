package pl.lodz.uni.math;

import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        ArrayList<Dwarf> myDwarfs=createDwarfes(90001);

        @SuppressWarnings("unchecked")
        ArrayList<Dwarf> dwarfsTemp = (ArrayList<Dwarf>) myDwarfs.clone();
        /*
         * for (Dwarf dwarf : dwarfsTemp) { System.out.println(dwarf); }
         */
        
        while (dwarfsTemp.size()>2) {
            dwarfsTemp=split(dwarfsTemp);
            dwarfsTemp=getEverySecond(dwarfsTemp);
        }
        Dwarf goodDwarf=dwarfsTemp.get(0);
        //splitting to good and bad
        ArrayList<Dwarf> goodDwarfs=new ArrayList<Dwarf>();
        ArrayList<Dwarf> badDwarfs=new ArrayList<Dwarf>();
        for (Dwarf dwarf : myDwarfs) {
            if (goodDwarf.isHeGood(dwarf)) {
                goodDwarfs.add(dwarf);
            } else {
                badDwarfs.add(dwarf);
            }
        }
        System.out.println("The end");

    }

    public static ArrayList<Dwarf> getEverySecond(ArrayList<Dwarf> dwarfs)
    {
        ArrayList<Dwarf> tempDwarfs=new ArrayList<Dwarf>();
        for (int i = 0; i < dwarfs.size(); i+=2) {
            tempDwarfs.add(dwarfs.get(i));
        }
        return tempDwarfs;
    }
    
    public static ArrayList<Dwarf> split(ArrayList<Dwarf> dwarfs) {
        ArrayList<Dwarf> tempDwarfs = new ArrayList<Dwarf>();
        for (int i = 0; i < dwarfs.size()-1; i+=2) {
            boolean firstDwarf=dwarfs.get(i+1).isHeGood(dwarfs.get(i));
            boolean secondDwarf=dwarfs.get(i).isHeGood(dwarfs.get(i+1));
            
            if (firstDwarf && secondDwarf) {
                tempDwarfs.add(dwarfs.get(i));
                tempDwarfs.add(dwarfs.get(i + 1));
            }
        }

        // odd case
        if (dwarfs.size() % 2 != 0) {
            boolean firstDwarf=dwarfs.get(dwarfs.size()-1).isHeGood(dwarfs.get(0));
            boolean secondDwarf=dwarfs.get(0).isHeGood(dwarfs.get(dwarfs.size()-1));
            
            if (firstDwarf&&secondDwarf) {
                if (!tempDwarfs.contains(dwarfs.get(0))) {
                    tempDwarfs.add(dwarfs.get(0));  
                }
                if (!tempDwarfs.contains(dwarfs.get(dwarfs.size() - 1))) {
                    tempDwarfs.add(dwarfs.get(dwarfs.size() - 1));  
                }
                
            }
            else {
                tempDwarfs.remove(dwarfs.get(dwarfs.size()-1));
                tempDwarfs.remove(dwarfs.get(0));
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
