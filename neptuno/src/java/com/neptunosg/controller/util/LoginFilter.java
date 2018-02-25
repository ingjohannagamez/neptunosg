package com.neptunosg.controller.util;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Filter checks if user is logged. If it is not set then request is being
 * redirected to the 403 error page.
 *
 */
public class LoginFilter implements Filter {

    @Inject
    private transient Session sesion;

    /**
     * Checks if user is logged in. If not sends a 403 error.
     *
     * @param request
     * @param response
     * @param chain
     * @throws java.io.IOException
     * @throws javax.servlet.ServletException
     */
    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws ServletException, IOException {
        final String contextPath = ((HttpServletRequest) request).getContextPath();
        final String uri = ((HttpServletRequest) request).getRequestURI();
        if (uri.equals(contextPath + "/")) {
            //System.out.println("Entrando a una página de inicio");
            chain.doFilter(request, response);
        } else if (uri.startsWith(contextPath.concat("/sistemadegestion/")) || uri.startsWith(contextPath.concat("/resources/img/"))) {
            String permiso = uri.substring(contextPath.concat("/sistemadegestion/").length());
            final String enlace = cleanUrl(permiso, ".xhtml");
            permiso = cleanUrl(enlace, "/index");
            if ("app/login".equals(permiso)
                    || permiso.contains(".css")
                    || permiso.contains(".jpg")
                    || permiso.contains(".png")
                    || permiso.contains(".gif")
                    || permiso.startsWith("javax.faces.resource/")
                    || permiso.startsWith("javax.sistemadegestion.resource/")
                    || permiso.startsWith("login.xhtml;jsessionid=")
                    || sesion.permisos(permiso)) {
                chain.doFilter(request, response);
            } else {
                ((HttpServletResponse) response).sendRedirect(contextPath + "/sistemadegestion/app/login/index.xhtml");
            }
        } else {
            ((HttpServletResponse) response).sendRedirect(contextPath + "/sistemadegestion/app/login/index.xhtml");
        }
    }

    private String cleanUrl(final String texto, final String pattern) {
        return texto.endsWith(pattern) ? texto.substring(0, texto.length() - pattern.length()) : texto;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {

    }

}
