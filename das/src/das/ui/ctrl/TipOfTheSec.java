package das.ui.ctrl;

import java.util.Random;

public class TipOfTheSec {
	
	private static Random random = new Random();
	
	private static String[] tips = {
		"Hungern ist Billiger!",
		"Fasten bis zum Tode ist durchaus in Mode!",
		"In dieser Sekunde, nichts im Munde",
		"Wenn Sie dann platzen, tun's mich bitte nicht anpatzen",
		"Das Skelett f&auml;llt aus dem Bett denn es hat zu wenig Fett"
	};
	
	public static String nextTip(){
		return tips[random.nextInt(tips.length)];
	}
}
