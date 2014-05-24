package viewModels;


import javax.swing.AbstractListModel;
//import javax.swing.event.ListDataListener;

import domain.Contact;
import domain.ContactStore;

public class ContactStoreJListModel extends AbstractListModel<Contact> {

	private static final long serialVersionUID = 1L;
	private ContactStore _contactStore;

	public ContactStoreJListModel(ContactStore contactStore){
		_contactStore = contactStore;
	}
	
	@Override
	public Contact getElementAt(int index) {
		
		return _contactStore.getContact(index);
	}

	@Override
	public int getSize() {
		
		return _contactStore.getLength();
	}



}
