package telran.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Function;

public interface InputOutput {
	String CANCEL = "cancel";
	String inputString(String prompt);
	default Integer inputInteger(String prompt) {
		String errorPrompt="no integer number";
		do {
			String line=inputString(prompt);
			if(line==null||line.equals(CANCEL))
				break;
			try {
				int res=Integer.parseInt(line);
				return res;
			}catch(NumberFormatException e) {
				outputLine(errorPrompt);
			}
		}while(true);
		return null;
	}
	default Integer inputInteger(String prompt,int min,int max) {
		if(max<min) {
			throw new IllegalArgumentException
			("max should be greater than min");
		}
		String errorPrompt = "number is out of range";
		prompt +=String.format("[%d,%d]", min,max);
		do {
			Integer number = inputInteger(prompt);
			if(number==null)
				break;
			if(number>=min&&number<=max)
				return number;
			outputLine(errorPrompt);
			
		}while(true);
		return null;
	}
	default Double inputDouble(String prompt) {
		String errorPrompt="no  number";
		do {
			String line=inputString(prompt);
			if(line.equals(CANCEL))
				break;
			try {
				double res=Double.parseDouble(line);
				return res;
			}catch(NumberFormatException e) {
				outputLine(errorPrompt);
			}
		}while(true);
		return null;
	}
	default Long inputLong(String prompt) {
		String errorPrompt="no long number";
		do {
			String line=inputString(prompt);
			if(line==null||line.equals(CANCEL))
				break;
			try {
				long res=Long.parseLong(line);
				return res;
			}catch(NumberFormatException e) {
				outputLine(errorPrompt);
			}
		}while(true);
		return null;
	}
	default String inputString(String prompt,List<String>options) {
		  if(options==null||options.isEmpty()){
	            throw new IllegalArgumentException
	            ("null or empty options");
	        }
	        prompt+=String.format("%s",
	        		options.toString());
	        do {
	            String line = inputString(prompt);
	            if(line==null||line.equals(CANCEL))
					break;
	            if(options.contains(line)){
	                return line;
	            }
	            outputLine(line+" is not included in options");
	        } while (true);
	        return null;
	}
	default LocalDate inputDate(String prompt,String format) {
		String errorPrompt = " not a date or wrong date format";
		prompt+=String.format(" [%s or %s]", format, CANCEL);
		DateTimeFormatter formatter =
				DateTimeFormatter.ofPattern(format);
		do {
			String line = inputString(prompt);
			if (line==null||line.equals(CANCEL))
				break;
			try {
												
				LocalDate res = LocalDate.parse(line,
						formatter);				
				return res;
			} catch (Exception e) {
				outputLine(line + errorPrompt);
			}
		} while (true);
		return null;
	}
	default <R>R inputObject(String prompt,
			Function<String,R>
	parser) {
		if(parser==null){
            throw new IllegalArgumentException
            ("parser can't be null");
        }
        String errorPrompt="Wrong input format";
        do {
            String line = inputString(prompt);
            if(line==null||line.equals(CANCEL))
				break;
            R res=parser.apply(line);
            if(res!=null)
            	return res;
            outputLine(errorPrompt);
            
        } while (true);
        return null;
	}
	void output(Object object);
	default void outputLine(Object object) {
		output(object.toString()+'\n');
	}
}
