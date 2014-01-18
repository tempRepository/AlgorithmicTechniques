package pl.lodz.uni.math;

import java.util.Random;

public class Dwarf {
    private boolean good;
    private static Random random = new Random();
    private Boolean fakeAnswer = null;

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
            if (fakeAnswer == null) {
                fakeAnswer = getRandomBoolean();
            }
            return fakeAnswer;
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
            if (fakeAnswer == null) {
                fakeAnswer = getRandomBoolean();
            }
            return "Bad and I represent myself as: " + fakeAnswer;
        }
    }

}
