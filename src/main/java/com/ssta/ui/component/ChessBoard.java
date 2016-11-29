package com.ssta.ui.component;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.*;
import org.vaadin.viritin.layouts.MHorizontalLayout;

import java.util.Arrays;

/**
 * Created by ssta on 11/27/16.
 */
public class ChessBoard extends CustomComponent {
  GridLayout layout;
  BoardState state;

  public ChessBoard(String fen) {
    setBoardState(fen);
    setCompositionRoot(layout);
  }

  public static Component initialPosition() {
    return new ChessBoard("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
  }

  public void setBoardState(String fen) {
    System.out.println("got call with boardstate: " + fen);
    state = parseFen(fen);
    layout = new GridLayout(8, 8);
    layout.setWidth(400, Unit.PIXELS);
    layout.setHeight(400, Unit.PIXELS);

    for (int i = 0; i < state.board.length; i++) {
      int[] pos = arrayIndexToGridIndex(i);
      Component square = mkSquare(state.board[i]);
      square.setWidth(100, Unit.PERCENTAGE);
      if (pos[1] % 2 == 1 && pos[0] % 2 == 0) {square.addStyleName("blacksquare");}
      if (pos[1] % 2 == 1 && pos[0] % 2 == 1) {square.addStyleName("whitesquare");}
      if (pos[1] % 2 == 0 && pos[0] % 2 == 1) {square.addStyleName("blacksquare");}
      if (pos[1] % 2 == 0 && pos[0] % 2 == 0) {square.addStyleName("whitesquare");}
      layout.addComponent(square, pos[1], pos[0]);
    }
  }

  /**
   * given a board state array index, return the indices of the row/olumn in the GridLayout
   */
  private int[] arrayIndexToGridIndex(int index) {
    int row = 7 - (index / 8);
    int column = index % 8;
    return new int[]{row, column};
  }

  private BoardState parseFen(String fen) {
    // FEN is 8 ranks separated by "/", "side to move", "castling allowed flags", "en passant square", "halfmove clock", "fullmove number"
    return new BoardState(fen);
  }

  private Component mkSquare(char p) {
    Piece piece = Piece.find(p);
    String filename = String.format("svg/Chess_%s45.svg", piece.getFileIdentifier());

    HorizontalLayout h = new MHorizontalLayout()
        .withWidth(50, Unit.PIXELS)
        .withHeight(50, Unit.PIXELS)
        .withMargin(false)
        .withSpacing(false);
    h.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
    if (piece.getFileIdentifier() != null) {
      Embedded svg = new Embedded(null, new ThemeResource(filename));
      //svg.setMimeType("image/svg+xml");
      h.addComponent(svg);
    }
    return h;
  }

  protected static class BoardState {
    /**
     * Where the pieces are - 0=a1 up to 63=h8.  Pieces are r=wr, etc...
     */
    char[] board;
    /**
     * Side to move; 0==white; 1==black
     */
    int sideToMove;
    /**
     * Flags for castling, 0:WK; 1:WQ; 2:BK; 3:BQ
     */
    boolean[] castlingFlags = new boolean[4];
    /**
     * The square index where en-passant is allowed
     */
    int enPassantSquare = -1;

    /**
     * The number of halfmoves since the last capture or pawn move - for tracking 50move rule draws
     */
    int halfMoves;

    /**
     * The current full move - starts at 1 and is incremented after every black move
     */
    int fullMoves;

    BoardState(String fen) {
      // initialise the board to spaces
      board = new char[64];
      Arrays.fill(board, ' ');
      // split the FEN around space, then parse each bit
      String[] fenArray = fen.split(" ");
      if (fenArray.length < 4) {throw new IllegalArgumentException("Invalid FEN string");}
      String[] rankArray = fenArray[0].split("/");
      if (rankArray.length != 8) {throw new IllegalArgumentException("Invalid FEN string");}
      int currentIndex = 0;
      for (int i = 7; i >= 0; i--) {
        String rank = rankArray[i];
        for (char c : rank.toCharArray()) {
          if (Character.isDigit(c)) {
            currentIndex += (c - '0');
          } else {
            board[currentIndex] = c;
            currentIndex++;
          }
        }
      }

      sideToMove = "w".equals(fenArray[1]) ? 0 : 1;

      if (fenArray[2].contains("K")) {castlingFlags[0] = true;}
      if (fenArray[2].contains("Q")) {castlingFlags[1] = true;}
      if (fenArray[2].contains("k")) {castlingFlags[2] = true;}
      if (fenArray[2].contains("q")) {castlingFlags[3] = true;}

      if (fenArray[3].equals("-")) { enPassantSquare = -1;} else { enPassantSquare = translateSquareToArrayIndex(fenArray[3]);}

      if (fenArray.length > 4) {
        halfMoves = Integer.parseInt(fenArray[4]);
      }
      if (fenArray.length > 5) {
        fullMoves = Integer.parseInt(fenArray[5]);
      }
    }

    protected int translateSquareToArrayIndex(String s) {
      int file = s.toCharArray()[0] - 'a';
      int rank = s.toCharArray()[1] - '1';
      return rank * 8 + file;
    }

    @Override
    public String toString() {
      return "BoardState{" +
          "board=" + Arrays.toString(board) +
          ", sideToMove=" + sideToMove +
          ", castlingFlags=" + Arrays.toString(castlingFlags) +
          ", enPassantSquare=" + enPassantSquare +
          ", halfMoves=" + halfMoves +
          ", fullMoves=" + fullMoves +
          '}';
    }
  }
}
