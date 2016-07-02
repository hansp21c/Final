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
package tetris_panel;

import javax.swing.*;

import tetris_helper.TetrisPlayer;

import java.util.Arrays;
import java.util.Observable;
import java.util.Random;

public class TetrisModel extends Observable implements Runnable 
{
	public int maxX,maxY;
	public int[][] map;
	
	private int x,y,kind;
	
	private boolean checked;
	private boolean paused;
	private boolean gameover;
	
	private int score;
	private int timeInterval;
	
	private double speedChangeRate = 0.75;              
	
	private TetrisPlayer player;
	
	
	
	private static final int[][][] BLOCK=
	{
		{},
		{{0,0,1},{0,0,2},{1,0,-1},{0,0,0}},
		{{0,0,0},{0,-1,0},{0,2,0},{0,1,0}},
		{{0,0,0},{0,1,0},{0,2,0},{0,0,1}},
		{{0,-1,0},{0,0,0},{0,0,1},{0,0,2}},
		{{0,0,0},{0,-1,0},{0,-2,0},{1,0,-1}},
		{{1,0,-1},{2,0,-2},{0,0,0},{0,1,0}},
		{{0,-1,0},{0,-2,0},{0,0,0},{0,0,1}},
		{{1,0,-1},{2,0,-2},{0,0,0},{0,-1,0}},
		{{0,1,0},{0,2,0},{0,0,0},{1,0,-1}},
		{{0,1,0},{0,0,0},{0,0,1},{0,0,2}},
		{{0,-1,0},{0,0,0},{0,1,0},{0,0,1}},
		{{0,-1,0},{0,0,0},{0,0,1},{1,0,-1}},
		{{1,0,-1},{0,1,0},{0,-1,0},{0,0,0}},
		{{0,1,0},{0,0,0},{0,0,1},{1,0,-1}},
		{{0,1,0},{0,0,1},{0,1,1},{0,0,0}},
		{{1,-1,0},{0,0,0},{0,0,1},{0,1,1}},
		{{0,-1,0},{1,0,-1},{0,-1,1},{0,0,0}},
		{{0,1,0},{0,0,1},{0,-1,1},{0,0,0}},
		{{0,1,0},{0,0,0},{1,0,-1},{0,1,1}}
	};
	
	private static final int[] GAMEOVER = 
	{0,1,0,0,0,1,2,0,2,1,0,0,1,1,1,0,0,1,0,1};

	static final int[] ROTATE = 
	{0,2,1,4,5,6,3,8,9,10,7,12,13,14,11,15,17,16,19,18};

	public TetrisModel(int maxX, int maxY)
	{
		this.maxX = maxX;
		this.maxY = maxY;
		this.x = maxX/2;
		this.y = 1;
		gamestart();
	}

	public void gamestart()
	{
	    init();
	    renew();
	    player.playBackgroundMusic();
	}

	private void gameover()
	{
		JOptionPane.showMessageDialog(null,"you failed",
				"Game Over",JOptionPane.INFORMATION_MESSAGE);
		player.stopBackgroundMusic();
	}

	
	private void init()
	{
		timeInterval = 200;                   
	    
	    gameover = false;
	    score = 0;
	    paused = false;
	    
	    x=maxX/2;
	    y = newY(kind);
	    kind=RandomKind();
	    
	    map = new int[maxX][];
	    for (int i = 0; i < maxX; ++i) 
	    {
	    	map[i] = new int[maxY];
	        Arrays.fill(map[i], 0);
	    }   
	    
	    player = new TetrisPlayer();
	}

	
	private int newY(int k)
	{
		int y=0;
		if (k==3||k==7||k==11||k==12||k==14||k==15||k==16||k==17||k==18||k==19) y=-1;
		else if (k==4||k==10||k==1) y=-2;
		return y;
	}
	
	public void renew()
	{
        setChanged();      
        notifyObservers();
	}
	

	private boolean isGameover(int kind,int y)
	{
		boolean temp = false;
		if (y<GAMEOVER[kind])
		{
			temp = true;
			renew();
			gameover();
		}
		return temp;
	}
	
	public int getX() 
	{
		return x;
	}

	public int getY() 
	{
		return y;
	}

	public int getKind() 
	{
		return kind;
	}
	
	public int getScore()
	{
		return score;
	}

	public void run()
	{
	    while (true) 
	    {
	    	checked = false;
	    	sleep(timeInterval);
	        if (!paused && !gameover) 
	        {
	        	if (!canNotMove(kind,x,y+1))
	        	{
	        		y++;
	        	}
	        	else
	        	{
	        		if (!checked)
	        		{
	        			this.setDown();
	        			if (isGameover(kind,y)) gameover = true;
		        		kind = RandomKind();
		        		y = newY(kind);
		        		x = maxX/2;
	        			eliminateLine();
	        			checked = true;
	        		} 
	        		else
	        		{
	        			if (isGameover(kind,y)) gameover = true;
	        			kind = RandomKind();
		        		y = newY(kind);
		        		x = maxX/2;
	        		}
	        	}
	            renew();
	        }
	    }
	}
	
	public void speedUp() 
	{
	    timeInterval *= speedChangeRate;
	}

	public void speedDown() 
	{
	    timeInterval /= speedChangeRate;
	}
	
	public boolean getPauseState()
	{
		return paused;
	}

	public void changePauseState() 
	{
	    paused = !paused;
	}

	public void move(int dx,int dy)
	{
		if (!this.paused)
    	{
    		if (!this.canNotMove(this.kind,this.x+dx,this.y+dy))
    		{
    			this.x+=dx;
    			this.y+=dy;
    			this.renew();
    		}
    		else if (dy>0 && !this.checked)
    		{
    			this.checked = true;
    			this.setDown();
    			this.eliminateLine();
    		} 
    	}
	}
	
	private boolean legal(int x,int y)
	{
		if ((x<=0)||(x>=maxX)||(y>=maxY)||((x>0)&&(x<maxX)&&(y>0)&&(y<maxY)&&(map[x][y]>0)))
			return false;
		else 
			return true;
	}
	
	private boolean canNotMove(int kind,int x,int y)
	{
		boolean temp = false;
		for (int i=0;i<4;i++)
		if (!legal(x+BLOCK[kind][i][1],y+BLOCK[kind][i][2]))
		{
			temp = true; 
			break;
		}
		return temp;
	}
	
	private void flicker(int line,int[][] tempMap) 
	{
		int i,j;
		int[][] temp;
		temp = new int[maxX+1][];
		for (i=1;i<maxX;i++)
		{
			temp[i] = new int[maxY+1];
			for (j=1;j<maxY;j++)
				temp[i][j] = map[i][j];
		}
		for (i=1;i<maxX;i++)
			for (j=1;j<maxY;j++)
			map[i][j]=tempMap[i][j];
		renew();
		sleep(50);
        for (i=1;i<maxX;i++)
			for (j=1;j<maxY;j++)
			map[i][j]=temp[i][j];
        renew();
        sleep(50);
	}
	
	private void sleep(int time)
	{
		try 
        {
        	Thread.sleep(time);
		} 
        catch (InterruptedException e) 
        {
			e.printStackTrace();
		}
	}
	
	private void eliminateLine()
	{
		int i,j,k=maxY-1;
		boolean bj = false,temp;
		int[][] tempMap;
		tempMap = new int[maxX+1][];
		for (i=1;i<maxX;i++)
		{
			tempMap[i] = new int[maxY+1];
			for (j=1;j<maxY;j++)
				tempMap[i][j] = map[i][j];
		}
		for(j=k;j>0;j--)
		{
			temp=true;
			for (i=1; i<maxX; i++)
			if (map[i][j]==0) 
			{
				temp=false;
				break;
			}
			if (temp)
			{
				for (i=1; i<maxX; i++)
					tempMap[i][j]=0;
			}
		}	
		player.playSoundEffect();
		flicker(k,tempMap);
		flicker(k,tempMap); 
		bj= false;
		do
		{
			for (j=maxY-1;j>0;)
			{
				temp = true;
				for (i=1;i<maxX;i++)
				if (map[i][j]==0)
				{
					temp=false;
					break;
				}
				if (temp)
				{
					k=j;
					for (int jj=k;jj>0;jj--)
						for (i=1;i<maxX;i++)
							map[i][jj]=map[i][jj-1];
					score+=10;
				}
				else j--;
			}
		} while (bj);
		renew();
	}
	
	public void rotate()
	{
		if (!this.paused)
    	{
    		int temp = 1,i;
    		if (this.canNotMove(ROTATE[this.kind],this.x,this.y))
    		for (i=1;i<=3;i++)
    		{
    			if (!this.canNotMove(ROTATE[this.kind],this.x+i,this.y))
    			{
    				this.move(i,0);
    				break;
    			} else
    			if (!this.canNotMove(ROTATE[this.kind],this.x-i,this.y))
    			{
    				this.move(-i,0);
    				break;
    			} else 
    			if (!this.canNotMove(ROTATE[this.kind],this.x,this.y-i))
    			{
    				this.move(-i,0);
    				break;
    			}
    		}     		
    		if (!this.canNotMove(ROTATE[this.kind],this.x,this.y))
    		{
    			this.kind = ROTATE[this.kind];
    			this.renew();
    		}
    	}
	}

	private int RandomKind()
	{
		Random r = new Random();
		int x = 0 , y = 0;
		x = r.nextInt(7) + 1;
		switch (x)
		{
			case 1:
				y = r.nextInt(2) + 1;
				break;
			case 2:
				y = r.nextInt(4) + 3;
				break;
			case 3:
				y = r.nextInt(4) + 7;
				break;
			case 4:
				y = r.nextInt(4) + 11;
				break;
			case 5:
				y = 15;
				break;
			case 6:
				y = r.nextInt(2) + 16;
				break;
			case 7:
				y = r.nextInt(2) + 18;
				break;
		}
		return y;
	}
	
	
	private void setDown()
	{
		for (int i=0;i<4;i++)
		{
			if (y>=BLOCK[kind][i][0]) 
				this.map[x+BLOCK[kind][i][1]][y+BLOCK[kind][i][2]] = kind;
		}
	}
	
}
	