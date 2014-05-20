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
					ContactStore newContactStore= new ContactStore();
					
					ContactListMasterDetailFrame window = new ContactListMasterDetailFrame(newContactStore);					
					ContactListMasterDetailFrame window2 = new ContactListMasterDetailFrame(newContactStore);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
