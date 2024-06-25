import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;


import java.io.*;
	

public class RecieverPage extends My_CNX {

	private JPanel contentPane;
	String value,q,query,value2,t_q,query1;
	int quantity;
	int V;
	String id="1";
	int b,b1,c,c1,m,m1,p,p1,s,s1;
	private JTable table;
	private JTextField itemQuantity;
	int item=0;
	int count=3;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RecieverPage frame = new RecieverPage();
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
	public RecieverPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 887, 612);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 235));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		table = new JTable();
		table.setFont(new Font("Rockwell", Font.PLAIN, 11));
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setBounds(458, 145, 297, 96);
		contentPane.add(table);
		
		int d=0, e=0, f=0,g=0,h=0;
		String dd,ee,ff,gg,hh;
		
		try {
			
			PreparedStatement st;
			Connection con=My_CNX.getConnection();
			 ResultSet rs;
			
		String sql="SELECT * FROM total";
		st=con.prepareStatement(sql);
		 rs = st.executeQuery();
		 while(rs.next()) {
			  d = rs.getInt("T_Blankets");
			     e = rs.getInt("T_Meals");
			     f = rs.getInt("T_DryRations");
			     g = rs.getInt("T_Shoes");
			     h = rs.getInt("T_Clothes");
			  
		 }
		 if(d>0) {
			 dd="Available";
		 }
		 else
			 dd="Not Available";
		 if(e>0) {
			 ee="Available";
		 }
		 else
			 ee="Not Available";
		 if(f>0) {
			 ff="Available";
		 }
		 else
			 ff="Not Available";
		 if(g>0) {
			 gg="Available";
		 }
		 else
			 gg="Not Available";
		 if(h>0) {
			 hh="Available";
		 }
		 else
			 hh="Not Available";
		
		 table.setModel(new DefaultTableModel(
		 	new Object[][] {
		 		{"Blankets",dd},
		 		{"Meals", ee},
		 		{"DryRations", ff},
		 		{"Shoes", gg},
		 		{"Clothes", hh},
		 		//{null, null},
		 	},
		 	new String[] {
		 		"Item Name", "Quantity"
		 	}
		 ) {
		 	Class[] columnTypes = new Class[] {
		 		String.class, String.class
		 	};
		 	public Class getColumnClass(int columnIndex) {
		 		return columnTypes[columnIndex];
		 	}
		 });
		}
		catch (Exception ex) {
			JOptionPane.showMessageDialog(null,ex);
		}
	      Font headerFont = new Font("Tahoma", Font.PLAIN, 18);
	      
	      JComboBox itemBox = new JComboBox();
	      itemBox.setBackground(new Color(255, 240, 245));
	 
	      itemBox.addActionListener(new ActionListener() {
	      	public void actionPerformed(ActionEvent e) {
	      		value=itemBox.getSelectedItem().toString();
	      		item++;
	      		if(item>=3) {
	      			itemBox.disable();
	      			
	      		}
	      	}
	      });
			itemBox.setFont(new Font("Rockwell", Font.PLAIN, 20));
			itemBox.setModel(new DefaultComboBoxModel(new String[] {"Blankets", "Meals", "DryRations", "Shoes", "Clothes"}));
			itemBox.setBounds(191, 145, 182, 41);
			contentPane.add(itemBox);
			
			itemQuantity = new JTextField();
			itemQuantity.setFont(new Font("Rockwell", Font.PLAIN, 20));
			itemQuantity.setBackground(new Color(255, 240, 245));
			itemQuantity.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					value2=itemQuantity.getText();
						V=Integer.parseInt(value2);
						count=count-V;
						System.out.println(count);
				
				}
			});
			itemQuantity.setBounds(192, 260, 181, 41);
			contentPane.add(itemQuantity);
			itemQuantity.setColumns(1);

			
		
		JButton printBtn = new JButton("Confirm");
		printBtn.setBackground(new Color(255, 228, 225));
		printBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PreparedStatement st,ps,ps1,q1s,qds,st1;
				
				 Connection con=My_CNX.getConnection();
				 ResultSet r1,rq,rt;
				 String que;
				 int pt=17;
				 int r;
				 int d1=0,d2=0,d3=0,d4=0,d5=0;
				 if(V<=3 && count>=0){
				try {
					 query = "select T_Blankets,T_Meals,T_DryRations,T_Shoes,T_Clothes from total "; //for prev values
				     ps=con.prepareStatement(query);
				     rt=ps.executeQuery();
				     while(rt.next()){
				    	  b=rt.getInt("T_Blankets");
				    	  m=rt.getInt("T_Meals");
				    	  p=rt.getInt("T_DryRations");
				    	  s=rt.getInt("T_Shoes");
				    	 c=rt.getInt("T_Clothes");

				     }
				    		
					 
					 query1 = "select * from donated_items where id = '" +id+ "' "; //for prev values
				     ps=con.prepareStatement(query1);
				     rq=ps.executeQuery();
				     while(rq.next()) {
				    	 d1=rq.getInt("Blankets");
				    	 d2=rq.getInt("Meals");
				    	 d3=rq.getInt("DryRations");
				    	 d4=rq.getInt("Shoes");
				    	 d5=rq.getInt("Clothes");			    	 
				    	 
				     }
				    
				    	 if(value=="Blankets") {
				    	 q="UPDATE donated_items set Blankets= "+(V+d1);
				    	 t_q="UPDATE total set T_Blankets= "+(b-V) ;
				  
				    }
				    else if(value=="Meals") {
				    	q="UPDATE donated_items set Meals= "+(V+d2);
				    	 t_q="UPDATE total set T_Meals= " +(m-V) ;
				    }
				    else if(value=="DryRations") {
				    	    	
				    	
				    	q="UPDATE donated_items set DryRations= "+(V+d3);
				    	 t_q="UPDATE total set T_DryRations= " +(p-V) ;
				    	
				    }
				    else if(value=="Shoes") {
				    	q="UPDATE donated_items set Shoes= "+(V+d4);
				    	 t_q="UPDATE total set T_Shoes= " +(s-V) ;
				    
				    }
				    else if (value=="Clothes") {
				    	q="UPDATE donated_items set Clothes="+(V+d5);
				    	 t_q="UPDATE total set T_Clothes= " +(c-V);
				    }
				    else {
				    	JOptionPane.showMessageDialog(null, "input column doesnt exist");
				    }
		
						 st=con.prepareStatement(q);
						 st1=con.prepareStatement(t_q);
						 r=st.executeUpdate();
						 int r2=st1.executeUpdate();
					     
					    
					        if(st.executeUpdate()!=0){
								JOptionPane.showMessageDialog(null, "You have received the donations");
								

								
							}else {
								JOptionPane.showMessageDialog(null, "Error; try again");
							}
					        
					    
				}
				 
				  catch (Exception ex) {
								JOptionPane.showMessageDialog(null,ex);
							}
			}
			 else
		     {
				 JOptionPane.showMessageDialog(null, "You have reached your limit for donations for today");
		     }
			} 

			
		});
		printBtn.setFont(new Font("Rockwell", Font.PLAIN, 20));
		printBtn.setBounds(191, 436, 177, 41);
		contentPane.add(printBtn);
		
		
		
		JTextArea recieptText = new JTextArea();
		recieptText.setFont(new Font("Rockwell", Font.PLAIN, 13));
		recieptText.setBounds(418, 306, 418, 177);
		contentPane.add(recieptText);
		
		JLabel itemLbl = new JLabel("Item\r\n");
		itemLbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		itemLbl.setBounds(53, 143, 128, 41);
		contentPane.add(itemLbl);
		
		JLabel quantityLbl = new JLabel("Quantity\r\n");
		quantityLbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		quantityLbl.setBounds(53, 256, 128, 41);
		contentPane.add(quantityLbl);
		
		
		
		JLabel lblNewLabel_1 = new JLabel("Only 3 items can be claimed per day");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel_1.setBounds(53, 522, 371, 27);
		contentPane.add(lblNewLabel_1);
		
		JButton addBtn = new JButton("Add to Cart");
		addBtn.setBackground(new Color(255, 228, 225));
		addBtn.addActionListener(new ActionListener() {
			int i=0;
			public void actionPerformed(ActionEvent e) {
				
				  
				   if(itemQuantity.getText().isEmpty())
				   {
					   JOptionPane.showMessageDialog(null, "empty quantity");
				   }
				   else {
					   i++;
				    if(i == 1)
				    {
				    	recieptText.setText(recieptText.getText()+"\t CARE n SHARE \t\n"+"\t NUM\t PRODUCT\t QUANTITY \n\t"+i+"\t"+itemBox.getSelectedItem().toString()+"\t"+V+"\n\t");
				    }
				    else{       
				         recieptText.setText("\t\n\t"+ recieptText.getText()+i+"\t"+itemBox.getSelectedItem().toString()+"\t"+V+"\n\t");
				    }
				    JOptionPane.showMessageDialog(null, "Kindly Press OK to get Donation");
				}
			}
					
				

		});
		addBtn.setFont(new Font("Rockwell", Font.PLAIN, 20));
		addBtn.setBounds(186, 368, 187, 47);
		contentPane.add(addBtn);
		
		JLabel lblNewLabel_2 = new JLabel("Get Donations\r\n");
		lblNewLabel_2.setFont(new Font("Rockwell", Font.BOLD, 32));
		lblNewLabel_2.setBounds(328, 21, 238, 41);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("exit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Homepage hp = new Homepage(); 
				hp.setVisible(true);
				dispose();
			}
		});
				btnNewButton.setBounds(709, 38, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Print Receipt");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			try { 
				
				boolean complete = recieptText.print();
				if(complete) {
					JOptionPane.showMessageDialog(null, "done printing", "information",
					JOptionPane.INFORMATION_MESSAGE);
					
				}else {
					JOptionPane.showMessageDialog(null,"Printing!", "Printer", JOptionPane.ERROR_MESSAGE);
				}
			}catch(PrinterException ex){
				JOptionPane.showMessageDialog(null, ex);
				
			}
			
			}
		});
		btnNewButton_1.setFont(new Font("Rockwell", Font.PLAIN, 20));
		btnNewButton_1.setBounds(540, 494, 163, 56);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			
			
		});
		
		
		
		
	}
}


