import java.util.*;

public class RandomizedSet {
    Map<Integer, Integer> map = new HashMap<>();
    List<Integer> list = new ArrayList<>();
    Random random = new Random();

    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        } else {
            list.add(val);
            map.put(val, list.size() - 1);
            return true;
        }
    }

    public boolean remove(int val) {
        if (map.containsKey(val)) {
            if (list.size() == 1) {
                list.remove(0);
                map.remove(val);
            } else {
                int index = map.get(val);
                int last = list.get(list.size() - 1);
                list.set(index, last);
                map.put(last, index);
                map.remove(val);
                list.remove(list.size() - 1);
            }
            return true;
        } else {
            return false;
        }
    }

    public int getRandom() {
        int ran = random.nextInt(list.size());
        return list.get(ran);
    }

    public static void main(String[] args) {
        RandomizedSet test = new RandomizedSet();
        test.insert(1);
        test.remove(2);
        test.insert(2);
        test.getRandom();
        test.remove(1);
        test.insert(2);
        test.getRandom();
    }
}
