package com.ssta.ui;

import com.ssta.ui.view.ChessboardView;
import com.ssta.ui.view.ChessboardViewInitial;
import com.ssta.ui.view.HelloView;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;


/**
 * Created by ssta on 11/27/16.
 */
@Theme("vchess")
@SpringUI
@SpringViewDisplay
public class VChessUI extends UI implements ViewDisplay {
  private Panel springViewDisplay;

  @Override
  protected void init(VaadinRequest request) {
    final VerticalLayout root = new VerticalLayout();
    root.setSizeFull();
    root.setMargin(true);
    root.setSpacing(true);
    setContent(root);

    final CssLayout navigationBar = new CssLayout();
    navigationBar.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
    // we need an empty display first
    navigationBar.addComponent(createNavigationButton("Empty", ""));
    getNavigator().addView("", new Navigator.EmptyView());

    navigationBar.addComponent(createNavigationButton("Hello View", HelloView.VIEW_NAME));
    navigationBar.addComponent(createNavigationButton("Chessboard", ChessboardView.VIEW_NAME));
    navigationBar.addComponent(createNavigationButton("Chessboard Initial", ChessboardViewInitial.VIEW_NAME));

    root.addComponent(navigationBar);

    springViewDisplay = new Panel();
    springViewDisplay.setSizeFull();
    root.addComponent(springViewDisplay);
    root.setExpandRatio(springViewDisplay, 1.0f);


  }

  private Button createNavigationButton(String caption, final String viewName) {
    Button button = new Button(caption);
    button.addStyleName(ValoTheme.BUTTON_SMALL);
    // If you didn't choose Java 8 when creating the project, convert this
    // to an anonymous listener class
    button.addClickListener(event -> getUI().getNavigator().navigateTo(viewName));
    return button;
  }

  @Override
  public void showView(View view) {
    springViewDisplay.setContent((Component) view);
  }
}
