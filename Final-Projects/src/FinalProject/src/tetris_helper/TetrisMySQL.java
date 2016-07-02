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

package tetris_helper;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

import tetris_frame.LoginFrame;
import tetris_frame.TetrisFrame;

public class TetrisMySQL 
{
	public TetrisMySQL()
	{
		
	}
	
	public int login(String HOST,String username,String password)
	{
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		try {
			myConn = DriverManager.getConnection("jdbc:mysql://"+HOST+":3306/tetris", "tetris" , "tetris");
			System.out.println("Database connection successful!(login)\n");
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from score where binary username = '"+username+
					"' and binary password = '" + password + "'");
			
			if (myRs.next()) 
			{
				int score = myRs.getInt("score");
				
				File file = new File("UserDefault.txt");
		   		try
		   		{
		   			FileWriter out = new FileWriter(file);
		   			String str = HOST + "\n" + username + "\n";
		   			out.write(str);
		   			out.close();
		   		}catch (Exception e1)
		   		{
		   			e1.printStackTrace();
		   		}
				return score;
			} else
			{
				return -1;
			}
		}
		catch (Exception exc) 
		{
			exc.printStackTrace();
		}
		return -1;
	}
	
	public boolean register(String HOST,String username,String password)
	{
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		try {
			myConn = DriverManager.getConnection("jdbc:mysql://"+HOST+":3306/tetris", "tetris" , "tetris");
			System.out.println("Database connection successful!(register)\n");
			myStmt = myConn.createStatement();
			myStmt.executeUpdate("insert into tetris.score value('"+username+"','"+password+"','0');");
			return true;
		}
		catch (Exception exc) 
		{
			exc.printStackTrace();
		}
		return false;
	}
	
	public String showLeaderBoard(String HOST)
	{
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		String nameString[] = new String[10];
		String scoreString[] = new String[10];
		try {
			myConn = DriverManager.getConnection("jdbc:mysql://"+HOST+":3306/tetris", "tetris" , "tetris");
			System.out.println("Database connection successful!(showLeaderBoard)\n");
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from score order by score desc");
			int a=0;
			while (myRs.next() && a<10) 
			{
				nameString[a] = myRs.getString("username");
				scoreString[a] = myRs.getString("score");
				System.out.println(myRs.getString("username")+" "+myRs.getString("score"));
				a++;
			} 
			String str = "";
			for (int i=0;i<a;i++)
			str = str + nameString[i] + " : "+ scoreString[i] + "\n";
			return str;
		}
		catch (Exception exc) 
		{
			exc.printStackTrace();
		}
		return "ERROR!";
	}
	
	public void updateScore(String HOST,String username,int bestscore)
	{
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		try {
			myConn = DriverManager.getConnection("jdbc:mysql://"+HOST+":3306/tetris", "tetris" , "tetris");
			System.out.println("Database connection successful!(updateScore)\n");
			myStmt = myConn.createStatement();
			myStmt.executeUpdate("update score set score='"+bestscore+"' where binary username = '"+username+"';");
		}
		catch (Exception exc) 
		{
			exc.printStackTrace();
		}
	}
}
