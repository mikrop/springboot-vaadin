package cz.mikropsoft.springboot.vaadin;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.ui.Button;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.declarative.Design;

@DesignRoot
public class LoginViewLayout extends VerticalLayout {

    protected TextField emailField;
    protected PasswordField passwordField;
    protected Button signInButton;

    public LoginViewLayout(){
        Design.read(this);
    }

}
