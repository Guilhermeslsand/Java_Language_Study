import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import escola.BancoCrud1;
import escola.JavaCrud;

public class JavaCrud {

	private JFrame frame;
	private JTextField jTextField1;
	private JTextField jTextField2;
	private JTextField jTextField3;
	private JTextField jTextField4;

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
	}
	
	Connection con = null;
    Statement stmt;
    ResultSet rs;
    private JTable jTable1;

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("serial")
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(UIManager.getColor("Button.shadow"));
		frame.setBackground(new Color(0, 0, 0));
		frame.getContentPane().setForeground(new Color(114, 159, 207));
		frame.setBounds(100, 100, 663, 504);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 230, 614, 149);
		frame.getContentPane().add(scrollPane);
		
		jTable1 = new JTable();
		scrollPane.setViewportView(jTable1);
		jTable1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		jTable1.setCellSelectionEnabled(true);
		jTable1.setColumnSelectionAllowed(true);
		jTable1.setFillsViewportHeight(true);
		jTable1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Matricula", "Nome", "Ano", "Turma", "Endere\u00E7o"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		jTable1.getColumnModel().getColumn(0).setResizable(false);
		jTable1.getColumnModel().getColumn(1).setResizable(false);
		jTable1.getColumnModel().getColumn(2).setResizable(false);
		jTable1.getColumnModel().getColumn(3).setResizable(false);
		jTable1.getColumnModel().getColumn(4).setResizable(false);
		
		JLabel lblNewLabel = new JLabel("Banco de Dados Escola Qualquer");
		lblNewLabel.setFont(new Font("L M Roman Caps10", Font.BOLD, 24));
		lblNewLabel.setBounds(109, 0, 502, 48);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblRoll = new JLabel("Matricula");
		lblRoll.setFont(new Font("Dialog", Font.BOLD, 16));
		lblRoll.setBounds(24, 87, 73, 15);
		frame.getContentPane().add(lblRoll);
		
		JLabel lblName = new JLabel("Nome");
		lblName.setFont(new Font("Dialog", Font.BOLD, 16));
		lblName.setBounds(24, 129, 51, 15);
		frame.getContentPane().add(lblName);
		
		JLabel lblRoll_1_1 = new JLabel("Endereço\n");
		lblRoll_1_1.setFont(new Font("Dialog", Font.BOLD, 16));
		lblRoll_1_1.setBounds(24, 169, 73, 15);
		frame.getContentPane().add(lblRoll_1_1);
		
		jTextField1 = new JTextField();
		jTextField1.setBounds(109, 86, 73, 19);
		frame.getContentPane().add(jTextField1);
		jTextField1.setColumns(10);
		
		jTextField2 = new JTextField();
		jTextField2.setColumns(10);
		jTextField2.setBounds(107, 128, 140, 19);
		frame.getContentPane().add(jTextField2);
		
		JLabel lblClass = new JLabel("Ano");
		lblClass.setFont(new Font("Dialog", Font.BOLD, 16));
		lblClass.setBounds(367, 87, 51, 15);
		frame.getContentPane().add(lblClass);
		
		jTextField3 = new JTextField();
		jTextField3.setColumns(10);
		jTextField3.setBounds(424, 86, 60, 19);
		frame.getContentPane().add(jTextField3);
		
		jTextField4 = new JTextField();
		jTextField4.setColumns(10);
		jTextField4.setBounds(424, 128, 60, 19);
		frame.getContentPane().add(jTextField4);
		
		final JTextArea jTextArea1 = new JTextArea();
		jTextArea1.setBounds(109, 170, 153, 48);
		frame.getContentPane().add(jTextArea1);
		
		JLabel lblSection = new JLabel("Turma");
		lblSection.setFont(new Font("Dialog", Font.BOLD, 16));
		lblSection.setBounds(367, 129, 60, 16);
		frame.getContentPane().add(lblSection);
		
		final JButton jButton1 = new JButton("Inserir");
		jButton1.setBounds(38, 393, 96, 25);
		frame.getContentPane().add(jButton1);
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				 try {
				        Class.forName("org.mariadb.jdbc.Driver");
				        con = DriverManager.getConnection(
				            "jdbc:mariadb://localhost:3306/student", "root",
				            "02110404");
				        stmt = con.createStatement();
				        String rollno = jTextField1.getText();
				        String name = jTextField2.getText();
				        String clss = jTextField3.getText();
				        String sec = jTextField4.getText();
				        String adr = jTextArea1.getText();
				        String INSERT = "INSERT INTO record VALUES('"
				                        + rollno + "','" + name + "','"
				                        + clss + "','" + sec + "','" + adr
				                        + "');";
				        stmt.executeUpdate(INSERT);
				        JOptionPane.showInputDialog(
				         this, "Record Added Successfully");
				        jButton1.setEnabled(true);
				    }
				    catch (Exception el) {
				        JOptionPane.showInputDialog(
				            this, "Error In Connectivity");
				    }
				}
			});
		
		
		JButton jButton2 = new JButton("Ver Dados");
		jButton2.setBounds(187, 393, 96, 25);
		frame.getContentPane().add(jButton2);
		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
				try {
					Class.forName("org.mariadb.jdbc.Driver");
			        con = DriverManager.getConnection(
			            "jdbc:mariadb://localhost:3306/student", "root",
			            "02110404");
			        stmt = con.createStatement();
					String query = "SELECT* FROM record;";
					ResultSet rs = stmt.executeQuery(query);
					while (rs.next()) {
						String rollno = rs.getString("rollno");
						String name = rs.getString("name");
						String clss = rs.getString("class");
						String sec = rs.getString("section");
						String adr = rs.getString("address");
						model.addRow(
								new Object[] { rollno, name, clss, sec, adr });
					}
					//rs.close();
					//stmt.close();
					//con.close();
				}
				catch (Exception el) {
					JOptionPane.showInputDialog(this,"Error In Connectivity");
				}
			}
		});
		
		JButton btnNewButton_1_1 = new JButton("Limpar");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jTextField1.setText("");
				jTextField2.setText("");
				jTextField3.setText("");
				jTextField4.setText("");
				jTextArea1.setText("");
				DefaultTableModel dm
				    = (DefaultTableModel)jTable1.getModel();
				dm.getDataVector().removeAllElements();
				jTable1.repaint();
			}
		});
		btnNewButton_1_1.setBounds(331, 393, 96, 25);
		frame.getContentPane().add(btnNewButton_1_1);
		
		JButton btnNewButton_1_1_1 = new JButton("Sair");
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_1_1_1.setBounds(479, 393, 96, 25);
		frame.getContentPane().add(btnNewButton_1_1_1);
		
	public JTable getJTable1() {
		return jTable1;
	}
}
