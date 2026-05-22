public class Story {

    private GameCharacter player;
    private GameWorld world;

    public Story(GameCharacter player, GameWorld world) {
        this.player = player;
        this.world = world;
    }
    
    public void start() {
        System.out.println("Starting game: \n");
        System.out.println("You are " + player.getName() + ".");
        System.out.println("You have woken up in " + world.getSmallTown() + ", a small town in the lands of " + world.getRegionName() + ".");
        System.out.println();
        System.out.println("You don't quite remember how you got here, or why. All you know is that you're good at " + player.getSkill() + ".");
        System.out.println("First things first: figuring out where to go from here. You woke up in some sort of bar or tavern, though nobody was inside.");
        System.out.println("You notice a map on one of the tables. You walk over and pick it up, inspecting it. On the map, the route from " + world.getSmallTown() + " to the capital city, " + world.getCapitalCity() + " was highlighted. \nThere was also a pile of coins on the table, which you decided could be useful, putting them in your pocket.");
        System.out.println("Do you follow the map and go to " + world.getCapitalCity() + ", or would you like to stay in " + world.getSmallTown() + " for the moment while you get your bearings? (type the city name): ");
    }
    public void goToCapital() {
        System.out.println("\nYou decide to hit the road for " + world.getCapitalCity() + ". As you walk down the path, a few people stare at you, clearly unsure of where you came from, but none of them talk to you.");
        System.out.println("As you're about halfway to the capital, you see something hidden in a bush. You walk over to find its a metal chestplate. It looks quite heavy, but it could be useful if you ever got into a fight. Take it? (y/n)");
    }
    public void takeArmor(boolean choice) {
        if (choice) {
            
        }
    }
}
