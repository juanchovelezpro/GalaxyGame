package tools;

/**
 * Esta clase se encarga de calcular los frames per seconds del {@code Juego}.
 * @author juanchovelezpro
 *
 */
public final class ManagerFPS {

	private static int startTime;
	private static int endTime;
	private static int frameTimes = 0;
	private static int frames = 0;
	public static int fps;

	/**
	 * Empieza el conteo de frames.
	 */
	public final static void StartCounter() {
		// get the current time
		startTime = (int) System.currentTimeMillis();
	}

	/**
	 * Postea los frames per second.
	 */
	public final static void StopAndPost() {
		// get the current time
		endTime = (int) System.currentTimeMillis();
		// the difference between start and end times
		frameTimes = frameTimes + endTime - startTime;
		// count one frame
		++frames;
		// if the difference is greater than 1 second (or 1000ms) post the results
		if (frameTimes >= 1000) {
			// post results at the console
			System.out.println("FPS: " + Long.toString(frames));
			fps = Integer.parseInt(Long.toString(frames));
			// reset time differences and number of counted frames
			frames = 0;
			frameTimes = 0;
		}
	}
}
