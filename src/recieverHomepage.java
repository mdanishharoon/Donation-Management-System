	import java.awt.EventQueue;

	import javax.swing.JFrame;
	import javax.swing.JPanel;
	import javax.swing.border.EmptyBorder;
	import java.awt.Dimension;
	import java.awt.EventQueue;
	import java.awt.Font;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.awt.event.KeyAdapter;
	import java.awt.event.KeyEvent;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.logging.Level;
	import java.util.logging.Logger;

	import javax.swing.ButtonGroup;
	import javax.swing.JButton;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JOptionPane;
	import javax.swing.JPanel;
	import javax.swing.JPasswordField;
	import javax.swing.JRadioButton;
	import javax.swing.JTextField;
	import javax.swing.border.EmptyBorder;
	import java.awt.event.MouseAdapter;
	import java.awt.event.MouseEvent;
import java.awt.Color;


	public class recieverHomepage extends JFrame {

	    private JPanel contentPane;
	    private JTextField textField_username;
	    private JTextField textField_mobNo;
	    private JTextField textField_cnic;
	    /**
	     * Launch the application.
	     */

	    public boolean verifyFields() {

	        String uname = textField_username.getText();
	        String phone = textField_mobNo.getText();
	        String cnic = textField_cnic.getText();
	        //String pass2 = String.valueOf(passwordField_2.getPassword());


	        if (uname.trim().equals("") || phone.trim().equals("") || phone.trim().equals("")) {
	            JOptionPane.showMessageDialog(null, "One or more fields are empty", "Empty Fields", 2);
	            return false;
	        }

	        	else if(cnic.charAt(6)!='-' && cnic.charAt(13)!='-' && cnic.length()!=15) 
	        {
	        JOptionPane.showMessageDialog(null, "CNIC mismatch", "invalid format ", 2);
	        return false; 
	        }



	        //if everything is correct
	        else {
	            return true;
	        }

	    }

	    public boolean checkUsername(String username) {

	        PreparedStatement st;
	        ResultSet rs;
	        boolean username_exist = false;

	        String query = "SELECT * FROM `recievers` WHERE `username` = ?";

	        try {
	            st = My_CNX.getConnection().prepareStatement(query);
	            st.setString(1, username);
	            rs = st.executeQuery();

	            if (rs.next()) {
	                username_exist = true;
	                JOptionPane.showMessageDialog(null, "This username is already taken, choose another Username", "Username Creation Failure ", 2);
	            }


	        } catch (SQLException e) {
	            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, e);
	        }

	        return username_exist;

	    }



	    //create a function to check 

	    //

	    /**
	     * Launch the application.
	     */
	    public static void main(String[] args) {
	        EventQueue.invokeLater(new Runnable() {
	            public void run() {
	                try {
	                    recieverHomepage frame = new recieverHomepage();
	                    frame.setVisible(true);
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        });
	    }
	    /**
	     * Create the frame.
	     */
	    public recieverHomepage() {
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setBounds(100, 100, 676, 650);
	        this.setPreferredSize(new Dimension(675, 650));
	        this.pack();
	        contentPane = new JPanel();
	        contentPane.setBackground(new Color(135, 206, 235));
	        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	        setContentPane(contentPane);
	        contentPane.setLayout(null);

	        JLabel lblRegistration = new JLabel("Registration");
	        lblRegistration.setFont(new Font("Rockwell", Font.BOLD, 32));
	        lblRegistration.setBounds(221, 28, 250, 47);
	        contentPane.add(lblRegistration);

	        JLabel crUsernameLbl = new JLabel("Username");
	        crUsernameLbl.setFont(new Font("Rockwell", Font.PLAIN, 20));
	        crUsernameLbl.setBounds(49, 140, 114, 40);
	        contentPane.add(crUsernameLbl);

	        JLabel crPasswordLbl = new JLabel("CNIC Number");
	        crPasswordLbl.setFont(new Font("Rockwell", Font.PLAIN, 20));
	        crPasswordLbl.setBounds(49, 217, 138, 40);
	        contentPane.add(crPasswordLbl);

	        JLabel mobNumberLbl = new JLabel("Mobile Number");
	        mobNumberLbl.setFont(new Font("Rockwell", Font.PLAIN, 20));
	        mobNumberLbl.setBounds(50, 306, 161, 40);
	        contentPane.add(mobNumberLbl);

	        textField_username = new JTextField();
	        textField_username.setBackground(new Color(255, 240, 245));
	        textField_username.setFont(new Font("Rockwell", Font.PLAIN, 20));
	        textField_username.setColumns(10);
	        textField_username.setBounds(268, 143, 263, 35);
	        contentPane.add(textField_username);

	        textField_mobNo = new JTextField();
	        textField_mobNo.setBackground(new Color(255, 240, 245));
	        textField_mobNo.setFont(new Font("Rockwell", Font.PLAIN, 20));
	        textField_mobNo.addKeyListener(new KeyAdapter() {
	            @Override
	            public void keyTyped(KeyEvent e) {

	                if (!Character.isDigit(e.getKeyChar())) {
	                    e.consume();

	                }
	            }
	        });
	        textField_mobNo.setColumns(10);
	        textField_mobNo.setBounds(268, 306, 263, 35);
	        contentPane.add(textField_mobNo);

	        JButton btnNewButton = new JButton("Submit and Create account");
	        btnNewButton.setBackground(new Color(255, 228, 225));
	        btnNewButton.setFont(new Font("Rockwell", Font.PLAIN, 20));
	        btnNewButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent ae) {



	                String username = textField_username.getText();
	                String cnic = textField_cnic.getText();
	                String phone = textField_mobNo.getText();


	                if (verifyFields()) {

	                    if (!checkUsername(username)) {
	                        PreparedStatement ps;
	                        ResultSet rs;
	                        String registerUserQuery = "INSERT INTO `recievers`(`username`, `cnic`, `phone`) VALUES (?,?,?)";

	                        try {

	                            ps = My_CNX.getConnection().prepareStatement(registerUserQuery);
	                            ps.setString(1, username);
	                            ps.setString(2, cnic);
	                            ps.setString(3, phone);


	                            if (ps.executeUpdate() != 0) {
	                                JOptionPane.showMessageDialog(null, "Your account has been created succesfully");
	                                recieverLogin rl = new recieverLogin();
	                                rl.setVisible(true);
	                                dispose();


	                            } else {
	                                JOptionPane.showMessageDialog(null, "Error; Check your details");
	                            }

	                        } catch (SQLException e) {

	                            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, e);
	                        }


	                    }

	                }

	            }
	        });



	        btnNewButton.setBounds(189, 404, 300, 51);
	        contentPane.add(btnNewButton);


	        JButton btnNewButton_1 = new JButton("Click here to Login to an existing account");
	        btnNewButton_1.setBackground(new Color(255, 228, 225));
	        btnNewButton_1.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                recieverLogin rl = new recieverLogin();
	                rl.setVisible(true);
	                dispose();
	            }

	        });
	        btnNewButton_1.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {}
	        });
	        btnNewButton_1.setFont(new Font("Rockwell", Font.PLAIN, 20));
	        btnNewButton_1.setBounds(71, 495, 517, 63);
	        contentPane.add(btnNewButton_1);

	        textField_cnic = new JTextField();
	        textField_cnic.setBackground(new Color(255, 240, 245));
	        textField_cnic.setFont(new Font("Rockwell", Font.PLAIN, 20));
	        textField_cnic.setBounds(265, 219, 266, 36);
	        contentPane.add(textField_cnic);
	        textField_cnic.setColumns(10);
	        
	        JButton btnNewButton_2 = new JButton("< back");
	        btnNewButton_2.setBackground(new Color(255, 228, 225));
	        btnNewButton_2.setFont(new Font("Rockwell", Font.PLAIN, 11));
	        btnNewButton_2.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		
	        		Homepage h = new Homepage();
	        		h.setVisible(true);
	        		dispose();
	        		
	        	}
	        });
	        btnNewButton_2.setBounds(29, 48, 89, 23);
	        contentPane.add(btnNewButton_2);


	    }
	}