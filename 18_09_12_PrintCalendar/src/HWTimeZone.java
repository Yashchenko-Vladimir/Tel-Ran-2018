import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HWTimeZone {

	public static void main(String[] args) {
		ZonedDateTime date = ZonedDateTime.now();
		
		Map<String, String> map = new HashMap<>();
		map.put("Tel-Aviv", "Israel");
		map.put("Moscow  ", "Europe/Moscow");
		map.put("London  ", "Europe/London");
		map.put("Tokyo   ", "Asia/Tokyo");
		map.put("Ottawa  ", "Canada/Central");
		map.put("Hawaii  ", "US/Hawaii");
		
		
		Set<Map.Entry<String, String>> set = map.entrySet();
		for(Map.Entry<String, String> elem : set) {
			date = ZonedDateTime.now(ZoneId.of(elem.getValue()));
			System.out.println("In " + elem.getKey() + DateTimeFormatter.ofPattern(" dd/MM/yyyy - HH:mm:ss  - X" ).format(date)
					+ " UTC, it is ZoneId: " + elem.getValue());
		}
		
//		date = ZonedDateTime.now();
//		
//		System.out.println(DateTimeFormatter.ofPattern("dd/MM/yyyy - hh:mm:ss  - X" ).format(date) + " UTC");

	}

}
