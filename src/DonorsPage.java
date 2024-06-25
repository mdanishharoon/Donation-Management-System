import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DonorsPage extends JFrame {

    private JPanel contentPane;
    private JTable table;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {


        DonorsPage frame = new DonorsPage();
        frame.setTitle("Donations Type");
        frame.setVisible(true);
    }

    /**
     * Create the frame.
     */
   public void GetTopDonor() {
	   
	   Connection con = My_CNX.getConnection();
       ResultSet rs, rs1;
       int c = 0, c1 = 0;
       String g = null;
       try {
           String f = "select id,username,(MAX(Blankets+Meals+Shoes+Clothes+Dry_Rations)) from donations";
           PreparedStatement stmt = con.prepareStatement(f);
           rs = stmt.executeQuery();
           while (rs.next()) {
               c = rs.getInt(1);
               g = rs.getString(2);

           }
           String topQ = "Select SUM(Blankets+Meals+Shoes+Clothes+Dry_Rations) from donations where id=" + c;
           PreparedStatement stmt1 = con.prepareStatement(topQ);
           rs1 = stmt1.executeQuery();
           while (rs1.next()) {
               c1 = rs1.getInt(1);
           }
           JOptionPane.showMessageDialog(null, "Username of Top Donor is " + g + " and " + "Total Donations by Top Donor is " + c1 + " Items");

       } catch (SQLException ex) {
           System.out.println(ex);
       }
	   
   } 
    
   public void GetDonReqs() {
	   Connection con = My_CNX.getConnection();
       ResultSet rs;
       PreparedStatement stmt;
       int a1 = 0, a2 = 0, a3 = 0, a4 = 0, a5 = 0;
       String reQ = "Select * from total";
       try {
           stmt = con.prepareStatement(reQ);
           rs = stmt.executeQuery();
           while (rs.next()) {
               a1 = rs.getInt(2);
               a2 = rs.getInt(3);
               a3 = rs.getInt(4);
               a4 = rs.getInt(5);
               a5 = rs.getInt(6);

           }
           if (a1 == 0) {
               JOptionPane.showMessageDialog(null, "Blankets are Required");
           } else if (a2 == 0) {
               JOptionPane.showMessageDialog(null, "Meals are Required");
           } else if (a3 == 0) {
               JOptionPane.showMessageDialog(null, "Dry Rations are Required");
           } else if (a4 == 0) {
               JOptionPane.showMessageDialog(null, "Shoes are Required");
           } else if (a5 == 0) {
               JOptionPane.showMessageDialog(null, "Clothes are Required");
           } else {
               JOptionPane.showMessageDialog(null, "All Items are sufficient");
           }
       } catch (SQLException ex) {
           System.out.println(ex);
       }


   } 
   
    public DonorsPage() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 595);
		this.setSize(new Dimension(729, 610));
		this.setPreferredSize(new Dimension(729, 610));
        contentPane = new JPanel();
        contentPane.setBackground(new Color(135, 206, 235));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setSize(new Dimension(600, 595));
        JButton donationBtn = new JButton("Make a Donation");
        donationBtn.setBackground(new Color(255, 228, 225));
        donationBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                DonationForm df = new DonationForm();
                df.setVisible(true);
                dispose();

            }
        });
        donationBtn.setFont(new Font("Rockwell", Font.PLAIN, 24));
        donationBtn.setBounds(125, 196, 457, 91);
        contentPane.add(donationBtn);

        JButton donationrequestsBtn = new JButton("Donation Requests");
        donationrequestsBtn.setBackground(new Color(255, 228, 225));
        donationrequestsBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GetDonReqs();
            }
        });
        donationrequestsBtn.setFont(new Font("Rockwell", Font.PLAIN, 24));
        donationrequestsBtn.setBounds(125, 308, 457, 91);
        contentPane.add(donationrequestsBtn);

        JButton topdonorBtn = new JButton("Top Donor");
        topdonorBtn.setBackground(new Color(255, 228, 225));
        topdonorBtn.setFont(new Font("Rockwell", Font.PLAIN, 24));
        topdonorBtn.addActionListener(new ActionListener() {
            public void
            actionPerformed(ActionEvent e) {
            	
            	GetTopDonor();
            	
            }
        });
        topdonorBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
        topdonorBtn.setBounds(125, 410, 457, 91);
        contentPane.add(topdonorBtn);

        JLabel lblNewLabel = new JLabel("Welcome to Care n Share");
        lblNewLabel.setFont(new Font("Rockwell", Font.BOLD, 28));
        lblNewLabel.setBounds(185, 58, 349, 91);
        contentPane.add(lblNewLabel);
        
        JButton btnNewButton_1 = new JButton("Sign out");
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		LoginPage l2 = new LoginPage();
        		l2.setVisible(true);
        		dispose();
        		
        		
        	}
        });
        btnNewButton_1.setBounds(10, 22, 127, 31);
        contentPane.add(btnNewButton_1);


    }
}