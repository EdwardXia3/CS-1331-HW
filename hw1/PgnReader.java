import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PgnReader {

    /**
     * Find the tagName tag pair in a PGN game and return its value.
     *
     * @see http://www.saremba.de/chessgml/standards/pgn/pgn-complete.htm
     *
     * @param tagName the name of the tag whose value you want
     * @param game a `String` containing the PGN text of a chess game
     * @return the value in the named tag pair
     */
    public static String tagValue(String tagName, String game) {
        int tagindex = game.indexOf(tagName);
        if (tagindex != -1) {
            String rest = game.substring(tagindex + tagName.length() + 1,
                game.length());
            int tagend = rest.indexOf("]");
            return rest.substring(1, tagend - 1);
        } else {
            return "NOT GIVEN";
        }
    }
    /**
     * Play out the moves in game and return a String with the game's
     * final position in Forsyth-Edwards Notation (FEN).
     *
     * @see http://www.saremba.de/chessgml/standards/pgn/pgn-complete.htm#c16.1
     *
     * @param game a `String` containing a PGN-formatted chess game or opening
     * @return the game's final position in FEN.
     */
    public static String removepiece(int row, int col, String[] board) {
        board[row] = board[row].substring(0, col) + '0'
            + board[row].substring(col + 1, 8);
        return board[row];
    }
    public static String[] determinepiece(String move, char turn, String[]
        board, boolean enpass) {
        char piece = ' ';
        String ensq = "-";
        int halfmove = Character.getNumericValue(board[10].charAt(0));
        halfmove = halfmove + 1;
        if (move.length() == 3 && move.charAt(0) == 'O') {
            if (turn == 'w') {
                board[0] = board[0].substring(0, 5) + "RK"
                    + board[0].substring(7, 8);
                board[0] = removepiece(0, 4, board);
                board[0] = removepiece(0, 7, board);
                board[8] = board[8].substring(0, board[8].indexOf('K'))
                    + board[8].substring(board[8].indexOf('K') + 1,
                    board[8].length());
                board[8] = board[8].substring(0, board[8].indexOf('Q'))
                    + board[8].substring(board[8].indexOf('Q') + 1,
                    board[8].length());
            } else {
                board[7] = board[7].substring(0, 5) + "rk"
                    + board[7].substring(7, 8);
                board[8] = board[8].substring(0, board[8].indexOf('k'))
                    + board[8].substring(board[8].indexOf('k') + 1,
                    board[8].length());
                board[8] = board[8].substring(0, board[8].indexOf('q'))
                    + board[8].substring(board[8].indexOf('q') + 1,
                    board[8].length());
                board[7] = removepiece(7, 4, board);
                board[7] = removepiece(7, 7, board);
            }
        } else if (move.length() == 5 && move.charAt(0) == 'O') {
            if (turn == 'w') {
                board[0] = removepiece(0, 0, board);
                board[0] = removepiece(0, 4, board);
                board[0] = board[0].substring(0, 2) + "KR"
                    + board[0].substring(4, 8);
                board[8] = board[8].substring(0, board[8].indexOf('K'))
                    + board[8].substring(board[8].indexOf('K') + 1,
                    board[8].length());
                board[8] = board[8].substring(0, board[8].indexOf('Q'))
                    + board[8].substring(board[8].indexOf('Q') + 1,
                    board[8].length());
            } else {
                board[7] = removepiece(7, 4, board);
                board[7] = removepiece(7, 0, board);
                board[7] = board[7].substring(0, 2) + "kr"
                    + board[7].substring(3, 7);
                board[8] = board[8].substring(0, board[8].indexOf('k'))
                    + board[8].substring(board[8].indexOf('q') + 1,
                    board[8].length());
                board[8] = board[8].substring(0, board[8].indexOf('k'))
                    + board[8].substring(board[8].indexOf('q') + 1,
                    board[8].length());
            }
        } else {
            if (move.indexOf('x') != -1) {
                halfmove = 0;
            }
            move = move.replace("x", "");
            move = move.replace("+", "");
            move = move.replace("-", "");
            move = move.replace("#", "");
            boolean a = true;
            if (Character.isLowerCase(move.charAt(0))) {
                piece = 'P';
                halfmove = 0;
                if (move.length() >= 5) {
                    if ((Integer.valueOf(move.substring(2, 3))) == 8) {
                        int col = Character.getNumericValue(move.charAt(1))
                            - 10;
                        int row = 7;
                        board[row] = board[row].substring(0, col)
                            + move.charAt(move.length() - 1)
                            + board[row].substring(col + 1, 8);
                        a = false;
                    }
                    move = move.substring(0, 3);
                }
            } else {
                piece = move.charAt(0);
            }
            if (turn == 'b') {
                piece = Character.toLowerCase(piece);
            }
            int col = Character.getNumericValue(move.charAt(move.length() - 2))
                - 10;
            int row = Integer.valueOf(move.substring(move.length() - 1,
                move.length())) - 1;
            if ((piece != 'Q'  || 'q' || 'K' || 'k')
                && a) {
                board[row] = board[row].substring(0, col) + piec
                    + board[row].substring(col + 1, 8);
                if (move.length() == 4) {
                    for (int i = 0; i <= 7; i++) {
                        if (Character.isLetter(move.charAt(1))) {
                            int coltest = Character.getNumericValue(
                                move.charAt(1)) - 10;
                            if (board[i].charAt(coltest) == piece) {
                                board[i] = removepiece(i, coltest, board);
                            }
                        } else if (Character.isDigit(move.charAt(1))) {
                            int rowtest = Integer.parseInt("" + move.charAt(1));
                            if (board[rowtest].charAt(i) == piece) {
                                board[rowtest] = removepiece(rowtest, i, board);
                            }
                        }
                    }
                } else {
                    boolean cond = true;
                    if (piece == 'P') {
                        if (enpass) {
                            board[row - 1] = removepiece(row - 1, col, board);
                        }
                        if (move.length() == 3) {
                            col = Character.getNumericValue(move.charAt(0))
                                - 10;
                            board[row - 1] = removepiece(row - 1, col, board);
                        } else {
                            if (board[row - 1].charAt(col) == 'P') {
                                board[row - 1] = removepiece(row - 1, col,
                                    board);
                            } else if (board[row - 2].charAt(col) == 'P') {
                                board[row - 2] = removepiece(row - 2, col,
                                    board);
                                ensq = move.substring(0, 1) + '3';
                            }
                        }
                    } else if (piece == 'p') {
                        if (enpass) {
                            board[row + 1] = removepiece(row + 1, col, board);
                        }
                        if (move.length() == 3) {
                            col = Character.getNumericValue(move.charAt(0))
                                - 10;
                            board[row + 1] = removepiece(row + 1, col, board);
                        } else {
                            if (board[row + 1].charAt(col) == 'p') {
                                board[row + 1] = removepiece(row + 1, col,
                                    board);
                            } else if (board[row + 2].charAt(col) == 'p') {
                                board[row + 2] = removepiece(row + 2, col,
                                    board);
                                ensq = move.substring(0, 1) + '6';
                            }

                        }
                    } else if (piece == 'N' || piece == 'n') {
                        if (row < 6) {
                            if (col < 7) {
                                if ((board[row + 2].charAt(col + 1)) == piece) {
                                    board[row + 2] = removepiece(row + 2, col
                                        + board);
                                }
                            }
                            if (col > 0) {
                                if ((board[row + 2].charAt(col - 1)) == piece) {
                                    board[row + 2] = removepiece(row + 2, col
                                        - 1, board);
                                }
                            }
                        }
                        if (row < 7) {
                            if (col > 1) {
                                if ((board[row + 1].charAt(col - 2)) == piece) {
                                    board[row + 1] = removepiece(row + 1, col
                                        - 2, board);
                                }
                            }
                            if (col < 6) {
                                if ((board[row + 1].charAt(col + 2)) == piece) {
                                    board[row + 1] = removepiece(row + 1, col
                                        + 2, board);
                                }
                            }
                        }
                        if (row > 0) {
                            if (col > 1) {
                                if ((board[row - 1].charAt(col - 2)) == piece) {
                                    board[row - 1] = removepiece(row - 1, col
                                        - 2, board);
                                }
                            }
                            if (col < 6) {
                                if ((board[row - 1].charAt(col + 2)) == piece) {
                                    board[row - 1] = removepiece(row - 1, col
                                        + 2, board);
                                }
                            }
                        }
                        if (row > 1) {
                            if (col > 0) {
                                if ((board[row - 2].charAt(col - 1)) == piece) {
                                    board[row - 2] = removepiece(row - 2, col
                                        - 1, board);
                                }
                            }
                            if (col < 6) {
                                if ((board[row - 2].charAt(col + 1)) == piece) {
                                    board[row - 2] = removepiece(row - 2, col
                                        + 1, board);
                                }
                            }
                        }
                    } else if (piece == 'B' || piece == 'b') {
                        int rowcheck = row;
                        int colcheck = col;
                        while (rowcheck < 7 && colcheck < 7) {
                            rowcheck = rowcheck + 1;
                            colcheck = colcheck + 1;
                            if ((board[rowcheck].charAt(colcheck) == piece)) {
                                board[rowcheck] = removepiece(rowcheck,
                                    colcheck, board);
                            }
                        }
                        rowcheck = row;
                        colcheck = col;
                        while (rowcheck > 0 && colcheck < 7) {
                            rowcheck = rowcheck - 1;
                            colcheck = colcheck + 1;
                            if ((board[rowcheck].charAt(colcheck) == piece)) {
                                board[rowcheck] = removepiece(rowcheck,
                                    colcheck, board);
                            }
                        }
                        rowcheck = row;
                        colcheck = col;
                        while (rowcheck > 0 && colcheck > 0) {
                            rowcheck = rowcheck - 1;
                            colcheck = colcheck - 1;
                            if ((board[rowcheck].charAt(colcheck) == piece)) {
                                board[rowcheck] = removepiece(rowcheck,
                                    colcheck, board);
                            }
                        }
                        rowcheck = row;
                        colcheck = col;
                        while (rowcheck < 7 && colcheck > 0) {
                            rowcheck = rowcheck + 1;
                            colcheck = colcheck - 1;
                            if ((board[rowcheck].charAt(colcheck) == piece)) {
                                board[rowcheck] = removepiece(rowcheck,
                                    colcheck, board);
                            }
                        }
                    } else if (piece == 'R' || piece == 'r') {
                        int rowcheck = row;
                        int colcheck = col;
                        if (row != 7) {
                            do {
                                rowcheck = rowcheck + 1;
                                if ((board[rowcheck].charAt(col) == piece)) {
                                    board[rowcheck] = removepiece(rowcheck, col,
                                        board);
                                    if (rowcheck == 0 && colcheck == 7
                                        && (board[8].indexOf('q') != -1)) {
                                        board[8] = board[8].substring(0,
                                            board[8].indexOf('q'))
                                            + board[8].substring(
                                            board[8].indexOf('q') + 1,
                                            board[8].length());
                                    }
                                    if (rowcheck == 7 && colcheck == 0
                                        && (board[8].indexOf('k') != -1)) {
                                        board[8] = board[8].substring(0,
                                            board[8].indexOf('k'))
                                            + board[8].substring(
                                            board[8].indexOf('k') + 1,
                                            board[8].length());
                                    }
                                }

                            } while (rowcheck < 7 && Character.isDigit(
                                board[rowcheck].charAt(col)));
                        }
                        rowcheck = row;
                        colcheck = col;
                        if (row != 0) {
                            do {
                                rowcheck = rowcheck - 1;
                                if ((board[rowcheck].charAt(col) == piece)) {
                                    board[rowcheck] = removepiece(rowcheck, col,
                                        board);
                                    if (rowcheck == 0 && colcheck == 7 && (
                                            board[8].indexOf('K') != -1)) {
                                        board[8] = board[8].substring(0,
                                            board[8].indexOf('K'))
                                            + board[8].substring(
                                            board[8].indexOf('K') + 1,
                                            board[8].length());
                                    }
                                    if (rowcheck == 0 && colcheck == 0
                                        && (board[8].indexOf('Q') != -1)) {
                                        board[8] = board[8].substring(0,
                                            board[8].indexOf('Q'))
                                            + board[8].substring(
                                            board[8].indexOf('Q') + 1,
                                            board[8].length());
                                    }
                                }
                            } while (rowcheck > 0 && Character.isDigit(
                                board[rowcheck].charAt(col)));
                        }
                        rowcheck = row;
                        colcheck = col;
                        if (col != 7) {
                            do {
                                colcheck = colcheck + 1;
                                if ((board[row].charAt(colcheck) == piece)) {
                                    board[row] = removepiece(row, colcheck,
                                        board);
                                    if (rowcheck == 0 && colcheck == 7
                                        && (board[8].indexOf('K') != -1)) {
                                        board[8] = board[8].substring(0,
                                            board[8].indexOf('K'))
                                            + board[8].substring(
                                            board[8].indexOf('K') + 1,
                                            board[8].length());
                                    }
                                    if (rowcheck == 7 && colcheck == 7
                                        && (board[8].indexOf('k') != -1)) {
                                        board[8] = board[8].substring(0,
                                            board[8].indexOf('k'))
                                            + board[8].substring(
                                            board[8].indexOf('k') + 1,
                                            board[8].length());
                                    }
                                }
                            } while (colcheck < 7 && Character.isDigit(
                                board[row].charAt(colcheck)));
                        }
                        rowcheck = row;
                        colcheck = col;
                        if (col != 0) {
                            do {
                                colcheck = colcheck - 1;
                                if ((board[row].charAt(colcheck) == piece)) {
                                    board[row] = removepiece(row, colcheck,
                                        board);
                                    if (rowcheck == 0 && colcheck == 0
                                        && (board[8].indexOf('Q') != -1)) {
                                        board[8] = board[8].substring(0,
                                            board[8].indexOf('Q'))
                                            + board[8].substring(
                                            board[8].indexOf('Q') + 1,
                                            board[8].length());
                                    }
                                    if (rowcheck == 7 && colcheck == 0
                                        && (board[8].indexOf('q') != -1)) {
                                        board[8] = board[8].substring(0,
                                            board[8].indexOf('q'))
                                            + board[8].substring(
                                            board[8].indexOf('q') + 1,
                                            board[8].length());
                                    }
                                }
                            } while (colcheck > 0 && Character.isDigit(
                                board[row].charAt(colcheck)));
                        }
                    } else if (piece == 'Q' || 'q' || 'K' || 'k') {
                        if (piece == 'K') {
                            if (board[8].indexOf('K') != -1) {
                                board[8] = board[8].substring(
                                    board[8].indexOf('K') + 1,
                                    board[8].length());
                            }
                            if (board[8].indexOf('Q') != -1) {
                                board[8] = board[8].substring(0,
                                    board[8].indexOf('Q')) + board[8].substring(
                                    board[8].indexOf('Q') + 1,
                                    board[8].length());
                            }
                        }
                        if (piece == 'k') {
                            if (board[8].indexOf('k') != -1) {
                                board[8] = board[8].substring(0,
                                    board[8].indexOf('k')) + board[8].substring(
                                    board[8].indexOf('k') + 1,
                                    board[8].length());
                            }
                            if (board[8].indexOf('q') != -1) {
                                board[8] = board[8].substring(0,
                                    board[8].indexOf('q')) + board[8].substring(
                                    board[8].indexOf('q') + 1,
                                    board[8].length());
                            }
                        }
                        for (int i = 0; i <= 7; i++) {
                            for (int j = 0; j <= 7; j++) {
                                if (board[i].charAt(j) == piece) {
                                    board[i] = removepiece(i, j, board);
                                }
                            }
                        }
                        board[row] = board[row].substring(0, col) + piece
                            + board[row].substring(col + 1, 8);
                    }
                }
            }
            enpass = false;
        }
        board[9] = ensq;
        board[10] = "" + Character.forDigit(halfmove, 10);
        return board;
    }
    public static String finalPosition(String game) {
        int moveindex = game.indexOf("1. ");
        String moves = game.substring(moveindex,
            (game.length() - 1));
        while (moves.indexOf('\n') != -1) {
            moves = moves.replace('\n', ' ');
        }
        String[] splits = moves.split(" ");
        int spaces = splits.length;
        char turn = ' ';
        String move = " ";
        int movecount = 0;
        String[] board = {"RNBQKBNR", "PPPPPPPP", "00000000", "00000000",
            "00000000", "00000000", "pppppppp", "rnbqkbnr", "KQkq", "-", "0"};
        boolean enpass = false;
        for (int i = 0; i < spaces; i++) {
            if (i % 3 == 1) {
                turn = 'w';
                move = splits[i];
                movecount = movecount + 1;
                if (i > 2 && Character.isLowerCase(splits[i - 2].charAt(0))) {
                    if (splits[i - 2].charAt(1) == '5') {
                        enpass = true;
                    }
                }
                if (Character.isLetter(move.charAt(0))) {
                    board = determinepiece(move, turn, board, enpass);
                }
            } else if (i % 3 == 2) {
                turn = 'b';
                move = splits[i];
                if (i > 3 && Character.isLowerCase(splits[i - 3].charAt(0))) {
                    if (splits[i - 3].charAt(1) == '6') {
                        enpass = true;
                    }
                }
                if (Character.isLetter(move.charAt(0))) {
                    board = determinepiece(move, turn, board, enpass);
                }
            }
        }
        for (int i = 0; i < 8; i++) {
            int zeros = 0;
            String newrow = "";
            int j = 0;
            for (j = 0; j < 8; j++) {
                if (Character.isDigit(board[i].charAt(j))) {
                    zeros = zeros + 1;
                } else if (zeros != 0 && Character.isLetter(
                        board[i].charAt(j))) {
                    newrow = newrow + Integer.toString(zeros)
                        + board[i].charAt(j);
                    zeros = 0;
                } else {
                    newrow = newrow + board[i].charAt(j);
                }
            }
            if (j == 8 && zeros != 0) {
                newrow = newrow + Integer.toString(zeros);
            }
            board[i] = newrow;
        }
        System.out.format("%s/%s/%s/%s/%s/%s/%s/%s %s %s %s %s %s", board[7],
            board[6], board[5], board[4], board[3], board[2], board[1],
            board[0], turn, board[8], board[9], board[10], movecount);
        return "";
    }

    /**
     * Reads the file named by path and returns its content as a String.
     *
     * @param path the relative or abolute path of the file to read
     * @return a String containing the content of the file
     */
    public static String fileContent(String path) {
        Path file = Paths.get(path);
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = Files.newBufferedReader(file)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                // Add the \n that's removed by readline()
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
            System.exit(1);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String game = fileContent(args[0]);
        System.out.format("Event: %s%n", tagValue("Event", game));
        System.out.format("Site: %s%n", tagValue("Site", game));
        System.out.format("Date: %s%n", tagValue("Date", game));
        System.out.format("Round: %s%n", tagValue("Round", game));
        System.out.format("White: %s%n", tagValue("White", game));
        System.out.format("Black: %s%n", tagValue("Black", game));
        System.out.format("Result: %s%n", tagValue("Result", game));
        System.out.println("Final Position:");
        System.out.println(finalPosition(game));
    }

}