import java.util.Scanner;
/*
TO DO:
Add leveling/experience system **DONE (?)**
Extend story *WIP*
Add combat system **DONE**
Add method overrides for Item subclasses
*/
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Creating new character... \nEnter character name: ");
        String name = input.nextLine();
        
        GameCharacter player = new GameCharacter(name);
        player.assignStats(27);

        GameWorld world = worldInitializer();
        
        
        Object[] objects = {player, world}; // holds objects in an object array
        
        for (int i = 0; i < objects.length; i++) { // Iterate through object array 
            System.out.println("\nThis is being displayed from a for loop which is iterating through my object array containing my GameWorld and GameCharacter.:\n" + objects[i].toString());
        } 
        
        
        System.out.println("Starting game: \n");
        System.out.println("You are " + player.getName() + ".");
        System.out.println("You have woken up in " + world.getSmallTown() + ", a small town in the lands of " + world.getRegionName() + ".");
        System.out.println();
        System.out.println("You don't quite remember how you got here, or why. But, you have a feeling that you're here for a reason.");
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
        player.tick();

        if (travel.equals(world.getCapitalCity())) { // If the player chooses to go to the capital city
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
            player.tick();

            if (takeArmor.toLowerCase().equals("y")) {
                boolean armorCheck = player.skillCheck("Strength", 9);
                Item chestplate = new Armor("Old Iron Chestplate", "A thick metal chestplate that looks like it's seen better days.", 32.0, 6);
                
                if (!armorCheck) {
                    System.out.println("You try to lift it up, but it's too heavy; it won't budge. You sigh and continue walking.");
                }

                else {
                    System.out.println("You put on the chestplate. It's heavy, and slows you down, but will definitely be effective protection-wise.");
                    player.addToInventory(chestplate);
                    player.setDexterity(player.getDexterity() - 1);
                    System.out.println("Chestplate was added to your inventory. Your Dexterity was reduced by 1.");
                    player.getInventory();
                }

            }

            else if (takeArmor.toLowerCase().equals("n")) {
                System.out.println("You decide to continue on without the armor.");
            }

            System.out.println("Soon enough, you've made it to " + world.getCapitalCity() + "! This place is bustling compared to where you came from, with horses and people filling the street. \nMarkets and stands sit on the side of the road, selling fruits, supplies, and a variety of other things.");
            System.out.println("You're starting to feel pretty hungry. Do you stop at the market and buy food, or keep moving (type \"market\" or \"continue\")?");
            String stop = input.nextLine();

            if (!stop.toLowerCase().equals("market") && !stop.toLowerCase().equals("continue")) {
                System.out.println("Invalid choice!");
                while (!stop.toLowerCase().equals("market") && !stop.toLowerCase().equals("continue")) {
                    System.out.println("Market or continue?");
                    stop = input.nextLine();
                }
            }

            player.tick();

            if (stop.toLowerCase().equals("market")) {
                System.out.println("You decide to stop for food at the market. You walk up to one of the stands, where a burly man stands. \"Welcome. If you're looking for meat, I got it. Anything else is not my forte.\" he states.");
                System.out.println("You're torn between purchasing grilled chicken or steak. Which do you purchase? (type \"chicken\" or \"steak\"): ");
                String food = input.nextLine();

                if (!food.toLowerCase().equals("chicken") && !food.toLowerCase().equals("steak")) {

                    System.out.println("Invalid choice!");

                    while (!food.toLowerCase().equals("chicken") && !food.toLowerCase().equals("steak")) {
                        System.out.println("Chicken or steak?");
                        food = input.nextLine();
                    }
                    player.tick();

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

            System.out.println("\nDAY 1 COMPLETE!");
            System.out.println();

            System.out.println("You wake up with a start. A strange dream had come to you in the night, of fires and destruction. You shook your head. It was just a dream.");
            System.out.println("Sunlight was streaming in through the window. You peeked outside, looking at the hustle and bustle of the city waking up.");
            System.out.println("You look over at the old wooden door to your room, noticing a piece of paper that had been slipped underneath.");
            System.out.println("It reads: \"Thank you for staying at the Trusty Horse motel. Please visit the front desk to check out when convenient.\"");
            System.out.println("You sigh, tossing the paper to the side. You pack up the few things you'd taken out of your pockets before going to bed, and exit the room.");
            System.out.println("After checking out, you step outside, the chilly morning air blowing over you.");
            System.out.println("Almost immediately, though, you notice the sound of a commotion nearby. You look around and spot the source of the noise.");
            System.out.println("A large man was beating up a smaller one, who was trying and failing to shield himself as he laid helpless on the ground.");
            System.out.println("You want to help, but you are a bit conflicted. Do you step in and (F)ight, or do you mind your own business and (L)eave?");
            
            String fight = input.nextLine();
            player.tick();

            if (fight.toLowerCase().equals("f")) { // if the player chooses to fight
                GameCharacter enemy = new GameCharacter("Grunt", 14, 8, 13, 6, 5, 5); // enemy is strong, but incredibly stupid
                System.out.println("You decide that you can't stand by and watch this happen. You walk over, and shove the larger man. He looks at you, rage in his eyes.");
                System.out.println("\"I would recommend you stay out of this, swine.\" he growled. He pulled his fist back, ready to hit you. Do you try to (B)lock the attack, or do you try to (D)odge?");
                String move1 = input.nextLine();
                player.tick();

                if (move1.toLowerCase().equals("b")) { // if the player chooses to block

                    boolean block = player.skillCheck("constitution", 11);
                    if (block) {
                        System.out.println("You successfully blocked the attack. The man recoils, stunned. \"What the...\"");
                        System.out.println("While he's caught off guard, you want to quickly attack. Do you (P)unch him in the face, or (K)ick his legs out from under him? (note: a punch requires a lower strength roll!)");
                        String hit = input.nextLine();
                        player.tick();

                        if (hit.toLowerCase().equals("p")) {
                            
                            boolean punch = player.skillCheck("Strength", 9);

                            if (punch) { // if the punch was successful
                                System.out.println("You swing your fist, which collided with his face with immense force. He falls to the ground, clutching his nose.");
                                player.attack(enemy);
                                System.out.println("\"You f- you broke my nose, you swine!\" he screamed, as blood began dripping from his nose.");
                                System.out.println("");
                                player.addExp(50);
                            }

                            else { // if the punch failed
                                System.out.println("You swing your fist, but you miss! Regaining his bearings, the man shoves you, hard.");
                                System.out.println("");
                            }
                        }

                        else if (hit.toLowerCase().equals("k")) {
                            boolean kick = player.skillCheck("Strength", 12);

                            if (kick) { // if the kick was successful

                            }

                            else { //if the kick failed

                            }
                        }
                    }

                    else {
                        System.out.println("You attempted to block the punch, but you missed! The fist collided with your face, sending a shockwave of pain through you.");
                        enemy.attack(player);
                        System.out.println("You stumble back, falling to the ground in pain as the man resumes his attack on his original victim.");
                        System.out.println("As you lay there, watching the violence unfold, a town guard upon a horse rides up, drawing his longsword as he forces the man off the other one.");
                        System.out.println("\"Halt this nonsense!\" the guard shouts. The man struggles against the guard, trying to punch him. \"GET OFF OF ME!\" he yells.");
                        System.out.println("Another two guards arrive on horseback. One of them goes to help the injured man, while the other approaches you.");
                        System.out.println("\"You! What happened here?\" he asked, his tone harsh. You put your hands up momentarily. \"I tried to help that man who was getting assaulted. It, uh, didn't work.\"");
                        System.out.println("He lifts you up, placing you on the back of his horse. \"You're injured. We'll take you to the city doctor.\" he stated. \"Word of advice: maybe don't intervene in fights you can't win.\" he chastised.");
                        System.out.println("You sit there in silence as your attention returns to the scene around you. The instigator had finally stopped struggling as the two other guards threw him by his arms to the ground, tying his hands behind his back.");
                        System.out.println("The other injured man is similarly put on the back of a different horse. His injuries were worse than yours; blood dripped down his face, and he looked quite woozy.");
                        System.out.println("");
                    }
                }

                else if (move1.toLowerCase().equals("d")) { // if the player chooses to dodge   

                    boolean dodge = player.skillCheck("dexterity", 11);

                    if (dodge) { // if the dodge succeeds
                        System.out.println("You duck out of the way as the man's fist flies through the air into nothing. The man loses his balance as you do so, falling to the ground with an \"oof!\".");
                        System.out.println("The man lies on the ground for a moment, stunned from the fall. Before he could get back up, two town guards, who had been alerted by nearby civilians, rushed over, restraining the man.");
                        System.out.println();
                    }

                    else { // if the dodge fails

                    }
                }

            }

            else if (fight.toLowerCase().equals("l")) { // if the player chooses not to fight / chooses to leave

            }

        }
        else if (travel.equals(world.getSmallTown())) {
            System.out.println("\nYou decide to stay in the town. You exit the tavern, and look around outside. A few passerbys look at you curiously, but none speak to you.");
            System.out.println("You figure that going to the market might be a good idea, since you feel pretty hungry. You walk aimlessly through the town until you find a small building with a sign saying \"Market\" on it.");
            System.out.println("You step inside the market, where an older man sits at the counter, reading a book. He looks up at you, closing his book.");
            System.out.println("\"Welcome in,\" he says. \"Haven't seen you around before. You new in town?\" he asks. You nod. \"Figures. Well, anyway, what can I get ya? We got fruits, veggies, soup, water, and anything else you could need.\"");
            System.out.println("Fruits and soup both sound really appetizing at the moment. Which would you like to buy? (type \"fruit\" or \"soup\") ");
            String food = input.nextLine();
            player.tick();

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
            Item potion1 = new Consumable("Unidentifiable Potion", "A purple serum of unclear origin. Keep out of reach of children.", "Intelligence", 1,5);
            System.out.println("She hands you a small bottle of a dull purple liquid. Seeing no reason not to, you put the bottle to your lips and drink the whole thing. The liquid tastes vaguely sour.");
            System.out.println("You feel your mind clearing up a bit. While you stil can't remember why you're here, your mind feels a bit sharper, and you can think more clearly.");
            
            player.setIntelligence(player.getIntelligence() + 1);
           
            System.out.println("\nYour Intelligence stat has increased by 1!");
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
