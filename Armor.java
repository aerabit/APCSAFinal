public class Armor extends Item {
    private double weight;
    private int protFactor;

    public Armor(String name, String description, double weight, int protFactor) {
        super(name, description);
        this.weight = weight;
        this.protFactor = protFactor;
    }

    public double getWeight() {
        return this.weight;
    }

    public int getProtFactor() {
        return this.protFactor;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setProtFactor(int protFactor) {
        this.protFactor = protFactor;
    }
}
