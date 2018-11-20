package com.neptunosg.controller.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

/**
 *
 * @author JOHANN AGAMEZ
 */
public abstract class JasperController implements java.io.Serializable {

    public static final Locale SPANISH = new Locale("es", "CO");

    private static final Logger LOG = Logger.getLogger(JasperController.class.getName());
    private static final long serialVersionUID = -6717436370935016427L;

    private FacesContext context;
    private ServletContext servletContext;
    private JasperPrint jasperPrint;
    private String nombreReporte;
    private Map<String, Object> parametros;

    public JasperController() {
        parametros = new HashMap<>();
    }

    /**
     * Este método debe ser sobreescrito asignando los parámetros que recibirá
     * el reporte a generar
     *
     * @param rutaReporte esta es la ruta donde se ejecuta el reporte
     * @return listado de parámetros que generaran el reporte
     */
    public Map<String, Object> configParametros(String rutaReporte) {
        parametros.put("SUBREPORT_DIR", rutaReporte);
        parametros.put(JRParameter.REPORT_LOCALE, JasperController.SPANISH);
        parametros.put("IMAGES_FOLDER", servletContext);//servletContext.getRealPath("/resources/img/")
//        parametros.put("enumValues", java.util.ResourceBundle.getBundle("/EnumValues"));
        parametros.put("IP_BASE", this.getIpUsuario());
        return parametros;
    }

    public String getIpUsuario() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        System.out.println("ipAddress:" + ipAddress);
        return ipAddress;
    }

    /**
     * Este método toma el reporte generado, (llama al método
     * inicializarReporte()) y lo exporta a PDF
     *
     * @throws net.sf.jasperreports.engine.JRException
     */
    public void PDF() throws JRException {
        PDF("report.pdf");
    }

    /**
     * Este método toma el reporte generado, (llama al método
     * inicializarReporte()) y lo exporta a PDF
     *
     * @param nombreArchivo
     * @throws net.sf.jasperreports.engine.JRException
     */
    public void PDF(String nombreArchivo) throws JRException {
        inicializarReporte();
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=" + nombreArchivo);
        FacesContext.getCurrentInstance().responseComplete();
        try (ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream()) {
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            servletOutputStream.flush();
            servletOutputStream.close();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (IOException | JRException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }

    public InputStream PDFb() throws JRException {
        inicializarReporte();

        return new ByteArrayInputStream(JasperExportManager.exportReportToPdf(jasperPrint));
    }

    public InputStream exportar(JRAbstractExporter exporter) throws JRException {
        inicializarReporte();
        ByteArrayOutputStream Teste = new ByteArrayOutputStream();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(Teste));
        exporter.exportReport();
        return new ByteArrayInputStream(Teste.toByteArray());
    }

    public InputStream XLSXb() throws JRException {
        return exportar(new JRXlsxExporter());
    }

    /**
     *
     * @return Nombre del reporte a generar
     */
    public String getNombreReporte() {
        return nombreReporte;
    }

    /**
     * Especifica el nombre del reporte en cuestión
     *
     * @param nombreReporte
     */
    public void setNombreReporte(String nombreReporte) {
        this.nombreReporte = nombreReporte;
    }

    /**
     * Inicializa las variables relacionadas con la solicitud http y genera el
     * reporte llamando al método responsable de generarlo
     *
     * @throws net.sf.jasperreports.engine.JRException
     */
    public void inicializarReporte() throws JRException {
        context = FacesContext.getCurrentInstance();
        servletContext = (ServletContext) context.getExternalContext().getContext();
        String ruta = servletContext.getRealPath("/reports/");
        generarReporte(ruta + "/" + getNombreReporte() + ".jasper", configParametros(ruta));
    }

    /**
     * Configura la fuente de datos y genera el jasperprint usado por los
     * exportadores
     *
     * @param ruta
     * @param parametros
     * @throws net.sf.jasperreports.engine.JRException
     */
    public abstract void generarReporte(String ruta, Map<String, Object> parametros) throws JRException;

    public JasperPrint getJasperPrint() {
        return jasperPrint;
    }

    public void setJasperPrint(JasperPrint jasperPrint) {
        this.jasperPrint = jasperPrint;
    }

    @Deprecated
    public Map<String, Object> getParametros() {
        return parametros;
    }

    public JasperController addParametro(String clave, Object valor) {
        parametros.put(clave, valor);
        return this;
    }

    @Deprecated
    public void setParametros(Map<String, Object> parametros) {
        this.parametros = parametros;

    }
}
