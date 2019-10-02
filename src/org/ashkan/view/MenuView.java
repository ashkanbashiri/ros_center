package org.ashkan.view;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MenuView {
	public void sequential() {
		addMessage("Success", "Sequential Movements Selected");
	}

	public void circular() {
		addMessage("Success", "Sequential Movements Selected");
	}

	public void conditional() {
		addMessage("Success", "Conditional Movements Selected");
	}

	public void addMessage(String summary, String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}
