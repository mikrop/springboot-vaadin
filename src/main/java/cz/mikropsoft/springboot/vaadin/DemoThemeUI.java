package cz.mikropsoft.springboot.vaadin;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.*;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import cz.mikropsoft.springboot.vaadin.valo.TestIcon;
import cz.mikropsoft.springboot.vaadin.valo.ValoMenuLayout;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

@SpringUI
@PreserveOnRefresh
@Theme("tests-valo")
@Title("Demo Valo Theme")
public class DemoThemeUI extends UI implements ViewChangeListener {

    @Autowired
    private SpringViewProvider viewProvider;

    ValoMenuLayout root = new ValoMenuLayout();
    ComponentContainer viewDisplay = root.getContentContainer();
    CssLayout menuItemsLayout = new CssLayout();

    CssLayout menu = new CssLayout();
    {
        menu.setId("testMenu");
    }

    private Navigator navigator;
    private final LinkedHashMap<String, String> menuItems = new LinkedHashMap<String, String>();

    @Override
    protected void init(VaadinRequest request) {

        Responsive.makeResponsive(this);

        addStyleName(ValoTheme.UI_WITH_MENU);
        setContent(root);
        root.setWidth("100%");
        root.addMenu(buildMenu());

        navigator = new Navigator(this, viewDisplay);
        navigator.addProvider(viewProvider);
        navigator.setErrorView(DefaultView.class);
        navigator.addViewChangeListener(this);

    }

    CssLayout buildMenu() {
        // Add items
//        menuItems.put(TodoView.VIEW_NAME, "Todo View");

        final HorizontalLayout top = new HorizontalLayout();
        top.setWidth("100%");
        top.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        top.addStyleName("valo-menu-title");
        menu.addComponent(top);

        final Button showMenu = new Button("Menu", new Button.ClickListener() {
            @Override
            public void buttonClick(final Button.ClickEvent event) {
                if (menu.getStyleName().contains("valo-menu-visible")) {
                    menu.removeStyleName("valo-menu-visible");
                } else {
                    menu.addStyleName("valo-menu-visible");
                }
            }
        });
        showMenu.addStyleName(ValoTheme.BUTTON_PRIMARY);
        showMenu.addStyleName(ValoTheme.BUTTON_SMALL);
        showMenu.addStyleName("valo-menu-toggle");
        showMenu.setIcon(FontAwesome.LIST);
        menu.addComponent(showMenu);

        final Label title = new Label("<h3>Vaadin <strong>Valo Theme</strong></h3>", ContentMode.HTML);
        title.setSizeUndefined();
        top.addComponent(title);
        top.setExpandRatio(title, 1);

        final MenuBar settings = new MenuBar();
        settings.addStyleName("user-menu");
        final MenuBar.MenuItem settingsItem = settings.addItem(
                "Michal_Hajek",
                new ThemeResource("../tests-valo/img/profile-pic-300px.jpg"),
                null
        );
        settingsItem.addItem("Edit Profile", null);
        settingsItem.addItem("Preferences", null);
        settingsItem.addSeparator();
        settingsItem.addItem("Sign Out", FontAwesome.SIGN_OUT, e -> navigator.navigateTo(LoginView.VIEW_NAME));
        menu.addComponent(settings);

        menuItemsLayout.setPrimaryStyleName("valo-menuitems");
        menu.addComponent(menuItemsLayout);

//        Label label = null;
//        int count = -1;
//        for (final Map.Entry<String, String> item : menuItems.entrySet()) {
//            count = 0;
//            label = new Label("Other", ContentMode.HTML);
//            label.setPrimaryStyleName("valo-menu-subtitle");
//            label.addStyleName("h4");
//            label.setSizeUndefined();
//            menuItemsLayout.addComponent(label);
//            final Button b = new Button(item.getValue(), e -> navigator.navigateTo(item.getKey()));
//            b.setCaption(b.getCaption() + " <span class=\"valo-menu-badge\">123</span>");
//            b.setHtmlContentAllowed(true);
//            b.setPrimaryStyleName("valo-menu-item");
//            b.setIcon(new TestIcon(100).get());
//            menuItemsLayout.addComponent(b);
//            count++;
//        }
//        label.setValue(label.getValue() + " <span class=\"valo-menu-badge\">" + count + "</span>");

        return menu;
    }

    @Override
    public boolean beforeViewChange(ViewChangeEvent event) {
        return true;
    }

    @Override
    public void afterViewChange(ViewChangeEvent event) {

        for (final Iterator<Component> it = menuItemsLayout.iterator(); it.hasNext();) {
            it.next().removeStyleName("selected");
        }
        for (final Map.Entry<String, String> item : menuItems.entrySet()) {
            if (event.getViewName().equals(item.getKey())) {
                for (final Iterator<Component> it = menuItemsLayout.iterator(); it.hasNext();) {
                    final Component c = it.next();
                    if (c.getCaption() != null && c.getCaption().startsWith(item.getValue())) {
                        c.addStyleName("selected");
                        break;
                    }
                }
                break;
            }
        }
        menu.removeStyleName("valo-menu-visible");

    }

}
