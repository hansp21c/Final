package toho;

import sun.audio.*;
import java.io.*;

public class Bgm extends Thread {
	static boolean start = true;

	public void run() {
		long timel = System.currentTimeMillis();
		long timen=0;

		while (start) {
			try {
				FileInputStream fileau = new FileInputStream("似有似无之乡Deep Mountain.mid");
				AudioStream as = new AudioStream(fileau);
				AudioPlayer.player.start(as);
			} catch (Exception e) {
			}
			while (true) {
				timen = System.currentTimeMillis();
				if(start == false){
					timel=timen;
					break;
					}
				if (timen - timel >93000) {
					timel = timen;
					break;
				}
			}
		}
	}

	public void end() {
		start = false;// 重启时音乐暂停播放
	}

}
