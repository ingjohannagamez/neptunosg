package com.neptunosg.controller;

import com.neptunosg.entity.Municipio;
import com.neptunosg.entity.Oficina;
import java.util.List;
import com.neptunosg.facade.MunicipioFacade;
import com.neptunosg.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "municipioController")
@ViewScoped
public class MunicipioController extends AbstractController<Municipio> {

    @Inject
    private DepartamentoController idedepController;
    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isOficinaListEmpty;

    public MunicipioController() {
        // Inform the Abstract parent controller of the concrete Municipio Entity
        super(Municipio.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idedepController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsOficinaListEmpty();
    }

    /**
     * Sets the "selected" attribute of the Departamento controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdedep(ActionEvent event) {
        Municipio selected = this.getSelected();
        if (selected != null && idedepController.getSelected() == null) {
            idedepController.setSelected(selected.getIdedep());
        }
    }

    public boolean getIsOficinaListEmpty() {
        return this.isOficinaListEmpty;
    }

    private void setIsOficinaListEmpty() {
        Municipio selected = this.getSelected();
        if (selected != null) {
            MunicipioFacade ejbFacade = (MunicipioFacade) this.getFacade();
            this.isOficinaListEmpty = ejbFacade.isOficinaListEmpty(selected);
        } else {
            this.isOficinaListEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Oficina entities that are
     * retrieved from Municipio and returns the navigation outcome.
     *
     * @return navigation outcome for Oficina page
     */
    public String navigateOficinaList() {
        Municipio selected = this.getSelected();
        if (selected != null) {
            MunicipioFacade ejbFacade = (MunicipioFacade) this.getFacade();
            List<Oficina> selectedOficinaList = ejbFacade.findOficinaList(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Oficina_items", selectedOficinaList);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/oficina/index";
    }

}
