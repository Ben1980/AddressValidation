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
	private String _userName;
	
	public ContactListCellRenderer(String userName){
		setOpaque(/*isOpaque*/true);
		_userName = userName;
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
		setText(formatContact(value));		
		
		setStyles(isSelected, Color.DARK_GRAY, Color.WHITE);
		setStyles(!isSelected, Color.WHITE, Color.BLACK);
		
		
		return this;
	}
	
	private String formatContact(Contact contact){
		String contactText = "";
		if(contact.getLastName() != null && contact.getLastName().length() > 0)
		{
			contactText = contactText + contact.getLastName() + " ";   
	    }
		
		if(contact.getFirstName() != null){
			contactText = contactText + contact.getFirstName();
		}
		
		contactText += getLockedByText(contact);		
		
		return contactText;
	}

	private String getLockedByText(Contact contact) {
		String contactText = "";
		
		if(contact.isLocked())
		{
			contactText += " *locked [" 
							+ (contact.isLockedByAnotherUser(_userName)
							? contact.getLockedByUserName() + "]" 
							  : "by you]");				
		}
		return contactText;
	}
	
	private void setStyles(boolean setStyle, Color backGround, Color foreGround){
		if(!setStyle){
			return;
		}
		setBackground(backGround);
		setForeground(foreGround);
	}

}
