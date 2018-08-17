import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Lesson3_Games {
	static Random random = new Random();
	static Scanner scaner = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		gameUgadaiNumber();
//		gameFindWord();
	}

	private static void gameFindWord() {
		String [] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot",
				"cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive",
				"pea", "penuat", "pear", "pepper", "pineapple", "pumpkin", "potato"};
		System.out.println("���� ��������!");
		String find = words[ranomNumber(words.length)];
		char [] charAr = find.toCharArray();
		char[] help = new char[15];
		int count= 0;
		for (int i = 0; i < help.length; i++) {
			help[i] = '#';
		}
		int lengt;
		
		while(true) {
			if (count > 5)
				help[0] = charAr[0];
			System.out.println("������� �����");
		String ww = scaner.next();
		if(ww.equals(find)) {
			System.out.println("�� ����� �����! ��� ��������!!!");
			System.out.println("���������� ����� - ��� " + find);
			break;
		} 
		char [] wwAr = ww.toCharArray();
		if (wwAr.length > charAr.length)
			lengt = charAr.length;
		else
			lengt = wwAr.length;
		
			for (int i = 0; i < lengt; i++) {
				if(charAr[i] == wwAr[i])
					help[i] = wwAr[i];
			}
			System.out.println("�������� ���� ����� �� �����, ���� ������������!!");
			System.out.print("��� ��� ���������  ");
			printArray(help);
			count++;
		}
	}

	private static void gameUgadaiNumber() {
		System.out.println("���� ��������");
		int a;
		while(true) {
		int b = ranomNumber(10);
			for (int i = 0; i < 3; i++) {
				a = scaner.nextInt();
				if (a == b) {
					System.out.println("���������� ����� = " + b);
					System.out.println("�� ��������!!!");
					break;
				}
				if (a > b ) 
					System.out.println("���������� ����� ������ ��� " + a );
				else 
					System.out.println("���������� ����� ������ ��� " + a ); 
				if (i == 2)
					System.out.println("�� ���������!! ���������� ����� - " + b);
				
			}
				System.out.println(" ��������� ���� ��� ���?  1 -- �� /  0 -- ���");
				if(0 == scaner.nextInt()) {
					System.out.println("���� �����������!!");
					break;
				}
				System.out.println("���� �������� ������!!!! ������!!!");
			}
	}
	
	public static int ranomNumber(int n) {
		return random.nextInt(n);
	}
	
	public static void printArray (char [] ar) {
		for (int i = 0; i < ar.length; i++) {
			System.out.print(ar[i]);
		}
		System.out.println();
		
	}

}
