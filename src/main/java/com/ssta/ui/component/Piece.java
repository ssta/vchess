package com.ssta.ui.component;

/**
 * Created by ssta on 11/27/16.
 */
public enum Piece {
  EMPTY(' ', null),
  BLACK_PAWN('p', "pdt"),
  BLACK_KNIGHT('n', "ndt"),
  BLACK_BISHOP('b', "bdt"),
  BLACK_ROOK('r', "rdt"),
  BLACK_QUEEN('q', "qdt"),
  BLACK_KING('k', "kdt"),
  WHITE_PAWN('P', "plt"),
  WHITE_KNIGHT('N', "nlt"),
  WHITE_BISHOP('B', "blt"),
  WHITE_ROOK('R', "rlt"),
  WHITE_QUEEN('Q', "qlt"),
  WHITE_KING('K', "klt");

  private char piece;
  private String fileIdentifier;

  private Piece(char piece, String fileIdentifier) {
    this.piece = piece;
    this.fileIdentifier = fileIdentifier;
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

  public String getFileIdentifier() {
    return fileIdentifier;
  }
}
