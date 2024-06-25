import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.TableModel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import java.awt.Color;

public class DonationForm extends My_CNX {
    private JTextField textField_2;
    private JTextField textField_Rate;
    private JTextField textField_cost;
    int n;
    int cost;
    String value, q, query, t_q;
    int x;
    int quantity, id;
    private JTextField textField;
    int value2;
    String i_d;
    public static String s = LoginPage.username;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DonationForm df = new DonationForm();
					
                    // f.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    };

  
    
    public DonationForm() {

        super();
        getContentPane().setBackground(new Color(135, 206, 235));
        System.out.print(s);

        setBounds(100, 100, 700, 660);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JButton submitBtn = new JButton("Submit");
        submitBtn.setBackground(new Color(255, 240, 245));
        submitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                int pt = 17;
                int tb = 0, td = 0, tm = 0, ts = 0, tc = 0;
                boolean usernameExists = false;
                try {
                	
                    String SQL1 = "Select * from total where id=" + pt;
                    Connection con = My_CNX.getConnection();
                    PreparedStatement st = con.prepareStatement(SQL1);
                    ResultSet rs1 = st.executeQuery();

                    while (rs1.next()) {
                        tb = rs1.getInt("T_Blankets");
                        tm = rs1.getInt("T_Meals");
                        td = rs1.getInt("T_DryRations");
                        ts = rs1.getInt("T_Shoes");
                        tc = rs1.getInt("T_Clothes");

                    }


                    PreparedStatement pst = My_CNX.getConnection()
                        .prepareStatement("select * from donations where username = ?");
                    pst.setString(1, s);
                    ResultSet r1 = pst.executeQuery();
                    if (r1.next()) {
                        usernameExists = true;
                        JOptionPane.showMessageDialog(null, "You are a Registered Donor");

                    } else {
                        JOptionPane.showMessageDialog(null,
                            "You are new here? kindly press enter button to be Registered Donor");
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }

                PreparedStatement st, ps, st1;
                Connection con;
                ResultSet r, r1;
                int rs, t_r;

                query = "select * from donations where username= '" + s + "' "; // for prev values
                quantity = 0;
                id = 0;
                if (value == "Blankets") {
                    q = "UPDATE donations set Blankets= ? where username= '" + s + "' ";
                    value2 = Integer.parseInt(textField.getText());
                    t_q = "UPDATE total set T_Blankets= ? where id=" + pt;
                } else if (value == "Meals") {
                    q = "UPDATE donations set Meals= ? where username= '" + s + "' ";
                    value2 = Integer.parseInt(textField.getText());
                    t_q = "UPDATE total set T_Meals= ? where id=" + pt;

                } else if (value == "Dry_Rations") {

                    q = "UPDATE donations set Dry_Rations= ? where username= '" + s + "' ";
                    value2 = Integer.parseInt(textField.getText());
                    t_q = "UPDATE total set T_DryRations= ? where id=" + pt;
                } else if (value == "Shoes") {
                    q = "UPDATE donations set Shoes= ? where username= '" + s + "' ";
                    value2 = Integer.parseInt(textField.getText());
                    t_q = "UPDATE total set T_Shoes= ? where id=" + pt;
                } else if (value == "Clothes") {
                    q = "UPDATE donations set Clothes= ? where username= '" + s + "' ";
                    value2 = Integer.parseInt(textField.getText());
                    t_q = "UPDATE total set T_Clothes= ? where id=" + pt;
                } else {
                    JOptionPane.showMessageDialog(null, "input column doesnt exist");
                }
                try {
                    con = My_CNX.getConnection();
                    st = con.prepareStatement(q);
                    ps = con.prepareStatement(query);
                    st1 = con.prepareStatement(t_q);


                    r = ps.executeQuery();
                    while (r.next()) {
                        if (value == "Blankets") {
                            quantity = r.getInt("Blankets");
                            id = r.getInt("id");
                            st1.setInt(1, (value2 + tb));
                        } else if (value == "Meals") {
                            quantity = r.getInt("Meals");
                            id = r.getInt("id");
                            st1.setInt(1, (value2 + tm));
                        } else if (value == "Dry_Rations") {
                            quantity = r.getInt("Dry_Rations");
                            id = r.getInt("id");
                            st1.setInt(1, (value2 + td));
                        } else if (value == "Shoes") {
                            quantity = r.getInt("Shoes");
                            id = r.getInt("id");
                            st1.setInt(1, (value2 + ts));
                        } else if (value == "Clothes") {
                            quantity = r.getInt("Clothes");
                            id = r.getInt("id");
                            st1.setInt(1, (value2 + tc));
                        } else {
                            JOptionPane.showMessageDialog(null, "input column doesnt exist");
                        }
                    }

                    System.out.println(quantity);
                    System.out.println(id);
                    st.setInt(1, (value2 + quantity));


                    rs = st.executeUpdate();
                    t_r = st1.executeUpdate();


                    if (st.executeUpdate() != 0) {
                        JOptionPane.showMessageDialog(null, "Your donation has been made");

                    } else {
                        JOptionPane.showMessageDialog(null, "Error; try again");
                    }

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }

            }

        });

        submitBtn.setFont(new Font("Rockwell", Font.PLAIN, 18));
        submitBtn.setBounds(351, 528, 127, 50);
        getContentPane().add(submitBtn);

        JLabel lblRate = new JLabel("Rate/unit ($)");
        lblRate.setFont(new Font("Rockwell", Font.PLAIN, 20));
        lblRate.setBounds(62, 212, 147, 64);
        getContentPane().add(lblRate);

        textField_Rate = new JTextField();
        textField_Rate.setBackground(new Color(255, 240, 245));
        textField_Rate.setEditable(false);
        textField_Rate.setFont(new Font("Rockwell", Font.PLAIN, 20));
        textField_Rate.setBounds(328, 224, 259, 47);

        JComboBox donationsType = new JComboBox();
        donationsType.setBackground(new Color(255, 228, 225));

        donationsType.setModel(
            new DefaultComboBoxModel(new String[] {
                "Blankets",
                "Meals",
                "Dry_Rations",
                "Shoes",
                "Clothes"
            }));
        donationsType.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                value = donationsType.getSelectedItem().toString();

                if (value == "Meals") {
                    String rate = "5";
                    textField_Rate.setText(rate);
                    n = Integer.parseInt(rate);

                } else if (value == "Blankets") {
                    String rate = "20";
                    textField_Rate.setText(rate);
                    n = Integer.parseInt(rate);

                } else if (value == "Shoes") {
                    String rate = "15";
                    textField_Rate.setText(rate);
                    n = Integer.parseInt(rate);

                } else if (value == "Dry_Rations") {
                    String rate = "10";
                    textField_Rate.setText(rate);
                    n = Integer.parseInt(rate);

                } else if (value == "Clothes") {
                    String rate = "20";
                    textField_Rate.setText(rate);
                    n = Integer.parseInt(rate);

                }
            }
        });

        donationsType.setFont(new Font("Rockwell", Font.PLAIN, 20));
        donationsType.setBounds(328, 112, 259, 64);
        getContentPane().add(donationsType);

        JLabel lblDonType = new JLabel("Type of Donation");
        lblDonType.setFont(new Font("Rockwell", Font.PLAIN, 20));
        lblDonType.setBounds(62, 112, 259, 64);
        getContentPane().add(lblDonType);

        JLabel lblQuant = new JLabel("Quantity");
        lblQuant.setFont(new Font("Rockwell", Font.PLAIN, 20));
        lblQuant.setBounds(62, 307, 259, 64);
        getContentPane().add(lblQuant);

        getContentPane().add(textField_Rate);
        textField_Rate.setColumns(10);
        textField_cost = new JTextField();
        textField_cost.setBackground(new Color(255, 240, 245));
        textField_cost.setEditable(false);
        textField_cost.setFont(new Font("Rockwell", Font.PLAIN, 20));
        textField_cost.setBounds(332, 423, 259, 47);
        getContentPane().add(textField_cost);
        textField_cost.setColumns(10);

        JLabel lblCost = new JLabel("Donation Cost");
        lblCost.setBackground(new Color(255, 228, 225));
        lblCost.setFont(new Font("Rockwell", Font.PLAIN, 20));
        lblCost.setBounds(62, 411, 259, 64);
        getContentPane().add(lblCost);

        JLabel lblNewLabel_1 = new JLabel("New Donation");
        lblNewLabel_1.setFont(new Font("Rockwell", Font.BOLD, 28));
        lblNewLabel_1.setBounds(231, 24, 202, 57);
        getContentPane().add(lblNewLabel_1);

        textField = new JTextField();
        textField.setBackground(new Color(255, 240, 245));
        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                x = Integer.parseInt(textField.getText());
                if (n == 20 && value == "Blankets") {
                    cost = n * x;
                    textField_cost.setText("" + cost);
                } else if (n == 5 && value == "Meals") {
                    cost = n * x;
                    textField_cost.setText("" + cost);
                } else if (n == 10 && value == "Dry_Rations") {
                    cost = n * x;
                    textField_cost.setText("" + cost);
                } else if (n == 15 && value == "Shoes") {
                    cost = n * x;
                    textField_cost.setText("" + cost);
                } else if (n == 20 && value == "Clothes") {
                    cost = n * x;
                    textField_cost.setText("" + cost);
                } else
                    textField_cost.setText("Kindly Enter valid input");

            }
        });
        textField.setFont(new Font("Rockwell", Font.PLAIN, 20));
        textField.setEditable(true);
        textField.setColumns(10);
        textField.setBounds(328, 318, 259, 47);
        getContentPane().add(textField);

        JButton btnEnter = new JButton("New Donor");
        btnEnter.setBackground(new Color(255, 240, 245));
        btnEnter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Connection con = My_CNX.getConnection();
                ResultSet rs;

                try {
                    String f = "select * from users where username= '" + s + "' ";
                    PreparedStatement stmt = con.prepareStatement(f);
                    rs = stmt.executeQuery();
                    while (rs.next()) {
                        i_d = rs.getString("id");
                        System.out.println(i_d);
                    }

                } catch (SQLException ex) {
                    System.out.println(ex);
                }
                String SQL = "INSERT INTO donations (id,username,Blankets,Meals,Dry_Rations,Shoes,Clothes) " +
                    "VALUES(?,?,?,?,?,?,?)";

                long id1 = 0;

                try (Connection conn = My_CNX.getConnection(); 
                	PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {

                    pstmt.setString(1, i_d);
                    pstmt.setString(2, s);
                    pstmt.setInt(3, 0);
                    pstmt.setInt(4, 0);
                    pstmt.setInt(5, 0);
                    pstmt.setInt(6, 0);
                    pstmt.setInt(7, 0);

                    int affectedRows = pstmt.executeUpdate();

                    if (affectedRows > 0) {

                        try (ResultSet rs1 = pstmt.getGeneratedKeys()) {
                            if (rs1.next()) {
                                id1 = rs1.getLong(1);
                            }
                        } catch (SQLException ex) {
                            System.out.println(ex.getMessage());
                        }
                    }

                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());

                }

            }
        });
        btnEnter.setFont(new Font("Rockwell", Font.PLAIN, 18));
        btnEnter.setBounds(183, 528, 138, 53);
        getContentPane().add(btnEnter);

        JButton btnNewButton = new JButton("< back");
        btnNewButton.setBackground(new Color(255, 228, 225));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DonorsPage dp = new DonorsPage();
                dp.setVisible(true);
                dispose();
            }
        });
        btnNewButton.setFont(new Font("Rockwell", Font.PLAIN, 12));
        btnNewButton.setBounds(34, 46, 89, 23);
        getContentPane().add(btnNewButton);
       
    }
}