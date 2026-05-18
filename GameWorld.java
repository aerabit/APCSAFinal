/** 
    GameWorld - creates the game world and its locations, etc.
    
    @param regionName - the name of the world
    @param capitalCity - the name of the capital city
    @param smallTown - the town your character wakes up in.


*/
public class GameWorld {
    private String regionName;
    private int numTowns;
    private int population;
    private String capitalCity;
    private String smallTown;
    
    public GameWorld(String regionName, String capitalCity, String smallTown) {
        this.regionName = regionName;
        this.capitalCity = capitalCity;
        this.smallTown = smallTown;
        this.population = (int) (Math.random() * 100000) + 10000;
        this.numTowns = (int) (Math.random() * 32) + 4;
    }
    
    public GameWorld(String regionName) {
        this.regionName = regionName;
        this.capitalCity = "Challsentown";
        this.smallTown = "New Ledgings";
    }
    
    public GameWorld() {
        this("Ravenhelm", "Westcastle", "Chestington");
    }
    
    public String getRegionName() {
        return this.regionName;
    }
    
    public String getCapitalCity() {
        return this.capitalCity;
    }
    
    public String getSmallTown() {
        return this.smallTown;
    }
    
    public int getNumTowns() {
        return this.numTowns;
    }
    
    public int getPopulation() {
        return this.population;
    }
    
    public void setRegionName(String name) {
        this.regionName = name;
    }
    
    public void setCapitalCity(String name) {
        this.capitalCity = name;
    }
    
    public void setSmallTown(String name) {
        this.smallTown = name;
    }
    
    public void setNumTowns(int numTowns) {
        this.numTowns = numTowns;
    }
    
    public void setPopulation(int pop) {
        this.population = pop;
    }
    
    public String toString() {
        return "\nGAME WORLD INFO: \nRegion name: " + this.regionName + "\nCapital city: " + this.capitalCity + "\nWell-known small town: " + this.smallTown + "\nPopulation: " + this.population + "\nNumber of towns: " + this.numTowns;
    }
    
    
}