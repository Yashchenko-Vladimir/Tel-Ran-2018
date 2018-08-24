package telran.util.queenchess.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.queenchess.QueenChess;

class ChessQueenTest {
	QueenChess ch = new QueenChess();
	
	@Test
	void test() {
		char [][] queen = ch.createdCharMassiv();
		ch.startForQueen(queen);
		assertEquals(92, ch.findSolition);
		 
	}

}
