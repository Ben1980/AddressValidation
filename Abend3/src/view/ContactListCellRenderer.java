package view;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import viewModels.ContactStoreJListModel;
import domain.Contact;
//import domain.ContactStore;

public class ContactListCellRenderer implements ListCellRenderer<Contact> {

	private JLabel _label = new JLabel();
//	private ContactStore _contactStore;
//	
//	public ContactListCellRenderer(ContactStore contactStore) {
//		_contactStore = contactStore;
//	}
	
	@Override
	public Component getListCellRendererComponent(
			JList<? extends Contact> list, Contact value, int index,
			boolean isSelected, boolean cellHasFocus) {
		// TODO Auto-generated method stub
		_label.setText(value.getLastName());
		_label.setBackground(Color.RED);
		_label.setForeground(Color.WHITE);		
		return _label;
	}

}
