package cz.mikropsoft.springboot.vaadin;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@SpringView(name = ViewScopedView.VIEW_NAME)
public class ViewScopedView extends VerticalLayout implements View {

    private static final long serialVersionUID = 1L;
	public final static String VIEW_NAME = "view";
	
	@Autowired
	private ViewGreeter viewGreeter;
	
	@Autowired 
	private Greeter uiGreeter;
	
	@PostConstruct
	void init() {
		setMargin(true);
		setSpacing(true);
		addComponent(new Label("This is a view scoped view"));
		addComponent(new Label(uiGreeter.sayHello()));
		addComponent(new Label(viewGreeter.sayHello()));
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
	}

}
