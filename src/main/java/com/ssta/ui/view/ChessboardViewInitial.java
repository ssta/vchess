package com.ssta.ui.view;

import com.ssta.ui.component.ChessBoard;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.VerticalLayout;

import javax.annotation.PostConstruct;

/**
 * Created by ssta on 11/27/16.
 */
@SpringView(name = ChessboardViewInitial.VIEW_NAME)
public class ChessboardViewInitial extends VerticalLayout implements View {
  public static final String VIEW_NAME = "chessboard-view-initial";

  @PostConstruct
  public void init() {
    addComponent(new ChessBoard("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1"));

  }

  @Override
  public void enter(ViewChangeListener.ViewChangeEvent event) {
    // construction done in init() method
  }
}
