package com.neptunosg.controller;

import com.neptunosg.entity.Menu;
import com.neptunosg.facade.MenuFacade;
import com.neptunosg.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "menuController")
@ViewScoped
public class MenuController extends AbstractController<Menu> {

    @Inject
    private SubmenuController submenuController;
    @Inject
    private MobilePageController mobilePageController;

    public MenuController() {
        // Inform the Abstract parent controller of the concrete Menu Entity
        super(Menu.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        submenuController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Submenu controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareSubmenu(ActionEvent event) {
        Menu selected = this.getSelected();
        if (selected != null && submenuController.getSelected() == null) {
            submenuController.setSelected(selected.getSubmenu());
        }
    }

}
