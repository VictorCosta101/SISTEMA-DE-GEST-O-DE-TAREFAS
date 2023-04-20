package br.com.esig.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Message  {

	public static void info(String texto) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, texto, null );
		FacesContext.getCurrentInstance().addMessage(null, message);

	}
	
	public static void erro(String texto) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, texto, null );
		FacesContext.getCurrentInstance().addMessage(null, message);

	}
	
	public static void warn(String texto) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, texto, null );
		FacesContext.getCurrentInstance().addMessage(null, message);

	}
	
	
	
}
