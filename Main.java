import java.util.Scanner;
/*
TO DO:
Add leveling/experience system **DONE (?)**
Extend story *WIP*
Add combat system **DONE**
Add method overrides for Item subclasses **DONE**
*/
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Creating new character... \nEnter character name: ");
        String name = in.nextLine();
        
        GameCharacter player = new GameCharacter(name);
        player.assignStats(27);

        GameWorld world = worldInitializer();
        Story s = new Story(player, world);
        
        Object[] objects = {player, world}; // holds objects in an object array
        
        s.start(); // the start() method will call all other appropriate methods from the Story class, depending on the choices you make.
        
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
