package cz.mikropsoft.springboot.vaadin;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SpringView(name = ErrorView.VIEW_NAME)
public class ErrorView extends VerticalLayout implements View {

    private static final long serialVersionUID = 1L;
    public static final String VIEW_NAME = "error";

    public ErrorView() {
        addComponent(new Label(
                "Oops. The view you tried to navigate to doesn't exist."));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // TODO Auto-generated method stub
    }

}
