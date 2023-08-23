package bookShopp;

import java.sql.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JavaCrud {

	private JFrame frame;
	private JTable scrollpane;
	private JTextField txtbname;
	private JTextField txtedition;
	private JTextField txtprice;
	private JTextField txtbid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JavaCrud window = new JavaCrud();
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
	public JavaCrud() {
		initialize();
		Connect();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	 
	public void Connect()
	    {
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            con = DriverManager.getConnection("jdbc:mysql://localhost/guilherme", "root","");
	        }
	        catch (ClassNotFoundException ex)
	        {
	          ex.printStackTrace();
	        }
	        catch (SQLException ex)
	        {
	            ex.printStackTrace();
	        }
	 
	    }
	
	  public void table_load()
	    {
	     try
	     {
	    pst = con.prepareStatement("select * from book");
	    rs = pst.executeQuery();
	    scrollpane.setModel(DbUtils.resultSetToTableModel(rs));
	}
	     catch (SQLException e)
	     {
	     e.printStackTrace();
	  }
	    }
	
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 615, 475);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Registrion", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 73, 309, 175);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblBookName = new JLabel("Book Name");
		lblBookName.setFont(new Font("Dialog", Font.BOLD, 15));
		lblBookName.setBounds(12, 33, 85, 15);
		panel.add(lblBookName);
		
		JLabel lblEdition = new JLabel("Edition");
		lblEdition.setFont(new Font("Dialog", Font.BOLD, 15));
		lblEdition.setBounds(12, 69, 85, 15);
		panel.add(lblEdition);
		
		JLabel lblBookName_1_1 = new JLabel("Price");
		lblBookName_1_1.setFont(new Font("Dialog", Font.BOLD, 15));
		lblBookName_1_1.setBounds(12, 107, 85, 15);
		panel.add(lblBookName_1_1);
		
		txtbname = new JTextField();
		txtbname.setBounds(102, 31, 167, 19);
		panel.add(txtbname);
		txtbname.setColumns(10);
		
		txtedition = new JTextField();
		txtedition.setColumns(10);
		txtedition.setBounds(102, 67, 167, 19);
		panel.add(txtedition);
		
		txtprice = new JTextField();
		txtprice.setColumns(10);
		txtprice.setBounds(102, 105, 167, 19);
		panel.add(txtprice);
		
		scrollpane = new JTable();
		scrollpane.setBounds(333, 73, 270, 267);
		frame.getContentPane().add(scrollpane);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(12, 260, 96, 59);
		frame.getContentPane().add(btnSave);
			
		
		JButton btnSave_1 = new JButton("Exit");
		btnSave_1.setBounds(120, 260, 96, 59);
		frame.getContentPane().add(btnSave_1);
		
		JButton btnSave_1_1 = new JButton("Clear");
		btnSave_1_1.setBounds(225, 260, 96, 59);
		frame.getContentPane().add(btnSave_1_1);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(12, 342, 309, 76);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblBookName_1_1_1 = new JLabel("BookID");
		lblBookName_1_1_1.setBounds(31, 31, 60, 18);
		lblBookName_1_1_1.setFont(new Font("Dialog", Font.BOLD, 15));
		panel_1.add(lblBookName_1_1_1);
		
		txtbid = new JTextField();
		txtbid.setBounds(109, 31, 176, 19);
		txtbid.setColumns(10);
		panel_1.add(txtbid);
		
		JButton btnSave_1_1_1 = new JButton("Update");
		btnSave_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSave_1_1_1.setBounds(361, 352, 96, 46);
		frame.getContentPane().add(btnSave_1_1_1);
		
		JButton btnSave_1_1_1_1 = new JButton("Delete");
		btnSave_1_1_1_1.setBounds(488, 352, 96, 46);
		frame.getContentPane().add(btnSave_1_1_1_1);
	}
}
