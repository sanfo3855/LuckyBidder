package com.luckybidder.client;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Login extends HorizontalPanel {
	private TextBox tUsername;
	private TextBox tPassword;
	private Button btnAccedi;
	private Button btnRegistra;
	private Grid main;
	
	
	String SESSION;
	
	
	public Login()  {
		final HorizontalPanel horizontalpanel = new HorizontalPanel();
		this.add(horizontalpanel);
		horizontalpanel.setSize("100%", "100%");
		main = new Grid(3, 3);
		horizontalpanel.add(main);
		main.setSize("100%", "100%");
		
		Label labelUser = new Label("Username");
		main.setWidget(0, 0, labelUser);
		tUsername = new TextBox();
		main.setWidget(0, 1, tUsername);
		tUsername.setSize("160px", "17px");
		
	
		Label labelPassword = new Label("Password ");
		tPassword = new PasswordTextBox();
		tPassword.setWidth("150px");
		Label requiredPassword = new Label("(*)");
		main.setWidget(1, 0, labelPassword);
		main.setWidget(1, 1, tPassword);
		tPassword.setSize("160px", "17px");
		
	}
	
	
}
