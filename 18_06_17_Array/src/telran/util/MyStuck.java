package telran.util;

public class MyStuck {
	private Array<Integer> ar = new Array<>();
	private Array<Integer> ar1 = new Array<>();

	public void pushNumber(int number) {

		ar.add(number);
		if (ar.size == 1)
			ar1.add(number);
		if (number >= ar1.get(ar1.size - 1))
			ar1.add(number);
	}

	public Integer popNumber() {
		if (ar.size == 0)
			return null;
		Integer a = ar.remove(ar.size - 1);
		if (ar1.get(ar1.size - 1) == a)
			ar1.remove(ar1.size - 1);
		return a;
	}

	public Integer getMax() {
		return ar1.get(ar1.size - 1);
	}
}

//	public static void main(String[] args) {
//		MyStuck s = new MyStuck();
//
//		s.pushNumber(2);
//		System.out.println("max1 = " + s.getMax());
//		s.pushNumber(1);
//		System.out.println("max1...2 = " + s.getMax());
//		s.pushNumber(5);
//		s.pushNumber(10);
//		s.pushNumber(3);
//		System.out.println("max2 = " + s.getMax());
//		s.pushNumber(55);
//		System.out.println("max3 = " + s.getMax());
//		s.pushNumber(34);
//		System.out.println("max4 = " + s.getMax());
//		s.popNumber();
//		s.popNumber();
//		System.out.println("max5 = " + s.getMax());
//		s.popNumber();
//		s.popNumber();
//		System.out.println("max6 = " + s.getMax());
//
//	}
//}
