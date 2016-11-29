package com.ssta.ui.view;

import com.ssta.ui.component.ChessBoard;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.viritin.label.MLabel;

import javax.annotation.PostConstruct;

/**
 * Created by ssta on 11/27/16.
 */
@SpringView(name = ChessboardView.VIEW_NAME)
public class ChessboardView extends VerticalLayout implements View {
  public static final String VIEW_NAME = "chessboard-view";

  @PostConstruct
  public void init() {
    addComponent(new ChessBoard("8/6pk/4Q2p/pp6/2PP4/P2P3P/4pqPK/8 w - - 2 34"));

  }

  @Override
  public void enter(ViewChangeListener.ViewChangeEvent event) {
    // construction done in init() method
  }
}
