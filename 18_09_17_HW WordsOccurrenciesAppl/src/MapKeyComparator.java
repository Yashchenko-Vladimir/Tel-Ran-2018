import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;

public class MapKeyComparator implements Comparator<Map.Entry<String, Integer>>{

	@Override
	public int compare(Map.Entry<String, Integer> set1, Map.Entry<String, Integer> set2) {
		
		return set1.getKey().compareTo(set2.getKey());
	}
}
