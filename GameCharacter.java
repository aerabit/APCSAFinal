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
    private int strength = 1;
    private int speed = 1;
    private int charisma = 1;
    private int stealth = 1;
    private int intelligence = 1;
    private double totalExp;
    private int level;
    private ArrayList<Item> inventory = new ArrayList<Item>();
    
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
            System.out.println("Your stats are all 1 by default. You have " + totalPoints + " points left to assign.");
            System.out.println("How many Strength points would you like to assign your character? ");
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
            System.out.println("How many Speed points would you like to assign your character? ");
            int speed = in.nextInt();
            if (totalPoints - speed < 0) {
                System.out.println("You can't assign that many points!");
                speed = in.nextInt();
                this.speed += speed;
                totalPoints -= speed;
            }
            else if (totalPoints == 0) {
                break;
            }
            else {
                this.speed += speed;
                totalPoints -= speed;
            }
            System.out.println("You have " + totalPoints +  " points left to assign your character.");
            System.out.println("How many Charisma points would you like to assign your character? ");
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
            System.out.println("You have " + totalPoints +  " points left to assign your character.");
            System.out.println("How many Stealth points would you like to assign your character? ");
            int stealth = in.nextInt();
            if (totalPoints - stealth < 0) {
                System.out.println("You can't assign that many points!");
                stealth = in.nextInt();
                this.stealth += stealth;
                totalPoints -= stealth;
            }
            else if (totalPoints == 0) {
                break;
            }
            else {
                this.stealth += stealth;
                totalPoints -= stealth;
            }
            System.out.println("You have " + totalPoints +  " points left to assign your character.");
            System.out.println("How many Intelligence points would you like to assign your character? ");
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
        }
        System.out.println("Stats saved.");
        System.out.println("Character information: \n" + this.toString());
    }
    
    public String toString() {
        return "Name: " + this.name + "\nSkill: " + this.skill + "\nStrength: " + this.strength + "\nSpeed: " + this.speed + "\nCharisma: " + this.charisma + "\nStealth: " + this.stealth + "\nIntelligence: " + this.intelligence;
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
    
    public int getSpeed() {
        return this.speed;
    }
    
    public int getCharisma() {
        return this.charisma;
    }
    
    public int getStealth() {
        return this.stealth;
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
    
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }
    
    public void setStealth(int stealth) {
        this.stealth = stealth;
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

    public boolean skillCheck(String skillType, int threshold) {
        int advantage = 0;
        if (skillType.toLowerCase().equals("strength")) {
            if (this.strength > 6) {
                advantage = 2;
            }
            int roll = (int) (Math.random)
        }
        return false;
    }
    
}
