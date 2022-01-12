import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ForgetMap {
    private Map<String, String> associationMap = new HashMap<>();
    private Map<String, Integer> associationMapCount = new HashMap<>();
    int max;

    public ForgetMap(int limit) {
        max = limit;
    }

//    use synchronized to prevent thread interference
    public synchronized void add(String key, String value) {
        if(associationMap.size() < max) {
            addItem(key, value);
            associationMap.put(key, value);
        } else if (associationMap.size() == max) {
            Optional<String> itemWithLowestUsage = getItemWithLowestUsage();
            removeItemFromMap((itemWithLowestUsage.get()));
            addItem(key, value);
        }
    }
    //   use synchronized to prevent thread interference
    public synchronized String find(String key) {
        if(associationMap.containsKey(key)) {
            associationMapCount.put(key, associationMapCount.get(key) + 1);
            return associationMap.get(key);
        } else {
            return null;
        }
    }

    public boolean mapContainsValue(String key) {
        if(associationMap.containsKey(key)) {
            return true;
        }
        return false;
    }

    private void addItem(String key, String value) {
        associationMap.put(key, value);
        associationMapCount.put(key, 0);
    }

    //For the suitable tiebreaker I decided to just use the first
    //item that it finds and will return
    private Optional<String> getItemWithLowestUsage() {
        return associationMapCount
                .entrySet()
                .stream()
                .sorted(Comparator.comparingDouble(Map.Entry::getValue))
                .findFirst()
                .map(Map.Entry::getKey);
    }

    private void removeItemFromMap(String key) {
        associationMap.remove(key);
        associationMapCount.remove(key);
    }

    public Map getAssociationMapCount() {
        return associationMapCount;
    }

}
