import java.util.Scanner;
import java.util.ArrayList;

/** 
    GameCharacter - your in-game character, with stats, name, etc
    
    @param name - character name
    @param skill - character skill, doesn't really do anything except add backstory


*/


public class GameCharacter {
    private String name;
    private int health = 16;
    private int strength = 8;
    private int dexterity = 8;
    private int constitution = 8;
    private int intelligence = 8;
    private int wisdom = 8;
    private int charisma = 8;
    private int totalExp;
    private int level;
    private ArrayList<Item> inventory = new ArrayList<Item>();
    private ArrayList<Item> equippedItems = new ArrayList<Item>();
    private ArrayList<Consumable> activeConsumables = new ArrayList<Consumable>();
    StatStore stats = new StatStore();
    
    public GameCharacter(String name) {
        this.name = name;
        this.totalExp = 0;
        this.level = 1;
    }
    
    public GameCharacter() {
        this("Unknown");
        this.level = 1;
    }

    public GameCharacter(String name, int strength, int dexterity, int constitution, int intelligence, int wisdom, int charisma) {
        this.name = name;
        this.strength = strength;
        this.dexterity = dexterity;
        this.constitution = constitution;
        this.intelligence = intelligence;
        this.wisdom = wisdom;
        this.charisma = charisma;
        this.level = 1;
    }
    
    public void assignStats(int totalPoints) {
        Scanner in = new Scanner(System.in);
        while (totalPoints > 0) {

            System.out.println("Your stats are all 8 by default. You have " + totalPoints + " points left to assign.");
            System.out.println("How many additional Strength points would you like to assign your character? ");
            int strength = in.nextInt();

            if (totalPoints - strength < 0 || this.strength + strength > 20) { // if you attempt to use more points than you have TotalPoints, or if you try to assign a stat above 20, force the user to retry
                System.out.println("You can't assign that many points! (no more than 20 ability points per stat allowed.");
                while (totalPoints - strength < 0 || this.strength + strength > 20) {
                    strength = in.nextInt();
                }
                this.strength += strength;
                totalPoints -= strength;
            }

            else {
                this.strength += strength;
                totalPoints -= strength;
            }

            if (totalPoints == 0) {
                break;
            }

            System.out.println("You have " + totalPoints +  " points left to assign your character.");
            System.out.println("How many additional Dexterity points would you like to assign your character? ");

            int dexterity = in.nextInt();

           if (totalPoints - dexterity < 0 || this.dexterity + dexterity > 20) {
                System.out.println("You can't assign that many points! (no more than 20 ability points per stat allowed.");
                while (totalPoints - dexterity < 0 || this.dexterity + dexterity > 20) {
                    dexterity = in.nextInt();
                }
                this.dexterity += dexterity;
                totalPoints -= dexterity;
            }
            else {
                this.dexterity += dexterity;
                totalPoints -= dexterity;
            }

            if (totalPoints == 0) {
                break;
            }


            System.out.println("You have " + totalPoints +  " points left to assign your character.");
            System.out.println("How many additional Constitution points would you like to assign your character? ");
            int constitution = in.nextInt();

            if (totalPoints - constitution < 0 || this.constitution + constitution > 20) {
                System.out.println("You can't assign that many points! (no more than 20 ability points per stat allowed.");
                while (totalPoints - constitution < 0 || this.constitution + constitution > 20) {
                    constitution = in.nextInt();
                }
                this.constitution += constitution;
                totalPoints -= constitution;
            }

            else {
                this.constitution += constitution;
                totalPoints -= constitution;
            }
            
            if (totalPoints == 0) {
                break;
            }


            System.out.println("You have " + totalPoints +  " points left to assign your character.");
            System.out.println("How many additional Intelligence points would you like to assign your character? ");
            int intelligence = in.nextInt();

            if (totalPoints - intelligence < 0 || this.intelligence + intelligence > 20) {
                System.out.println("You can't assign that many points! (no more than 20 ability points per stat allowed.");
                while (totalPoints - intelligence < 0 || this.intelligence + intelligence > 20) {
                    intelligence = in.nextInt();
                }
                this.intelligence += intelligence;
                totalPoints -= intelligence;
            }

            else {
                this.intelligence += intelligence;
                totalPoints -= intelligence;
            }

            if (totalPoints == 0) {
                break;
            }


            System.out.println("You have " + totalPoints +  " points left to assign your character.");
            System.out.println("How many additional Wisdom points would you like to assign your character? ");
            int wisdom = in.nextInt();

            if (totalPoints - wisdom < 0 || this.wisdom + wisdom > 20) {
                System.out.println("You can't assign that many points! (no more than 20 ability points per stat allowed.");
                while (totalPoints - wisdom < 0 || this.wisdom + wisdom > 20) {
                    wisdom = in.nextInt();
                }
                this.wisdom += wisdom;
                totalPoints -= wisdom;
            }

            else {
                this.wisdom += wisdom;
                totalPoints -= wisdom;
            }

            if (totalPoints == 0) {
                break;
            }


            System.out.println("You have " + totalPoints +  " points left to assign your character.");
            System.out.println("How many additional Charisma points would you like to assign your character? ");
            int charisma = in.nextInt();

            if (totalPoints - charisma < 0 || this.charisma + charisma > 20) {
                System.out.println("You can't assign that many points! (no more than 20 ability points per stat allowed.");
                while (totalPoints - charisma < 0 || this.charisma + charisma > 20) {
                    charisma = in.nextInt();
                }
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

            if (totalPoints == 0) {
                break;
            }


        }
        stats.update("strength", (Integer)this.strength);
        stats.update("dexterity", (Integer)this.dexterity);
        stats.update("constitution", (Integer)this.constitution);
        stats.update("intelligence", (Integer)this.intelligence);
        stats.update("wisdom", (Integer)this.wisdom);
        stats.update("charisma", (Integer)this.charisma);
        health = constitution * 2;
        System.out.println("Stats saved.");
        System.out.println("Character information: \n" + this.toString());
    }
    
    public String toString() {
        return "Name: " + this.name + "\nStrength: " + this.strength + "\nDexterity: " + this.dexterity + "\nConstitution: " + this.constitution + "\nIntelligence: " + this.intelligence + "\nWisdom: " + this.wisdom + "\nCharisma: " + this.charisma;
    }
    
    public String getName() {
        return this.name;
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

    public int getHealth() {
        return this.health;
    }
    
    // Precondition: inventory size is not zero
    public void removeFromInventory(Item item) {
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).equals(item)) {
                inventory.remove(item);
                break;
            }
        }
    }
    // Postcondition: given item no longer in inventory
    
    public void getInventory() {
        if (inventory.size() == 0) {
            System.out.println("There is nothing in your inventory.");
        }
        else {
            System.out.println("\nCurrent inventory: ");
            for (int i = 0; i < inventory.size(); i++) { // loops thru inventory, printing out the toString() method of each item.
                System.out.println("Inventory slot " + i + ": " + inventory.get(i));
            }
        System.out.println();
        }
        
    }

    public ArrayList<Item> getInventoryArrayList() {
        return inventory;
    }

    public void getEquippedItems() {
        if (equippedItems.size() == 0) {
            System.out.println("You have nothing equipped at the moment.");
            return;
        }
        System.out.println("Equipped items: ");
        for (Item i : equippedItems) {
            System.out.println(i);
        }
    }

    public void equip(Item item) {
        if (inventory.indexOf(item) != -1) { // checks if the item is actually in the player's inventory
            if (item.getClass().toString().equals("weapon")) { // checks if the item the player wants to equip is of the Weapon class
                for (Item i : equippedItems) { 
                    if (i.getClass().toString().toLowerCase().equals("weapon")) {  // checks if any of the other equipped items are of the Weapon class, and prevents the player from equipping a second one if so.   
                        System.out.println("You cannot have more than one weapon equipped!");
                        return;
                    }
                }
            } 
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
        int advantage = stats.getAdvantage(skillType.toLowerCase()); // characters can have advantages during stat checks based on how high a particular stat is in value.
        int roll = (int) (Math.random() * 20) + 1; // rolls a random number 1-20 inclusive
        System.out.println();
        System.out.println("***" + skillType.toUpperCase() + " CHECK***");
        System.out.println("You must roll a " + threshold + " to succeed. Your " + skillType + " stat is " + this.strength + ", which gives you an advantage of " + advantage + ".");
        System.out.println("You rolled: " + roll);
        System.out.println("With advantage: " + (roll + advantage));
        if ((roll + advantage) >= threshold) {
            System.out.println(skillType + " check succeeded!");
            System.out.println();
            return true;   
        }
        System.out.println(skillType + " check failed.");
        System.out.println();
        return false;
    }

    public void tick() { // runs after every decision, used to keep track of active consumables.
        for (int i = 0; i < activeConsumables.size(); i++) {
            Consumable c = activeConsumables.get(i);
            boolean expired = c.expire();
            if (expired) {
                System.out.println("Your " + c + " has expired. Its effect has been removed.");
                stats.update(c.getStat(), stats.getStat(c.getStat()) - c.getBoost());
                activeConsumables.remove(i);
                i--;
            }
        }
    }

    public void damage(int amount) {
        for (Item item : equippedItems) {
            if (item.getClass().toString().toLowerCase().equals("armor")) { // if the player has armor equipped
                Armor armor = (Armor)item;
                amount -= ((armor.getProtFactor() / 2) + 1); // damage is reduced by the armor's protection factor divided by two, plus one.
                System.out.println(this.name + "\'s equipped " + item.getName() + " blocked some damage!");
            }
        }
        this.health -= amount;
        System.out.println("\n" + this.name + " took " + amount + " damage!\n");
    }

    public void attack(GameCharacter other) {
        int damage = 0;
        damage = this.strength / 3;
        for (int i = 0; i < this.equippedItems.size(); i++) {
            if (this.equippedItems.get(i) instanceof Weapon) {
                Weapon weapon = (Weapon)equippedItems.get(i);
                damage += weapon.getDamage();
            }
        }
       other.damage(damage);
    }

    public void addExp(int amount) {
        this.totalExp += amount;
        System.out.println("\nGained " + amount + " experience points!\n");
        if (this.totalExp > (this.level * 100)) { // the amount of total experience points needed for each level is the level # times 100. ex. level 5 needs 500 xp
            level++;
            System.out.println("You leveled up to level " + level + "!");
            System.out.println("You have two additional ability points to assign!");
            this.assignStats(2);
        }
    }

    public void consume(Consumable c) {
        if (inventory.indexOf(c) != -1) { // if the consumable item is in the player inventory
            activeConsumables.add(c);
            stats.update(c.getStat(), stats.getStat(c.getStat()) + c.getBoost()); // updates the appropriate stat to be boosted by the consumables boost value
            System.out.println("Your " + c.getStat() + " was boosted by " + c.getBoost() + "! Your new " + c.getStat() + " value is " + stats.getStat(c.getStat()) + ". This effect lasts for " + c.getNumTurns() + " turns.");
        }
        else {
            System.out.println("That item is not in your inventory!");
        }
    }
}
