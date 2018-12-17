package Map;


import java.util.Timer;
import java.util.TimerTask;

public class gameTimer {
	private Timer _timer;

	public void startTimer(TimerTask task) {
		_timer = new Timer();
		_timer.scheduleAtFixedRate(task, 0, 5);
	}
	public void endTimer() {
		_timer.cancel();
	}
}
