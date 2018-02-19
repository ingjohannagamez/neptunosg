package com.neptunosg.controller;

import com.neptunosg.entity.Continente;
import com.neptunosg.entity.Pais;
import java.util.List;
import com.neptunosg.facade.ContinenteFacade;
import com.neptunosg.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "continenteController")
@ViewScoped
public class ContinenteController extends AbstractController<Continente> {

    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isPaisListEmpty;

    public ContinenteController() {
        // Inform the Abstract parent controller of the concrete Continente Entity
        super(Continente.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsPaisListEmpty();
    }

    public boolean getIsPaisListEmpty() {
        return this.isPaisListEmpty;
    }

    private void setIsPaisListEmpty() {
        Continente selected = this.getSelected();
        if (selected != null) {
            ContinenteFacade ejbFacade = (ContinenteFacade) this.getFacade();
            this.isPaisListEmpty = ejbFacade.isPaisListEmpty(selected);
        } else {
            this.isPaisListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Pais entities that are
     * retrieved from Continente and returns the navigation outcome.
     *
     * @return navigation outcome for Pais page
     */
    public String navigatePaisList() {
        Continente selected = this.getSelected();
        if (selected != null) {
            ContinenteFacade ejbFacade = (ContinenteFacade) this.getFacade();
            List<Pais> selectedPaisList = ejbFacade.findPaisList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Pais_items", selectedPaisList);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/pais/index";
    }

}
