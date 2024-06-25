import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;



public class Homepage extends JFrame {
	public static String s=LoginPage.username;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Homepage frame = new Homepage();
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
	public Homepage() {
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 777, 512);
		this.setPreferredSize(new Dimension(777, 512));
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 235));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel homepageLbl = new JLabel("Homepage");
		homepageLbl.setFont(new Font("Rockwell", Font.BOLD, 32));
		homepageLbl.setBounds(284, 47, 186, 54);
		contentPane.add(homepageLbl);
		
		JButton receiverBtn = new JButton("Reciever");
		receiverBtn.setBackground(new Color(255, 228, 225));
		receiverBtn.setFont(new Font("Rockwell", Font.PLAIN, 30));
		receiverBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				recieverHomepage lr=new recieverHomepage();
				lr.setVisible(true);
				dispose();
				
				
				
			}
		});
		receiverBtn.setBounds(63, 167, 274, 237);
		contentPane.add(receiverBtn);
		
		JButton donorBtn = new JButton("Donor");
		donorBtn.setBackground(new Color(255, 228, 225));
		donorBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
			
				LoginPage lp=new LoginPage();
				
				  lp.setVisible(true); 
				  
				dispose();
				 
			
			}
		});
		donorBtn.setFont(new Font("Rockwell", Font.PLAIN, 30));
		donorBtn.setBounds(415, 167, 274, 237);
		contentPane.add(donorBtn);
	}
}
