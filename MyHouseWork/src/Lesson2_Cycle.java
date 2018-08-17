import java.util.Arrays;

public class Lesson2_Cycle {

	public static void main(String[] args) {
//		Zadanie1();
//		Zadanie2();
//		Zadanie3();
		Zadanie4();
//		Zadanie5();
//		Zadanie6();
//		Zadanie7();
		

	}

	private static void Zadanie1() {
		int ar [] = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
		System.out.println(Arrays.toString(ar));
		for (int i = 0; i < ar.length; i++) {
			if (ar[i] == 1) 
				ar[i] = 0;
			 else 
				ar[i]= 1;
		}
		System.out.println(Arrays.toString(ar));
	}

	private static void Zadanie2() {
		int [] ar1 = new int[8];
		for(int i= 0, b=0; i<ar1.length;i++, b +=3) {
			ar1[i] = b;
		}
		for(int nu : ar1) {
			System.out.println(nu);
		}
	}
	
	private static void Zadanie3() {
		int [] ar = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
		for(int i= 0; i<ar.length;i++) {
			if (ar[i] <6)
			ar[i] *= 2;
		}
		for(int nu : ar) {
			System.out.println(nu);
		}
	}
	
	private static void Zadanie4() {
		int [][] ar = new int [5][5];
		for(int i= 0; i<ar.length;i++) {
			for(int j = 0; j< ar.length; j++) {
					if (i == j || i == 4-j)
						ar[i][j] = 1;
			System.out.print(ar[i][j] + " ");
			}
			System.out.println();
		}
		
			
		
	}
	
	private static void Zadanie5() {
		int [] ar = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1, -1};
		int max = ar[0];
		int min = ar[0];
		for(int i= 0; i<ar.length;i++) {
			if (ar[i] > max)
			 max = ar[i];
			if (ar[i] < min)
				min = ar[i];
		}
		System.out.println("min = " + min);
		System.out.println("max = " + max); 
	}
	
	private static void Zadanie6() {
		int [] ar = {1, 5, 3, 2,  4, 3, 18};
		System.out.println(checkBalance(ar));
	}
	
	private static boolean checkBalance(int[] ar) {
		
		int sum = 0;
		for (int i =0; i < ar.length; i++) {
			sum += ar[i];
		}
		int sumRight = sum;
		int sumLeft = 0;
		for (int i = 0; i < ar.length; i++) {
			sumLeft += ar[i];
			sumRight -= ar[i];
			if (sumLeft == sumRight)
				return true;
		}
		return false;
	}
	private static void Zadanie7() {
		int [] ar = {1, 2, 3, 4,  5, 6, 7};
		Zdvig(ar, -3);
		for (int nu: ar) {
		System.out.println(nu);
		}
	}
	
	private static int [] Zdvig(int[] ar, int n) {
		int save;
		if (n >= 0) {
			int right = n%ar.length;
			for (int i = 0; i < right; i ++) {
				save = ar[ar.length-1];
				for (int j = ar.length - 1; j > 0; j --) { 
					ar [j] = ar[j -1];
				}
				ar[0] = save;
			}
		} else {
			int  left = Math.abs(n%ar.length) ;
			for (int i = 0; i < left; i ++) {
				save = ar[0];
				for (int j = 1; j < ar.length; j ++) {
					ar [j-1] = ar[j ];
				}
				ar[ar.length - 1] = save;
			}
		}
	
		return ar;
	}

}
