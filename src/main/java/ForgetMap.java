import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ForgetMap {
    private Map<String, String> carRegModelMap = new HashMap<>();
    private Map<String, Integer> carRegCountMap = new HashMap<>();
    int max;

    public ForgetMap(int limit) {
        max = limit;
    }

//    use synchronized to prevent thread interference
    public synchronized void add(String key, String value) {
        if(carRegModelMap.size() < max) {
            addItem(key, value);
            carRegModelMap.put(key, value);
        } else if (carRegModelMap.size() == max) {
            Optional<String> itemWithLowestUsage = getItemWithLowestUsage();
            removeItemFromMap((itemWithLowestUsage.get()));
            addItem(key, value);
        }
    }
    //   use synchronized to prevent thread interference
    public synchronized String find(String key) {
        if(carRegModelMap.containsKey(key)) {
            carRegCountMap.put(key, carRegCountMap.get(key) + 1);
            return carRegModelMap.get(key);
        } else {
            return null;
        }
    }

    public boolean mapContainsValue(String key) {
        if(carRegModelMap.containsKey(key)) {
            return true;
        }
        return false;
    }

    private void addItem(String key, String value) {
        carRegModelMap.put(key, value);
        carRegCountMap.put(key, 0);
    }

    //For the suitable tiebreaker I decided to just use the first
    //item that it finds and will return
    private Optional<String> getItemWithLowestUsage() {
        return carRegCountMap
                .entrySet()
                .stream()
                .sorted(Comparator.comparingDouble(Map.Entry::getValue))
                .findFirst()
                .map(Map.Entry::getKey);
    }

    private void removeItemFromMap(String key) {
        carRegModelMap.remove(key);
        carRegCountMap.remove(key);
    }

    public Map getCarRegCountMap() {
        return carRegCountMap;
    }

}
