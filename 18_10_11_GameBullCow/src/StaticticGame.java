import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class StaticticGame {
	private List<String> listNumbers = new ArrayList<>();
	
	
	public  void addVariant(String line) {
		listNumbers.add(line);
		}
	
	public void saveInFile() throws IOException {
		LocalDateTime ld = LocalDateTime.now();
		DateTimeFormatter  dtf = DateTimeFormatter.ofPattern("d-MMMM-yyyy hh-MM", Locale.forLanguageTag("ru"));
		String data = ld.format(dtf);
//		String nameFile = new String("E:\\Java\\Tel-Ran\\Result game.txt");
		String nameFile = String.format("E:\\Java\\Tel-Ran\\Result game %s.txt", data);
		File file = new File(nameFile);
		file.createNewFile();
		BufferedWriter writer = new BufferedWriter(new FileWriter(nameFile));
		
		for(String str : listNumbers) {
			writer.write(str + System.lineSeparator());
		}
		writer.flush();
		writer.close();
		
	}
	
	
	public static void main(String[] args) {
		
		LocalDateTime ld = LocalDateTime.now();
	
		DateTimeFormatter  dtf = DateTimeFormatter.ofPattern("d/MMMM/yyyy HH-mm", Locale.forLanguageTag("ru"));
		String data = ld.format(dtf);
		System.out.println(data);
		
	}
}
