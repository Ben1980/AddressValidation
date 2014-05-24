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
		_contactStore.addObserver(contactStore);
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		//TODO: change dimension size!
		frame.setMinimumSize(new Dimension(250,250));
		frame.setBounds(100, 100, 846, 549);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		FocusListener saveCecker =  new CheckSaveableFocusListener();
			
		ImageIcon errorIcon = new ImageIcon(ContactListMasterDetailFrame.class.getResource("/com/sun/java/swing/plaf/windows/icons/Error.gif"));
				frame.getContentPane().setLayout(null);
		
		JPanel ContactDetailJPanel = new JPanel();
		ContactDetailJPanel.setBounds(382, 29, 410, 420);
		frame.getContentPane().add(ContactDetailJPanel);
		ContactDetailJPanel.setLayout(null);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(12, 31, 92, 15);
		ContactDetailJPanel.add(lblName);
		
		nameField = new JTextField();
		nameField.setBounds(109, 29, 165, 19);
		ContactDetailJPanel.add(nameField);
		nameField.addFocusListener(saveCecker);
		nameField.addFocusListener(new FocusAdapter() {

			@Override
			public void focusLost(FocusEvent e) {
				setContactLastName();
			}	
			
		});
		nameField.setColumns(10);
		
				namesErrorLabel = new JLabel(errorIcon);
				namesErrorLabel.setBounds(279, 29, 51, 32);
				ContactDetailJPanel.add(namesErrorLabel);
				namesErrorLabel.setVisible(false);
				namesErrorLabel.setToolTipText("Vorname und Nachmame duerfen nicht beide leer sein");
				
				vornameField = new JTextField();
				vornameField.setBounds(109, 53, 165, 19);
				ContactDetailJPanel.add(vornameField);
				vornameField.addFocusListener(saveCecker);
				vornameField.addFocusListener(new FocusAdapter() {
					@Override			
					public void focusLost(FocusEvent arg0){
						setContactFirstName();
					}
				});
				vornameField.setColumns(10);
				
				JLabel lblNewLabel = new JLabel("Vorname:");
				lblNewLabel.setBounds(12, 55, 92, 15);
				ContactDetailJPanel.add(lblNewLabel);
				
				JLabel lblEmail = new JLabel("eMail:");
				lblEmail.setBounds(12, 94, 92, 15);
				ContactDetailJPanel.add(lblEmail);
				
				emailField = new JTextField();
				emailField.setBounds(109, 90, 165, 19);
				ContactDetailJPanel.add(emailField);
				emailField.addFocusListener(saveCecker);
				emailField.addFocusListener(new FocusAdapter() {
					@Override			
					public void focusLost(FocusEvent arg0){
						setContactEmail();
					}
				});
				emailField.setColumns(10);
				
				mailError = new JLabel("");
				mailError.setBounds(279, 77, 51, 32);
				ContactDetailJPanel.add(mailError);
				mailError.setVisible(false);
				mailError.setHorizontalAlignment(SwingConstants.CENTER);
				mailError.setToolTipText("eMail darf nicht leer sein");
				mailError.setIcon(new ImageIcon(ContactListMasterDetailFrame.class.getResource("/com/sun/java/swing/plaf/windows/icons/Error.gif")));
				
				telefonError = new JLabel("");
				telefonError.setBounds(279, 114, 51, 32);
				ContactDetailJPanel.add(telefonError);
				telefonError.setVisible(false);
				telefonError.setHorizontalAlignment(SwingConstants.CENTER);
				telefonError.setToolTipText("Telefon darf nicht leer sein");
				telefonError.setIcon(new ImageIcon(ContactListMasterDetailFrame.class.getResource("/com/sun/java/swing/plaf/windows/icons/Error.gif")));
				
				telfonField = new JTextField();
				telfonField.setBounds(109, 127, 165, 19);
				ContactDetailJPanel.add(telfonField);
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
				
				JLabel lblTelefon = new JLabel("Telefon:");
				lblTelefon.setBounds(12, 131, 92, 15);
				ContactDetailJPanel.add(lblTelefon);
				
				JLabel lblNotizen = new JLabel("Notizen:");
				lblNotizen.setBounds(12, 151, 92, 15);
				ContactDetailJPanel.add(lblNotizen);
				
					JTextArea notesArea = new JTextArea();
					notesArea.setBounds(17, 171, 257, 159);
					ContactDetailJPanel.add(notesArea);
					
					saveButton = new JButton("Save");
					saveButton.setBounds(27, 350, 68, 25);
					ContactDetailJPanel.add(saveButton);
					saveButton.setEnabled(false);
					
					JLabel lblKontaktDetails = new JLabel("Kontakt Details");
					lblKontaktDetails.setBounds(61, 2, 104, 17);
					ContactDetailJPanel.add(lblKontaktDetails);
					lblKontaktDetails.setFont(new Font("Tahoma", Font.PLAIN, 14));
					
					ContactListJPanel = new JPanel();
					ContactListJPanel.setBounds(88, 60, 223, 245);
					frame.getContentPane().add(ContactListJPanel);
					ContactListJPanel.setLayout(null);
					
					_contactJList = new JList<Contact>();
					_contactJList.setBounds(20, 41, 152, 174);
					
					ContactListJPanel.add(_contactJList);
					initializeContactList(getContactjList());
					
					saveButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							System.out.println("Saved record for: "+getNameField().getText()+" "+getFirstNameField().getText());
						}
					});
		
	}
	
	private JList<Contact> getContactjList(){
		return _contactJList;
	}
	
	private void initializeContactList(JList<Contact> list) {
		if(_contactStore != null){
			list.setModel(new ContactStoreJListModel(_contactStore));
			list.repaint();	
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
			//TODO validierung
			//_contact.setLastName(getNameField().getText());
		}
	}

	private void setContactFirstName(){
		if(hasAtLeastOneName()){
			//TODO: validierung
			//_contact.setFirstName(getFirstNameField().getText());
		}
	}

	private void setContactEmail(){
		if(isValidEmail()){
			//TODO: validierung
			//_contact.setEmail(getEMailField().getText());
		}
	}

	private void setContactTelNr(){
		if(isValidTelNr()){
			//TODO: validierung
			//_contact.setTelNr(getTelNrField().getText());
		}
	}
	
	private class CheckSaveableFocusListener extends FocusAdapter {
		@Override
		public void focusLost(FocusEvent arg0) {
			checkSaveable();
		}	
	}


	@Override
	public void update(Observable o, Object arg) {
		Contact contact = (Contact)o;
		if(arg == "lastName"){
			getNameField().setText(contact.getLastName());		
		}
		
		if(arg == "firstName"){
			getFirstNameField().setText(contact.getFirstName());
		}
		
		if(arg == "email"){
			getEMailField().setText(contact.getEmail());
		}
		
		if(arg == "telNr"){
			getTelNrField().setText(contact.getTelNr());
		}
	
		checkSaveable();
	}
}
