package com.neptunosg.controller;

import com.neptunosg.entity.Permisos;
import com.neptunosg.entity.Rol;
import java.util.List;
import com.neptunosg.facade.PermisosFacade;
import com.neptunosg.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "permisosController")
@ViewScoped
public class PermisosController extends AbstractController<Permisos> {

    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isRolListEmpty;

    public PermisosController() {
        // Inform the Abstract parent controller of the concrete Permisos Entity
        super(Permisos.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsRolListEmpty();
    }

    public boolean getIsRolListEmpty() {
        return this.isRolListEmpty;
    }

    private void setIsRolListEmpty() {
        Permisos selected = this.getSelected();
        if (selected != null) {
            PermisosFacade ejbFacade = (PermisosFacade) this.getFacade();
            this.isRolListEmpty = ejbFacade.isRolListEmpty(selected);
        } else {
            this.isRolListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Rol entities that are
     * retrieved from Permisos and returns the navigation outcome.
     *
     * @return navigation outcome for Rol page
     */
    public String navigateRolList() {
        Permisos selected = this.getSelected();
        if (selected != null) {
            PermisosFacade ejbFacade = (PermisosFacade) this.getFacade();
            List<Rol> selectedRolList = ejbFacade.findRolList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Rol_items", selectedRolList);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/rol/index";
    }

}
