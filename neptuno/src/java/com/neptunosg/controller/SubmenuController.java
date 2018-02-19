package com.neptunosg.controller;

import com.neptunosg.entity.Submenu;
import com.neptunosg.entity.MenuRol;
import java.util.List;
import com.neptunosg.facade.SubmenuFacade;
import com.neptunosg.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "submenuController")
@ViewScoped
public class SubmenuController extends AbstractController<Submenu> {

    @Inject
    private MenuController idemenController;
    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isMenuRolListEmpty;

    public SubmenuController() {
        // Inform the Abstract parent controller of the concrete Submenu Entity
        super(Submenu.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idemenController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsMenuRolListEmpty();
    }

    public boolean getIsMenuRolListEmpty() {
        return this.isMenuRolListEmpty;
    }

    private void setIsMenuRolListEmpty() {
        Submenu selected = this.getSelected();
        if (selected != null) {
            SubmenuFacade ejbFacade = (SubmenuFacade) this.getFacade();
            this.isMenuRolListEmpty = ejbFacade.isMenuRolListEmpty(selected);
        } else {
            this.isMenuRolListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of MenuRol entities that are
     * retrieved from Submenu and returns the navigation outcome.
     *
     * @return navigation outcome for MenuRol page
     */
    public String navigateMenuRolList() {
        Submenu selected = this.getSelected();
        if (selected != null) {
            SubmenuFacade ejbFacade = (SubmenuFacade) this.getFacade();
            List<MenuRol> selectedMenuRolList = ejbFacade.findMenuRolList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("MenuRol_items", selectedMenuRolList);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/menuRol/index";
    }

    /**
     * Sets the "selected" attribute of the Menu controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdemen(ActionEvent event) {
        Submenu selected = this.getSelected();
        if (selected != null && idemenController.getSelected() == null) {
            idemenController.setSelected(selected.getIdemen());
        }
    }

}
