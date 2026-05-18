public class Weapon extends Item {
    private String type;
    private int damage;

    public Weapon(String name, String description, String type, int damage) {
        super(name, description);
        this.type = type;
        this.damage = damage;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public int getDamage() {
        return this.damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
