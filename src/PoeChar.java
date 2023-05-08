import java.security.SecureRandom;

public class PoeChar {

    int hitsPerSecond;
    int currentFrenzyCharges = 0;
    int maxFrenzyCharges;
    int minFrenzyCharges;
    int frenzyGainChance;
    boolean multistrike;

    public PoeChar(int hps, int mifc, int mafc, int fgc, boolean ms) {
        hitsPerSecond = hps;
        maxFrenzyCharges = mafc;
        minFrenzyCharges = mifc;
        frenzyGainChance = fgc;
        multistrike = ms;
    }

    public float simulate(int count) {
        SecureRandom rand = new SecureRandom();
        int randInt;
        currentFrenzyCharges = maxFrenzyCharges;
        int strikeCount = (multistrike ? 3 : 1);
        for (float i = 0; i < count; i++) {
            currentFrenzyCharges--;
//            System.out.println("Debug: current frenzy charges: " + currentFrenzyCharges);
            for (int j = 0; j < strikeCount; j++) {
                randInt = rand.nextInt(100) + 1;
//                System.out.println("Debug: randInt: " + randInt);
                if (randInt <= frenzyGainChance) {
                    currentFrenzyCharges = (currentFrenzyCharges == maxFrenzyCharges ? maxFrenzyCharges : currentFrenzyCharges + 1);
                }
                if (currentFrenzyCharges <= minFrenzyCharges) {
                    return i/count;
                }
            }
        }
        return 1;
    }

    public float simulate(int count, int simulationCount) {
        float result = 0;
        for (int i = 0; i<simulationCount; i++) {
            result += simulate(count);
        }
        return result/simulationCount;
    }

    public int getHitsPerSecond() {
        return hitsPerSecond;
    }

    public int getCurrentFrenzyCharges() {
        return currentFrenzyCharges;
    }

    public int getMaxFrenzyCharges() {
        return maxFrenzyCharges;
    }

    public int getMinFrenzyCharges() {
        return minFrenzyCharges;
    }

    public int getFrenzyGainChance() {
        return frenzyGainChance;
    }

    public boolean isMultistrike() {
        return multistrike;
    }

    @Override
    public String toString() {
        return "Multistrike is " + (this.isMultistrike() ? "on" : "off") + "\nMaximum Frenzy Charges: " + this.getMaxFrenzyCharges() + "\nChance to gain Frenzy Charge on attack: " + this.getFrenzyGainChance() + "\nFrenzy uptime is: " + this.simulate(100, 100);
    }
}
