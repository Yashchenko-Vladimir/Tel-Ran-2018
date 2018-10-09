package telran.time.tests;

import java.time.DayOfWeek;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class WorkingDays implements TemporalAdjuster {
	private int days;
	 DayOfWeek[] dayOfWeeks;

	public WorkingDays(int days, DayOfWeek[] dayOfWeeks) {
		this.days = days;
		this.dayOfWeeks = dayOfWeeks;
	}

	@Override
	public Temporal adjustInto(Temporal temporal) {
		
//		temporal = temporal.plus(days, ChronoUnit.DAYS);
		for (int i = 0; i < days; i++) {
			temporal = temporal.plus(1, ChronoUnit.DAYS);
				for(DayOfWeek b : dayOfWeeks) {
					if(b.getValue() == temporal.get(ChronoField.DAY_OF_WEEK))
					temporal = temporal.plus(1, ChronoUnit.DAYS);
		}
		
	}
				
//			temporal = temporal.plus(days, ChronoUnit.DAYS);
//				for(DayOfWeek b : dayOfWeeks) {
//					if(b.getValue() == temporal.get(ChronoField.DAY_OF_WEEK))
//					temporal = temporal.plus(1, ChronoUnit.DAYS);
//		}
		return temporal;
	}

}
