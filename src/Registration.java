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


public class Registration extends JFrame {

    private JPanel contentPane;
    private JTextField textField_username;
    private JTextField textField_mobNo;
    private JTextField textField_fullname;
    private JPasswordField passwordField_1;
    private JPasswordField passwordField_2;
    ButtonGroup G1;
    public static String s = LoginPage.username;
    // int=LoginPage.id;



    public boolean verifyFields() {

        String fname = textField_fullname.getText();
        String uname = textField_username.getText();
        String phone = textField_mobNo.getText();
        String pass1 = String.valueOf(passwordField_1.getPassword());
        String pass2 = String.valueOf(passwordField_2.getPassword());

        if (fname.trim().equals("") || uname.trim().equals("") || phone.trim().equals("") || pass1.trim().equals("") || pass2.trim().equals("")) {
        	//to check whether any of the fields have been left empty
        	JOptionPane.showMessageDialog(null, "One or more fields are empty", "Empty Fields", 2);
            return false;
        } else if (!pass1.equals(pass2)) {
            JOptionPane.showMessageDialog(null, "Passwords do not Match", "Password Mismatch", 2);
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

        String query = "SELECT * FROM `users` WHERE `username` = ?";

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
                    Registration frame = new Registration();
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
    public Registration() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 730);
        this.setPreferredSize(new Dimension(700, 730));
        this.pack();
        contentPane = new JPanel();
        contentPane.setBackground(new Color(135, 206, 250));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblRegistration = new JLabel("Registration");
        lblRegistration.setFont(new Font("Rockwell", Font.PLAIN, 32));
        lblRegistration.setBounds(245, 30, 182, 47);
        contentPane.add(lblRegistration);

        JLabel crUsernameLbl = new JLabel("Username");
        crUsernameLbl.setFont(new Font("Rockwell", Font.PLAIN, 18));
        crUsernameLbl.setBounds(51, 187, 114, 40);
        contentPane.add(crUsernameLbl);

        JLabel crPasswordLbl = new JLabel("Create Password");
        crPasswordLbl.setFont(new Font("Rockwell", Font.PLAIN, 18));
        crPasswordLbl.setBounds(49, 226, 175, 40);
        contentPane.add(crPasswordLbl);

        JRadioButton radiobMale = new JRadioButton("Male");
        radiobMale.setBackground(new Color(135, 206, 250));
        radiobMale.setFont(new Font("Tahoma", Font.PLAIN, 19));
        radiobMale.setBounds(442, 406, 89, 25);
        contentPane.add(radiobMale);

        JRadioButton radiobFemale = new JRadioButton("Female");
        radiobFemale.setBackground(new Color(135, 206, 250));
        radiobFemale.setSelected(true);
        radiobFemale.setFont(new Font("Tahoma", Font.PLAIN, 19));
        radiobFemale.setBounds(265, 406, 127, 25);
        contentPane.add(radiobFemale);

        JLabel genderLbl = new JLabel("Gender");
        genderLbl.setFont(new Font("Rockwell", Font.PLAIN, 18));
        genderLbl.setBounds(49, 405, 114, 40);
        contentPane.add(genderLbl);

        JLabel mobNumberLbl = new JLabel("Mobile Number");
        mobNumberLbl.setFont(new Font("Rockwell", Font.PLAIN, 18));
        mobNumberLbl.setBounds(49, 330, 161, 40);
        contentPane.add(mobNumberLbl);

        textField_username = new JTextField();
        textField_username.setBackground(new Color(255, 240, 245));
        textField_username.setFont(new Font("Rockwell", Font.PLAIN, 18));
        textField_username.setColumns(10);
        textField_username.setBounds(265, 192, 263, 35);
        contentPane.add(textField_username);

        textField_mobNo = new JTextField();
        textField_mobNo.setBackground(new Color(255, 240, 245));
        textField_mobNo.setFont(new Font("Rockwell", Font.PLAIN, 18));
        textField_mobNo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {

                if (!Character.isDigit(e.getKeyChar())) {
                    e.consume();

                }
            }
        });
        textField_mobNo.setColumns(10);
        textField_mobNo.setBounds(265, 335, 263, 35);
        contentPane.add(textField_mobNo);

        JButton btnNewButton = new JButton("Submit and Create account");
        btnNewButton.setBackground(new Color(255, 228, 225));
        btnNewButton.setFont(new Font("Rockwell", Font.PLAIN, 18));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {



                String fname = textField_fullname.getText();
                String username = textField_username.getText();
                String pass1 = String.valueOf(passwordField_1.getPassword());
                String pass2 = String.valueOf(passwordField_2.getPassword());
                String phone = textField_mobNo.getText();
                String gender = "Male";

                if (radiobFemale.isSelected()) {

                    gender = "Female";


                }

                if (verifyFields()) {

                    if (!checkUsername(username)) {
                        PreparedStatement ps;
                        ResultSet rs;
                        String registerUserQuery = "INSERT INTO `users`(`full_name`, `username`, `password`, `phone`, `gender`) VALUES (?,?,?,?,?)";

                        try {

                            ps = My_CNX.getConnection().prepareStatement(registerUserQuery);
                            ps.setString(1, fname);
                            ps.setString(2, username);
                            ps.setString(3, pass1);
                            ps.setString(4, phone);
                            ps.setString(5, gender);

                            if (ps.executeUpdate() != 0) {
                                JOptionPane.showMessageDialog(null, "Your account has been created succesfully");
                                LoginPage l = new LoginPage();
                                l.frame.setVisible(true);
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



        btnNewButton.setBounds(210, 491, 300, 51);
        contentPane.add(btnNewButton);

        JLabel lblFullname = new JLabel("Fullname");
        lblFullname.setFont(new Font("Rockwell", Font.PLAIN, 18));
        lblFullname.setBounds(51, 137, 114, 40);
        contentPane.add(lblFullname);

        textField_fullname = new JTextField();
        textField_fullname.setBackground(new Color(255, 240, 245));
        textField_fullname.setFont(new Font("Rockwell", Font.PLAIN, 18));
        textField_fullname.setColumns(10);
        textField_fullname.setBounds(265, 147, 263, 35);
        contentPane.add(textField_fullname);

        JLabel lblConfirmPassword = new JLabel("Confirm Password");
        lblConfirmPassword.setFont(new Font("Rockwell", Font.PLAIN, 18));
        lblConfirmPassword.setBounds(51, 280, 159, 40);
        contentPane.add(lblConfirmPassword);

        passwordField_1 = new JPasswordField();
        passwordField_1.setBackground(new Color(255, 240, 245));
        passwordField_1.setFont(new Font("Rockwell", Font.PLAIN, 18));
        passwordField_1.setBounds(265, 237, 263, 35);
        contentPane.add(passwordField_1);

        passwordField_2 = new JPasswordField();
        passwordField_2.setBackground(new Color(255, 240, 245));
        passwordField_2.setFont(new Font("Rockwell", Font.PLAIN, 18));
        passwordField_2.setBounds(265, 291, 263, 35);
        contentPane.add(passwordField_2);


        ButtonGroup bg = new ButtonGroup();
        bg.add(radiobMale);
        bg.add(radiobFemale);

        JButton btnNewButton_1 = new JButton("Click here to Login");
        btnNewButton_1.setBackground(new Color(255, 228, 225));
        btnNewButton_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {



                LoginPage l = new LoginPage();
                l.setVisible(true);
                dispose();



            }

        });
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {


            }
        });
        btnNewButton_1.setFont(new Font("Rockwell", Font.PLAIN, 18));
        btnNewButton_1.setBounds(94, 592, 517, 63);
        contentPane.add(btnNewButton_1);






    }
}