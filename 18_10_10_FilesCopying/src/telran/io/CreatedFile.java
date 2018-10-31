package telran.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CreatedFile {
	
	public static void main(String[] args) throws IOException {
//		Runtime runtime = Runtime.getRuntime();
//		long freememory = runtime.freeMemory();
		
		FileOutputStream outputStream = new FileOutputStream(new File("E:\\Java\\Tel-Ran\\srcFile.txt"));
		String str = "Hello World!!! Hello World!!! Hello World!!! Hello World!!! Hello World!!! Hello World!!! Hello World!!! Hello World!!!";
		byte [] buffer = str.getBytes();
		for(long i=0; i< 40000000; i++ ) {
			outputStream.write(buffer);
		}
		
	}
}
