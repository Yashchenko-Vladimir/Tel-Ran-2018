package telran.util.queenchess;

public class Prob {
	
	static final char DONT_EMPTY = '*';
	static final int SIZE_CHESSBOARD = 8;
	static char [][] chessboard;
	static int number = 1;

	public static void main(String[] args) {
		
		
		chessboard = new char[SIZE_CHESSBOARD][SIZE_CHESSBOARD];
		fillChessboard();
		startForQueen();
//		
//		findPlaceForQueen();
		
		printChessboard();
//		System.out.println("----------------------------------------");
//		firstProb(6,3);
//		System.out.println("----------------------------------------");
//		firstProb(2,3);
//		System.out.println("----------------------------------------");
//		firstProb(3,5);
//		System.out.println("----------------------------------------");
//		firstProb(4,7);
//		System.out.println("----------------------------------------");
//		firstProb(4,1);
//		System.out.println("----------------------------------------");
//		firstProb(7,3);


	}


	private static void fillCels(int a, int b) {
		chessboard[a][b] = 'Q';
		fillDiagonal(a,b);
		fillVertical(a,b);
		fillHorizontal(a,b);
		printChessboard();
	}
	

	private static void startForQueen() {
		fillCels(0,2);
//		fillCels(1, 6);
//		fillCels(2, 6);
//		fillCels(3, 6);
//		chessboard [5][5] = 'Q' ;
				
		findPlaceQueen(0, 2);
	}
	
	private static void findPlaceQueen(int a, int b) {
		if((b + 2) < 8) {
			a +=1;
		} 
		for(int i = a; i < SIZE_CHESSBOARD; i++ ) {
			for(int j = b; j < SIZE_CHESSBOARD; j++) {
				if(chessboard[i][j] != '*' && chessboard[i][j] != 'Q') {
					chessboard[i][j] ='Q';
					number++;
					fillDiagonal(i,j);
					fillVertical(i,j);
					fillHorizontal(i,j);
					printChessboard();
					System.out.println("              ");
					if(number < 5) {
						findPlaceQueen(i, j);
					}
				}
					
			}
			b=0;
		}
		
	}


	private static void fillChessboard() {
		for(int i = 0; i < SIZE_CHESSBOARD; i++ ) {
			for(int j = 0; j < SIZE_CHESSBOARD; j++) {
				chessboard[i][j] = '-';
			}
		}
	}
	
	private static void printChessboard() {
		for(int i = 0; i < SIZE_CHESSBOARD; i++ ) {
			for(int j = 0; j < SIZE_CHESSBOARD; j++) {
				System.out.print(chessboard[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	private static  void fillVertical(int a, int b) {
		for(int i = 1; i < SIZE_CHESSBOARD; i++) {
			chessboard[a][(b+i)%SIZE_CHESSBOARD] = DONT_EMPTY;
		}
	}
	
	private static  void fillDiagonal(int a, int b) {
		for(int i = 1; i < SIZE_CHESSBOARD; i++) {
			if((a+i)<8 && (b+i)<8 ||(a+i)>=8 && (b+i)>=8) {
			chessboard[(a+i)%SIZE_CHESSBOARD][(b+i)%SIZE_CHESSBOARD] = DONT_EMPTY;
			}
			if(((a + i) < a*2) && (b+i) < 8 ||((a + i)>(a*2)) && (b+i)>=8) {
				chessboard[(a+SIZE_CHESSBOARD-i)%SIZE_CHESSBOARD][(b+i)%SIZE_CHESSBOARD] = DONT_EMPTY;
			}
				 
		}
	}
	private static  void fillHorizontal(int a, int b) {
		for(int i = 1; i < SIZE_CHESSBOARD; i++) {
			chessboard[(a+i)%SIZE_CHESSBOARD][b] = DONT_EMPTY;
			
		}
	}
	
	

}
