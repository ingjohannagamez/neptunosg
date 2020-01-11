package com.neptunosg.controller;

import com.neptunosg.facade.AbstractFacade;
import com.neptunosg.facade.LazyEntityDataModel;
import com.neptunosg.controller.util.JsfUtil;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import java.util.ResourceBundle;
import javax.ejb.EJBException;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.UploadedFile;

/**
 * Represents an abstract shell of to be used as JSF Controller to be used in
 * AJAX-enabled applications. No outcomes will be generated from its methods
 * since handling is designed to be done inside one page.
 *
 * @param <T> the concrete Entity type of the Controller bean to be created
 */
public abstract class AbstractController<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private AbstractFacade<T> ejbFacade;
    private Class<T> itemClass;
    private T selected;
    private Collection<T> items;
    private LazyEntityDataModel<T> lazyItems;
    private List<T> filteredItems;
    private UploadedFile file;
    private boolean skip;
    private static final int CHUNK_SIZE = 4194304;

    private enum PersistAction {
        CREATE,
        DELETE,
        UPDATE
    }

    public AbstractController() {
    }

    public AbstractController(Class<T> itemClass) {
        this.itemClass = itemClass;
    }

    /**
     * Retrieve the current EJB Facade object so that other beans in this
     * package can perform additional data layer tasks (e.g. additional queries)
     *
     * @return the concrete EJB Facade associated with the concrete controller
     * bean.
     */
    protected AbstractFacade<T> getFacade() {
        return ejbFacade;
    }

    /**
     * Set any isChildEntityEmpty flags, if any children are defined in entity.
     * This method should be overridden inside specific entity controllers if
     * the entity has any OneToMany relationships. (see specific controllers for
     * more detail.
     *
     */
    protected void setChildrenEmptyFlags() {
    }

    /**
     * Retrieve the currently selected item.
     *
     * @return the currently selected Entity
     */
    public T getSelected() {
        return selected;
    }

    /**
     * Pass in the currently selected item.
     *
     * @param selected the Entity that should be set as selected
     */
    public void setSelected(T selected) {
        if (selected != null) {
            if (this.selected == null || !this.selected.equals(selected)) {
                this.selected = this.ejbFacade.findWithParents(selected);
                this.setChildrenEmptyFlags();
            }
        } else {
            this.selected = null;
        }
    }

    /**
     * Sets any embeddable key fields if an Entity uses composite keys. If the
     * entity does not have composite keys, this method performs no actions and
     * exists purely to be overridden inside a concrete controller class.
     */
    protected void setEmbeddableKeys() {
        // Nothing to do if entity does not have any embeddable key.
    }

    ;

    /**
     * Sets the concrete embedded key of an Entity that uses composite keys.
     * This method will be overriden inside concrete controller classes and does
     * nothing if the specific entity has no composite keys.
     */
    protected void initializeEmbeddableKey() {
        // Nothing to do if entity does not have any embeddable key.
    }

    /**
     * Returns all items as a Collection object.
     *
     * @return a collection of Entity items returned by the data layer
     */
    public Collection<T> getItems() {
        if (items == null) {
            items = this.ejbFacade.findAll();
        }
        return items;
    }

    /**
     * Pass in collection of items
     *
     * @param items a collection of Entity items
     */
    public void setItems(Collection<T> items) {
        this.items = items;
    }

    /**
     *
     * @return Entity-specific Lazy Data Model
     */
    public LazyEntityDataModel<T> getLazyItems() {
        if (lazyItems == null) {
            lazyItems = new LazyEntityDataModel<>(this.ejbFacade);
        }
        return lazyItems;
    }

    public void setLazyItems(LazyEntityDataModel<T> lazyItems) {
        this.lazyItems = lazyItems;
    }

    public void setLazyItems(Collection<T> items) {
        if (items instanceof List) {
            lazyItems = new LazyEntityDataModel<>((List<T>) items);
        } else {
            lazyItems = new LazyEntityDataModel<>(new ArrayList<>(items));
        }
    }

    public List<T> getFilteredItems() {
        return filteredItems;
    }

    public void setFilteredItems(List<T> filteredItems) {
        this.filteredItems = filteredItems;
    }

    /**
     * Apply changes to an existing item to the data layer.
     *
     * @param event an event from the widget that wants to save an Entity to the
     * data layer
     */
    public void save(ActionEvent event) {
        String msg = ResourceBundle.getBundle("/MyBundle").getString(itemClass.getSimpleName() + "Updated");
        persist(PersistAction.UPDATE, msg);

        if (!isValidationFailed()) {

            // Update the existing entity inside the item list
            List<T> itemList = refreshItem(this.selected, this.items);
            // If the original list has changed (it is a new object)
            if (this.items != itemList) {
                this.setItems(itemList);
            }

            // Also refresh the filteredItems list in case the user has filtered the DataTable
            if (filteredItems != null) {
                refreshItem(this.selected, this.filteredItems);
            }

        }

    }

    /**
     * Store a new item in the data layer.
     *
     * @param event an event from the widget that wants to save a new Entity to
     * the data layer
     */
    public void saveNew(ActionEvent event) {
        String msg = ResourceBundle.getBundle("/MyBundle").getString(itemClass.getSimpleName() + "Created");
        persist(PersistAction.CREATE, msg);
        if (!isValidationFailed()) {
            items = null; // Invalidate list of items to trigger re-query.
            lazyItems = null; // Invalidate list of lazy items to trigger re-query.
        }
    }

    /**
     * Remove an existing item from the data layer.
     *
     * @param event an event from the widget that wants to delete an Entity from
     * the data layer
     */
    public void delete(ActionEvent event) {
        String msg = ResourceBundle.getBundle("/MyBundle").getString(itemClass.getSimpleName() + "Deleted");
        persist(PersistAction.DELETE, msg);
        if (!isValidationFailed()) {
            selected = null; // Remove selection
            items = null; // Invalidate list of items to trigger re-query.
            lazyItems = null; // Invalidate list of lazy items to trigger re-query.
        }
    }

    /**
     * Performs any data modification actions for an entity. The actions that
     * can be performed by this method are controlled by the
     * {@link PersistAction} enumeration and are either CREATE, EDIT or DELETE.
     *
     * @param persistAction a specific action that should be performed on the
     * current item
     * @param successMessage a message that should be displayed when persisting
     * the item succeeds
     */
    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            this.setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    this.ejbFacade.edit(selected);
                } else {
                    this.ejbFacade.remove(selected);
                }
                this.setChildrenEmptyFlags();
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                Throwable cause = JsfUtil.getRootCause(ex.getCause());
                if (cause != null) {
                    if (cause instanceof ConstraintViolationException) {
                        ConstraintViolationException excp = (ConstraintViolationException) cause;
                        for (ConstraintViolation s : excp.getConstraintViolations()) {
                            JsfUtil.addErrorMessage(s.getMessage());
                        }
                    } else {
                        String msg = cause.getLocalizedMessage();
                        if (msg.length() > 0) {
                            JsfUtil.addErrorMessage(msg);
                        } else {
                            JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                        }
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/MyBundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    /**
     * Creates a new instance of an underlying entity and assigns it to Selected
     * property.
     *
     * @param event an event from the widget that wants to create a new,
     * unmanaged Entity for the data layer
     * @return a new, unmanaged Entity
     */
    public T prepareCreate(ActionEvent event) {
        T newItem;
        try {
            newItem = itemClass.newInstance();
            this.selected = newItem;
            initializeEmbeddableKey();
            return newItem;
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Inform the user interface whether any validation error exist on a page.
     *
     * @return a logical value whether form validation has passed or failed
     */
    public boolean isValidationFailed() {
        return JsfUtil.isValidationFailed();
    }

    /**
     * Retrieve all messages as a String to be displayed on the page.
     *
     * @param clientComponent the component for which the message applies
     * @param defaultMessage a default message to be shown
     * @return a concatenation of all messages
     */
    public String getComponentMessages(String clientComponent, String defaultMessage) {
        return JsfUtil.getComponentMessages(clientComponent, defaultMessage);
    }

    /**
     * Retrieve a collection of Entity items for a specific Controller from
     * another JSF page via Request parameters.
     */
    @PostConstruct
    public void initParams() {
        Object paramItems = FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get(itemClass.getSimpleName() + "_items");
        if (paramItems != null) {
            setItems((Collection<T>) paramItems);
            setLazyItems((Collection<T>) paramItems);
        }
    }

    private List<T> refreshItem(T item, Collection<T> items) {
        // Use List#set to replace the existing instance of this entity
        // If items is not a List, convert the Collection to a List
        List<T> itemList;
        if (this.items instanceof List) {
            itemList = (List<T>) items;
        } else {
            itemList = new ArrayList<>(items);
        }
        int i = itemList.indexOf(item);
        if (i >= 0) {
            try {
                itemList.set(i, item);
            } catch (UnsupportedOperationException ex) {
                return refreshItem(item, new ArrayList<>(items));
            }
        }
        return itemList;
    }

    /**
     * HandleFileUpload Método sube una imagen al directorio definido
     *
     * @param fileName
     * @param in
     * @param carpeta
     * @param nombre
     * @throws java.io.FileNotFoundException
     * @throws java.lang.Exception
     * @autor Johann Agamez Ferres Fecha creación : 17/06/2015 Fecha
     * Modificación: 17/06/2015
     */
    public void copyFile(String fileName, InputStream in, String carpeta, String nombre) throws FileNotFoundException, Exception {
        try {
            String imagen;
            imagen = nombre + "." + fileName.split("\\.")[1];
            FacesContext context = FacesContext.getCurrentInstance();
            ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
            String ruta = servletContext.getRealPath("./resources/img/".concat(carpeta + "/"));
            ruta = ruta != null ? ruta : "./resources/img/otros/";
            File fichero = new File(ruta);
            if (!fichero.exists()) {
                fichero.mkdirs();
            }
            try (OutputStream out = new FileOutputStream(new File(ruta + "/" + imagen))) {
                int read = 0;
                byte[] bytes = new byte[CHUNK_SIZE];

                while ((read = in.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
                in.close();
                out.flush();
            }
        } catch (IOException ex) {
            Logger.getLogger(AbstractController.class.getSimpleName()).log(Level.SEVERE, null, ex);
        }
    }

    public String onFlowProcess(FlowEvent event) {
        if (skip) {
            skip = false; //reset in case user goes back
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }

    public void handleFileUpload(FileUploadEvent event) {
        setFile(event.getFile());
        JsfUtil.addSuccessMessage(event.getFile().getFileName() + " se subio.");
    }

    public int calcularEdad(Date fecha) {
        Calendar fechaNac = Calendar.getInstance();
        fechaNac.setTime(fecha);

        Calendar today = Calendar.getInstance();
        int diffYear = today.get(Calendar.YEAR) - fechaNac.get(Calendar.YEAR);
        int diffMonth = today.get(Calendar.MONTH) - fechaNac.get(Calendar.MONTH);
        int diffDay = today.get(Calendar.DAY_OF_MONTH) - fechaNac.get(Calendar.DAY_OF_MONTH);
        // Si está en ese año pero todavía no los ha cumplido
        if (diffMonth < 0 || (diffMonth == 0 && diffDay < 0)) {
            diffYear = diffYear - 1;
        }
        return diffYear;
    }
    
    public void creaArchivo(String ruta, InputStream is) throws Exception {
        //BufferedOutputStream es para escribir el contenido del stream
        //por partes para no llenar la memoria y porque es más rápido
        //FileOutputStream es para indicar que vamos a escribir el
        //contenido en un archivo
        try (OutputStream os = new BufferedOutputStream(new FileOutputStream(new File(ruta)))) {
            byte[] chunk = new byte[CHUNK_SIZE];
            int bytesLeidos = 0;
            //mientras que podamos leer bytes del stream de entrada
            //en bloques de tamaño CHUNK_SIZE
            while ((bytesLeidos = is.read(chunk)) > 0) {
                //escribir los bytes leidos en el arreglo
                //desde la posición 0 hasta la posición marcada por
                //el valor de la variable bytesLeidos
                os.write(chunk, 0, bytesLeidos);
            }
        }
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

}
