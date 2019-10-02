package org.ashkan.view;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


public class UserLoginView {
	private String username;
	private String password;

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	private boolean loggedIn;

	public UserLoginView()
	{
		username=null;
		password=null;
		loggedIn=false;
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", username);

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void login(ActionEvent event) {
		FacesMessage message = null;

		if(username != null && username.equals("admin") && password != null && password.equals("admin")) {
			loggedIn = true;
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", username);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", "admin");

			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("app/baxter.jsf");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			loggedIn = false;
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Invalid credentials");
			FacesContext.getCurrentInstance().addMessage(null, message);

		}

		//		context.addCallbackParam("loggedIn", loggedIn);
	}
	public void logout() {
		System.out.println("Signing out...");
		if(this.loggedIn)
		{
			this.loggedIn = false;
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("user");
			try {
				FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
				FacesContext.getCurrentInstance().getExternalContext().redirect("/Demo/login.jsf");
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		System.out.println("Error! You're not Logged In!");

	}


}
