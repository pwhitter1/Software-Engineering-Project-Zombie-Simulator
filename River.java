package zombie;

import java.awt.Color;
import java.util.Random;

public class River {
	private ZombieModel model; //instance variables
	private int tempx;
	private int tempy = 0;
	private Random random = new Random();
	
	public void initialize(ZombieModel modelArg) {
		model = modelArg;
		tempx = random.nextInt(model.getWidth());
		if(tempx < 0 || tempx > model.getWidth() - 5) { //river has to be placed within bounds of the frame
			this.initialize(modelArg);
		}
		else { for(int i = 0; i < model.getHeight(); i++) { 
				model.setColor(tempx, tempy, Color.BLUE); //five dots wide
				model.setColor(tempx + 1, tempy, Color.BLUE);
				model.setColor(tempx + 2, tempy, Color.BLUE);
				model.setColor(tempx + 3, tempy, Color.BLUE);
				model.setColor(tempx + 4, tempy, Color.BLUE);
				tempy++;
			}
		}
	}
}
