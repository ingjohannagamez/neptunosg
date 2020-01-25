package com.neptunosg.ws;

import com.neptunosg.controller.*;
import com.neptunosg.entity.Acceso;
import com.neptunosg.entity.Empresa;
import com.neptunosg.entity.Oficina;
import com.neptunosg.entity.Usuario;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author JohannAndres
 *
 * http://localhost:8080/NeptunoSGWebService/NeptunoSGWebService?wsdl
 */
@WebService(serviceName = "NeptunoSGWebService")
@Stateless()
public class NeptunoSGWebService {

    /**
     * Web service operation login
     *
     * @param usuario
     * @param contrasena
     * @param empresa
     * @return
     */
    @WebMethod(operationName = "login")
    public String login(@WebParam(name = "usuario") final String usuario,
            @WebParam(name = "contrasena") final String contrasena,
            @WebParam(name = "empresa") final Integer empresa) {

        LoginController loginController = new LoginController();

        Acceso acceso = new Acceso();

        acceso.setIdeusr(new Usuario());
        acceso.getIdeusr().setIdeofi(new Oficina());
        acceso.getIdeusr().getIdeofi().setIdeemp(new Empresa());

        acceso.setNikacc(usuario);
        acceso.setPswacc(contrasena);
        acceso.getIdeusr().getIdeofi().getIdeemp().setIdeemp(empresa);

        return loginController.validarAccesoWS(acceso);
    }
}
