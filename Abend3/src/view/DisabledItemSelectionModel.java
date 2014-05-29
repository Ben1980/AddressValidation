package view;

import javax.swing.DefaultListSelectionModel;

import domain.Contact;
import domain.ContactStore;

public class DisabledItemSelectionModel extends DefaultListSelectionModel {

    private static final long serialVersionUID = 1L;
    private ContactStore _contactStore;
	private String _userName;
    
    public DisabledItemSelectionModel(ContactStore contactStore, String userName){
    	_contactStore = contactStore;
    	_userName = userName;
    }

    @Override
    public void setSelectionInterval(int index0, int index1) {
    	
    	if(_contactStore.hasNoLockedItemsByUser(_userName)){
    		super.setSelectionInterval(index0, index0);
   	}
//    	if (_contactStore.getContact(index0).isLockedByAnotherUser(_userName)) {
//    		/*
//             * The previously selected index is before this one,
//             * so walk forward to find the next selectable item.
//             */
//            if (getAnchorSelectionIndex() < index0) {
//                for (int i = index0; i < _contactStore.getLength(); i++) {
//                    if (!_contactStore.getContact(i).isLockedByAnotherUser(_userName)) {
//                        super.setSelectionInterval(i, i);
//                        return;
//                    }
//                }
//            } /*
//             * Otherwise, walk backward to find the next selectable item.
//             */ else {
//                for (int i = index0; i >= 0; i--) {
//                    if (!_contactStore.getContact(i).isLockedByAnotherUser(_userName)) {
//                        super.setSelectionInterval(i, i);
//                        return;
//                    }
//                }
//            }
//            
//        } else {
//        	super.setSelectionInterval(index0, index0);
//        }
    }
}
