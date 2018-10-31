import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
public class Game {
	
	static StaticticGame stat;
	
	static String number;
	
	static int count;
	
	public static void main(String[] args) throws IOException {
	
		startGame();
		
		while(true) {
			String chance =  getInputPlayerNumber();
			if(chance.equals(number)) {
				stat.saveInFile();
				System.out.println(" You win, numbers was " + number);
				
				if(!inputRestart()) {
					break;
				} else {
					System.out.println("Start game");
					startGame();
					continue;
				}
			}
//			System.out.println("hfhsfkhs");
			step(chance);
			
		}
		
		

	}

	private static void step(String chance) {
		count++;
		
		if (chance == null) {
			System.out.println("Null");
			return;
		}
		int size = (chance.length() < 4)? chance.length(): 4;
		
		
		int countBulls = 0;
		int countCows = 0;
		for(int i = 0; i < size; i++) {
			String str = String.valueOf(chance.charAt(i));
			if (number.contains(str)) {
				if(number.charAt(i) == chance.charAt(i)) {
					countBulls++;
				} else {
					countCows++;
				}
				
			}
		}
		
		System.out.printf("Step : %d. Cows - %d, Bulls - %d\n", count, countCows, countBulls);
		
			
	}

	private static void startGame() {
		System.out.println("Start game");
		number = getRandomNumbers();
//		System.out.println(number);
		stat = new StaticticGame();
		count = 0;
		
		
	}

	private static String getInputPlayerNumber() throws IOException {
		
					
		BufferedReader reader1 = new BufferedReader(new InputStreamReader(System.in));
			String line = reader1.readLine();
			stat.addVariant(line);
						
		
		return line;
	}

	private static String getRandomNumbers() {
		String res = new Random().ints(0,10).distinct().limit(4).mapToObj(x -> x + "").collect(Collectors.joining(""));
		return res;
	}

	private static boolean inputRestart() throws IOException {
		System.out.println(" If you want restart game input  -  {Yes}");
		boolean res = false;
		
		 BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
					
						String answer = reader.readLine();
						if(answer.equals("Yes")) {
						res = true;
					 
			
		} 
		
		return res;
	}

}
