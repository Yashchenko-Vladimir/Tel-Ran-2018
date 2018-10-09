import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class JavaTimeTestAppl {

	public static void main(String[] args) {
		LocalDateTime ld = LocalDateTime.now();
//		System.out.println("Current local date: " + ld);
//		System.out.println("Local date after 100 days: " + ld.plusDays(100));
//		
//		ChronoUnit unit = ChronoUnit.MILLIS;
//		LocalDate ft = LocalDate.of(3000, 12, 31);
//		System.out.println("period between two dates is: " +unit.between(ld, ft) + " " + unit);
		
		DateTimeFormatter  dtf = DateTimeFormatter.ofPattern("d/MMMM/yyyy HH:mm a", Locale.forLanguageTag("ru"));
		
		System.out.println(ld.format(dtf));

	}

}
