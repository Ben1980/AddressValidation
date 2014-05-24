package application;

import javax.swing.SwingUtilities;

import domain.Contact;
import view.ContactListMasterDetailFrame;

public class Application {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					Contact newContact = new Contact();
					
					ContactListMasterDetailFrame window = new ContactListMasterDetailFrame(newContact);
					
					ContactListMasterDetailFrame window2 = new ContactListMasterDetailFrame(newContact);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
