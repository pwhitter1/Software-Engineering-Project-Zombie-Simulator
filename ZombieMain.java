package zombie;

import javax.swing.JComponent;
import javax.swing.JFrame;

public final class ZombieMain {

	private final static int MAX_X = 80;
	private final static int MAX_Y = 60;
	private final static int DOT_SIZE = 10;
	private final static int SLEEP_TIME = 300;
	ZombieMain zombie;
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(ZombieMain::createAndShowGUI);
	}

	private static void createAndShowGUI() {
		ZombieModel model = new ZombieModel(MAX_X, MAX_Y, DOT_SIZE);
		ZombieView view = new ZombieView(model);
		ZombieController controller = new ZombieController(model, view, SLEEP_TIME);
		view.addMouseListener(controller);
		JFrame frame = new JFrame("Zombie");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JComponent content = view;
		content.setOpaque(true);
		frame.setContentPane(content);
		frame.setSize(MAX_X * DOT_SIZE, MAX_Y * DOT_SIZE + 21); //"+ 21" because without it the size of the frame is shorter lengthwise than
																	//the size of the black canvas
		frame.setVisible(true);

		controller.beginSimulation();
	}
	
	public static int getMaxX() {
		return MAX_X;
	}
	
	public static int getMaxY() {
		return MAX_Y;
	}
	
	public ZombieMain getMain() {
		return zombie;
	}
}
