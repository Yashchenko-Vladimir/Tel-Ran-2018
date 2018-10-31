import java.io.*;
public class BufferReaderTestApplication {

	public static void main(String[] args) throws IOException {
		try(BufferedReader inputConsole = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter outputConsole = new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader inputFile = new BufferedReader(new FileReader("E:\\Java\\Javarush\\Test.txt"));
		BufferedWriter outputFile = new BufferedWriter(new FileWriter("E:\\Java\\Javarush\\TestResult.txt"));
		PrintStream outputFileStream = new PrintStream("E:\\Java\\Javarush\\TestResult.txt");)
		{

		
		
//		processInputOutput(inputConsole, outputConsole);
//		processInputOutput(inputFile, outputConsole);
//		processInputOutput(inputConsole, outputFile);
//		processInputOutput(inputFile, outputFile);
//		reverseStringInpuOutput(inputConsole, System.out);
		reverseStringInpuOutput(inputFile, outputFileStream);
		}
		
		//���� ������� 4 �����, ����� ����� �������� 4 �����, ����� �������� ����� ���������� ���
		// ���� ������ ����� � �� ������������ 
		// put number random 4 digitals
		//��� ����� �������� ���� � �����
		// ������� ����� �������
		// ���� ���� ��� absd - 0 �����  - 0 �����  ������ ����� ��������� �� �����
		// ������ � ���� � �����
		
		
 
	}
	public static void reverseStringInpuOutput(BufferedReader input, PrintStream output) throws IOException {
		while(true) {
			String line = input.readLine();
			if(line == null) {
				break;
			}
			output.println(getReverseString(line));
		}
			
	}
	

	private static void processInputOutput(BufferedReader inputBuffer, BufferedWriter outBuffer) throws IOException {
		BufferedReader input = inputBuffer;//new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out =outBuffer; // new BufferedWriter(new OutputStreamWriter(System.out));
		while(true) {
			String line = input.readLine();
			if(line == null) {
				break;
			}
			
			 out.write(getReverseString(line) + "\n");
			 out.flush();
		    
		}
		out.close();
	}

	private static String getReverseString(String line) {
		
		StringBuilder builder = new StringBuilder(line);
		String result = builder.reverse().toString();
				
		return result;
	}

}
