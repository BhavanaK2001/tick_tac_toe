package tictaktoe;

import java.util.Random;
import java.util.Scanner;

class Tictaktoe {
	static char[][] board;

	public Tictaktoe() {
		board = new char[3][3];
		initailboard();
	}

	void initailboard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				board[i][j] = ' ';
			}

		}
	}

	static void displayBoard() {
		System.out.println("--------------");
		for (int i = 0; i < board.length; i++) {
			System.out.print("| ");
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j] + " | ");
			}

			System.out.println();
			System.out.println("--------------");

		}
	}

	static void addMark(int row, int col, char ar) {
		if (row >= 0 && row <= 2 && col >= 0 && col <= 2) {
			board[row][col] = ar;
		} else {
			System.out.println("invalid try again");

		}
	}

	static boolean checkColWin() {
		for (int j = 0; j < board.length; j++) {
			if (board[0][j] != ' ' && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {

				return true;

			}
		}
		return false;
	}

	static boolean checkRowWin() {
		for (int i = 0; i < board.length; i++) {
			if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {

				return true;

			}
		}
		return false;
	}

	static boolean checkDiagonalWin() {
		if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
			return true;
		}
		if (board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
			return true;
		}
		return false;
	}

	static boolean checkDraw() {
		for (int i = 0; i <= 2; i++) {
			for (int j = 0; j <= 2; j++) {
				if (board[i][j] == ' ') {
					return false;
				}
			}

		}
		return true;
	}

}

abstract class Player {
	String name;
	char ar;

	abstract void MakeMove();

	boolean isValid(int row, int col) {
		if (row >= 0 && row <= 2 && col >= 0 && col <= 2) {
			if (Tictaktoe.board[row][col] == ' ') {
				return true;
			}
		}
		return false;

	}
}

class HumanPlayer extends Player {
	Scanner sc = new Scanner(System.in);

	public HumanPlayer(String name, char ar) {
		this.name = name;
		this.ar = ar;
	}

	int row;
	int col;

	void MakeMove() {
		do {
			System.out.println("Enter row and column");
			
			row = sc.nextInt();
			col = sc.nextInt();
		} while (!isValid(row, col));
		Tictaktoe.addMark(row, col, ar);
	}

}

class AIPlayer extends Player {

	public AIPlayer(String name, char ar) {
		this.name = name;
		this.ar = ar;
	}

	int row;
	int col;

	void MakeMove() {
		do {
			Random r = new Random();
			row = r.nextInt(3);
			col = r.nextInt(3);

		} while (!isValid(row, col));
		Tictaktoe.addMark(row, col, ar);
	}

}

public class LaunchGame {

	public static void main(String[] args) {
		Player p1;
		Player p2;

		Tictaktoe t = new Tictaktoe();
		Tictaktoe.displayBoard();
		Scanner sc = new Scanner(System.in);
		System.out.println("2 Player ?  Answer with Yes or No");
		String b = sc.next();
		if (b.equalsIgnoreCase("Yes")) {
			System.out.println("Enter  Player  1 name");
			sc.nextLine();
			String name1 = sc.nextLine();
			p1 = new HumanPlayer(name1, 'X');
			System.out.println("Enter Player 2  name");
			String name2 = sc.nextLine();
			// p1 = new HumanPlayer(name1, 'X');
			p2 = new HumanPlayer(name2, 'O');

		} else {
			System.out.println("Enter your name");

			sc.nextLine();
			String name = sc.nextLine();

			p1 = new HumanPlayer(name, 'X');
			p2 = new AIPlayer("Tai", 'O');
		}
		Player cp = p1;

		while (true) {
			System.out.println(cp.name + " its your trun");
			cp.MakeMove();
			Tictaktoe.displayBoard();
			if (Tictaktoe.checkColWin() || Tictaktoe.checkRowWin() || Tictaktoe.checkDiagonalWin()) {
				System.out.println(cp.name + " You Won The Game");
				break;
			} else if (Tictaktoe.checkDraw()) {
				System.out.println("game is Draw");
				break;
			} else {
				cp = (cp == p1) ? p2 : p1;
			}

		}

	}
}
