import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Creating new character... \nEnter character name: ");
        String name = input.nextLine();
        System.out.println("Enter character skill: ");
        String skill = input.nextLine();
        
        GameCharacter player = new GameCharacter(name, skill);
        player.assignStats(32);

        GameWorld world = worldInitializer();
        
        
        Object[] objects = {player, world}; // holds objects in an object array
        
        
        for (int i = 0; i < objects.length; i++) { // Iterate through object array 
            System.out.println("\nThis is being displayed from a for loop which is iterating through my object array containing my GameWorld and GameCharacter.:\n" + objects[i].toString());
        } 
        
        
        System.out.println("Starting game: \n");
        System.out.println("You are " + player.getName() + ".");
        System.out.println("You have woken up in " + world.getSmallTown() + ", a small town in the lands of " + world.getRegionName() + ".");
        System.out.println();
        System.out.println("You don't quite remember how you got here, or why. All you know is that you're good at " + player.getSkill() + ".");
        System.out.println("First things first: figuring out where to go from here. You woke up in some sort of bar or tavern, though nobody was inside.");
        System.out.println("You notice a map on one of the tables. You walk over and pick it up, inspecting it. On the map, the route from " + world.getSmallTown() + " to the capital city, " + world.getCapitalCity() + " was highlighted. \nThere was also a pile of coins on the table, which you decided could be useful, putting them in your pocket.");
        System.out.println("Do you follow the map and go to " + world.getCapitalCity() + ", or would you like to stay in " + world.getSmallTown() + " for the moment while you get your bearings? (type the city name): ");
        String travel = input.nextLine();
        if(!travel.equals(world.getCapitalCity()) && !travel.equals(world.getSmallTown())) {
            System.out.println("Invalid option!");
            while (!travel.equals(world.getCapitalCity()) && !travel.equals(world.getSmallTown())) {
                System.out.println("Do you follow the map and go to the capital, or would you like to stay in " + world.getSmallTown() + " for the moment while you get your bearings? (type the city name with proper capitalization): ");
                travel = input.nextLine();
            }
        }
        if (travel.equals(world.getCapitalCity())) {
            System.out.println("\nYou decide to hit the road for " + world.getCapitalCity() + ". As you walk down the path, a few people stare at you, clearly unsure of where you came from, but none of them talk to you.");
            System.out.println("As you're about halfway to the capital, you see something hidden in a bush. You walk over to find its a metal chestplate. It looks quite heavy, but it could be useful if you ever got into a fight. Take it? (y/n)");
            String takeArmor = input.nextLine();
            if (!takeArmor.toLowerCase().equals("y") && !takeArmor.toLowerCase().equals("n")) {
                System.out.println("Invalid option!");
                while (!takeArmor.toLowerCase().equals("y") && !takeArmor.toLowerCase().equals("n")) {
                    System.out.println("Take it? (y/n)");
                    takeArmor = input.nextLine();
                }
            }
            if (takeArmor.toLowerCase().equals("y")) {
                Item chestplate = new Armor("Old Iron Chestplate", "A thick metal chestplate that looks like it's seen better days.", 32.0, 6)
                if (player.getStrength() < 5) {
                    System.out.println("You try to lift it up, but it's too heavy; it won't budge. You sigh and continue walking.");
                }
                else {
                    System.out.println("You put on the chestplate. It's heavy, and slows you down, but will definitely be effective protection-wise.");
                    player.addToInventory(chestplate);
                    player.setSpeed(player.getSpeed() - 1);
                    System.out.println("Chestplate was added to your inventory. Your speed was reduced by 1.");
                    System.out.println("Current inventory:");
                    player.getInventory();
                }
            }
            else if (takeArmor.toLowerCase().equals("n")) {
                System.out.println("You decide to continue on without the armor.");
            }
            System.out.println("Soon enough, you've made it to " + world.getCapitalCity() + "! This place is bustling compared to where you came from, with horses and people filling the street. \nMarkets and stands sit on the side of the road, selling fruits, supplies, and a variety of other things.");
            System.out.println("You're starting to feel pretty hungry. Do you stop at the market and buy food, or keep moving (type \"market\" or \"continue\"?");
            String stop = input.nextLine();
            if (!stop.toLowerCase().equals("market") && !stop.toLowerCase().equals("continue")) {
                System.out.println("Invalid choice!");
                while (!stop.toLowerCase().equals("market") && !stop.toLowerCase().equals("continue")) {
                    System.out.println("Market or continue?");
                    stop = input.nextLine();
                }
            }
            if (stop.toLowerCase().equals("market")) {
                System.out.println("You decide to stop for food at the market. You walk up to one of the stands, where a burly man stands. \"Welcome. If you're looking for meat, I got it. Anything else is not my forte.\" he states.");
                System.out.println("You're torn between purchasing grilled chicken or steak. Which do you purchase? (type \"chicken\" or \"steak\": ");
                String food = input.nextLine();
                if (!food.toLowerCase().equals("chicken") && !food.toLowerCase().equals("steak")) {
                    System.out.println("Invalid choice!");
                    while (!food.toLowerCase().equals("chicken") && !food.toLowerCase().equals("steak")) {
                        System.out.println("Chicken or steak?");
                        food = input.nextLine();
                    }
                }
                if (food.toLowerCase().equals("chicken")) {
                    System.out.println("\"Good choice.\" the shopkeeper says. After you hand him 5 credits, he hands you a large, cooked chicken breast on a plate. \"Enjoy.\"");
                    System.out.println("You thank him and walk away, chowing down on your chicken. You are extremely hungry, and somehow wolf the whole thing down in about 20 minutes.");
                }
                else if (stop.toLowerCase().equals("steak")) {
                    System.out.println("\"Good choice.\" the shopkeeper says. After you hand him 5 credits, he hands you a large slab of steak on a plate. \"Enjoy.\"");
                    System.out.println("You thank him and walk away, chowing down on your steak. You are extremely hungry, and somehow wolf the whole thing down in about 20 minutes.");
                }
            }
            else if (stop.toLowerCase().equals("market")) {
                System.out.println("You decide to not stop at the market.");
            }
            System.out.println("Soon enough, you notice the sun beginning to set over the horizon. You decide that it's a good time to go to a motel.");
            System.out.println("You find one very quickly, walking in to find a short man running the counter. He looks up at you. \"Welcome in.\" he says, sounding tired. \"Rooms are 10 credits a night.\"");
            System.out.println("You hand him the 10 credits, and he takes a piece of paper and a key from a drawer, handing them to you. \"That's your room. Have a good night.\"");
            System.out.println("You nod, walking up to your room. The room is nice enough, and you flop down on the bed, exhausted from the day.");
            System.out.println("You fall asleep within minutes, your exhaustion quickly overtaking you.");
            System.out.println("The end... or is it?");
        }
        else if (travel.equals(world.getSmallTown())) {
            System.out.println("\nYou decide to stay in the town. You exit the tavern, and look around outside. A few passerbys look at you curiously, but none speak to you.");
            System.out.println("You figure that going to the market might be a good idea, since you feel pretty hungry. You walk aimlessly through the town until you find a small building with a sign saying \"Market\" on it.");
            System.out.println("You step inside the market, where an older man sits at the counter, reading a book. He looks up at you, closing his book.");
            System.out.println("\"Welcome in,\" he says. \"Haven't seen you around before. You new in town?\" he asks. You nod. \"Figures. Well, anyway, what can I get ya? We got fruits, veggies, soup, water, and anything else you could need.\"");
            System.out.println("Fruits and soup both sound really appetizing at the moment. Which would you like to buy? (type \"fruit\" or \"soup\") ");
            String food = input.nextLine();
            if (!food.toLowerCase().equals("fruit") && !food.toLowerCase().equals("soup")) {
                System.out.println("Invalid input!");
                while (!food.toLowerCase().equals("fruit") && !food.toLowerCase().equals("soup")) {
                    System.out.println("Fruit or soup?");
                    food = input.nextLine();
                }
            }
            if (food.toLowerCase().equals("fruit")) {
                System.out.println("\"Good choice.\" the shopkeeper says. \"We got the freshest fruit in all " + world.getRegionName() +  ".\" he says. \"That'll be five credits.\"");
                System.out.println("You pull the coins from the tavern out of your pocket. You have more than enough, handing them to the shopkeeper. He nods approvingly, reaching under the counter and pulling out a large basket of various fruit, including apples, bananas, and grapes.");
                System.out.println("You thank the shopkeeper, and exit the market, holding your basket of fruit. You sit down on a bench, beginning to eat.");
            }
            else if (food.toLowerCase().equals("soup")) {
                System.out.println("\"Good choice.\" the shopkeeper says. \"My soup is famous across all" + world.getRegionName() + ".\"That'll be five credits.\"");
                System.out.println("You pull the coins from the tavern out of your pocket. You have more than enough, handing them to the shopkeeper. He nods approvingly, reaching under the counter and pulling out a steaming bowl of soup \"Fresh from the pot. Made it about ten minutes ago.\"");
                System.out.println("You thank the shopkeeper, and exit the market, holding your bowl of soup. You sit down on a bench, beginning to eat.");
            }
            System.out.println("As you sit and eat, a woman from the town sits down next to you. She looks you up and down, sizing you up. \"You new around these parts?\" she asks. You nod. She looks at you for a moment longer, before a small smile appears on her face. \"Well, welcome to " + world.getSmallTown() + ". We don't get new faces around here much. What's your name?\"");
            System.out.println("\"My name is " + player.getName() + ". To be honest, I don't know how I got here. I don't really remember anything.\" you explain.");
            System.out.println("She purses her lips, seemingly thinking about something. \"That ain't unusual. This place can have... strange effects on the mind. Here, take this. Maybe it'll help jog your memory.\"");
            System.out.println("She hands you a small bottle of a dull purple liquid. Seeing no reason not to, you put the bottle to your lips and drink the whole thing. The liquid tastes vaguely sour.");
            System.out.println("You feel your mind clearing up a bit. While you stil can't remember why you're here, your mind feels a bit sharper, and you can think more clearly.");
            player.setIntelligence(player.getIntelligence() + 1);
            System.out.println("\nYour intelligence stat has increased by 1!");
            System.out.println();
            System.out.println("\"Hey, I do feel a bit better.\" you say. She smiles. \"I'm glad. Well, I must be off now. Good luck with your travels.\" she replies, standing up. You both wave goodbye, and you're left alone once again.");
            System.out.println("Looking at the sky, hues of yellow and orange begin to appear, signifying the imminent arrival of nighttime. You decide to find a motel or some other place to stay at for the night. You see one right down the street.");
            System.out.println("You enter the motel, where a short man sits at the counter. He smiles as you enter. \"Well, hello there, chap? Looking for a room? They're all available, since nobody ever wants to come to this town.\" he says cheerily.");
            System.out.println("You nod. \"I don't have many credits, so I don't need anything fancy. Whatever your cheapest room is, please.\" you reply, placing the rest of your coins, 6 credits, on the table.");
            System.out.println("The man gives you a thumbs up, scribbling something down on a piece of paper. He hands you the paper, which has a room number on it. He sets the key down, as well. \"Here you go, friend. Enjoy your stay!\"");
            System.out.println("You give him a thumbs up, heading up to your room. For 6 credits, it wasn't half bad, with all the things a motel might need.");
            System.out.println("You lay down on the bed, exhausted. You fall asleep within minutes, the uncertainty of the day fading into a dreamless sleep.");
            System.out.println("The end... or is it?");
            
        }
        
    }

    public static GameWorld worldInitializer() {
        Scanner input = new Scanner(System.in);
        System.out.println("Would you like to make a (C)ustom world, or use the (P)reset one? ");
        String worldType = input.nextLine();
        if (worldType.toUpperCase().equals("C")) {
            System.out.println("What will your world be called? (ex: Ravenhelm) ");
            String worldName = input.nextLine();
            System.out.println("What is the name of the capital city of " + worldName + "?");
            String capitalCity = input.nextLine();
            System.out.println("Now give a name to one of the small towns of " + worldName + ".");
            String smallTown = input.nextLine();
            System.out.println("Selection saved. Initializing custom world...");
            GameWorld world = new GameWorld(worldName, capitalCity, smallTown);
            return world;
        }
        else if (worldType.toUpperCase().equals("P")){
            System.out.println("Initializing preset world...");
            GameWorld world = new GameWorld();
            return world;
        }
        else {
            System.out.println("Invalid selection.");
            return worldInitializer();
        }
    }
}
