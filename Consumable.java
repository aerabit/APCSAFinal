public class Consumable extends Item {
    private int boost;
    private String stat;
    private int numTurns;

    public Consumable(String name, String description, String stat, int boost, int numTurns) {
        super(name, description);
        this.stat = stat;
        this.boost = boost;
        this.numTurns = numTurns;
    }

    public int getBoost() {
        return this.boost;
    }

    public int getNumTurns() {
        return this.numTurns;
    }

    public String getStat() {
        return this.stat.toLowerCase();
    }

    public boolean expire() {
        numTurns--;
        if (numTurns == 0) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return super.toString() + "\n  Affected stat: " + this.stat + "\n  Stat boost: " + this.boost + "\n  Turn duration: " + this.numTurns;
    }
}
