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

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TetrisControl implements KeyListener
{
	TetrisModel model;
	
	public TetrisControl(TetrisModel model)
	{	
		this.model = model;
	}
	
	public void keyPressed(KeyEvent e) 
	{     
		int keyCode = e.getKeyCode();
    	switch (keyCode) 
    	{
            case KeyEvent.VK_UP:
            	model.rotate();
                break;
            case KeyEvent.VK_DOWN:
            	model.move(0, 1);
                break;
            case KeyEvent.VK_LEFT:
            	model.move(-1, 0);
                break;
            case KeyEvent.VK_RIGHT:
            	model.move(1, 0);
                break;
            case KeyEvent.VK_A:
                model.speedUp();
                break;
            case KeyEvent.VK_S:
                model.speedDown();
                break;
            case KeyEvent.VK_SPACE:
            case KeyEvent.VK_P:
                model.changePauseState();
                break;
            default:
        }
	}

	public void keyReleased(KeyEvent e) 
	{
	}
	
	public void keyTyped(KeyEvent e) 
	{
	}
}
