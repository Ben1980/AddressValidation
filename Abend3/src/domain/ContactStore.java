package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ContactStore extends Observable implements Observer {
	
	private List<Contact> contactList = new ArrayList<Contact>();
	private int editedContactIndex;
	private int addContactIndex;
	private int removeContactIndex;
	private int contactCounter = 0;
	
	//creates new contact and adds to list.
	public Contact getNewContact(){
		Contact newContact = new Contact();
		newContact.setContactId(++contactCounter);
		addContact(newContact);
		return newContact;
	}

	public void addContact(Contact newContact) {
		newContact.addObserver(this);
		contactList.add(newContact);

		addContactIndex=contactList.indexOf(newContact);
		editedContactIndex=-1;
		removeContactIndex=-1;

		doNotify();
	}
	
	public boolean isEmpty(){
		return contactList.size() == 0;
	}
	
	
	private void doNotify() {
		setChanged();
		notifyObservers();
	}

	@Override
	public void update(Observable contact, Object arg1) {
		
		editedContactIndex=contactList.indexOf(contact);	
		addContactIndex=-1;
		removeContactIndex=-1;

		setChanged();
		notifyObservers(contact);
	}
	
	public Contact getContact(int index){
		if (index > contactList.size()){
			index=contactList.size()-1;
		}
		return contactList.get(index);
	}

	public void removeContactAt(int index){
		Contact remContact = contactList.get(index);
		remContact.deleteObserver(this);
		contactList.remove(index);
		doNotify();
	}

	public boolean removeContact(Contact contact){
		contact.deleteObserver(this);
		boolean succeeded = contactList.remove(contact);

		removeContactIndex=contactList.indexOf(contact);
		editedContactIndex=-1;
		addContactIndex=-1;

		doNotify();
		return succeeded;
	}

	public int getLength(){
		return contactList.size();
	}
	
	
	public int getEditedContactPos() {
		return editedContactIndex;
	}

	public int getInsertedContactIndex() {
		return addContactIndex;
	}

	public int getRemovedContactIndex() {
		return removeContactIndex;
	}
	public int getContactIndex(Contact contact) {
		return contactList.indexOf(contact);
	}
	
	public List<Contact> getContacts(){
		return contactList;
	}


}
