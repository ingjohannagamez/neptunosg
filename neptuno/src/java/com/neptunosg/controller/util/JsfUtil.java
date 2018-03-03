package com.neptunosg.controller.util;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UISelectItem;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class JsfUtil {

    private SecretKey key;
    private Cipher cipher;
    private final String algoritmo = "Blowfish";
    private final int keysize = 16;

    public static void addErrorMessage(Exception ex, String defaultMsg) {
        String msg = ex.getLocalizedMessage();
        if (msg != null && msg.length() > 0) {
            addErrorMessage(msg);
        } else {
            addErrorMessage(defaultMsg);
        }
    }

    public static void addErrorMessages(List<String> messages) {
        messages.forEach((message) -> {
            addErrorMessage(message);
        });
    }

    public static void addErrorMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        FacesContext.getCurrentInstance().validationFailed(); // Invalidate JSF page if we raise an error message

    }

    public static void addSuccessMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
        FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
    }

    public static Throwable getRootCause(Throwable cause) {
        if (cause != null) {
            Throwable source = cause.getCause();
            if (source != null) {
                return getRootCause(source);
            } else {
                return cause;
            }
        }
        return null;
    }

    public static boolean isValidationFailed() {
        return FacesContext.getCurrentInstance().isValidationFailed();
    }

    public static boolean isDummySelectItem(UIComponent component, String value) {
        for (UIComponent children : component.getChildren()) {
            if (children instanceof UISelectItem) {
                UISelectItem item = (UISelectItem) children;
                if (item.getItemValue() == null && item.getItemLabel().equals(value)) {
                    return true;
                }
                break;
            }
        }
        return false;
    }

    public static String getComponentMessages(String clientComponent, String defaultMessage) {
        FacesContext fc = FacesContext.getCurrentInstance();
        UIComponent component = UIComponent.getCurrentComponent(fc).findComponent(clientComponent);
        if (component instanceof UIInput) {
            UIInput inputComponent = (UIInput) component;
            if (inputComponent.isValid()) {
                return defaultMessage;
            } else {
                Iterator<FacesMessage> iter = fc.getMessages(inputComponent.getClientId());
                if (iter.hasNext()) {
                    return iter.next().getDetail();
                }
            }
        }
        return "";
    }

    /**
     * Metodo para encriptar un texto
     *
     * @param String texto
     * @return String texto encriptado
     */
    private String encriptar(String texto) {
        String value = "";
        try {
            cipher = Cipher.getInstance(algoritmo);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] textobytes = texto.getBytes();
            byte[] cipherbytes = cipher.doFinal(textobytes);
            value = new BASE64Encoder().encode(cipherbytes);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            System.err.println(ex.getMessage());
        }
        return value;
    }

    /**
     * Metodo para desencriptar un texto
     *
     * @param texto Texto encriptado
     * @return String texto desencriptado
     */
    private String desencriptar(String texto) {
        String str = "";
        try {
            byte[] value = new BASE64Decoder().decodeBuffer(texto);
            cipher = Cipher.getInstance(algoritmo);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] cipherbytes = cipher.doFinal(value);
            str = new String(cipherbytes);
        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | IOException | NoSuchAlgorithmException | NoSuchPaddingException ex) {
            System.err.println(ex.getMessage());
        }
        return str;
    }

    public static String encriptaDato(String args) {
        JsfUtil ML = new JsfUtil();
        ML.addKey("NeptunoSG20180224");

        String Encripta = args;
        String encriptar = ML.encriptar(Encripta);
        String codificado = Base64.encode(encriptar.getBytes());

        return codificado;
    }

    public static String desencriptaDato(String args) {
        JsfUtil ML = new JsfUtil();
        ML.addKey("NeptunoSG20180224");

        byte[] decodificado = Base64.decode(args);
        String desencriptar = ML.desencriptar(new String(decodificado));

        return desencriptar;
    }

    public static void updateView(String update) {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update(update);
    }

    /**
     * Crea la Llave para encriptar/desencriptar
     *
     * @param String value
     */
    private void addKey(String value) {
        byte[] valuebytes = value.getBytes();
        key = new SecretKeySpec(Arrays.copyOf(valuebytes, keysize), algoritmo);
    }
    
    /**
     * @param mensaje
     * @param typeMsg
     * @param updateForm
     * @Descripcion Muestra un mensaje y actualiza el contenedor
     * @autor Johann Agamez
     * @Fecha creación: 23/09/2014
     * @Fecha Modificación: 23/09/2014
     */
    public static void showMessage(String mensaje, FacesMessage.Severity typeMsg, String updateForm) {
        FacesMessage msg = new FacesMessage(typeMsg, "www.neptunosg.com", mensaje);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        RequestContext.getCurrentInstance().update(updateForm);
    }
    
    /**
     * @param mensaje
     * @param typeMsg
     * @Descripcion Muestra un mensaje y actualiza el contenedor
     * @autor Johann Agamez
     * @Fecha creación: 23/09/2014
     * @Fecha Modificación: 23/09/2014
     */
    public static void showMessage(String mensaje, FacesMessage.Severity typeMsg) {
        FacesMessage msg = new FacesMessage(typeMsg, "www.neptunosg.com", mensaje);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /**
     * @param dialog
     * @Descripcion Muestra dialogo de confirmación
     * @autor Johann Agamez
     * @Fecha creación: 23/09/2014
     * @Fecha Modificación: 23/09/2014
     */
    public static void showDialog(String dialog) {
        RequestContext.getCurrentInstance().execute("PF('" + dialog + "').show()");
    }

}
