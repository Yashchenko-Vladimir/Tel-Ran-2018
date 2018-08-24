package telran.util.queenchess;

public class QueenChess {
	public int findSolition = 0;
	private final int SIZE_CHESSBOARD = 8;
	private final char QUEEN = 'Q';
	private  final char DONT_EMPTY = '*';
	
	
	public char[][] createdCharMassiv(){
		char [][] chessboard = new char[SIZE_CHESSBOARD][SIZE_CHESSBOARD];
		return chessboard;
	}


	private  char[][] fillCels(char[][] chessboard, int a, int b) {
		chessboard[a][b] = QUEEN;
		chessboard = fillDiagonal(chessboard, a,b);
		chessboard = fillVertical(chessboard, a,b);
		chessboard = fillHorizontal(chessboard, a,b);
		return chessboard;
	}
	

	public  void startForQueen(char[][] chessboard) {
		int a = 0;
		int countQuenn = 0;
		for(int i = 0; i < SIZE_CHESSBOARD; i++) {
			char [][] chessboard1 = copyCharMassiv(chessboard);
			findPlaceQueen(chessboard1, a, i, countQuenn);
		}
	}
	
	private  char[][] copyCharMassiv(char[][] chessboard) {
		char[][] chessboard1 = new char[SIZE_CHESSBOARD][SIZE_CHESSBOARD]; 
		for(int i = 0; i < SIZE_CHESSBOARD; i++ ) {
			for(int j = 0; j < SIZE_CHESSBOARD; j++) {
				chessboard1[i][j] = chessboard[i][j];
			}
		}
		return chessboard1;
	}


	private  void findPlaceQueen(char[][] chessboard, int a, int b, int countQuenn) {
		fillCels(chessboard, a, b);
		a++;
		countQuenn++;
		if(countQuenn == 8) {
			findSolition++;
			System.out.println("Solition number - " + findSolition );
			printChessboard(chessboard);
			System.out.println("");
		} else if (a < SIZE_CHESSBOARD) {
			for(int i = 0; i < SIZE_CHESSBOARD; i++) {
				if(chessboard[a][i] != '*' && chessboard[a][i] != 'Q') {
					char [][] chessboard1 = copyCharMassiv(chessboard);
					findPlaceQueen(chessboard1, a, i, countQuenn);
				}
			}
		}
		
	}


	private  void fillChessboard(char[][] chessboard) {
		for(int i = 0; i < SIZE_CHESSBOARD; i++ ) {
			for(int j = 0; j < SIZE_CHESSBOARD; j++) {
				chessboard[i][j] = '-';
			}
		}
	}
	
	private  void printChessboard(char[][] chessboard) {
		for(int i = 0; i < SIZE_CHESSBOARD; i++ ) {
			for(int j = 0; j < SIZE_CHESSBOARD; j++) {
				System.out.print(chessboard[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	private char[][] fillVertical(char[][] chessboard, int a, int b) {
		for(int i = 1; i < SIZE_CHESSBOARD; i++) {
			chessboard[a][(b+i)%SIZE_CHESSBOARD] = DONT_EMPTY;
		}
		return chessboard;
	}
	
	private char[][] fillDiagonal(char[][] chessboard, int a, int b) {
		for(int i = 1; i < SIZE_CHESSBOARD; i++) {
			if((a+i)<8 && (b+i)<8 ||(a+i)>=8 && (b+i)>=8) {
			chessboard[(a+i)%SIZE_CHESSBOARD][(b+i)%SIZE_CHESSBOARD] = DONT_EMPTY;
			}
			if(((a + i) <= a*2) && (b+i) < 8 ||((a + i)>(a*2)) && (b+i)>=8) {
				chessboard[(a+SIZE_CHESSBOARD-i)%SIZE_CHESSBOARD][(b+i)%SIZE_CHESSBOARD] = DONT_EMPTY;
			}
				 
		}
		return chessboard;
	}
	private char[][] fillHorizontal(char[][] chessboard, int a, int b) {
		for(int i = 1; i < SIZE_CHESSBOARD; i++) {
			chessboard[(a+i)%SIZE_CHESSBOARD][b] = DONT_EMPTY;
		}
		return chessboard;
	}
}