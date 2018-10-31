package telran.view;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.function.Function;

import javax.swing.text.DateFormatter;

public interface InputOutput {
	
	String CANCEL = "cancel";

	String inputString(String prompt);
	
	default Integer inputInteger(String prompt) {
		String errorPrompt = "no integer number";
//		prompt += " or " + CANCEL + "\n->";
		do {
			String line = inputString(prompt);
			if(line == null || line.equals(CANCEL))
				break;
			try {
				int result = Integer.parseInt(line);
				return result;
			}catch (NumberFormatException e) {
				outputLine(errorPrompt);
			}
			
		} while (true );
		
		return null;
	}
	
	default Integer inputInteger(String prompt, int min, int max) {
		if(min>=max) {
			throw new IllegalArgumentException("max should be greater than min");
		}
		
		Integer result;
//		String errorPrompt = "Input  integer number not between min = "+ min + " max = " + max;
		String errorPrompt = "number is not of range";
		prompt+=String.format("[%d, %d]", min, max);
		
		do { 
			result = inputInteger(prompt);
			
			if(result == null || result >= min&& result <=max) {
				break;
			}
			System.out.println(errorPrompt);
			
		} while (true);
		
			return result;
		
	}
	default Double inputDouble(String prompt) {
		String errorPrompt = "no double number";
//		prompt += " or " + CANCEL + "\n->";
		do {
			String line = inputString(prompt);
			if(line == null || line.equals(CANCEL))
				break;
			try {
				double result = Double.parseDouble(line);
				return result;
			}catch (NumberFormatException e) {
				outputLine(errorPrompt);
			}
			
		} while (true );
		
		return null;
	}
	default Long inputLong(String prompt) {
		String errorPrompt = "no long number";
//		prompt += " or " + CANCEL + "\n->";
		do {
			String line = inputString(prompt);
			if(line == null || line.equals(CANCEL))
				break;
			try {
				long result = Long.parseLong(line);
				return result;
			}catch (NumberFormatException e) {
				outputLine(errorPrompt);
			}
			
		} while (true );
		return null;
	}
	default String inputString(String prompt, List<String> options) {
	    if (options == null || options.isEmpty()) {
	      throw new IllegalArgumentException("List size is 0 or list is null");
	    }
	    prompt += String.format( options.toString());
//	    prompt += " or " + CANCEL;
	    do {
	      String line = inputString(prompt);
	      if (line == null || line.toLowerCase().equals(CANCEL))
	        break;
	      if (options.contains(line)) {
	        return line;
	      }
	      outputLine(line + " is not included in options");
	    } while (true);
	    return null;
	  }
	
	default LocalDate inputDate(String prompt, String format){
		String errorPrompt = " not a date or wrong format";
	    prompt+=String.format(" [%s or %s]", format, CANCEL);
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
	    do {
	      String line = inputString(prompt);
	      if (line == null || line.equals(CANCEL))
	        break;
	      try {
	                        
	        LocalDate res = LocalDate.parse(line, formatter);        
	        return res;
	      } catch (Exception e) {
	        outputLine(line + errorPrompt);
	      }
	    } while (true);
	    return null;
	}
	default <R>R inputObject(String prompt,Function<String, R> parser){
		if(parser == null) {
			throw new IllegalArgumentException("parser can't be null");
		}
		String errorPrompt = "Wrong input format";
		
//		prompt+=String.format(" or %s", CANCEL);
		do {
			String line = inputString(prompt);
			if(line.equals(CANCEL)) {
				break;
			}
			R res = parser.apply(line);
			if(res != null) {
				return res;
			}
			outputLine(errorPrompt);
			
		}while (true);
		
		return null;
	}
	
	void output(Object object);
	
	default void  outputLine(Object object){
		output(object.toString() + "\n");
		
	}

}
