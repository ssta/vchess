package com.ssta.ui.component;

/**
 * Created by ssta on 11/27/16.
 */
public enum Piece {
  EMPTY(' ', '\u00a0'),
  BLACK_PAWN('p', 'o'),
  BLACK_KNIGHT('n', 'm'),
  BLACK_BISHOP('b', 'v'),
  BLACK_ROOK('r', 't'),
  BLACK_QUEEN('q', 'w'),
  BLACK_KING('k', 'l'),
  WHITE_PAWN('P', 'p'),
  WHITE_KNIGHT('N', 'n'),
  WHITE_BISHOP('B', 'b'),
  WHITE_ROOK('R', 'r'),
  WHITE_QUEEN('Q', 'q'),
  WHITE_KING('K', 'k');

  private char piece;
  private char unicode;

  private Piece(char piece, char unicode) {
    this.piece = piece;
    this.unicode = unicode;
  }

  public static Piece find(char piece) {
    for (Piece p : Piece.values()) {
      if (p.piece == piece) {
        return p;
      }
    }
    throw new IllegalArgumentException("Unknown piece character: " + piece);
  }

  public char getPiece() {
    return piece;
  }

  public char getUnicode() {
    return unicode;
  }
}
