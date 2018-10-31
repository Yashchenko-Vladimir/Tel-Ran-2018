import java.io.*;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
public class LineOriemtedStreamsAppl {
	private static final int N_LINES = 10000000;
	private static final String WORDS = "Hello World!";

	public static void main(String[] args) throws FileNotFoundException {
		try (PrintStream printStream = new PrintStream("file_stream");
			 PrintWriter printWriter = new PrintWriter("print_writer");){
		
			runTest(printStream);
			runTest(printWriter);
		}
	}

	private static void runTest(PrintWriter printWriter) {
		Instant start =  Instant.now();
		for (int i = 0; i <N_LINES; i++) {
			printWriter.println(WORDS);
		}
		Instant finish = Instant.now();
		printResults("printWriter", start, finish);
		
	}

	private static void printResults(String string, Instant start, Instant finish) {
		System.out.printf("Test: %s rinning time %d\n", string, ChronoUnit.MILLIS.between(start, finish));
		
	}

	private static void runTest(PrintStream printStream) {
		Instant start =  Instant.now();
		for (int i = 0; i <N_LINES; i++) {
			printStream.println(WORDS);
		}
		Instant finish = Instant.now();
		printResults("printWriter", start, finish);
		
		
	}
}
