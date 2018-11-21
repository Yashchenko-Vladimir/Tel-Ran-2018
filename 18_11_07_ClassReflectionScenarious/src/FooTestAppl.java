import java.lang.reflect.*;

public class FooTestAppl {
	 public static void main(String[] args) {
		    if (args.length != 2) {    System.out.println("usage: <function name> <text>");
		      return;
		    }
		    Foo foo = new Foo();
		    Class<Foo> clazz = Foo.class;   
		    System.out.println("Protocol: ");
		    //только public методы отбирает, т.е. протокол
		    //то же самое для полей    System.out.println(Arrays.deepToString(clazz.getMethods()));    System.out.println("Contract: ");
		    //только те методы, которые определены в Foo: и private и public  System.out.println(Arrays.deepToString(clazz.getDeclaredMethods()));
		    try 
		    {  // можно любой метод вызвать но только у текущего класса,
		      //если надо идти по иерархии вверх - то нужно отбирать все методы для родителя
		      Method method = clazz.getDeclaredMethod(args[0], String.class);
		      try {    method.setAccessible(true);    method.invoke(foo, args[1]);
		      } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {        e.printStackTrace();
		      }
		    } catch (NoSuchMethodException | SecurityException e) {      e.printStackTrace();
		    }
		  }
}
