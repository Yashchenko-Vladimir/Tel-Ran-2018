import java.io.File;

public class PrintDirectoryAppl {

	public static void main(String[] args) {
		
		PrintFullDirectoryOfDisc(args);
//		printParentDirectory(args);
	}
	

	private static void PrintFullDirectoryOfDisc(String[] args) {
		if(args.length == 0) {
			System.out.println("usage: directoru path" );
			return;
		}
		File dir = new File(args[0]);
		if(!dir.exists()) {
			System.out.println(args[0] + " dosn't exist");
			return;
		}
		if(dir.isFile()) {
			System.out.println(args[0]);
			return;
		}
			printDirRecursione(args);
		} 


	private static void printDirRecursione(String[] args) {
		int level = 1;
		File dir = new File (args[0]);
		System.out.println(dir.getName());
		searchDirectory(dir, level);
	}

	private static void searchDirectory(File dir, int level) {
		
		for(File item: dir.listFiles()){
			if(item.isDirectory()) {
				for (int j = 0; j < level; j++) {
					System.out.print("-----");
				}
				System.out.println(item.getName());
				searchDirectory(item, level + 1);
			}
		}
	}


	private static void printDirectory(String string, int level) {
		for (int i = 0; i < level; i++) {
			System.out.print(" ");
		}
		System.out.println(string);
		
		
	}


	private static void printParentDirectory(String[] args) {
		if(args.length == 0) {
			System.out.println("usage: directoru path" );
			return;
		}
		File dir = new File(args[0]);
		if(!dir.exists()) {
			System.out.println(args[0] + " dosn't exist");
			return;
		}
		if(dir.isFile()) {
			System.out.println(args[0]);
			return;
		}
		printDir(dir);
		System.out.println("-----------------");
		System.out.println("++++++" + dir.getAbsolutePath());
	}

	private static void printDir(File dir) { // есть  list список файлов
		File content[] = dir.listFiles();
		for (int i = 0; i < content.length; i++) {
			System.out.println(content[i]);
		}
		
	}

}
