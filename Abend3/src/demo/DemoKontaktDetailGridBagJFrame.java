package demo;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.JButton;

import util.SimpleValidator;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.SwingConstants;

import domain.Contact;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.sql.BatchUpdateException;
import java.util.Observable;
import java.util.Observer;


public class DemoKontaktDetailGridBagJFrame implements Observer {

	private JFrame frame;
	private JTextField nameField;	
	private JLabel namesErrorLabel;
	private JButton saveButton;
	private JTextField vornameField;
	private JTextField emailField;
	private JTextField telfonField;
	private JLabel mailError;
	private JLabel telefonError;
	private Contact _contact;

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
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Contact newContact = new Contact();
					DemoKontaktDetailGridBagJFrame window = new DemoKontaktDetailGridBagJFrame(newContact);
					window.frame.setVisible(true);
					
					DemoKontaktDetailGridBagJFrame window2 = new DemoKontaktDetailGridBagJFrame(newContact);
					window2.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DemoKontaktDetailGridBagJFrame  (Contact contact) {
		_contact = contact;
		_contact.addObserver(this);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		//TODO: change dimension size!
		frame.setMinimumSize(new Dimension(250,250));
		frame.setBounds(100, 100, 326, 398);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		FocusListener saveCecker =  new CheckSaveableFocusListener();
			
		ImageIcon errorIcon = new ImageIcon(DemoKontaktDetailGridBagJFrame.class.getResource("/com/sun/java/swing/plaf/windows/icons/Error.gif"));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{71, 151, 32, 0};
		gridBagLayout.rowHeights = new int[]{17, 20, 19, 34, 32, 14, 119, 23, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		vornameField = new JTextField();
		vornameField.addFocusListener(saveCecker);
		vornameField.addFocusListener(new FocusAdapter() {
			@Override			
			public void focusLost(FocusEvent arg0){
				setContactFirstName();
			}
		});
		
				namesErrorLabel = new JLabel(errorIcon);
				namesErrorLabel.setVisible(false);
				
				JLabel lblKontaktDetails = new JLabel("Kontakt Details");
				lblKontaktDetails.setFont(new Font("Tahoma", Font.PLAIN, 14));
				GridBagConstraints gbc_lblKontaktDetails = new GridBagConstraints();
				gbc_lblKontaktDetails.anchor = GridBagConstraints.NORTH;
				gbc_lblKontaktDetails.insets = new Insets(0, 0, 5, 5);
				gbc_lblKontaktDetails.gridwidth = 3;
				gbc_lblKontaktDetails.gridx = 0;
				gbc_lblKontaktDetails.gridy = 0;
				frame.getContentPane().add(lblKontaktDetails, gbc_lblKontaktDetails);
				
				JLabel lblName = new JLabel("Name:");
				GridBagConstraints gbc_lblName = new GridBagConstraints();
				gbc_lblName.fill = GridBagConstraints.HORIZONTAL;
				gbc_lblName.insets = new Insets(0, 5, 5, 5);
				gbc_lblName.gridx = 0;
				gbc_lblName.gridy = 1;
				frame.getContentPane().add(lblName, gbc_lblName);
				
				nameField = new JTextField();
				nameField.addFocusListener(saveCecker);
				nameField.addFocusListener(new FocusAdapter() {

					@Override
					public void focusLost(FocusEvent e) {
						setContactLastName();
					}	
					
				});
				
				GridBagConstraints gbc_nameField = new GridBagConstraints();
				gbc_nameField.fill = GridBagConstraints.BOTH;
				gbc_nameField.insets = new Insets(0, 0, 5, 5);
				gbc_nameField.gridx = 1;
				gbc_nameField.gridy = 1;
				frame.getContentPane().add(nameField, gbc_nameField);
				nameField.setColumns(10);
				namesErrorLabel.setToolTipText("Vorname und Nachmame duerfen nicht beide leer sein");
				GridBagConstraints gbc_namesErrorLabel = new GridBagConstraints();
				gbc_namesErrorLabel.anchor = GridBagConstraints.NORTH;
				gbc_namesErrorLabel.fill = GridBagConstraints.HORIZONTAL;
				gbc_namesErrorLabel.insets = new Insets(0, 0, 5, 0);
				gbc_namesErrorLabel.gridheight = 2;
				gbc_namesErrorLabel.gridx = 2;
				gbc_namesErrorLabel.gridy = 1;
				frame.getContentPane().add(namesErrorLabel, gbc_namesErrorLabel);
		
		JLabel lblNewLabel = new JLabel("Vorname:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel.insets = new Insets(0, 5, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 2;
		frame.getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		GridBagConstraints gbc_vornameField = new GridBagConstraints();
		gbc_vornameField.anchor = GridBagConstraints.NORTH;
		gbc_vornameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_vornameField.insets = new Insets(0, 0, 5, 5);
		gbc_vornameField.gridx = 1;
		gbc_vornameField.gridy = 2;
		frame.getContentPane().add(vornameField, gbc_vornameField);
		vornameField.setColumns(10);
		
		telfonField = new JTextField();
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
		
		JLabel lblEmail = new JLabel("eMail:");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.SOUTH;
		gbc_lblEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblEmail.insets = new Insets(0, 5, 5, 5);
		gbc_lblEmail.gridx = 0;
		gbc_lblEmail.gridy = 3;
		frame.getContentPane().add(lblEmail, gbc_lblEmail);
		
		mailError = new JLabel("");
		mailError.setVisible(false);
		
		emailField = new JTextField();
		emailField.addFocusListener(saveCecker);
		emailField.addFocusListener(new FocusAdapter() {
			@Override			
			public void focusLost(FocusEvent arg0){
				setContactEmail();
			}
		});
		GridBagConstraints gbc_emailField = new GridBagConstraints();
		gbc_emailField.anchor = GridBagConstraints.SOUTH;
		gbc_emailField.fill = GridBagConstraints.HORIZONTAL;
		gbc_emailField.insets = new Insets(0, 0, 5, 5);
		gbc_emailField.gridx = 1;
		gbc_emailField.gridy = 3;
		frame.getContentPane().add(emailField, gbc_emailField);
		emailField.setColumns(10);
		mailError.setHorizontalAlignment(SwingConstants.CENTER);
		mailError.setToolTipText("eMail darf nicht leer sein");
		mailError.setIcon(new ImageIcon(DemoKontaktDetailGridBagJFrame.class.getResource("/com/sun/java/swing/plaf/windows/icons/Error.gif")));
		GridBagConstraints gbc_mailError = new GridBagConstraints();
		gbc_mailError.anchor = GridBagConstraints.NORTH;
		gbc_mailError.fill = GridBagConstraints.HORIZONTAL;
		gbc_mailError.insets = new Insets(0, 0, 5, 0);
		gbc_mailError.gridx = 2;
		gbc_mailError.gridy = 3;
		frame.getContentPane().add(mailError, gbc_mailError);
		
		JLabel lblTelefon = new JLabel("Telefon:");
		GridBagConstraints gbc_lblTelefon = new GridBagConstraints();
		gbc_lblTelefon.anchor = GridBagConstraints.SOUTH;
		gbc_lblTelefon.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblTelefon.insets = new Insets(0, 5, 5, 5);
		gbc_lblTelefon.gridx = 0;
		gbc_lblTelefon.gridy = 4;
		frame.getContentPane().add(lblTelefon, gbc_lblTelefon);
		GridBagConstraints gbc_telfonField = new GridBagConstraints();
		gbc_telfonField.anchor = GridBagConstraints.SOUTH;
		gbc_telfonField.fill = GridBagConstraints.HORIZONTAL;
		gbc_telfonField.insets = new Insets(0, 0, 5, 5);
		gbc_telfonField.gridx = 1;
		gbc_telfonField.gridy = 4;
		frame.getContentPane().add(telfonField, gbc_telfonField);
		telfonField.setColumns(10);
		
		saveButton = new JButton("Save");
		saveButton.setEnabled(false);
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Saved record for: "+getNameField().getText()+" "+getFirstNameField().getText());
			}
		});
		
		telefonError = new JLabel("");
		telefonError.setVisible(false);
		telefonError.setHorizontalAlignment(SwingConstants.CENTER);
		telefonError.setToolTipText("Telefon darf nicht leer sein");
		telefonError.setIcon(new ImageIcon(DemoKontaktDetailGridBagJFrame.class.getResource("/com/sun/java/swing/plaf/windows/icons/Error.gif")));
		GridBagConstraints gbc_telefonError = new GridBagConstraints();
		gbc_telefonError.anchor = GridBagConstraints.NORTH;
		gbc_telefonError.fill = GridBagConstraints.HORIZONTAL;
		gbc_telefonError.insets = new Insets(0, 0, 5, 0);
		gbc_telefonError.gridx = 2;
		gbc_telefonError.gridy = 4;
		frame.getContentPane().add(telefonError, gbc_telefonError);
		
		JLabel lblNotizen = new JLabel("Notizen:");
		GridBagConstraints gbc_lblNotizen = new GridBagConstraints();
		gbc_lblNotizen.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNotizen.insets = new Insets(0, 5, 5, 5);
		gbc_lblNotizen.gridx = 0;
		gbc_lblNotizen.gridy = 5;
		frame.getContentPane().add(lblNotizen, gbc_lblNotizen);
		
			JTextArea notesArea = new JTextArea();
			GridBagConstraints gbc_notesArea = new GridBagConstraints();
			gbc_notesArea.fill = GridBagConstraints.BOTH;
			gbc_notesArea.insets = new Insets(0, 10, 5, 5);
			gbc_notesArea.gridwidth = 2;
			gbc_notesArea.gridx = 0;
			gbc_notesArea.gridy = 6;
			frame.getContentPane().add(notesArea, gbc_notesArea);
		GridBagConstraints gbc_saveButton = new GridBagConstraints();
		gbc_saveButton.anchor = GridBagConstraints.WEST;
		gbc_saveButton.fill = GridBagConstraints.VERTICAL;
		gbc_saveButton.insets = new Insets(0, 10, 5, 5);
		gbc_saveButton.gridx = 0;
		gbc_saveButton.gridy = 7;
		frame.getContentPane().add(saveButton, gbc_saveButton);
		
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
			_contact.setLastName(getNameField().getText());
		}
	}

	private void setContactFirstName(){
		if(hasAtLeastOneName()){
			_contact.setFirstName(getFirstNameField().getText());
		}
	}

	private void setContactEmail(){
		if(isValidEmail()){
			_contact.setEmail(getEMailField().getText());
		}
	}

	private void setContactTelNr(){
		if(isValidTelNr()){
			_contact.setTelNr(getTelNrField().getText());
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
