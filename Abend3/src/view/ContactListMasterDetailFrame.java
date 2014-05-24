package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;

import util.SimpleValidator;
import viewModels.ContactStoreJListModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.SwingConstants;

import domain.Contact;
import domain.ContactStore;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Window;
import java.sql.BatchUpdateException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.GridLayout;
import javax.swing.Box;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;


public class ContactListMasterDetailFrame implements Observer {

	private JFrame frame;
	private JTextField nameField;	
	private JLabel namesErrorLabel;
	private JButton saveButton;
	private JTextField vornameField;
	private JTextField emailField;
	private JTextField telfonField;
	private JLabel mailError;
	private JLabel telefonError;
	private ContactStore _contactStore;
	private JPanel ContactListJPanel;
	private JList<Contact> _contactJList;
	private Contact _selectedContact;
	private Contact _copySelectedContact;
	private JTextField txtSearch;
	private JButton btnRemove;

	private JTextField getNameField()
	{
		return nameField;
	}
	private JTextField getFirstNameField()
	{
		return vornameField; 
	}
	private JTextField getEMailField()
	{
		return emailField;
	}
	private JTextField getTelNrField()
	{
		return telfonField;
	}	 
	
	private JLabel getNamesErrorLabel()
	{
		return namesErrorLabel;
	}
	private JLabel getEMailErrorLabel()
	{
		return mailError;
	} 
	
	private JLabel getTelNrErrorLabel()
	{
		return telefonError;
	}	 

	/**
	 * Create the application.
	 */
	public ContactListMasterDetailFrame  (ContactStore contactStore) {
		_contactStore = contactStore;
		_contactStore.addObserver(this);
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		//TODO: change dimension size!
		frame.setMinimumSize(new Dimension(650, 300));
		frame.setBounds(100, 100, 846, 549);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		FocusListener saveCecker =  new CheckSaveableFocusListener();
			
		ImageIcon errorIcon = new ImageIcon(ContactListMasterDetailFrame.class.getResource("/com/sun/java/swing/plaf/windows/icons/Error.gif"));
		frame.getContentPane().setLayout(new GridLayout(0, 2, 0, 0));
		
		ContactListJPanel = new JPanel();
		ContactListJPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		frame.getContentPane().add(ContactListJPanel);
		GridBagLayout gbl_ContactListJPanel = new GridBagLayout();
		gbl_ContactListJPanel.columnWidths = new int[]{71, 61, 89, 71, 71, 0};
		gbl_ContactListJPanel.rowHeights = new int[]{46, 497, 40, 0};
		gbl_ContactListJPanel.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_ContactListJPanel.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		ContactListJPanel.setLayout(gbl_ContactListJPanel);
		
		txtSearch = new JTextField();
		txtSearch.setText("Search...");
		GridBagConstraints gbc_txtSearch = new GridBagConstraints();
		gbc_txtSearch.gridwidth = 4;
		gbc_txtSearch.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSearch.insets = new Insets(0, 10, 10, 5);
		gbc_txtSearch.gridx = 0;
		gbc_txtSearch.gridy = 0;
		ContactListJPanel.add(txtSearch, gbc_txtSearch);
		txtSearch.setColumns(10);
		
		_contactJList = new JList<Contact>();
		_contactJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		GridBagConstraints gbc__contactJList = new GridBagConstraints();
		gbc__contactJList.fill = GridBagConstraints.BOTH;
		gbc__contactJList.insets = new Insets(10, 10, 5, 0);
		gbc__contactJList.gridwidth = 5;
		gbc__contactJList.gridx = 0;
		gbc__contactJList.gridy = 1;
		ContactListJPanel.add(_contactJList, gbc__contactJList);
		initializeContactList(getContactjList());
		
		JButton btnAdd = new JButton("Add");
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.insets = new Insets(0, 0, 10, 5);
		gbc_btnAdd.gridx = 1;
		gbc_btnAdd.gridy = 2;
		ContactListJPanel.add(btnAdd, gbc_btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				addButtonPressed();
			}
		});		
		
		btnRemove = new JButton("Remove");
		GridBagConstraints gbc_btnRemove = new GridBagConstraints();
		gbc_btnRemove.insets = new Insets(0, 0, 10, 5);
		gbc_btnRemove.gridx = 2;
		gbc_btnRemove.gridy = 2;
		ContactListJPanel.add(btnRemove, gbc_btnRemove);
		btnRemove.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				removeButtonPressed();
			}
		});
		
		JButton btnUndo = new JButton("Undo");
		GridBagConstraints gbc_btnUndo = new GridBagConstraints();
		gbc_btnUndo.insets = new Insets(0, 0, 10, 5);
		gbc_btnUndo.gridx = 3;
		gbc_btnUndo.gridy = 2;
		ContactListJPanel.add(btnUndo, gbc_btnUndo);
		
		JButton btnRemove_1 = new JButton("Redo");
		GridBagConstraints gbc_btnRemove_1 = new GridBagConstraints();
		gbc_btnRemove_1.insets = new Insets(0, 0, 10, 0);
		gbc_btnRemove_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRemove_1.gridx = 4;
		gbc_btnRemove_1.gridy = 2;
		ContactListJPanel.add(btnRemove_1, gbc_btnRemove_1);
		
		JPanel ContactDetailJPanel = new JPanel();
		ContactDetailJPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		frame.getContentPane().add(ContactDetailJPanel);
		GridBagLayout gbl_ContactDetailJPanel = new GridBagLayout();
		gbl_ContactDetailJPanel.columnWidths = new int[]{87, 88, 131, 70, 0};
		gbl_ContactDetailJPanel.rowHeights = new int[]{17, 19, 19, 32, 32, 15, 315, 25, 0};
		gbl_ContactDetailJPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_ContactDetailJPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		ContactDetailJPanel.setLayout(gbl_ContactDetailJPanel);
		
		JLabel lblKontaktDetails = new JLabel("Contact Details");
		GridBagConstraints gbc_lblKontaktDetails = new GridBagConstraints();
		gbc_lblKontaktDetails.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblKontaktDetails.insets = new Insets(0, 0, 5, 5);
		gbc_lblKontaktDetails.gridwidth = 2;
		gbc_lblKontaktDetails.gridx = 1;
		gbc_lblKontaktDetails.gridy = 0;
		ContactDetailJPanel.add(lblKontaktDetails, gbc_lblKontaktDetails);
		lblKontaktDetails.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblName = new JLabel("Surname:");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblName.insets = new Insets(0, 5, 5, 5);
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 1;
		ContactDetailJPanel.add(lblName, gbc_lblName);
				
				nameField = new JTextField();
				GridBagConstraints gbc_nameField = new GridBagConstraints();
				gbc_nameField.anchor = GridBagConstraints.NORTH;
				gbc_nameField.fill = GridBagConstraints.HORIZONTAL;
				gbc_nameField.insets = new Insets(0, 0, 5, 5);
				gbc_nameField.gridwidth = 2;
				gbc_nameField.gridx = 1;
				gbc_nameField.gridy = 1;
				ContactDetailJPanel.add(nameField, gbc_nameField);
				nameField.addFocusListener(saveCecker);
				nameField.addFocusListener(new FocusAdapter() {

					@Override
					public void focusLost(FocusEvent e) {
						setContactLastName();
					}	
					
				});
				nameField.setColumns(10);
		
				namesErrorLabel = new JLabel(errorIcon);
				GridBagConstraints gbc_namesErrorLabel = new GridBagConstraints();
				gbc_namesErrorLabel.anchor = GridBagConstraints.NORTH;
				gbc_namesErrorLabel.fill = GridBagConstraints.HORIZONTAL;
				gbc_namesErrorLabel.insets = new Insets(0, 0, 5, 0);
				gbc_namesErrorLabel.gridheight = 2;
				gbc_namesErrorLabel.gridx = 3;
				gbc_namesErrorLabel.gridy = 1;
				ContactDetailJPanel.add(namesErrorLabel, gbc_namesErrorLabel);
				namesErrorLabel.setVisible(false);
				namesErrorLabel.setToolTipText("Vorname und Nachmame duerfen nicht beide leer sein");
		
		JLabel lblNewLabel = new JLabel("First Name");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel.insets = new Insets(0, 5, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 2;
		ContactDetailJPanel.add(lblNewLabel, gbc_lblNewLabel);
		
		vornameField = new JTextField();
		GridBagConstraints gbc_vornameField = new GridBagConstraints();
		gbc_vornameField.anchor = GridBagConstraints.NORTH;
		gbc_vornameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_vornameField.insets = new Insets(0, 0, 5, 5);
		gbc_vornameField.gridwidth = 2;
		gbc_vornameField.gridx = 1;
		gbc_vornameField.gridy = 2;
		ContactDetailJPanel.add(vornameField, gbc_vornameField);
		vornameField.addFocusListener(saveCecker);
		vornameField.addFocusListener(new FocusAdapter() {
			@Override			
			public void focusLost(FocusEvent arg0){
				setContactFirstName();
			}
		});
		vornameField.setColumns(10);
		
		JLabel lblEmail = new JLabel("eMail:");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.SOUTH;
		gbc_lblEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblEmail.insets = new Insets(0, 5, 5, 5);
		gbc_lblEmail.gridx = 0;
		gbc_lblEmail.gridy = 3;
		ContactDetailJPanel.add(lblEmail, gbc_lblEmail);
		
		emailField = new JTextField();
		GridBagConstraints gbc_emailField = new GridBagConstraints();
		gbc_emailField.anchor = GridBagConstraints.SOUTH;
		gbc_emailField.fill = GridBagConstraints.HORIZONTAL;
		gbc_emailField.insets = new Insets(0, 0, 5, 5);
		gbc_emailField.gridwidth = 2;
		gbc_emailField.gridx = 1;
		gbc_emailField.gridy = 3;
		ContactDetailJPanel.add(emailField, gbc_emailField);
		emailField.addFocusListener(saveCecker);
		emailField.addFocusListener(new FocusAdapter() {
			@Override			
			public void focusLost(FocusEvent arg0){
				setContactEmail();
			}
		});
		emailField.setColumns(10);
		
		mailError = new JLabel("");
		GridBagConstraints gbc_mailError = new GridBagConstraints();
		gbc_mailError.anchor = GridBagConstraints.NORTH;
		gbc_mailError.fill = GridBagConstraints.HORIZONTAL;
		gbc_mailError.insets = new Insets(0, 0, 5, 0);
		gbc_mailError.gridx = 3;
		gbc_mailError.gridy = 3;
		ContactDetailJPanel.add(mailError, gbc_mailError);
		mailError.setVisible(false);
		mailError.setHorizontalAlignment(SwingConstants.CENTER);
		mailError.setToolTipText("eMail darf nicht leer sein");
		mailError.setIcon(new ImageIcon(ContactListMasterDetailFrame.class.getResource("/com/sun/java/swing/plaf/windows/icons/Error.gif")));
		
		JLabel lblTelefon = new JLabel("Telephone:");
		GridBagConstraints gbc_lblTelefon = new GridBagConstraints();
		gbc_lblTelefon.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblTelefon.insets = new Insets(0, 5, 5, 5);
		gbc_lblTelefon.gridx = 0;
		gbc_lblTelefon.gridy = 4;
		ContactDetailJPanel.add(lblTelefon, gbc_lblTelefon);
		
		telfonField = new JTextField();
		GridBagConstraints gbc_telfonField = new GridBagConstraints();
		gbc_telfonField.anchor = GridBagConstraints.SOUTH;
		gbc_telfonField.fill = GridBagConstraints.HORIZONTAL;
		gbc_telfonField.insets = new Insets(0, 0, 5, 5);
		gbc_telfonField.gridwidth = 2;
		gbc_telfonField.gridx = 1;
		gbc_telfonField.gridy = 4;
		ContactDetailJPanel.add(telfonField, gbc_telfonField);
		telfonField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				checkSaveable();
				setContactTelNr();
			}
		});
		telfonField.addFocusListener(saveCecker);
		telfonField.addFocusListener(new FocusAdapter() {
			@Override			
			public void focusLost(FocusEvent arg0){
				setContactTelNr();
			}
		});
		telfonField.setColumns(10);
			
			telefonError = new JLabel("");
			GridBagConstraints gbc_telefonError = new GridBagConstraints();
			gbc_telefonError.anchor = GridBagConstraints.NORTH;
			gbc_telefonError.fill = GridBagConstraints.HORIZONTAL;
			gbc_telefonError.insets = new Insets(0, 0, 5, 0);
			gbc_telefonError.gridx = 3;
			gbc_telefonError.gridy = 4;
			ContactDetailJPanel.add(telefonError, gbc_telefonError);
			telefonError.setVisible(false);
			telefonError.setHorizontalAlignment(SwingConstants.CENTER);
			telefonError.setToolTipText("Telefon darf nicht leer sein");
			telefonError.setIcon(new ImageIcon(ContactListMasterDetailFrame.class.getResource("/com/sun/java/swing/plaf/windows/icons/Error.gif")));
			
			JLabel lblNotizen = new JLabel("Notes:");
			GridBagConstraints gbc_lblNotizen = new GridBagConstraints();
			gbc_lblNotizen.anchor = GridBagConstraints.NORTH;
			gbc_lblNotizen.fill = GridBagConstraints.HORIZONTAL;
			gbc_lblNotizen.insets = new Insets(0, 5, 5, 5);
			gbc_lblNotizen.gridx = 0;
			gbc_lblNotizen.gridy = 5;
			ContactDetailJPanel.add(lblNotizen, gbc_lblNotizen);
		
			JTextArea notesArea = new JTextArea();
			notesArea.setColumns(20);
			GridBagConstraints gbc_notesArea = new GridBagConstraints();
			gbc_notesArea.fill = GridBagConstraints.BOTH;
			gbc_notesArea.insets = new Insets(0, 5, 5, 10);
			gbc_notesArea.gridwidth = 4;
			gbc_notesArea.gridx = 0;
			gbc_notesArea.gridy = 6;
			ContactDetailJPanel.add(notesArea, gbc_notesArea);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		GridBagConstraints gbc_horizontalGlue = new GridBagConstraints();
		gbc_horizontalGlue.anchor = GridBagConstraints.SOUTH;
		gbc_horizontalGlue.fill = GridBagConstraints.HORIZONTAL;
		gbc_horizontalGlue.gridheight = 2;
		gbc_horizontalGlue.gridwidth = 2;
		gbc_horizontalGlue.gridx = 2;
		gbc_horizontalGlue.gridy = 6;
		ContactDetailJPanel.add(horizontalGlue, gbc_horizontalGlue);
		
		JButton btnCancel = new JButton("Cancel");
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnCancel.insets = new Insets(0, 5, 10, 5);
		gbc_btnCancel.gridx = 0;
		gbc_btnCancel.gridy = 7;
		ContactDetailJPanel.add(btnCancel, gbc_btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Nothing saved");
			}
		});
		
		saveButton = new JButton("Save");
		GridBagConstraints gbc_saveButton = new GridBagConstraints();
		gbc_saveButton.anchor = GridBagConstraints.NORTH;
		gbc_saveButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_saveButton.insets = new Insets(0, 0, 10, 5);
		gbc_saveButton.gridx = 1;
		gbc_saveButton.gridy = 7;
		ContactDetailJPanel.add(saveButton, gbc_saveButton);
		saveButton.setEnabled(false);
		
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Saved record for: "+getNameField().getText()+" "+getFirstNameField().getText());
			}
		});
		
	}
	
	private JList<Contact> getContactjList(){
		return _contactJList;
	}
	
	private void initializeContactList(JList<Contact> contactList) {
		if(_contactStore != null){
			contactList.setCellRenderer(new ContactListCellRenderer());
			contactList.setModel(new ContactStoreJListModel(_contactStore));
			contactList.addListSelectionListener(new ListSelectionListener() {
				
				@Override
				public void valueChanged(ListSelectionEvent e) {
					// TODO Auto-generated method stub
					if(e.getFirstIndex() > -1){
						_selectedContact = _contactStore.getContact(getContactjList().getSelectedIndex());
						_copySelectedContact = _selectedContact.Copy();
						update(_selectedContact, "all");
					}					
				}
			});
			contactList.repaint();	
		}
		
		//list.setModel();
	}
	private boolean hasAtLeastOneName () {
		if (getNameField().getText().equals("") && getFirstNameField().getText().equals("")){
			getNamesErrorLabel().setVisible(true);
			return false;
		}else{
			getNamesErrorLabel().setVisible(false);
			return true;			
		}
	}

	private boolean isValidEmail () {
		if (SimpleValidator.isValidEmail(getEMailField().getText())) {
			getEMailErrorLabel().setVisible(false);
			return true;
		}else{
			getEMailErrorLabel().setVisible(true);
			return false;		
		}
	}


	private boolean isValidTelNr() {
		if (SimpleValidator.isValidTelNr(getTelNrField().getText())) {
			getTelNrErrorLabel().setVisible(false);
			return true;
		}else{
			getTelNrErrorLabel().setVisible(true);
			return false;		
		}
	}

	private void checkSaveable() {
		Boolean isOk = hasAtLeastOneName();
		
		isOk = isValidEmail() && isOk; 
		isOk = isValidTelNr() && isOk;		
		
		saveButton.setEnabled(isOk);
	}
	
//	private void saveAll(){
//		//TODO: :)
//	}
	
	private void setContactLastName(){
		if(hasAtLeastOneName()){			
			_selectedContact.setLastName(getNameField().getText());
		}
	}

	private void setContactFirstName(){
		if(hasAtLeastOneName()){			
			_selectedContact.setFirstName(getFirstNameField().getText());
		}
	}

	private void setContactEmail(){
		if(isValidEmail()){			
			_selectedContact.setEmail(getEMailField().getText());
		}
	}

	private void setContactTelNr(){
		if(isValidTelNr()){			
			_selectedContact.setTelNr(getTelNrField().getText());
		}
	}
	
	private class CheckSaveableFocusListener extends FocusAdapter {
		@Override
		public void focusLost(FocusEvent arg0) {
			checkSaveable();
		}	
	}

	private void addButtonPressed() {
		Contact newContact = new Contact();
		_contactStore.addContact(newContact);
		_contactJList.updateUI();
	}
	
	private void removeButtonPressed() {
		Contact contact = _contactJList.getSelectedValue();
		_contactStore.removeContact(contact);
		_contactJList.updateUI();
	}

	@Override
	public void update(Observable o, Object arg) {
		if(!(o instanceof Contact)){
			return;
		}
		Contact contact = (Contact)o;
		boolean updateAll = arg == "all";
		
		if(updateAll || (arg == "lastName")){
			getNameField().setText(contact.getLastName());		
		}
		
		if(updateAll || (arg == "firstName")){
			getFirstNameField().setText(contact.getFirstName());
		}
		
		if(updateAll || (arg == "email")){
			getEMailField().setText(contact.getEmail());
		}
		
		if(updateAll || (arg == "telNr")){
			getTelNrField().setText(contact.getTelNr());
		}
	
		checkSaveable();
	}
}
