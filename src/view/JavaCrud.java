package view;

import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JavaCrud {

	private JFrame frame;
	private JTextField txtbname;
	private JTextField txtprice;
	private JTextField txtedition;
	private JTable table;
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
		table_load();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	public void Connect()
    {
		
		try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/javacrud", "root","");
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
	    pst = con.prepareStatement("select * from livro");
	    rs = pst.executeQuery();
	    table.setModel(DbUtils.resultSetToTableModel(rs));
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
		frame.setBounds(100, 100, 716, 461);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Livraria Milenio");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 33));
		lblNewLabel.setBounds(186, 11, 279, 80);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Registro", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 77, 349, 181);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Nome do livro");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_2.setBounds(21, 67, 129, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Preço");
		lblNewLabel_2_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_2_1.setBounds(21, 105, 89, 14);
		panel.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Edição");
		lblNewLabel_2_1_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_2_1_1.setBounds(21, 141, 89, 14);
		panel.add(lblNewLabel_2_1_1);
		
		txtbname = new JTextField();
		txtbname.setBounds(139, 65, 159, 20);
		panel.add(txtbname);
		txtbname.setColumns(10);
		
		txtprice = new JTextField();
		txtprice.setColumns(10);
		txtprice.setBounds(139, 103, 159, 20);
		panel.add(txtprice);
		
		txtedition = new JTextField();
		txtedition.setColumns(10);
		txtedition.setBounds(139, 139, 159, 20);
		panel.add(txtedition);
		
		JButton btnNewButton = new JButton("Salvar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String bname,edition,price;
				bname = txtbname.getText();
				edition = txtedition.getText();
				price = txtprice.getText();
				
				try {
				pst = con.prepareStatement("insert into livro(bname,edition,price)values(?,?,?)");
				pst.setString(1, bname);
				pst.setString(2, edition);
				pst.setString(3, price);
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Livro Salvo!!!!!");
				table_load();
				          
				txtbname.setText("");
				txtedition.setText("");
				txtprice.setText("");
				txtbname.requestFocus();
				   }
				 
				catch (SQLException e1)
				        {
				e1.printStackTrace();
				
				
				
			}
		}});
		btnNewButton.setBounds(20, 269, 106, 50);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Limpar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtbname.setText("");
				txtedition.setText("");
				txtprice.setText("");
				txtbname.requestFocus();
				
				
			}
		});
		btnNewButton_1.setBounds(151, 269, 92, 50);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Sair");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
				
			}
		});
		btnNewButton_2.setBounds(255, 269, 92, 50);
		frame.getContentPane().add(btnNewButton_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(390, 77, 286, 210);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Procurar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(25, 343, 334, 68);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Livro ID");
		lblNewLabel_2_1_1_1.setBounds(27, 21, 73, 18);
		lblNewLabel_2_1_1_1.setFont(new Font("Arial", Font.BOLD, 15));
		panel_1.add(lblNewLabel_2_1_1_1);
		
		txtbid = new JTextField();
		txtbid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
					try {
					          
					            String id = txtbid.getText();
					 
					                pst = con.prepareStatement("select bname,edition,price from livro where id = ?");
					                pst.setString(1, id);
					                ResultSet rs = pst.executeQuery();
					 
					            if(rs.next()==true)
					            {
					              
					                String name = rs.getString(1);
					                String edition = rs.getString(2);
					                String price = rs.getString(3);
					                
					                txtbname.setText(name);
					                txtedition.setText(edition);
					                txtprice.setText(price);
					                
					                
					            }  
					            else
					            {
					             txtbname.setText("");
					             txtedition.setText("");
					                txtprice.setText("");
					                
					            }
					            
					 
					 
					        }
					catch (SQLException ex) {
					          
					        }
					}
				
				
		});
		txtbid.setBounds(110, 21, 139, 20);
		txtbid.setColumns(10);
		panel_1.add(txtbid);
		
		JButton btnNewButton_3 = new JButton("Uptdate");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					
					
					String bname,edition,price,bid;
					
					
					bname = txtbname.getText();
					edition = txtedition.getText();
					price = txtprice.getText();
					bid  = txtbid.getText();
					
					 try {
							pst = con.prepareStatement("update livro set bname= ?,edition=?,price=? where id =?");
							pst.setString(1, bname);
				            pst.setString(2, edition);
				            pst.setString(3, price);
				            pst.setString(4, bid);
				            pst.executeUpdate();
				            JOptionPane.showMessageDialog(null, "Livro Atualizado!!!!!");
				            table_load();
				           
				            txtbname.setText("");
				            txtedition.setText("");
				            txtprice.setText("");
				            txtbname.requestFocus();
						}

			            catch (SQLException e1) {
							
							e1.printStackTrace();
						}
		
				}
				
				
				
			
		});
		btnNewButton_3.setBounds(401, 343, 112, 55);
		frame.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Delete");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
                String bid;
                bid  = txtbid.getText();
	
	 try {
			pst = con.prepareStatement("delete from livro where id =?");
	
            pst.setString(1, bid);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Livro deletado!!!!!");
            table_load();
           
            txtbname.setText("");
            txtedition.setText("");
            txtprice.setText("");
            txtbname.requestFocus();
		}

        catch (SQLException e1) {
			
			e1.printStackTrace();
		}
				
				
				
				
				
				
			}
		});
		btnNewButton_4.setBounds(540, 343, 112, 55);
		frame.getContentPane().add(btnNewButton_4);
	}
}
