package com.neptunosg.controller;

import com.neptunosg.entity.Departamento;
import com.neptunosg.entity.Municipio;
import java.util.List;
import com.neptunosg.facade.DepartamentoFacade;
import com.neptunosg.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "departamentoController")
@ViewScoped
public class DepartamentoController extends AbstractController<Departamento> {

    @Inject
    private RegionController ideregController;
    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isMunicipioListEmpty;

    public DepartamentoController() {
        // Inform the Abstract parent controller of the concrete Departamento Entity
        super(Departamento.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        ideregController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsMunicipioListEmpty();
    }

    public boolean getIsMunicipioListEmpty() {
        return this.isMunicipioListEmpty;
    }

    private void setIsMunicipioListEmpty() {
        Departamento selected = this.getSelected();
        if (selected != null) {
            DepartamentoFacade ejbFacade = (DepartamentoFacade) this.getFacade();
            this.isMunicipioListEmpty = ejbFacade.isMunicipioListEmpty(selected);
        } else {
            this.isMunicipioListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Municipio entities that
     * are retrieved from Departamento and returns the navigation outcome.
     *
     * @return navigation outcome for Municipio page
     */
    public String navigateMunicipioList() {
        Departamento selected = this.getSelected();
        if (selected != null) {
            DepartamentoFacade ejbFacade = (DepartamentoFacade) this.getFacade();
            List<Municipio> selectedMunicipioList = ejbFacade.findMunicipioList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Municipio_items", selectedMunicipioList);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/municipio/index";
    }

    /**
     * Sets the "selected" attribute of the Region controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdereg(ActionEvent event) {
        Departamento selected = this.getSelected();
        if (selected != null && ideregController.getSelected() == null) {
            ideregController.setSelected(selected.getIdereg());
        }
    }

}
