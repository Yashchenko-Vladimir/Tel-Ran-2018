
@SuppressWarnings("serial")
public class X implements IX {
	int a = 10;
	String b = "Abc";
	@Override
	public void display() {
		System.out.printf("a=%d, b=%s\n", a, b);
		
	}

}
