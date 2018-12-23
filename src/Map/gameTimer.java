package Map;


import java.util.Timer;
import java.util.TimerTask;

public class gameTimer {
	/**
	 * This class represents Timer.
	 * @author Bar Genish
	 * @author Elyashiv Deri
	 */
	private Timer _timer;
	/**
	 * Start the timer.
	 * @param task - the task that we want to do every single time(that we choose in the timer).
	 */
	public void startTimer(TimerTask task) {
		_timer = new Timer();
		_timer.scheduleAtFixedRate(task, 0, 10);
	}
	/**
	 * Ends the timer.
	 */
	public void endTimer() {
		_timer.cancel();
	}
}
