package telran.tests.performance;

public class PerformanceTestFramework {

	public static void main(String[] args) {
		if(args.length == 0) {
			System.out.println("usage: <classname>");
			return;
		}
		
		PerformanceTest test = new PerformanceTest();
		try {
			Class<?> clazz = Class.forName(args[0]);
			test.runTest(clazz);
		} catch (ClassNotFoundException e) {
			System.out.println(args[0] + " class not found");
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		
	}

}
