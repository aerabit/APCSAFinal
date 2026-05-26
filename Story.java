import java.util.Scanner;

public class Story {
    private Scanner in = new Scanner(System.in);
    private GameCharacter player;
    private GameWorld world;

    public Story(GameCharacter player, GameWorld world) {
        this.player = player;
        this.world = world;
    }

    public boolean other(String input) { // checks for keywords to display inventory, equipped items, or to use an item
        if (input.toLowerCase().equals("inventory") || input.toLowerCase().equals("i")) {
            player.getInventory();
            return true; // boolean is returned for the story decision methods to detect whether an input was invalid, or was simply calling a keyword from this method.
        }
        else if (input.toLowerCase().equals("equipped") || input.toLowerCase().equals("e")) {
           player.getEquippedItems();
           return true;
        }
        else if (input.toLowerCase().equals("use") || input.toLowerCase().equals("u")) {
            System.out.println("Which item would you like to use / equip? (type \"cancel\" to cancel)");
            player.getInventory();
            String item = in.nextLine().toLowerCase();
            if (item.equals("cancel")) {
                return false;
            }
            boolean found = false;
            int index = 0;
            for (int i = 0; i < player.getInventoryArrayList().size(); i++) {
                if (item.equals(player.getInventoryArrayList().get(i).getName())) {
                    found = true;
                    index = i;
                    break;
                }
                else {
                    index = -1;
                }
            }
            if (found) {
                if (player.getInventoryArrayList().get(index) instanceof Consumable) { // if the item being used is a consumable, use the consume() method
                    player.consume((Consumable)player.getInventoryArrayList().get(index));
                }
                else {
                    player.equip(player.getInventoryArrayList().get(index));  // otherwise, use the equip() method
                }
                return true;
            }
            else {
                System.out.println("That item is not in your inventory.");
            }
        }
        else if (input.toLowerCase().equals("help")) {
            System.out.println("Type \"use\" or \"u\" to use an item in your inventory. \nType \"inventory\" or \"i\" to see your inventory. \nType \"equipped\" or \"e\"to see equipped items.");
            return true;
        }
        return false;
    }
    
    public void start() {
        System.out.println("Starting game: \n");
        System.out.println("You are " + player.getName() + ".");
        System.out.println("You have woken up in " + world.getSmallTown() + ", a small town in the lands of " + world.getRegionName() + ".");
        System.out.println();
        System.out.println("You don't quite remember how you got here, or why. But, you have a feeling that you're here for a reason.");
        System.out.println("First things first: figuring out where to go from here. You woke up in some sort of bar or tavern, though nobody was inside.");
        System.out.println("You notice a map on one of the tables. You walk over and pick it up, inspecting it. On the map, the route from " + world.getSmallTown() + " to the capital city, " + world.getCapitalCity() + " was highlighted. \nThere was also a pile of coins on the table, which you decided could be useful, putting them in your pocket.");
        System.out.println("Do you follow the map and go to " + world.getCapitalCity() + ", or would you like to stay in " + world.getSmallTown() + " for the moment while you get your bearings? (type the city name): ");
        String choice = in.nextLine();
        boolean other = other(choice);
        if (other) {
            System.out.println("You cannot do another USE / INVENTORY / EQUIPPED action during this turn.");
            System.out.println("Do you follow the map and go to " + world.getCapitalCity() + ", or would you like to stay in " + world.getSmallTown() + " for the moment while you get your bearings? (type the city name): ");
            choice = in.nextLine();
        }
        while (!choice.equals(world.getCapitalCity()) && !choice.equals(world.getSmallTown())) {
            System.out.println("Invalid selection!");
            choice = in.nextLine();
            other = other(choice);
        }
        if (choice.equals(world.getCapitalCity())) {
            goToCapital();
        }
        else {
            stayInTown();
        }
    }

    public void goToCapital() {
        System.out.println("\nYou decide to hit the road for " + world.getCapitalCity() + ". As you walk down the path, a few people stare at you, clearly unsure of where you came from, but none of them talk to you.");
        System.out.println("As you're about halfway to the capital, you see something hidden in a bush. You walk over to find its a metal chestplate. It looks quite heavy, but it could be useful if you ever got into a fight. Take it? (y/n)");
        String choice = in.nextLine().toLowerCase();
        boolean other = other(choice);
        if (other) {
            System.out.println("You cannot do another USE / INVENTORY / EQUIPPED action during this turn.");
            System.out.println("As you're about halfway to the capital, you see something hidden in a bush. You walk over to find its a metal chestplate. It looks quite heavy, but it could be useful if you ever got into a fight. Take it? (y/n)");
            choice = in.nextLine().toLowerCase();
        }
        while (!choice.equals("y") && !choice.equals("n")) {
            System.out.println("Invalid selection!");
            choice = in.nextLine().toLowerCase();
            other = other(choice);
        }
        player.tick();
        if (choice.equals("y")) {
            takeArmor(true);
        }
        else {
            takeArmor(false);
        }
        cityMarket();
    }

    public void stayInTown() {
        System.out.println("\nYou decide to stay in the town. You exit the tavern, and look around outside. A few passerbys look at you curiously, but none speak to you.");
        System.out.println("You figure that going to the market might be a good idea, since you feel pretty hungry. You walk aimlessly through the town until you find a small building with a sign saying \"Market\" on it.");
        System.out.println("You step inside the market, where an older man sits at the counter, reading a book. He looks up at you, closing his book.");
        System.out.println("\"Welcome in,\" he says. \"Haven't seen you around before. You new in town?\" he asks. You nod. \"Figures. Well, anyway, what can I get ya? We got fruits, veggies, soup, water, and anything else you could need.\"");
        System.out.println("Fruits and soup both sound really appetizing at the moment. Which would you like to buy? (type \"fruit\" or \"soup\") ");
        String choice = in.nextLine().toLowerCase();
        boolean other = other(choice);
        if (other) {
            System.out.println("You cannot do another USE / INVENTORY / EQUIPPED action during this turn.");
            System.out.println("Fruits and soup both sound really appetizing at the moment. Which would you like to buy? (type \"fruit\" or \"soup\") ");
            choice = in.nextLine().toLowerCase();
        }
        while (!choice.equals("fruit") && !choice.equals("soup")) {
            System.out.println("Invalid selection!");
            choice = in.nextLine().toLowerCase();
            other = other(choice);
        }
        player.tick();
        if (choice.equals("fruit")) {
            townMarket("fruit");
        }
        else {
           townMarket("soup");
        }
        meeting();
        
    }

    public void meeting() {
        System.out.println("As you sit and eat, a woman from the town sits down next to you. She looks you up and down, sizing you up. \"You new around these parts?\" she asks. You nod. She looks at you for a moment longer, before a small smile appears on her face. \"Well, welcome to " + world.getSmallTown() + ". We don't get new faces around here much. What's your name?\"");
        System.out.println("\"My name is " + player.getName() + ". To be honest, I don't know how I got here. I don't really remember anything.\" you explain.");
        System.out.println("She purses her lips, seemingly thinking about something. \"That ain't too unusual, " + player.getName() + ". This place can have... strange effects on the mind. Here, take this. Maybe it'll help jog your memory.\"");
        System.out.println("She hands you a small bottle of a dull purple liquid. Do you drink it? (y/n)");
        String choice = in.nextLine().toLowerCase();
        boolean other = other(choice);
        if (other) {
            System.out.println("You cannot do another USE / INVENTORY / EQUIPPED action during this turn.");
            System.out.println("She hands you a small bottle of a dull purple liquid. Do you drink it? (y/n)");
            choice = in.nextLine().toLowerCase();
        }
         while (!choice.equals("y") && !choice.equals("n")) {
            System.out.println("Invalid selection!");
            choice = in.nextLine().toLowerCase();
            other = other(choice);
        }
        player.tick();
        if (choice.equals("y")) {
            drinkPotion(true);
        }
        else {
            drinkPotion(false);
        }
    }

    public void drinkPotion(boolean choice) {
        Item potion1 = new Consumable("Unidentifiable Potion", "A purple serum of unclear origin. Keep out of reach of children.", "intelligence", 1,5);
        player.addToInventory(potion1);
        if (choice) {
            System.out.println("You put the bottle to your lips, downing the bottle in one gulp. The liquid tastes vaguely sour, but not unpleasant.");
            player.consume((Consumable)potion1);
            System.out.println("\"Hey, I do feel a bit better.\" you say. She smiles. \"I'm glad. Well, I must be off now. Good luck with your travels, " + player.getName() + ". \" she replies, standing up. You both wave goodbye, and you're left alone once again.");
        }
        else {
            System.out.println("\"Oh, um... I'm not really thirsty right now. I'll keep it for later, though.\" you reply, uncertain.");
            System.out.println("She shrugs. \"Suit yourself. Anyways, I must be off now. Good luck with your travels, " + player.getName() + ".\" she replies, standing up. You both wave goodbye, and you're left alone once again.");
        }
        townMotel();
    }

    public void townMotel() {
        System.out.println("Looking at the sky, hues of yellow and orange begin to appear, signifying the imminent arrival of nighttime. You decide to find a motel or some other place to stay at for the night. You see one right down the street.");
        System.out.println("You enter the motel, where a short man sits at the counter. He smiles as you enter. \"Well, hello there, chap? Looking for a room? They're all available, since nobody ever wants to come to this town.\" he says cheerily.");
        System.out.println("You nod. \"I don't have many credits, so I don't need anything fancy. Whatever your cheapest room is, please.\" you reply, placing the rest of your coins, 6 credits, on the table.");
        System.out.println("The man gives you a thumbs up, scribbling something down on a piece of paper. He hands you the paper, which has a room number on it. He sets the key down, as well. \"Here you go, friend. Enjoy your stay!\"");
        System.out.println("You give him a thumbs up, heading up to your room. For 6 credits, it wasn't half bad, with all the things a motel might need.");
        System.out.println("You lay down on the bed, exhausted. You fall asleep within minutes, the uncertainty of the day fading into a dreamless sleep.");
    }

    public void townMarket(String choice) {
        if (choice.equals("fruit")) {
            System.out.println("\"Good choice.\" the shopkeeper says. \"We got the freshest fruit in all " + world.getRegionName() +  ".\" he says. \"That'll be five credits.\"");
            System.out.println("You pull the coins from the tavern out of your pocket. You have more than enough, handing them to the shopkeeper. He nods approvingly, reaching under the counter and pulling out a large basket of various fruit, including apples, bananas, and grapes.");
            System.out.println("You thank the shopkeeper, and exit the market, holding your basket of fruit. You sit down on a bench, beginning to eat.");
        }
        else {
            System.out.println("\"Good choice.\" the shopkeeper says. \"My soup is famous across all" + world.getRegionName() + ".\"That'll be five credits.\"");
            System.out.println("You pull the coins from the tavern out of your pocket. You have more than enough, handing them to the shopkeeper. He nods approvingly, reaching under the counter and pulling out a steaming bowl of soup \"Fresh from the pot. Made it about ten minutes ago.\"");
            System.out.println("You thank the shopkeeper, and exit the market, holding your bowl of soup. You sit down on a bench, beginning to eat.");
        }
    }

    public void takeArmor(boolean choice) {
        Item chestplate = new Armor("Old Iron Chestplate", "A thick metal chestplate that looks like it's seen better days.", 32.0, 6);

        if (choice) {
            boolean check = player.skillCheck("strength", 9);
            if (check) {
                    System.out.println("You put on the chestplate. It's heavy, and slows you down, but will definitely be effective protection-wise.");
                    player.addToInventory(chestplate);
                    player.setDexterity(player.getDexterity() - 1);
                    System.out.println("Chestplate was added to your inventory. Your Dexterity was reduced by 1.");
                    player.getInventory();
            }
            else {
                System.out.println("You try to lift it up, but it's too heavy; it won't budge. You sigh and continue walking.");
            }
        }
        else {
            System.out.println("You decide to continue on without the armor.");
        }
    }

    public void cityMarket() {
        System.out.println("Soon enough, you've made it to " + world.getCapitalCity() + "! This place is bustling compared to where you came from, with horses and people filling the street. \nMarkets and stands sit on the side of the road, selling fruits, supplies, and a variety of other things.");
        System.out.println("You're starting to feel pretty hungry. Do you stop at the market and buy food, or keep moving (type \"market\" or \"continue\")?");
        String choice = in.nextLine().toLowerCase();
        boolean other = other(choice);
        if (other) {
            System.out.println("You cannot do another USE / INVENTORY / EQUIPPED action during this turn.");
            System.out.println("You're starting to feel pretty hungry. Do you stop at the market and buy food, or keep moving (type \"market\" or \"continue\")?");
            choice = in.nextLine().toLowerCase();
        }
        while (!choice.equals("market") && !choice.equals("continue")) {
            System.out.println("Invalid selection!");
            choice = in.nextLine().toLowerCase();
            other = other(choice);
        }
        player.tick();
        if (choice.equals("market")) {
            meat();
        }
        else {
            System.out.println("You decide to not stop at the market.");
        }
        cityMotel();
    }

    public void meat() {
        System.out.println("You decide to stop for food at the market. You walk up to one of the stands, where a burly man stands, wearing an apron stained a dull red with dried animal blood. \"Welcome. If you're looking for meat, I got it. Anything else is not my forte.\" he states.");
        System.out.println("You're torn between purchasing grilled chicken or steak. Which do you purchase? (type \"chicken\" or \"steak\"): ");
        String choice = in.nextLine().toLowerCase();
        boolean other = other(choice);
        if (other) {
            System.out.println("You cannot do another USE / INVENTORY / EQUIPPED action during this turn.");
            System.out.println("You're torn between purchasing grilled chicken or steak. Which do you purchase? (type \"chicken\" or \"steak\"): ");
            choice = in.nextLine().toLowerCase();
        }
        while (!choice.equals("chicken") && !choice.equals("steak")) {
            System.out.println("Invalid selection!");
            choice = in.nextLine().toLowerCase();
            other = other(choice);
        }
        player.tick();
        if (choice.equals("chicken")) {
            System.out.println("\"Good choice.\" the shopkeeper says. After you hand him 5 credits, he hands you a large, cooked chicken breast on a plate. \"Enjoy.\"");
            System.out.println("You thank him and walk away, chowing down on your chicken. You are extremely hungry, and somehow wolf the whole thing down in about 20 minutes.");
        }
        else {
            System.out.println("\"Good choice.\" the shopkeeper says. After you hand him 5 credits, he hands you a large slab of steak on a plate. \"Enjoy.\"");
            System.out.println("You thank him and walk away, chowing down on your steak. You are extremely hungry, and somehow wolf the whole thing down in about 20 minutes.");
        }
    }

    public void cityMotel() {
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
        String choice = in.nextLine().toLowerCase();
        boolean other = other(choice);
        if (other) {
            System.out.println("You cannot do another USE / INVENTORY / EQUIPPED action during this turn.");
            System.out.println("You want to help, but you are a bit conflicted. Do you step in and (F)ight, or do you mind your own business and (L)eave?");
            choice = in.nextLine().toLowerCase();
        }
        while (!choice.equals("l") && !choice.equals("f")) {
            System.out.println("Invalid selection!");
            choice = in.nextLine().toLowerCase();
            other = other(choice);
        }
        player.tick();
        if (choice.equals("f")) {
            fight();
        }
        else {
            System.out.println("You decide that you'd rather not get your butt kicked right now.");
            System.out.println("To be continued...");
        }
    }

    public void fight() {
        GameCharacter enemy = new GameCharacter("Grunt", 14, 8, 13, 6, 5, 5); // enemy is strong, but incredibly stupid
        System.out.println("You decide that you can't stand by and watch this happen. You walk over, and shove the larger man. He looks at you, rage in his eyes.");
        System.out.println("\"I would recommend you stay out of this, swine.\" he growled. He pulled his fist back, ready to hit you.  Do you try to (B)lock the attack, or do you try to (D)odge?");
        String choice = in.nextLine().toLowerCase();
        boolean other = other(choice);
        if (other) {
            System.out.println("You cannot do another USE / INVENTORY / EQUIPPED action during this turn.");
            System.out.println("Do you try to (b)lock the attack, or do you try to (d)odge?");
            choice = in.nextLine().toLowerCase();
        }
        while (!choice.equals("b") && !choice.equals("d")) {
            System.out.println("Invalid selection!");
            choice = in.nextLine().toLowerCase();
            other = other(choice);
        }
        player.tick();
        if (choice.equals("b")) {
            boolean block = player.skillCheck("constitution", 11);
            if (block) {
                hit();
            }
            else {
                System.out.println("You attempted to block the punch, but you missed! The fist collided with your face, sending a shockwave of pain through you. \nAlmost immediately, you could feel blood pouring down your face from your nose.");
                enemy.attack(player);
                System.out.println("You stumble back, falling to the ground in pain as the man resumes his attack on his original victim.");
                System.out.println("As you lay there, watching the violence unfold, a town guard upon a horse rides up, drawing his longsword as he forces the man off the other one.");
                System.out.println("\"Halt this nonsense!\" the guard shouts. The man struggles against the guard, trying to punch him. \"GET OFF OF ME!\" he yells.");
                System.out.println("Another two guards arrive on horseback. One of them goes to help the injured man, while the other approaches you.");
                System.out.println("\"You! What happened here?\" he asked, his tone harsh. You put your hands up momentarily. \"I tried to help that man who was getting assaulted. It, uh, didn't work.\"");
                System.out.println("He lifts you up, placing you on the back of his horse. \"You're injured. We'll take you to the city doctor.\" he stated. \"Word of advice: maybe don't intervene in fights you can't win.\" he chastised.");
                System.out.println("You sit there in silence as your attention returns to the scene around you. The instigator had finally stopped struggling as the two other guards threw him by his arms to the ground, tying his hands behind his back.");
                System.out.println("The other injured man is similarly put on the back of a different horse. His injuries were worse than yours; his entire face was caked in blood, and he looked on the verge of passing out.");
                System.out.println("You weren't too far from unconsciousness yourself, and you could feel yourself nodding off. You closed your eyes, the pain fading into nothingness.");
                System.out.println("To be continued...");
            }
        }
        else {
            boolean dodge = player.skillCheck("dexterity", 11);

            if (dodge) { // if the dodge succeeds
                System.out.println("You duck out of the way as the man's fist flies through the air into nothing. The man loses his balance as you do so, falling to the ground with an \"oof!\".");
                System.out.println("The man lies on the ground for a moment, stunned from the fall. Before he could get back up, two town guards, who had been alerted by nearby civilians, rushed over, restraining the man.");
                System.out.println("You watch, a bit of satisfaction rising in you as you watch the man be dragged away, kicking and screaming, by the authorities.");
                System.out.println("You walk over to the man on the ground, who looked pretty roughed up, but otherwise not terrible. He looked up at you with shock in his eyes.");
                System.out.println("\"Are you alright?\" you ask, reaching a hand out to help the man up. He hesitates for a second, before taking your hand, pulling himself up.");
                System.out.println("\"I- I'm alright...\" he mutters, rubbing his bruised face. \"Thanks, uh, for helping me.\"");
                System.out.println("You nod. \"Of course. What'd you do to get that guy on you, anyway?\"");
                System.out.println("\"O-Oh, uh, he thought I was someone who owed him money. Wouldn't listen when I told him he had the wrong guy.\"");
                System.out.println("\"I see.\" you reply. \"Well, I don't think you'll need to worry about him anymore.\"");
                System.out.println("The man nervously smiles. \"Yeah, I hope not. Well, um, thank you again. I have to get to work. I'm already late as it is.\"");
                System.out.println("You wave goodbye to him as he hurries off, still rubbing his injuries gingerly.");
                System.out.println("To be continued...");
            }

            else { // if the dodge fails
                System.out.println("You try to dodge the attack, but you're too slow! The man's fist collides with your face, sending a shockwave of pain through you. \nAlmost immediately, you could feel blood pouring down your face from your nose.");
                enemy.attack(player);
                System.out.println("You stumble back, falling to the ground in pain as the man resumes his attack on his original victim.");
                System.out.println("As you lay there, watching the violence unfold, a town guard upon a horse rides up, drawing his longsword as he forces the man off the other one.");
                System.out.println("\"Halt this nonsense!\" the guard shouts. The man struggles against the guard, trying to punch him. \"GET OFF OF ME!\" he yells.");
                System.out.println("Another two guards arrive on horseback. One of them goes to help the injured man, while the other approaches you.");
                System.out.println("\"You! What happened here?\" he asked, his tone harsh. You put your hands up momentarily. \"I tried to help that man who was getting assaulted. It, uh, didn't work.\"");
                System.out.println("He lifts you up, placing you on the back of his horse. \"You're injured. We'll take you to the city doctor.\" he stated. \"Word of advice: maybe don't intervene in fights you can't win.\" he chastised.");
                System.out.println("You sit there in silence as your attention returns to the scene around you. The instigator had finally stopped struggling as the two other guards threw him by his arms to the ground, tying his hands behind his back.");
                System.out.println("The other injured man is similarly put on the back of a different horse. His injuries were worse than yours; his entire face was caked in blood, and he looked on the verge of passing out.");
                System.out.println("You weren't too far from unconsciousness yourself, and you could feel yourself nodding off. You closed your eyes, the pain fading into nothingness.");
                System.out.println("To be continued...");
            }
        }
    }

    public void hit() {
        GameCharacter enemy = new GameCharacter("Grunt", 14, 8, 13, 6, 5, 5); // enemy is strong, but incredibly stupid
        System.out.println("You successfully blocked the attack. The man recoils, stunned. \"What the...\"");
        System.out.println("While he's caught off guard, you want to quickly attack. Do you (P)unch him in the face, or (K)ick his legs out from under him? (note: a punch requires a lower strength roll!)");
        String choice = in.nextLine().toLowerCase();
        boolean other = other(choice);
        if (other) {
            System.out.println("You cannot do another USE / INVENTORY / EQUIPPED action during this turn.");
            System.out.println("While he's caught off guard, you want to quickly attack. Do you (p)unch him in the face, or (k)ick his legs out from under him?");
            choice = in.nextLine().toLowerCase();
        }
        while (!choice.equals("p") && !choice.equals("k")) {
            System.out.println("Invalid selection!");
            choice = in.nextLine().toLowerCase();
            other = other(choice);
        }
        player.tick();
        if (choice.equals("p")) {
            boolean punch = player.skillCheck("Strength", 9);
            if (punch) { // if the punch was successful
                System.out.println("You swing your fist, which collided with his face with immense force. He falls to the ground, clutching his nose.");
                player.attack(enemy);
                System.out.println("\"You f- you broke my nose, you swine!\" he screamed, as blood began dripping from his nose.");
                player.addExp(150);
                System.out.println("Suddenly, town guards are swarming the area. You are tackled to the ground by one of them, while another restrains the man you were fighting.");
                System.out.println("\"Wait! Don't arrest them! They were helping, not instigating!\" someone called. It was the guy on the ground, the one who had been getting beaten originally.");
                System.out.println("You realized that he was talking about you, a bit of gratefulness rushing through you. The guard holding you released his grip. \"Okay, well, we're still gonna need your name.\" he said, looking down at you.");
                System.out.println("\"My name is " + player.getName() + ",\" you reply. A pause. \"Alright, " + player.getName() + ", you're free to go. Sorry for bothering you. And, good job being an upstanding citizen.\"");
                System.out.println("You sighed in relief, dusting yourself off. You felt a bit proud of yourself. You'd gotten in a fight, and actually won!");
                System.out.println("To be continued...");
            }
            else {
                System.out.println("You swing your fist, but you miss! Regaining his bearings, the man shoves you, hard.");
                enemy.attack(player);
                System.out.println("You fall to the ground, wincing in pain as you hit your head on the hard gravel.");
                System.out.println("Before he could hit you again, though, the person that he had been attacking had stood up behind him, punching him in the back of the head.");
                System.out.println("The man fell to the ground with a thump, groaning as he did so. \"You little...\" he growled, but was silenced as both you and the other man began beating him down, until he had passed out on the ground.");
                System.out.println("The two of you looked at eachother, panting. \"Good save.\" you say, breaking the silence.");
                System.out.println("\"No, I should be thanking you. He probably would've beaten me to death if you hadn't shown up.\" the man admitted. \"I'm Samuel.\"");
                System.out.println("\"Nice to meet you, Samuel.\" you say, shaking his hand. \"I'm " + player.getName() +".\"");
                System.out.println("To be continued...");
            }
        }
        else {
            player.attack(enemy);
            System.out.println("You swiftly kick the man right in an extremely sensitive area, causing him to howl in pain as he doubled over onto the ground.");
            System.out.println("It was clear he wasn't getting back up anytime soon.");
            player.addExp(150);
            System.out.println("You won the fight! A sense of pride filled you.");
            System.out.println("To be continued...");
        }
    }
}
