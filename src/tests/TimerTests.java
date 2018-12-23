package tests;

import static org.junit.Assert.fail;

import java.util.TimerTask;
import org.junit.jupiter.api.Test;
import Packman_Game.gameTimer;

class TimerTests {
	gameTimer timer;
	static int i;
	@Test
	void Start_and_ent_timertest() {
		timer=new gameTimer();
		i=0;
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				i++;
			}
		};
		timer.startTimer(task);
		for(int j=0;j<2000000000;j++) {
			for(int k=0;k<2000000000;k++) {
			}
		}
		timer.endTimer();
		if(i==0)
			fail("Not yet implemented");
	}
}

