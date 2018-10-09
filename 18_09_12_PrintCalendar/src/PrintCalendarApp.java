import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class PrintCalendarApp {

	private static final int COL_WIDTH = 3;
	

	public static void main(String[] args) {
		if(args.length!=2) {
			System.out.println("<usage> first arg-year," + "second arg-month");
			return;
		}
		
		int year = Integer.parseInt(args[0]);
		int month = Integer.parseInt(args[1]);
		
		
		if (month < 1 && month > 12) {
			System.out.println("Wrong month");
			return;
		}
		printYearMonth(year, month);
		printDaysofWeek();
		DayOfWeek firstDayOfWeek = getFirstWeekDay(year, month);
		int firstCol = getFirstColumn(firstDayOfWeek);
		
		printOffSet(firstCol);
		printDates(firstCol, year, month);
		
	}

	private static void printDates(int col, int year, int month) {
//		LocalDate date = LocalDate.of(year, month, 1);
//		int daysOfMonth = date.lengthOfMonth();
//		for(int day = 1; day <= daysOfMonth; day++) {
//			System.out.println(day + );
//		}
		
		int numberDays = YearMonth.of(year, month).lengthOfMonth();
		for(int day = 1; day <= numberDays; day++) {
			System.out.printf("%3d" , day);
			if(col == 7) {
				System.out.println();
				col = 1;
			} else {
				col++;
			}
		}
	}

	private static void printOffSet(int firstCol) {
	for(int i = COL_WIDTH; i < firstCol*COL_WIDTH; i++) {
			System.out.print(" ");
		}
		
	}

	private static int getFirstColumn(DayOfWeek firstDayOfWeek) {
		
		return firstDayOfWeek.getValue();
	}

	private static DayOfWeek getFirstWeekDay(int year, int month) {
		LocalDate firstDay = LocalDate.of(year, month, 1);
		DayOfWeek dayOfWeek = firstDay.getDayOfWeek();
		return dayOfWeek;
	}

	private static void printDaysofWeek() {
//		System.out.println();
//		for(int i = 1; i < 8; i++) {
//		System.out.print(DayOfWeek.of(i).toString().substring(0, 3) + " ");
//		}
		System.out.print(" ");
		DayOfWeek weekDays[] = DayOfWeek.values();
		for(DayOfWeek day : weekDays) {
			
			System.out.print(day.getDisplayName(TextStyle.SHORT, Locale.getDefault()) + " ");
		}
		System.out.println();
		
	}

	private static void printYearMonth(int year, int month) {
		
		printTitle(year, month);
		
			
		
	}

	private static void printTitle(int year, int month) {
		System.out.printf("%3s %d %s", " ", year, Month.of(month).getDisplayName(TextStyle.FULL_STANDALONE, Locale.getDefault()) + "\n");
		
		
	}
//// время иерусалим, токио, оттава, москва, лондон
}
