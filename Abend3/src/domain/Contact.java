package domain;

import java.util.Observable;
import java.util.logging.Logger;

public class Contact extends Observable {

	private String lastName;
	private String firstName;
	private String email;
	private String telNr;
	private String notes;
	private int contactId;
	private String lockedBy = null;

	private static Contact emptyContact = new Contact();

	public Contact() {

	}

	// makes a copy of the contact (lastname, firstname, email, telnr)
	public Contact(Contact toCopyContact) {
		this.lastName = toCopyContact.lastName;
		this.firstName = toCopyContact.firstName;
		this.email = toCopyContact.email;
		this.telNr = toCopyContact.telNr;
		this.notes = toCopyContact.notes;
		this.contactId = toCopyContact.contactId;
	}

	public Contact Copy() {
		return new Contact(this);
	}

	public static Contact getEmptyContact() {
		return emptyContact;
	}

	public boolean isEmpty() {
		return this == emptyContact;
	}

	public static boolean AreSameContact(Contact firstToCompare,
			Contact secondToCompare) {
		if ((firstToCompare == null) && (secondToCompare == null)) {
			return true;
		}

		if ((firstToCompare == null) || (secondToCompare == null)) {
			return false;
		}

		return (firstToCompare.firstName == secondToCompare.firstName)
				&& (firstToCompare.lastName == secondToCompare.lastName)
				&& (firstToCompare.email == secondToCompare.email)
				&& (firstToCompare.telNr == secondToCompare.telNr)
				&& (firstToCompare.contactId == secondToCompare.contactId)
				&& (firstToCompare.notes == secondToCompare.notes);

	}

	public String getTelNr() {
		return telNr;
	}

	public void setTelNr(String telNr) {
		String oldValue = this.telNr;
		this.telNr = telNr;
		setChanged(oldValue, telNr, "telNr");
	}

	private void setChanged(String oldValue, String newValue,
			String changedField) {
		if (oldValue == null) {
			oldValue = "";
		}

		if (!oldValue.equals(newValue)) {
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

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		String oldValue = this.notes;
		this.notes = notes;
		setChanged(oldValue, notes, "notes");

	}

	public int getContactId() {
		return contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;

	}

	public Boolean tryLock(String userName) {
		if (isEmpty() || isLockedByAnotherUser(userName)) {
			return false;
		}
		String oldValue = this.lockedBy;
		this.lockedBy = userName;
		setChanged(oldValue, userName, "lock");
		return true;
	}

	public Boolean UnLock(String userName) {
		if (isLockedByAnotherUser(userName)) {
			return false;
		}

		String oldValue = this.lockedBy;
		this.lockedBy = null;
		setChanged(oldValue, null, "lock");
		return true;
	}

	public String getLockedByUserName() {
		return this.lockedBy;
	}

	public boolean isLocked() {
		return !isEmpty() && (this.lockedBy != null);
	}

	public boolean isLockedByAnotherUser(String userName) {

		return isLocked() && (this.lockedBy.compareTo(userName) != 0);
	}
}
