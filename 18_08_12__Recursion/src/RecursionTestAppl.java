
public class RecursionTestAppl {
	
	// Rule №1 Any recursion function has to have at least one non-recursion exit
	// Rule №2 All recursion exit should get closer to non-recursion exit
	// Rule №3 Recursion formula 


	public static void main(String[] args) {
//		f(10000);
//		System.out.println(fact(5));
//		System.out.println(pow(2,6));// возведение в степень  без цикла
//		int [ ] ar = {1,2,3,10};
//		System.out.println(sum(ar));
		
		System.out.println(square(1)); //result 100
//		System.out.println(sumX(3, 4));
		

	}

private static int square(int a) {
		if(a == 1)
			return a;
		return (a+(a-1)) + square(a-1);
	}

//	private static int sum(int[] ar) {
////		int b=1;
//		int a = ar.length-b;
//		if (ar.length  < b)
//			return 0;
//		b++;
//		return ar[a] + sum(ar) ;
//	}
	private static int sum(int[] ar) {
	  return sum(ar,0);
	}
	

	private static int sum(int[] ar, int i) {
		if (i>= ar.length)
			return 0;
		
	return ar[i] + sum(ar, i +1);
}

	private static int pow(int a, int b) { // написать дома без цикла  и без умножения
//		if(b==0)
//			return 1;
//		return a*pow(a,b-1);
		if(b==0)
			return 1;
		int x = sumX(a,a); 
		b--;
		if(b > 1)
			x = powPow(x,a,b);
		
		return x;
	}
	
	private static int powPow(int i, int a, int b) {
		int x = sumX(i,a); 
		b--;
		if(b > 1)
			x = powPow(x,a,b);
		
		return x;
		
	}

	private static int sumX (int a, int b) {
		if (b == 0) 
			return 0;
		return a  + sumX(a, b-1);
	}

	
	private static long fact(int n) {
		if(n<2)
			return 1;
		return n*fact(n-1);
	}

	private static void f(int a) {
		if(a>=4) {
			a--;
		f(a);
		
		}
	}

}
