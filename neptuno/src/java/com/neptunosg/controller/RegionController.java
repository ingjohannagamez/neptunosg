package com.neptunosg.controller;

import com.neptunosg.entity.Region;
import com.neptunosg.entity.Departamento;
import java.util.List;
import com.neptunosg.facade.RegionFacade;
import com.neptunosg.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "regionController")
@ViewScoped
public class RegionController extends AbstractController<Region> {

    @Inject
    private PaisController idepaiController;
    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isDepartamentoListEmpty;

    public RegionController() {
        // Inform the Abstract parent controller of the concrete Region Entity
        super(Region.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idepaiController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsDepartamentoListEmpty();
    }

    public boolean getIsDepartamentoListEmpty() {
        return this.isDepartamentoListEmpty;
    }

    private void setIsDepartamentoListEmpty() {
        Region selected = this.getSelected();
        if (selected != null) {
            RegionFacade ejbFacade = (RegionFacade) this.getFacade();
            this.isDepartamentoListEmpty = ejbFacade.isDepartamentoListEmpty(selected);
        } else {
            this.isDepartamentoListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Departamento entities
     * that are retrieved from Region and returns the navigation outcome.
     *
     * @return navigation outcome for Departamento page
     */
    public String navigateDepartamentoList() {
        Region selected = this.getSelected();
        if (selected != null) {
            RegionFacade ejbFacade = (RegionFacade) this.getFacade();
            List<Departamento> selectedDepartamentoList = ejbFacade.findDepartamentoList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Departamento_items", selectedDepartamentoList);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/departamento/index";
    }

    /**
     * Sets the "selected" attribute of the Pais controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdepai(ActionEvent event) {
        Region selected = this.getSelected();
        if (selected != null && idepaiController.getSelected() == null) {
            idepaiController.setSelected(selected.getIdepai());
        }
    }

}
