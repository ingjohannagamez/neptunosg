package com.neptunosg.controller;

import com.neptunosg.entity.Rol;
import com.neptunosg.entity.Acceso;
import com.neptunosg.entity.MenuRol;
import java.util.List;
import com.neptunosg.facade.RolFacade;
import com.neptunosg.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "rolController")
@ViewScoped
public class RolController extends AbstractController<Rol> {

    @Inject
    private PermisosController ideperController;
    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isAccesoListEmpty;
    private boolean isMenuRolListEmpty;

    public RolController() {
        // Inform the Abstract parent controller of the concrete Rol Entity
        super(Rol.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        ideperController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsAccesoListEmpty();
        this.setIsMenuRolListEmpty();
    }

    public boolean getIsAccesoListEmpty() {
        return this.isAccesoListEmpty;
    }

    private void setIsAccesoListEmpty() {
        Rol selected = this.getSelected();
        if (selected != null) {
            RolFacade ejbFacade = (RolFacade) this.getFacade();
            this.isAccesoListEmpty = ejbFacade.isAccesoListEmpty(selected);
        } else {
            this.isAccesoListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Acceso entities that are
     * retrieved from Rol and returns the navigation outcome.
     *
     * @return navigation outcome for Acceso page
     */
    public String navigateAccesoList() {
        Rol selected = this.getSelected();
        if (selected != null) {
            RolFacade ejbFacade = (RolFacade) this.getFacade();
            List<Acceso> selectedAccesoList = ejbFacade.findAccesoList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Acceso_items", selectedAccesoList);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/acceso/index";
    }

    public boolean getIsMenuRolListEmpty() {
        return this.isMenuRolListEmpty;
    }

    private void setIsMenuRolListEmpty() {
        Rol selected = this.getSelected();
        if (selected != null) {
            RolFacade ejbFacade = (RolFacade) this.getFacade();
            this.isMenuRolListEmpty = ejbFacade.isMenuRolListEmpty(selected);
        } else {
            this.isMenuRolListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of MenuRol entities that are
     * retrieved from Rol and returns the navigation outcome.
     *
     * @return navigation outcome for MenuRol page
     */
    public String navigateMenuRolList() {
        Rol selected = this.getSelected();
        if (selected != null) {
            RolFacade ejbFacade = (RolFacade) this.getFacade();
            List<MenuRol> selectedMenuRolList = ejbFacade.findMenuRolList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("MenuRol_items", selectedMenuRolList);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/menuRol/index";
    }

    /**
     * Sets the "selected" attribute of the Permisos controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdeper(ActionEvent event) {
        Rol selected = this.getSelected();
        if (selected != null && ideperController.getSelected() == null) {
            ideperController.setSelected(selected.getIdeper());
        }
    }

}
