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
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.Box;
import java.awt.FlowLayout;


public class DemoKontaktDetailSimpleJFrame {

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
					DemoKontaktDetailSimpleJFrame window = new DemoKontaktDetailSimpleJFrame();
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
	public DemoKontaktDetailSimpleJFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 647, 537);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		FocusListener saveCecker =  new CheckSaveableFocusListener();
			
		ImageIcon errorIcon = new ImageIcon(DemoKontaktDetailSimpleJFrame.class.getResource("/com/sun/java/swing/plaf/windows/icons/Error.gif"));
		frame.getContentPane().setLayout(null);
		
		JLabel lblKontaktDetails = new JLabel("Kontakt Details");
		lblKontaktDetails.setBounds(124, 0, 104, 17);
		lblKontaktDetails.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().add(lblKontaktDetails);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 17, 552, 369);
		frame.getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
				
				JPanel panel_1 = new JPanel();
				panel.add(panel_1, BorderLayout.CENTER);
				panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
				
				JLabel lblNotizen = new JLabel("Notizen:");
				panel_1.add(lblNotizen);
				
					JTextArea notesArea = new JTextArea();
					panel_1.add(notesArea);
									
									JPanel panel_9 = new JPanel();
									panel.add(panel_9, BorderLayout.SOUTH);
									panel_9.setLayout(new BoxLayout(panel_9, BoxLayout.X_AXIS));
									
									saveButton = new JButton("Save");
									panel_9.add(saveButton);
									saveButton.setEnabled(false);
									
									Component horizontalGlue = Box.createHorizontalGlue();
									panel_9.add(horizontalGlue);
									
									JPanel panel_10 = new JPanel();
									panel.add(panel_10, BorderLayout.NORTH);
									panel_10.setLayout(new BoxLayout(panel_10, BoxLayout.X_AXIS));
									
									JPanel panel_8 = new JPanel();
									panel_10.add(panel_8);
									panel_8.setLayout(new BoxLayout(panel_8, BoxLayout.Y_AXIS));
									
									JPanel panel_5 = new JPanel();
									panel_8.add(panel_5);
									panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.X_AXIS));
									
									JPanel panel_4 = new JPanel();
									panel_5.add(panel_4);
									panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.Y_AXIS));
									
									JPanel panel_3 = new JPanel();
									panel_4.add(panel_3);
									panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
									
									JLabel lblNewLabel = new JLabel("Vorname:");
									lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
									panel_3.add(lblNewLabel);
									
									vornameField = new JTextField();
									vornameField.setHorizontalAlignment(SwingConstants.CENTER);
									panel_3.add(vornameField);
									vornameField.addFocusListener(saveCecker);
									vornameField.setColumns(10);
									
									JPanel panel_2 = new JPanel();
									panel_4.add(panel_2);
									panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
									
									JLabel lblName = new JLabel("Name:");
									lblName.setHorizontalAlignment(SwingConstants.LEFT);
									panel_2.add(lblName);
									
									nameField = new JTextField();
									nameField.setHorizontalAlignment(SwingConstants.CENTER);
									panel_2.add(nameField);
									nameField.addFocusListener(saveCecker);
									nameField.setColumns(10);
									
											namesErrorLabel = new JLabel(errorIcon);
											namesErrorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
											namesErrorLabel.setHorizontalAlignment(SwingConstants.RIGHT);
											panel_5.add(namesErrorLabel);
											namesErrorLabel.setVisible(false);
											namesErrorLabel.setToolTipText("Vorname und Nachmame duerfen nicht beide leer sein");
											
											JPanel panel_6 = new JPanel();
											panel_8.add(panel_6);
											panel_6.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
											
											JLabel lblEmail = new JLabel("eMail:");
											panel_6.add(lblEmail);
											
											telfonField = new JTextField();
											telfonField.setHorizontalAlignment(SwingConstants.CENTER);
											telfonField.setAlignmentX(Component.RIGHT_ALIGNMENT);
											telfonField.setAlignmentY(Component.BOTTOM_ALIGNMENT);
											panel_6.add(telfonField);
											telfonField.addKeyListener(new KeyAdapter() {
												@Override
												public void keyReleased(KeyEvent e) {
													checkSaveable();
												}
											});
											telfonField.addFocusListener(saveCecker);
											telfonField.setColumns(10);
											
											mailError = new JLabel("");
											panel_6.add(mailError);
											mailError.setVisible(false);
											mailError.setHorizontalAlignment(SwingConstants.RIGHT);
											mailError.setToolTipText("eMail darf nicht leer sein");
											mailError.setIcon(new ImageIcon(DemoKontaktDetailSimpleJFrame.class.getResource("/com/sun/java/swing/plaf/windows/icons/Error.gif")));
											
											JPanel panel_7 = new JPanel();
											panel_8.add(panel_7);
											panel_7.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
											
											JLabel lblTelefon = new JLabel("Telefon:");
											panel_7.add(lblTelefon);
											
											emailField = new JTextField();
											panel_7.add(emailField);
											emailField.addFocusListener(saveCecker);
											emailField.setColumns(10);
											
											telefonError = new JLabel("");
											panel_7.add(telefonError);
											telefonError.setVisible(false);
											telefonError.setHorizontalAlignment(SwingConstants.RIGHT);
											telefonError.setToolTipText("Telefon darf nicht leer sein");
											telefonError.setIcon(new ImageIcon(DemoKontaktDetailSimpleJFrame.class.getResource("/com/sun/java/swing/plaf/windows/icons/Error.gif")));
											
											Component horizontalGlue_1 = Box.createHorizontalGlue();
											panel_10.add(horizontalGlue_1);
									saveButton.addActionListener(new ActionListener() {
										public void actionPerformed(ActionEvent arg0) {
											System.out.println("Saved record for: "+getNameField().getText()+" "+getFirstNameField().getText());
										}
									});
		
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
