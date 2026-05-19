import java.util.Scanner;
import java.util.ArrayList;

/** 
    GameCharacter - your in-game character, with stats, name, etc
    
    @param name - character name
    @param skill - character skill, doesn't really do anything except add backstory


*/


public class GameCharacter {
    private String name;
    private String skill;
    private final double health = 100;
    private int strength = 8;
    private int dexterity = 8;
    private int constitution = 8;
    private int intelligence = 8;
    private int wisdom = 8;
    private int charisma = 8;
    private double totalExp;
    private int level;
    private int numTurns;
    private ArrayList<Item> inventory = new ArrayList<Item>();
    private ArrayList<Item> equippedItems = new ArrayList<Item>();
    private ArrayList<Consumable> activeConsumables = new ArrayList<Consumable>();
    StatStore stats = new StatStore();
    
    public GameCharacter(String name, String skill) {
        this.name = name;
        this.skill = skill;
        this.totalExp = 0;
    }
    
    public GameCharacter() {
        this("Unknown", "Shyness");
    }
    
    public void assignStats(int totalPoints) {
        Scanner in = new Scanner(System.in);
        while (totalPoints > 0) {
            System.out.println("Your stats are all 8 by default. You have " + totalPoints + " points left to assign.");
            System.out.println("How many additional Strength points would you like to assign your character? ");
            int strength = in.nextInt();
            if (totalPoints - strength < 0) {
                System.out.println("You can't assign that many points!");
                strength = in.nextInt();
                this.strength += strength;
                totalPoints -= strength;
            }
            else if (totalPoints == 0) {
                break;
            }
            else {
                this.strength += strength;
                totalPoints -= strength;
            }
            System.out.println("You have " + totalPoints +  " points left to assign your character.");
            System.out.println("How many additional Dexterity points would you like to assign your character? ");
            int dexterity = in.nextInt();
            if (totalPoints - dexterity < 0) {
                System.out.println("You can't assign that many points!");
                dexterity = in.nextInt();
                this.dexterity += dexterity;
                totalPoints -= dexterity;
            }
            else if (totalPoints == 0) {
                break;
            }
            else {
                this.dexterity += dexterity;
                totalPoints -= dexterity;
            }
            System.out.println("You have " + totalPoints +  " points left to assign your character.");
            System.out.println("How many additional Constitution points would you like to assign your character? ");
            int constitution = in.nextInt();
            if (totalPoints - constitution < 0) {
                System.out.println("You can't assign that many points!");
                constitution = in.nextInt();
                this.constitution += constitution;
                totalPoints -= constitution;
            }
            else if (totalPoints == 0) {
                break;
            }
            else {
                this.constitution += constitution;
                totalPoints -= constitution;
            }
            System.out.println("You have " + totalPoints +  " points left to assign your character.");
            System.out.println("How many additional Intelligence points would you like to assign your character? ");
            int intelligence = in.nextInt();
            if (totalPoints - intelligence < 0) {
                System.out.println("You can't assign that many points!");
                intelligence = in.nextInt();
                this.intelligence += intelligence;
                totalPoints -= intelligence;
            }
            else if (totalPoints == 0) {
                break;
            }
            else {
                this.intelligence += intelligence;
                totalPoints -= intelligence;
            }
            System.out.println("You have " + totalPoints +  " points left to assign your character.");
            System.out.println("How many additional Wisdom points would you like to assign your character? ");
            int wisdom = in.nextInt();
            if (totalPoints - wisdom < 0) {
                System.out.println("You can't assign that many points!");
                wisdom = in.nextInt();
                this.wisdom += wisdom;
                totalPoints -= wisdom;
            }
            else if (totalPoints == 0) {
                break;
            }
            else {
                this.wisdom += wisdom;
                totalPoints -= wisdom;
            }
            System.out.println("You have " + totalPoints +  " points left to assign your character.");
            System.out.println("How many additional Charisma points would you like to assign your character? ");
            int charisma = in.nextInt();
            if (totalPoints - charisma < 0) {
                System.out.println("You can't assign that many points!");
                charisma = in.nextInt();
                this.charisma += charisma;
                totalPoints -= charisma;
            }
            else if (totalPoints == 0) {
                break;
            }
            else {
                this.charisma += charisma;
                totalPoints -= charisma;
            }
        }
        stats.update("strength", (Integer)this.strength);
        stats.update("dexterity", (Integer)this.dexterity);
        stats.update("constitution", (Integer)this.constitution);
        stats.update("intelligence", (Integer)this.intelligence);
        stats.update("wisdom", (Integer)this.wisdom);
        stats.update("charisma", (Integer)this.charisma);
        System.out.println("Stats saved.");
        System.out.println("Character information: \n" + this.toString());
    }
    
    public String toString() {
        return "Name: " + this.name + "\nSkill: " + this.skill + "\nStrength: " + this.strength + "\nDexterity: " + this.dexterity + "\nConstitution: " + this.constitution + "\nIntelligence: " + this.intelligence + "\nWisdom: " + this.wisdom + "\nCharisma: " + this.charisma;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getSkill() {
        return this.skill;
    }
    
    public int getStrength() {
        return this.strength;
    }
    
    public int getDexterity() {
        return this.dexterity;
    }
    
    public int getCharisma() {
        return this.charisma;
    }
    
    public int getConstitution() {
        return this.constitution;
    }
    
    public int getIntelligence() {
        return this.intelligence;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setSkill(String skill) {
        this.skill = skill;
    }
    
    public void setStrength(int strength) {
        this.strength = strength;
    }
    
    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }
    
    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }
    
    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }
    
    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }
    
    public void addToInventory(Item item) {
        inventory.add(item);
    }
    
    // Precondition: inventory size is not zero
    public void removeFromInventory(String item) {
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).equals(item)) {
                inventory.remove(item);
                break;
            }
        }
    }
    // Postcondition: given item no longer in inventory
    
    // Precondition: the inventory actually has something in it.
    public void getInventory() {
        for (int i = 0; i < inventory.size(); i++) {
            System.out.println("Inventory slot " + i + ": " + inventory.get(i));
        }
    }

    public void equip(Item item) {
        if (inventory.indexOf(item) != -1) {
            equippedItems.add(item);
            inventory.remove(inventory.indexOf(item));
            System.out.println(item + " has been equipped.");
        }
        else {
            System.out.println(item + " cannot be equipped, because it is not in your inventory."); 
        }
    }

    public void unequip(Item item) {
        if (equippedItems.indexOf(item) != -1) {
            inventory.add(item);
            equippedItems.remove(equippedItems.indexOf(item));
            System.out.println(item + " has been unequipped. It has been returned to your inventory.");
        }
    }

    public boolean skillCheck(String skillType, int threshold) {
        int advantage = stats.getAdvantage(skillType.toLowerCase());
        int roll = (int) (Math.random() * 20) + 1;
        System.out.println("***" + skillType.toUpperCase() + " CHECK***");
        System.out.println("You rolled: " + roll);
        System.out.println("With advantage: " + (roll + advantage));
        if ((roll + advantage) >= threshold) {
            System.out.println(skillType + " check succeeded!");
            return true;   
        }
        System.out.println(skillType + " check failed.");
        return false;
    }

    public void tick() {
        numTurns++;
        for (int i = 0; i < activeConsumables.size(); i++) {
            Consumable c = activeConsumables.get(i);
            boolean expired = c.expire();
            if (expired) {
                System.out.println("Your " + c + " has expired. Its effect has been removed.");
                stats.update(c.getStat(), stats.getStat(c.getStat()) - c.getBoost());
            }
        }
    }

}
