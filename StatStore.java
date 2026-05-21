import java.util.HashMap;
import java.util.Map;


/* This class was written by me, but with a tutorial: https://www.iditect.com/program-example/java--get-variable-by-name-from-a-string.html 
No AI was used.
*/
public class StatStore {
    private Map<String, Integer> stats = new HashMap<>();    

    public void update(String name, Integer value) {
        stats.put(name, value);
    }

    public Integer getStat(String name) {
        return stats.get(name);
    }

    public int getAdvantage(String name) { // higher stat = greater advantage during skill checks
        int val = getStat(name);
        if (val >= 10) {
            if (val >= 14) {
                return 3;
            }
            else if (val >= 12) {
                return 2;
            }
            else {
                return 1;
            }
        }
        return 0;
    }
}
