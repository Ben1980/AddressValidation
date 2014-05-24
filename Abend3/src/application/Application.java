package application;

import javax.swing.SwingUtilities;

import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;

import domain.Contact;
import domain.ContactStore;
import view.ContactListMasterDetailFrame;

public class Application {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					ContactStore contactStore = new ContactStore();
					//sample contact
					addSampleDataToStore(contactStore);
										
					ContactListMasterDetailFrame window = new ContactListMasterDetailFrame(contactStore);
					
					ContactListMasterDetailFrame window2 = new ContactListMasterDetailFrame(contactStore);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		
	}
	
	private static void addSampleDataToStore(ContactStore contactStore){
		addSampleContact(contactStore, "Ghostreiter", "Robin", "robin.ghostreiter@test.ch", "007007007");
		addSampleContact(contactStore, "Master", "Ben", "ben.master@test.ch", "008008008");
		addSampleContact(contactStore, "Slave", "Raffi", "raffi.slave@test.ch", "009009009");
		
	}
	
	private static void addSampleContact(ContactStore contactStore, String lastName, String firstName, String email, String tel){
		Contact newContact = contactStore.getNewContact();
		newContact.setFirstName(firstName);
		newContact.setLastName(lastName);
		newContact.setEmail(email);
		newContact.setTelNr(tel);
	}
}
