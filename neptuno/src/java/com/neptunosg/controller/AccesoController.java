package com.neptunosg.controller;

import static com.neptunosg.controller.util.JsfUtil.encriptaDato;
import com.neptunosg.entity.Acceso;
import com.neptunosg.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "accesoController")
@ViewScoped
public class AccesoController extends AbstractController<Acceso> {

    @Inject
    private RolController iderolController;
    @Inject
    private UsuarioController ideusrController;
    @Inject
    private MobilePageController mobilePageController;

    public AccesoController() {
        // Inform the Abstract parent controller of the concrete Acceso Entity
        super(Acceso.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        iderolController.setSelected(null);
        ideusrController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Rol controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIderol(ActionEvent event) {
        Acceso selected = this.getSelected();
        if (selected != null && iderolController.getSelected() == null) {
            iderolController.setSelected(selected.getIderol());
        }
    }

    /**
     * Sets the "selected" attribute of the Usuario controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdeusr(ActionEvent event) {
        Acceso selected = this.getSelected();
        if (selected != null && ideusrController.getSelected() == null) {
            ideusrController.setSelected(selected.getIdeusr());
        }
    }

    @Override
    public void saveNew(ActionEvent event) {
        this.getSelected().setPswacc(encriptaDato(this.getSelected().getPswacc()));
        super.saveNew(event);
    }

    @Override
    public void save(ActionEvent event) {
        this.getSelected().setPswacc(encriptaDato(this.getSelected().getPswacc()));
        super.save(event);
    }

}
