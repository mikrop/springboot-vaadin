package cz.mikropsoft.springboot.vaadin;

import com.vaadin.data.validator.EmailValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

@SpringView(name = LoginView.VIEW_NAME)
public class LoginView extends VerticalLayout implements View {

    private static final long serialVersionUID = 1L;
    public static final String VIEW_NAME = "login";

    protected LoginViewLayout loginForm;

    public LoginView() {
        loginForm = new LoginViewLayout();
        addComponent(loginForm);
        addValidators();
        addLoginButtonListener();
    }

    private void performLogin() {
        String email=this.loginForm.emailField.getValue();
        String password=this.loginForm.passwordField.getValue();
        if(email.equals("jmeno@email.cz") && password.equals("mojeheslo")){
            Notification.show("SUCCESS");
        } else{
            Notification.show("Login failed. Try 'jmeno@email.cz/mojeheslo'");
        }
    }

    private void addValidators() {
        this.loginForm.emailField.addValidator(new EmailValidator("Input valid email"));
    }

    private void addLoginButtonListener() {
        this.loginForm.signInButton.addClickListener(e -> performLogin());
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // TODO Auto-generated method stub
    }

}
