
import java.lang.reflect.*;

import telran.sportsmen.ISportsman;
public class SpotsmenTetsAppl {

	private static final String PACKAGE = "telran.sportsmen.";

	public static void main(String[] args) {
		if (args.length != 3) {
			System.out.println("Usage: <classname> <sportsmean name> <describtion>");
			return;
		}
		ISportsman spotsman = null;
		try {
	        Class<?> clazz = Class.forName(PACKAGE + args[0]);
	        try {
	          // sportsman = (ISportsman) clazz.getConstructor(String.class,
	          // Integer.class).newInstance("Vasya", 1950);
	        	spotsman = (ISportsman) clazz.getConstructor(String.class, String.class).newInstance(args[1], args[2]);
	        	spotsman.action();
	        } catch (InstantiationException | IllegalAccessException 
	        		| IllegalArgumentException| InvocationTargetException | NoSuchMethodException | SecurityException e) {
	          System.out.println("no public constractor getting two strings");
	        }
	      } catch (ClassNotFoundException e) {
	        System.out.println(args[0] + " unknown class");        
	      }
//		switch (args[0]) {
//		case "FootBaller":
//			spotsman = new FootBaller();
//			break;
//		case "Runner":
//			spotsman = new Runner();
//			break;
//		default:
//			System.out.println(args[0] + "Unknown class");
//			return;
//		}
		
	}

}
