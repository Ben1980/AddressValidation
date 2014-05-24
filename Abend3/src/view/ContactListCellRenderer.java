package view;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import viewModels.ContactStoreJListModel;
import domain.Contact;
//import domain.ContactStore;

public class ContactListCellRenderer extends JLabel implements ListCellRenderer<Contact> {

	public ContactListCellRenderer(){
		setOpaque(/*isOpaque*/true);
	}
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
		setText(value.getLastName());		
		
		setStyles(isSelected, Color.RED, Color.WHITE);
		setStyles(!isSelected, Color.WHITE, Color.BLACK);
		
		
		return this;
	}
	
	private void setStyles(boolean setStyle, Color backGround, Color foreGround){
		if(!setStyle){
			return;
		}
		setBackground(backGround);
		setForeground(foreGround);
	}

}
