package zombie;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Timer;

public class ZombieController implements MouseListener {

	private final ZombieModel model;
	private final ZombieView view;
	private final int delay;
	
	public ZombieController(ZombieModel modelArg, ZombieView viewArg, int sleepTimeArg) {
		model = modelArg;
		view = viewArg;
		delay = sleepTimeArg;
	}

	public void beginSimulation() {
		model.initialize();
		view.draw();
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				model.update();
				view.draw();
			}
		};
		new Timer(delay, taskPerformer).start();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		int pixelFactor = 10; //times 10 because the MouseEvent "e" deals with pixels and not dot_size (have to convert)
							//hotspots = 20x20 squares

		if(y < (model.getHeight() * pixelFactor) && y >= (model.getHeight() * pixelFactor) - 200 && (x >= ((model.getWidth()/2) * pixelFactor) - 100 && (x <= ((model.getWidth()/2) * pixelFactor) + 100))) {
			model.getAlphaZombie().setDirection(Direction.SOUTH);
			System.out.println("SOUTH");
		}
		else if(y <= 200 && x >= ((model.getWidth()/2)* pixelFactor) - 100 && x <= ((model.getWidth()/2) * pixelFactor) + 100) { 
			model.getAlphaZombie().setDirection(Direction.NORTH);
			System.out.println("NORTH");
		}
		else if(x < (model.getWidth() * pixelFactor) && x >= (model.getWidth()* pixelFactor) - 200  && y >= ((model.getHeight()/2) * pixelFactor) - 100 && y <= ((model.getHeight()/2)* pixelFactor) + 100) {
			model.getAlphaZombie().setDirection(Direction.EAST);
			System.out.println("EAST");
		}
		else if(x < 200 && (y >= (((model.getHeight()/2) * pixelFactor) - 100) && y <= (((model.getHeight()/2) * pixelFactor) + 100))) {
			model.getAlphaZombie().setDirection(Direction.WEST);
			System.out.println("WEST");
		}
	}

	@Override
	public void mousePressed(MouseEvent e) { }
	@Override
	public void mouseReleased(MouseEvent e) { }
	@Override
	public void mouseEntered(MouseEvent e) { }
	@Override
	public void mouseExited(MouseEvent e) {	}
	
}
