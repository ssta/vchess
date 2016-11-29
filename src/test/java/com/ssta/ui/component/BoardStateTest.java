package com.ssta.ui.component;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by ssta on 11/27/16.
 */
public class BoardStateTest {

  @Test
  public void testTranslateSquareToArrayIndex() throws Exception {
    ChessBoard.BoardState state = new ChessBoard.BoardState("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
    String[] in = new String[]{"a1", "a2", "b1", "h1", "a8", "h8"};
    int[] out = new int[]{0, 8, 1, 7, 56, 63};
    for (int i = 0; i < in.length; i++) {
      assertEquals(out[i], state.translateSquareToArrayIndex(in[i]));
    }
  }
}