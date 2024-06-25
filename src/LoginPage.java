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
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

public class LoginPage extends My_CNX {


    public JFrame frame;
    public JPanel contentPane;
    public static String username;
    private JTextField usernameField;
    private JPasswordField passwordField;
    //public static String s=LoginPage.username;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
               LoginPage window = new LoginPage();

        window.setVisible(true);

    }
    /**
     * Create the application.
     */
    public LoginPage() { // this.username=s;

        initialize();

    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 622);
        this.setPreferredSize(new Dimension(700, 600));
        this.pack();
        contentPane = new JPanel();
        contentPane.setBackground(new Color(135, 206, 235));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel usernameLbl = new JLabel("Username");
        usernameLbl.setFont(new Font("Rockwell", Font.PLAIN, 22));
        usernameLbl.setBounds(56, 123, 136, 53);
        //frame.getContentPane().add(usernameLbl);
        contentPane.add(usernameLbl);

        JLabel passwordLbl = new JLabel("Password");
        passwordLbl.setFont(new Font("Rockwell", Font.PLAIN, 22));
        passwordLbl.setBounds(56, 222, 136, 53);
        //frame.getContentPane().add(passwordLbl);
        contentPane.add(passwordLbl);

        usernameField = new JTextField();
        usernameField.setFont(new Font("Rockwell", Font.PLAIN, 20));
        usernameField.setBackground(new Color(255, 240, 245));
        usernameField.addFocusListener(new FocusAdapter() {

        });
        usernameField.setBounds(223, 132, 300, 46);
        //frame.getContentPane().add(usernameField);
        usernameField.setColumns(10);
        contentPane.add(usernameField);

        JLabel welcomeLbl = new JLabel("Welcome, Please Login In");
        welcomeLbl.setFont(new Font("Rockwell", Font.BOLD, 30));
        welcomeLbl.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLbl.setBounds(109, 28, 466, 67);
        //frame.getContentPane().add(welcomeLbl);
        contentPane.add(welcomeLbl);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBackground(new Color(255, 228, 225));
        loginBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                PreparedStatement st;
                ResultSet rs;

                //getting username and password

                username = usernameField.getText();
                String password = String.valueOf(passwordField.getText());

                //string query to check whether username and password exist in database
                String query = "SELECT * FROM `users` WHERE `username` = ? AND `password`= ?";

                if (username.trim().equals("username")) {
                    JOptionPane.showMessageDialog(null, "Enter Your Username", "Empty Username", 2);
                } else if (password.trim().equals("password")) {
                    JOptionPane.showMessageDialog(null, "Enter Your Password", "Empty Password", 2);
                } else {



                    try {
                        st = My_CNX.getConnection().prepareStatement(query);

                        st.setString(1, username);
                        st.setString(2, password);

                        rs = st.executeQuery();

                        if (rs.next()) {

                            DonorsPage page = new DonorsPage();
                            page.setVisible(true);
                            page.pack();
                            page.setLocationRelativeTo(null);
                            page.setBounds(289, 329, 800, 800);
                            dispose();



                        } else {
                            JOptionPane.showMessageDialog(null, "Invalid username/password", "login error", 2);
                        }


                    } catch (SQLException ex) {
                        Logger.getLogger(" Get Connection -> " + My_CNX.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        loginBtn.setFont(new Font("Rockwell", Font.PLAIN, 20));
        loginBtn.setBounds(289, 329, 136, 46);
        //frame.getContentPane().add(loginBtn);
        contentPane.add(loginBtn);
        passwordField = new JPasswordField();
        passwordField.setBackground(new Color(255, 240, 245));
        passwordField.setFont(new Font("Rockwell", Font.PLAIN, 20));
        passwordField.setBounds(223, 227, 300, 46);
        contentPane.add(passwordField);

        JButton navRegBtn = new JButton("Don't have an account?, Click here to register");
        navRegBtn.setBackground(new Color(255, 228, 225));
        navRegBtn.setFont(new Font("Rockwell", Font.PLAIN, 20));
        navRegBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {


                dispose();
                Registration r = new Registration();
                r.setVisible(true);
            }
        });

        navRegBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
        navRegBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {}
        });
        navRegBtn.setBounds(43, 428, 606, 87);
        //frame.getContentPane().add(navRegBtn);
        contentPane.add(navRegBtn);

        JButton btnNewButton = new JButton("< back");
        btnNewButton.setBackground(new Color(255, 228, 225));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Homepage hp = new Homepage();
                hp.setVisible(true);
                dispose();


            }
        });
        btnNewButton.setBounds(31, 56, 80, 23);
        contentPane.add(btnNewButton);
        //frame.add(contentPane);
    }
}