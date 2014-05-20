package domain;

import java.util.Observable;

public class Contact extends Observable {
	
	private String lastName;
	private String firstName;
	private String email;
	private String telNr;
			
	public String getTelNr() {
		return telNr;		
	}
	
	public void setTelNr(String telNr) {
		String oldValue = this.telNr;
		this.telNr = telNr;
		setChanged(oldValue, telNr, "telNr");		
	}
	
	
	private void setChanged(String oldValue, String newValue, String changedField) {
		if(oldValue == null){
			oldValue = "";
		}
		
		if(!oldValue.equals(newValue)){
			setChanged();
			notifyObservers(changedField);
		}
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		String oldValue = this.email;		
		this.email = email;
		setChanged(oldValue, email, "email");
	}
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {		
		String oldValue = this.firstName;		
		this.firstName = firstName;
		setChanged(oldValue, firstName, "firstName");

	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		String oldValue = this.lastName;
		this.lastName = lastName;
		setChanged(oldValue, lastName, "lastName");
	}	 	
}
