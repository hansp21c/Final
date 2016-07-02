//                         Tetris Game By 
//                    Jamesir Yao and Jerry Shin
//                            _ooOoo_  
//                           o8888888o  
//                           88" . "88  
//                           (| -_- |)  
//                            O\ = /O  
//                        ____/`---'\____  
//                      .   ' \\| |// `.  
//                       / \\||| : |||// \  
//                     / _||||| -:- |||||- \  
//                       | | \\\ - /// | |  
//                     | \_| ''\---/'' | |  
//                      \ .-\__ `-` ___/-. /  
//                   ___`. .' /--.--\ `. . __  
//                ."" '< `.___\_<|>_/___.' >'"".  
//               | | : `- \`.;`\ _ /`;.`/ - ` : | |  
//                 \ \ `-. \_ __\ /__ _/ .-` / /  
//         ======`-.____`-.___\_____/___.-`____.-'======  
//                            `=---='  
//                         佛祖保佑 永无BUG             
//         .............................................  
package tetris_frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import tetris_helper.TetrisMySQL;

import java.sql.*;
import java.util.Properties;

public class LoginFrame extends JFrame implements ActionListener
{
	private String HOST = "localhost";
	
	private JPanel shapePanel;
	public String usernameInput;
	public String passwordInput; 
	
	private Connection myConn = null;
	private Statement myStmt = null;
	private ResultSet myRs = null;
	
	private GridBagLayout layout;
	private GridBagConstraints gbc;
	
	private JButton btn;
	private JButton btn2;
	
	private JTextField username;
	private JTextField host;
	private JPasswordField password;
	
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JLabel hostLabel;

	private int score;
	
	private TetrisMySQL mySQL;
	private void work()
	{
		TetrisFrame tetrisFrame = new TetrisFrame(HOST,usernameInput,score); 
		tetrisFrame.setLocation(500,200);
		tetrisFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		tetrisFrame.pack();
		tetrisFrame.setVisible(true);
		tetrisFrame.setResizable(false);
	}
	
	public LoginFrame() throws IOException 
	{
		super("Login");
		
		this.setLayout(new BorderLayout());
		mySQL = new TetrisMySQL();
		
		this.setIconImage(this.getToolkit().getImage(getClass().getResource("icon.png"))); 
		
		layout = new GridBagLayout();
	   	layout.columnWidths = new int[] {35,35,35,35};
	   	layout.rowHeights = new int[]{30,30,30,30};
	   	shapePanel = new JPanel(layout);
	   	
		gbc = new GridBagConstraints();
		gbc.fill =GridBagConstraints.BOTH;
   		gbc.insets = new Insets(2,2,2,2);
   		
   		HOST = "";
   		usernameInput = "";
   		passwordInput = "";
   		File file = new File("UserDefault.txt");
   		try
   		{
   			FileReader in = new FileReader(file);
   			char byt[] = new char[10000];
   			int length = in.read(byt);
   			int temp=0;
   			for (int i=0;i<length;i++)
   			if (byt[i]!='\n')
   			{
   				HOST += byt[i];
   			} else 
   			{
   				temp = i;
   				break;
   			}
   			
   			for (int i=temp+1;i<length;i++)
   	   		if (byt[i]!='\n')
   	   		{
   	   			usernameInput += byt[i];
   	   		}
   			
   			
   			in.close();
   		}catch (Exception e1)
   		{
   			e1.printStackTrace();
   		}
   		
   		setGBC(0,3,2,1);
   		btn = new JButton("Login");
   		btn.addActionListener(this);
		shapePanel.add(btn,gbc);
		
		setGBC(2,3,2,1);
   		btn2 = new JButton("Register");
   		btn2.addActionListener(this);
		shapePanel.add(btn2,gbc);
	   
		setGBC(1,0,3,1);
		host= new JTextField(HOST);
		shapePanel.add(host,gbc);
		
		setGBC(0,0,1,1);
		hostLabel = new JLabel("Host");
		shapePanel.add(hostLabel,gbc);
		
		setGBC(1,1,3,1);
		username= new JTextField(usernameInput);
		shapePanel.add(username,gbc);
		
		setGBC(0,1,1,1);
		usernameLabel = new JLabel("Username");
		shapePanel.add(usernameLabel,gbc);
		
		setGBC(1,2,3,1);
		password = new JPasswordField("");
		shapePanel.add(password,gbc);
		
		setGBC(0,2,1,1);
		passwordLabel = new JLabel("Password");
		shapePanel.add(passwordLabel,gbc);
		
		add(shapePanel, BorderLayout.CENTER);
		this.setSize(200,150);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		usernameInput = username.getText(); 
		passwordInput = password.getText();
		HOST = host.getText();
		
		if (e.getSource() == btn) 
		{
			int temp = mySQL.login(HOST, usernameInput, passwordInput);
			if (temp>=0)
			{
				score = temp;
				work();
				this.setVisible(false);
			} else
			{
				JOptionPane.showMessageDialog(LoginFrame.this,
						"Wrong Username/Password!",
						"Error",
						JOptionPane.PLAIN_MESSAGE );
			}
			
		} else
		if (e.getSource() == btn2) 
		{
			if (mySQL.register(HOST, usernameInput, passwordInput))
			{
				JOptionPane.showMessageDialog(LoginFrame.this,
						"Succeed!",
						"Succeed",
						JOptionPane.PLAIN_MESSAGE );
			} else
			{
				JOptionPane.showMessageDialog(LoginFrame.this,
						"Register Failed!",
						"Error",
						JOptionPane.PLAIN_MESSAGE );
			}
		}
	}
	
	
	private void setGBC(int gridx,int gridy,int width,int height)
	{
		gbc.gridx = gridx;
		gbc.gridy = gridy;
		gbc.gridwidth = width;
		gbc.gridheight = height;
	}
	
	public static void main(String[] args) throws SQLException, IOException 
	{
			LoginFrame loginFrame = new LoginFrame();
			loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			loginFrame.setSize(220, 170);
			loginFrame.setResizable(false);
			loginFrame.setLocationRelativeTo(null);
			loginFrame.setVisible(true);
	}

}
