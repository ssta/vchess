package com.ssta.ui.view;

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
@SpringView(name = HelloView.VIEW_NAME)
public class HelloView extends VerticalLayout implements View {
  public static final String VIEW_NAME = "hello-view";

  @PostConstruct
  void init() {
    addComponent(new MLabel("This is HelloView").withStyleName(ValoTheme.LABEL_H1));
  }

  @Override
  public void enter(ViewChangeListener.ViewChangeEvent event) {
    // construction done in init() method
  }
}
