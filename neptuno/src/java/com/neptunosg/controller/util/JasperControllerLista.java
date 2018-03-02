package com.neptunosg.controller.util;

import java.util.Collection;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;

/**
 *
 * @author JOHANN AGAMEZ
 * @param <T>
 */
public class JasperControllerLista<T> extends JasperController {

    private static final long serialVersionUID = 1492972822905781700L;

    public JasperControllerLista() {
        super();
    }

    private Collection<T> items;

    /**
     * Obtiene el listado de items que pueden llegar a ser generado en el
     * reporte
     *
     * @return
     */
    public Collection<T> getItems() {
        return items;
    }

    /**
     *
     * @param items
     */
    public void setItems(Collection items) {
        this.items = items;
    }

    @Override
    public void generarReporte(String ruta, Map<String, Object> parametros) throws JRException {
        parametros.put(JRParameter.REPORT_LOCALE, JasperController.SPANISH);
        setJasperPrint(JasperFillManager.fillReport(ruta, parametros, new net.sf.jasperreports.engine.data.JRBeanCollectionDataSource(getItems())));
    }
}
