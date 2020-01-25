/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neptunosg.ws;

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
     * @param usuario
     * @param contrasena
     * @param empresa
     * @return 
     */
    @WebMethod(operationName = "login")
    public String login(@WebParam(name = "usuario") final String usuario, 
                        @WebParam(name = "contrasena") final String contrasena, 
                        @WebParam(name = "empresa") final String empresa) {
        //TODO write your implementation code here:
        return "ENTRO";
    }
}
