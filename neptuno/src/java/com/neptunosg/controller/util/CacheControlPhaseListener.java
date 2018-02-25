package com.neptunosg.controller.util;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author JohannAndres
 */
public class CacheControlPhaseListener implements PhaseListener {

    private static final long serialVersionUID = 7223747336703314035L;

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RENDER_RESPONSE;
    }

    @Override
    public void afterPhase(final PhaseEvent event) {
        //TODO CODE HERE
    }

    @Override
    public void beforePhase(final PhaseEvent event) {
        final HttpServletResponse response = (HttpServletResponse) event.getFacesContext().getExternalContext().getResponse();
        response.addHeader("Pragma", "no-cache");
        response.addHeader("Cache-Control", "no-cache");
        response.addHeader("Cache-Control", "no-store");
        response.addHeader("Cache-Control", "must-revalidate");
        response.addHeader("Expires", "0");
    }
    
}
