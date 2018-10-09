
public class RuntimeMemoryTestApp {

	private static final int SIZE = 100000000;
	//  Xmx - max memory in JVN
	// Xms - step memory

	public static void main(String[] args) {
		Runtime runtime = Runtime.getRuntime();
		printMemoryData(runtime);
		byte[] []ar = new byte [4] [];
		for (int i = 0; i < 4; i++) {
			ar [i]  = new byte [SIZE];
			System.out.printf("allocated size=%d memory\n ", SIZE*(i+1));
			printMemoryData(runtime);
		}
		
		

	}

	private static void printMemoryData(Runtime runtime) {
		System.out.printf("free memory = %d\n"
				+ "total meory = %d\n"
				+ "max memory = %d\n", runtime.freeMemory(), runtime.totalMemory(), runtime.maxMemory());
	}

}
