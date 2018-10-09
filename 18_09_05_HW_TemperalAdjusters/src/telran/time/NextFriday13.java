package telran.time;

import java.time.DayOfWeek;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalField;

public class NextFriday13 implements TemporalAdjuster {

	@Override
	public Temporal adjustInto(Temporal temporal) {
		if(temporal.get(ChronoField.DAY_OF_MONTH)< 13) {
			int per = 13 - temporal.get(ChronoField.DAY_OF_MONTH);
			 temporal = temporal.plus(per, ChronoUnit.DAYS);
			}
		
		 while(!(temporal.get(ChronoField.DAY_OF_WEEK) == 5)) {
			 temporal = temporal.with(TemporalAdjusters.lastDayOfMonth());
			 temporal = temporal.plus(13, ChronoUnit.DAYS);
		 }
		
		return temporal;
	}

}
