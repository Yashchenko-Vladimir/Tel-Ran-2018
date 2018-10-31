package telran.io;

import java.io.*;
import java.nio.file.Files;
import java.sql.Time;

public class CopyingFilesAppl {

	public static void main(String[] args) throws IOException {
		//args[0] - path to src file
		//args[1] - path to dst file
		//args[2] - "overwrite" overwriting if a dst file exists
		// if dst file exists and no args[2] the application does nothing 
		// with printing out the message "File<name> can`t be overwritten"
		// as less as possible accesses to file
		
		if((args.length == 3 ) && (args[2].equals("overwrite"))) {
			String srcFile = args[0];
			String dstFile = args[1];
			writeDataFile(srcFile, dstFile );
		} else {
			System.out.println("File " + " \'" + args[1] + "\'" + " can`t be overwritten");
		}
	}

	private static void writeDataFile(String srcFile, String dstFile) throws IOException  {
		File fileSrc = new File(srcFile);
		
		if(!fileSrc.exists()) {
			
			System.out.println("File " + srcFile + " can`t find");
			return;
		}
		File fileDst = new File(dstFile);
		
		if(!fileSrc.exists()) {
			System.out.println("File " + dstFile + " can`t find");
			return;
		}
		
		FileInputStream inputStream = null;
		FileOutputStream outputStream = null;
		Runtime runtime = Runtime.getRuntime();
		
		long countByte = 0;
		int data = 0;
		long freememory =  runtime.freeMemory();
		
		byte [] buffer = new byte[ (int) (freememory * 0.0001)];// 127611672 (int) freememory
		
		
		try {
			inputStream = new FileInputStream(new File(srcFile));
			outputStream = new FileOutputStream(new File(dstFile));

			long startTime = System.currentTimeMillis();
			while ((data = inputStream.read(buffer) )> 0) {
				
				
				outputStream.write(buffer, 0, data);
				countByte+=data;
			}
			long finishTime = System.currentTimeMillis();
			long timeRun = finishTime - startTime;
			System.out.println("Time is " + (double) timeRun/1000);
			System.out.println("Byte " +countByte/timeRun  + " in millisecounds");
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			return;
		} catch (IOException e) {
			
			e.printStackTrace();
			return;
		}	finally {

			inputStream.close();
			outputStream.close();
		}
		
//		Files.copy(source, target, options)
		
		
	}

}
