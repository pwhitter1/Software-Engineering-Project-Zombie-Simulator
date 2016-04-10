package zombie;

import java.awt.Color;
import java.util.Random;

public class Rock {
	private ZombieModel model; 
	int tempx, tempy;
	Random rand = new Random();

	public void initialize(ZombieModel modelArg) {
		model = modelArg;
		
		int radius, xstart, ystart, xfinishRight, yfinishRight, xfinishLeft, yfinishLeft;
		
		for(int h = 0; h < 6; h++) { //six rocks
		
			radius = rand.nextInt(4) + 4; //range = 4 - 8			
			xstart = rand.nextInt((80 - radius) - radius) + radius; //random = (high - low) + low
			ystart = rand.nextInt((60 - radius) - radius) + radius; //center point = (xstart, ystart) 
			
			xfinishRight = xstart + radius;
			yfinishRight = ystart + radius;
			xfinishLeft = xstart - radius;
			yfinishLeft = ystart - radius;
			
			for(int i = xstart; i < xfinishRight; i++) {
				for(int j = ystart; j < yfinishRight; j++) {	
					if ((Math.pow((Math.pow((double) xstart - i, 2) + Math.pow((double) ystart - j, 2)), 0.5)) < radius) {  
								//sqrt((xstart - i)^2 + (ystart - j)^2) < radius
						model.setColor(i, j, Color.DARK_GRAY);
					}
				}
			}
		
			for(int i = xstart; i > xfinishLeft; i--) {
				for(int j = ystart; j > yfinishLeft; j--) {	
					if ((Math.pow((Math.pow((double) xstart - i, 2) + Math.pow((double) ystart - j, 2)), 0.5)) < radius) {  
						model.setColor(i, j, Color.DARK_GRAY);	
					}
				}
			}
		
			for(int i = xstart; i > xfinishLeft; i--) {
				for(int j = ystart; j < yfinishRight; j++) {	
					if ((Math.pow((Math.pow((double) xstart - i, 2) + Math.pow((double) ystart - j, 2)), 0.5)) < radius) {  
						model.setColor(i, j, Color.DARK_GRAY);	
					}
				}
			}
		
			for(int i = xstart; i < xfinishRight; i++) {
				for(int j = ystart; j > yfinishLeft; j--) {	
					if ((Math.pow((Math.pow((double) xstart - i, 2) + Math.pow((double) ystart - j, 2)), 0.5)) < radius) { 
						model.setColor(i, j, Color.DARK_GRAY);	
					}
				}
			}
		}
	}
}
