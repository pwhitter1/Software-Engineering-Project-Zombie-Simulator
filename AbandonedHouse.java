package zombie;

import java.awt.Color;
import java.util.Random;

public class AbandonedHouse {
	private ZombieModel model; //instance variables
	private int tempx;
	private int tempy;
	private Random random = new Random();
	
	public void initialize(ZombieModel modelArg) {
		model = modelArg;
		
		for(int i = 0; i < 8; i++) { //eight houses
			tempx = random.nextInt(model.getWidth() - 2) + 1; 
			tempy = random.nextInt(model.getHeight() - 2) + 1;  
																
			if(model.getColor(tempx, tempy) == Color.BLACK &&
					model.getColor(tempx + 1, tempy) == Color.BLACK &&
					model.getColor(tempx, tempy + 1) == Color.BLACK &&
					model.getColor(tempx + 1, tempy + 1 ) == Color.BLACK) { //only place house if all sides are on the screen
				model.setColor(tempx, tempy, Color.LIGHT_GRAY); //upper left (chimney)
				model.setColor(tempx + 1, tempy, Color.CYAN); //upper right
				model.setColor(tempx, tempy + 1, Color.CYAN); //lower left
				model.setColor(tempx + 1, tempy + 1, Color.CYAN); //lower right
			}
			else i--; //try again
		}
	}
}
