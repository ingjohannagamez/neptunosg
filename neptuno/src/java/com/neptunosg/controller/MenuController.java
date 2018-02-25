package com.neptunosg.controller;

import com.neptunosg.entity.Menu;
import com.neptunosg.controller.util.MobilePageController;
import com.neptunosg.controller.util.Session;
import com.neptunosg.entity.MenuRol;
import com.neptunosg.entity.Submenu;
import com.neptunosg.facade.MenuRolFacade;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

@Named(value = "menuController")
@ViewScoped
public class MenuController extends AbstractController<Menu> {

    @Inject
    private SubmenuController submenuController;
    @Inject
    private MobilePageController mobilePageController;
    @Inject
    private transient Session sessionNeptunoSG;
    @Inject
    private transient MenuRolFacade menuRolFacade;

    private MenuModel model;
    private String page;
    private List<MenuRol> menuRol;

    public MenuController() {
        // Inform the Abstract parent controller of the concrete Menu Entity
        super(Menu.class);
    }

    @PostConstruct
    public void init() {
        this.setPage("inicio");
        this.showMenu();
    }

    public boolean showMenu() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session != null && session.getAttribute("sessionNeptunoSG") != null && sessionNeptunoSG.isMenuVisible()) {
            if (sessionNeptunoSG.isMenuVisible()) {
                constructorDeMenu();
                return true;
            }
        }
        return false;
    }

    private void constructorDeMenu() {
        this.menuRol = sessionNeptunoSG.getDatosAcceso().getIderol().getMenuRolList();
        List<Menu> menus = new ArrayList<>();
        List<Submenu> submenus = new ArrayList<>();
        menuRol.stream().map((menu) -> {
            if (!menus.contains(menu.getIdesub().getIdemen())) {
                menus.add(menu.getIdesub().getIdemen());
            }
            return menu;
        }).filter((menu) -> (!submenus.contains(menu.getIdesub()))).forEachOrdered((menu) -> {
            submenus.add(menu.getIdesub());
        });
        this.model = new DefaultMenuModel();
        menus.stream().map((menu) -> {
            DefaultSubMenu itemMenu = new DefaultSubMenu(menu.getNommen());
            itemMenu.setIcon(menu.getIcomen());
            itemMenu.setStyleClass("classMenu");
            submenus.stream().filter((submenu) -> (submenu.getIdemen().equals(menu))).map((submenu) -> {
                DefaultMenuItem item = new DefaultMenuItem(submenu.getLabsub());
                item.setIcon(submenu.getIcosub());
                item.setCommand("#{" + submenu.getComsub() + "}");
                //item.setUrl("/faces/jsf/".concat(submenu.getComsub()));
                item.setTitle(submenu.getTitsub());
                item.setStyleClass("button");
                item.setRendered(submenu.getRensub());
                return item;
            }).map((item) -> {
                item.setUpdate(":centerpanel");
                return item;
            }).forEachOrdered((item) -> {
                itemMenu.addElement(item);
            });
            return itemMenu;
        }).forEachOrdered((itemMenu) -> {
            this.getModel().addElement(itemMenu);
        });
    }

    public String navegacion(String ruta) {
        return ruta + "?faces-redirect=true";
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        submenuController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Submenu controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareSubmenu(ActionEvent event) {
        Menu selected = this.getSelected();
        if (selected != null && submenuController.getSelected() == null) {
            submenuController.setSelected(selected.getSubmenu());
        }
    }

    public MenuModel getModel() {
        return model;
    }

    public void setModel(MenuModel model) {
        this.model = model;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

}
