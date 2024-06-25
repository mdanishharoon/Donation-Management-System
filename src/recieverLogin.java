import java.awt.Dimension;
import java.awt.EventQueue;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JPanel;
import java.awt.Color;

public class recieverLogin extends My_CNX {

    public JFrame frame;
    public static String username;
    public JPanel contentPane;
    private JTextField usernameField;
    private JPasswordField cnicField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
    	
        recieverLogin window = new recieverLogin();
        window.setVisible(true);

    }

    /**
     * Create the application.
     */
    public recieverLogin() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 750, 680);
        this.setPreferredSize(new Dimension(700, 730));
        this.pack();
        contentPane = new JPanel();
        contentPane.setBackground(new Color(135, 206, 250));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel usernameLbl = new JLabel("Username");
        usernameLbl.setFont(new Font("Rockwell", Font.PLAIN, 24));
        usernameLbl.setBounds(67, 171, 136, 53);
        contentPane.add(usernameLbl);

        usernameField = new JTextField();
        usernameField.setBackground(new Color(255, 240, 245));
        usernameField.setFont(new Font("Rockwell", Font.PLAIN, 20));
        usernameField.addFocusListener(new FocusAdapter() {

        });
        usernameField.setBounds(244, 176, 300, 46);
        usernameField.setColumns(10);
        contentPane.add(usernameField);

        JLabel welcomeLbl = new JLabel("Welcome, Please Login In");
        welcomeLbl.setFont(new Font("Rockwell", Font.PLAIN, 26));
        welcomeLbl.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLbl.setBounds(10, 28, 684, 67);
        contentPane.add(welcomeLbl);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBackground(new Color(255, 240, 245));
        loginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                PreparedStatement st;
                ResultSet rs;

                //getting username and cnic

                username = usernameField.getText();
                String cnic = String.valueOf(cnicField.getText());

                //string query to check whether username and cnic exist in database
                String query = "SELECT * FROM `recievers` WHERE `username` = ? AND `cnic`= ?";

                if (username.trim().equals("username")) {
                    JOptionPane.showMessageDialog(null, "Enter Your Username", "Empty Username", 2);
                } else if (cnic.trim().equals("cnic")) {
                    JOptionPane.showMessageDialog(null, "Enter Your CNIC", "Empty CNIC", 2);
                } else {



                    try {
                        st = My_CNX.getConnection().prepareStatement(query);

                        st.setString(1, username);
                        st.setString(2, cnic);

                        rs = st.executeQuery();

                        if (rs.next()) {

                            RecieverPage page = new RecieverPage();
                            page.setVisible(true); 
                            dispose();



                        } else {
                            JOptionPane.showMessageDialog(null, "Invalid username/cnic, try again", "login error", 2);
                        }


                    } catch (SQLException ex) {
                        Logger.getLogger(" Get Connection -> " + My_CNX.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        loginBtn.setFont(new Font("Rockwell", Font.PLAIN, 24));
        loginBtn.setBounds(273, 387, 136, 46);
        contentPane.add(loginBtn);

        cnicField = new JPasswordField();
        cnicField.setBackground(new Color(255, 240, 245));
        cnicField.setFont(new Font("Tahoma", Font.PLAIN, 20));
        cnicField.setBounds(244, 293, 300, 46);
        contentPane.add(cnicField);

        JButton navRegBtn = new JButton("Don't have an account?, Click here to register");
        navRegBtn.setBackground(new Color(255, 240, 245));
        navRegBtn.setFont(new Font("Rockwell", Font.PLAIN, 20));
        navRegBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {

            	recieverHomepage rh = new recieverHomepage();
                rh.setVisible(true);
                dispose();
            }
        });

        navRegBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
        navRegBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {}
        });
        navRegBtn.setBounds(45, 496, 606, 87);
        contentPane.add(navRegBtn);
        JLabel lblCnic = new JLabel("CNIC");
        lblCnic.setFont(new Font("Rockwell", Font.PLAIN, 24));
        lblCnic.setBounds(67, 288, 136, 53);
        contentPane.add(lblCnic);
    }
}