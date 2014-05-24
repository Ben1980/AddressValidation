package application;

import javax.swing.SwingUtilities;

import domain.Contact;
import domain.ContactStore;
import view.ContactListMasterDetailFrame;

public class Application {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					ContactStore contactStore = new ContactStore();
					
					ContactListMasterDetailFrame window = new ContactListMasterDetailFrame(contactStore);
					
					ContactListMasterDetailFrame window2 = new ContactListMasterDetailFrame(contactStore);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
