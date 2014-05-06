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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class DemoKontaktDetailJFrame {

	private JFrame frame;
	private JTextField nameField;	
	private JLabel namesErrorLabel;
	private JButton saveButton;
	private JTextField vornameField;
	private JTextField emailField;
	private JTextField telfonField;
	private JLabel mailError;
	private JLabel telefonError;

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
	private JTextField getTelNr()
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
					DemoKontaktDetailJFrame window = new DemoKontaktDetailJFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DemoKontaktDetailJFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 326, 398);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		FocusListener saveCecker =  new CheckSaveableFocusListener();
			
		ImageIcon errorIcon = new ImageIcon(DemoKontaktDetailJFrame.class.getResource("/com/sun/java/swing/plaf/windows/icons/Error.gif"));
		
		JLabel lblKontaktDetails = new JLabel("Kontakt Details");
		lblKontaktDetails.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblKontaktDetails.setBounds(10, 15, 129, 17);
		frame.getContentPane().add(lblKontaktDetails);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(10, 47, 71, 14);
		frame.getContentPane().add(lblName);
		
		nameField = new JTextField();
		nameField.addFocusListener(saveCecker);
		nameField.setBounds(97, 44, 150, 20);
		frame.getContentPane().add(nameField);
		nameField.setColumns(10);

		namesErrorLabel = new JLabel(errorIcon);
		namesErrorLabel.setVisible(false);
		namesErrorLabel.setToolTipText("Vorname und Nachmame duerfen nicht beide leer sein");
		namesErrorLabel.setBounds(265, 47, 32, 32);
		frame.getContentPane().add(namesErrorLabel);
	
		JTextArea notesArea = new JTextArea();
		notesArea.setBounds(10, 193, 220, 119);
		frame.getContentPane().add(notesArea);
		
		saveButton = new JButton("Save");
		saveButton.setEnabled(false);
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Saved record for: "+getNameField().getText()+" "+getFirstNameField().getText());
			}
		});
		saveButton.setBounds(10, 323, 89, 23);
		frame.getContentPane().add(saveButton);
		
		JLabel lblNotizen = new JLabel("Notizen:");
		lblNotizen.setBounds(10, 177, 71, 14);
		frame.getContentPane().add(lblNotizen);
		
		JLabel lblNewLabel = new JLabel("Vorname:");
		lblNewLabel.setBounds(10, 73, 70, 15);
		frame.getContentPane().add(lblNewLabel);
		
		vornameField = new JTextField();
		vornameField.addFocusListener(saveCecker);
		vornameField.setBounds(97, 71, 150, 19);
		frame.getContentPane().add(vornameField);
		vornameField.setColumns(10);
		
		JLabel lblEmail = new JLabel("eMail:");
		lblEmail.setBounds(10, 108, 70, 15);
		frame.getContentPane().add(lblEmail);
		
		JLabel lblTelefon = new JLabel("Telefon:");
		lblTelefon.setBounds(10, 150, 70, 15);
		frame.getContentPane().add(lblTelefon);
		
		emailField = new JTextField();
		emailField.addFocusListener(saveCecker);
		emailField.setBounds(97, 106, 150, 19);
		frame.getContentPane().add(emailField);
		emailField.setColumns(10);
		
		telfonField = new JTextField();
		telfonField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				checkSaveable();
			}
		});
		telfonField.addFocusListener(saveCecker);
		telfonField.setBounds(96, 146, 150, 19);
		frame.getContentPane().add(telfonField);
		telfonField.setColumns(10);
		
		telefonError = new JLabel("");
		telefonError.setVisible(false);
		telefonError.setHorizontalAlignment(SwingConstants.CENTER);
		telefonError.setToolTipText("Telefon darf nicht leer sein");
		telefonError.setIcon(new ImageIcon(DemoKontaktDetailJFrame.class.getResource("/com/sun/java/swing/plaf/windows/icons/Error.gif")));
		telefonError.setBounds(265, 133, 32, 32);
		frame.getContentPane().add(telefonError);
		
		mailError = new JLabel("");
		mailError.setVisible(false);
		mailError.setHorizontalAlignment(SwingConstants.CENTER);
		mailError.setToolTipText("eMail darf nicht leer sein");
		mailError.setIcon(new ImageIcon(DemoKontaktDetailJFrame.class.getResource("/com/sun/java/swing/plaf/windows/icons/Error.gif")));
		mailError.setBounds(265, 91, 32, 32);
		frame.getContentPane().add(mailError);
		
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


	private boolean validTelNr() {
		if (SimpleValidator.isValidTelNr(getTelNr().getText())) {
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
		isOk = validTelNr() && isOk;		
		
		saveButton.setEnabled(isOk);
	}
	
	
	private class CheckSaveableFocusListener extends FocusAdapter {
		@Override
		public void focusLost(FocusEvent arg0) {
			checkSaveable();
		}	
	}
}
