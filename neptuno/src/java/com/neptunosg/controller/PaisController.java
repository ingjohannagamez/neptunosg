package com.neptunosg.controller;

import com.neptunosg.entity.Pais;
import com.neptunosg.entity.Region;
import java.util.List;
import com.neptunosg.facade.PaisFacade;
import com.neptunosg.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "paisController")
@ViewScoped
public class PaisController extends AbstractController<Pais> {

    @Inject
    private ContinenteController ideconController;
    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isRegionListEmpty;

    public PaisController() {
        // Inform the Abstract parent controller of the concrete Pais Entity
        super(Pais.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        ideconController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsRegionListEmpty();
    }

    /**
     * Sets the "selected" attribute of the Continente controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdecon(ActionEvent event) {
        Pais selected = this.getSelected();
        if (selected != null && ideconController.getSelected() == null) {
            ideconController.setSelected(selected.getIdecon());
        }
    }

    public boolean getIsRegionListEmpty() {
        return this.isRegionListEmpty;
    }

    private void setIsRegionListEmpty() {
        Pais selected = this.getSelected();
        if (selected != null) {
            PaisFacade ejbFacade = (PaisFacade) this.getFacade();
            this.isRegionListEmpty = ejbFacade.isRegionListEmpty(selected);
        } else {
            this.isRegionListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Region entities that are
     * retrieved from Pais and returns the navigation outcome.
     *
     * @return navigation outcome for Region page
     */
    public String navigateRegionList() {
        Pais selected = this.getSelected();
        if (selected != null) {
            PaisFacade ejbFacade = (PaisFacade) this.getFacade();
            List<Region> selectedRegionList = ejbFacade.findRegionList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Region_items", selectedRegionList);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/region/index";
    }

}
