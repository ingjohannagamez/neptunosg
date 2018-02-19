package com.neptunosg.controller;

import com.neptunosg.entity.MenuRol;
import com.neptunosg.facade.MenuRolFacade;
import com.neptunosg.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "menuRolController")
@ViewScoped
public class MenuRolController extends AbstractController<MenuRol> {

    @Inject
    private RolController iderolController;
    @Inject
    private SubmenuController idesubController;
    @Inject
    private MobilePageController mobilePageController;

    public MenuRolController() {
        // Inform the Abstract parent controller of the concrete MenuRol Entity
        super(MenuRol.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        iderolController.setSelected(null);
        idesubController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Rol controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIderol(ActionEvent event) {
        MenuRol selected = this.getSelected();
        if (selected != null && iderolController.getSelected() == null) {
            iderolController.setSelected(selected.getIderol());
        }
    }

    /**
     * Sets the "selected" attribute of the Submenu controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdesub(ActionEvent event) {
        MenuRol selected = this.getSelected();
        if (selected != null && idesubController.getSelected() == null) {
            idesubController.setSelected(selected.getIdesub());
        }
    }

}
