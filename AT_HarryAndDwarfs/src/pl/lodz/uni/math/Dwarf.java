package pl.lodz.uni.math;

import java.util.Random;

public class Dwarf {
    private boolean good;
    private static Random random = new Random();

    public Dwarf(boolean good) {
        this.good = good;
    }

    private static boolean getRandomBoolean() {

        return random.nextBoolean();
    }

    public boolean areYouGood() {
        if (good) {
            return true;
        } else {
            return getRandomBoolean();
        }
    }

    public boolean isHeGood(Dwarf dwarf) {
        if (good) {
            if (dwarf.isGood()) {
                return true;
            } else {
                return false;
            }
        } else {
            return getRandomBoolean();
        }
    }

    public boolean isGood() {
        return good;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        if (good) {
            return "Good";
        } else {
            return "Bad";
        }
    }

}
