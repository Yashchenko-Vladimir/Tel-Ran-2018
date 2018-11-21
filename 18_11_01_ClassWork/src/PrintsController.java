import java.util.Scanner;

public class PrintsController {

	public static void main(String[] args) {
		String word = "hello";
		String [] arr = word.split("");
		Printer printer; 
		Scanner scanner = new Scanner(System.in);
		int count = 0;
		while(true){
			int index = count % arr.length;
			printer = new Printer(arr[index]);
			printer.start();
			String end = scanner.nextLine();
			
			if(end.equals("q")){
				System.out.println("finish");
				break;
			} 
			
				count++;
				printer.interrupt();
	
		}
		

	}

}
