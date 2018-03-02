package com.neptunosg.controller.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateful;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;

/**
 *
 * @author JOHANN AGAMEZ
 */
@Stateful
public class JasperControllerCDIJDBC extends JasperController {

    private static final Logger LOG = Logger.getLogger(JasperControllerCDIJDBC.class.getName());
    private static final long serialVersionUID = 8550420506613775933L;

    /**
     * Recurso JDBC, en este caso una fuente de datos, con la cual se realiza la
     * conexión
     */
    @Resource(mappedName = "java:app/neptunosg")
    private DataSource dataSource;

    public JasperControllerCDIJDBC() {
        super();
    }

    @Override
    public void generarReporte(String ruta, Map<String, Object> parametros) {
        try (Connection connection = dataSource.getConnection()) {
            parametros.put(JRParameter.REPORT_LOCALE, JasperController.SPANISH);
            setJasperPrint(JasperFillManager.fillReport(ruta, parametros, connection));
        } catch (JRException | SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            LOG.log(Level.SEVERE, null, e);
        }
    }
}
