public class test2{
	public static void main (String[] args) {
	   String[] board = {"rnbqkbnr", "pppppppp", "00000000", "00000000", "00000000", "00000000", "00000000", "00000000", "PPPPPPPP", "RNBQKNBR"};
	   board[2].charAt(3) = "0";
	   System.out.println(board[2]);
    }
}