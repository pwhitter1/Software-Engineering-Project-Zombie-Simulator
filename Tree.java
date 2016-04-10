package zombie;

import java.awt.Color;
import java.util.Random;

public class Tree {
	private ZombieModel model; //instance variables
	private int tempx;
	private int tempy;
	private Random random = new Random();
	
	public void initialize(ZombieModel modelArg) {
		model = modelArg;
		
		for(int i = 0; i < 40; i++) {
			tempx = random.nextInt(model.getWidth() - 2) + 1; //min is 1 because you can have tempx -1,
			tempy = random.nextInt(model.getHeight() - 2) + 1; //model is minus four for similar reasons; 
																//causes errors if not present, need full tree to be on screen
			if(model.getColor(tempx - 1, tempy - 1 ) == Color.BLACK &&
					model.getColor(tempx + 1, tempy - 1) == Color.BLACK &&
					model.getColor(tempx - 1, tempy + 1) == Color.BLACK &&
					model.getColor(tempx + 1, tempy + 1 ) == Color.BLACK) { //only place tree if all sides are on the screen
				model.setColor(tempx, tempy, Color.GREEN); //center
				model.setColor(tempx, tempy - 1, Color.GREEN); //top
				model.setColor(tempx - 1, tempy, Color.GREEN); //left
				model.setColor(tempx + 1, tempy, Color.GREEN); //right
				model.setColor(tempx, tempy + 1, Color.GREEN); //bottom
			}
			else i--; //try again
		}
	}
}
