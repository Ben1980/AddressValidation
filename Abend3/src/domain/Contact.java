package domain;

import java.util.Observable;

public class Contact extends Observable {
	
	private String lastName;
	private String firstName;
	private String email;
	private String telNr;
	
	public Contact(){
		
	}
	
	//makes a copy of the contact (lastname, firstname, email, telnr)
	public Contact(Contact toCopyContact){
		this.lastName = toCopyContact.lastName;
		this.firstName = toCopyContact.firstName;
		this.email = toCopyContact.email;
		this.telNr = toCopyContact.telNr;
	}
	
	public Contact Copy(){
		return new Contact(this);
	}
	
	public static boolean AreSameContact(Contact firstToCompare, Contact secondToCompare){
		if((firstToCompare == null) && (secondToCompare == null)){
			return true;
		}
		
		if((firstToCompare == null) || (secondToCompare == null)){
			return false;
		}
		
		return (firstToCompare.firstName == secondToCompare.firstName)
				&& (firstToCompare.lastName == secondToCompare.lastName)
				&& (firstToCompare.email == secondToCompare.email)
				&& (firstToCompare.telNr == secondToCompare.telNr);
	}
	
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
