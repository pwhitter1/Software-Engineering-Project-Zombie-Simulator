package zombie;

import java.awt.Color;
import java.util.Random;

public class Human {
	private ZombieModel model; //instance variables
	private int tempx;
	private int tempy;
	private Direction direction = Direction.NORTH; //default direction
	private Random random = new Random();
	private int randDirection;
	
	public void initialize(ZombieModel modelArg) {
		model = modelArg;
		
			tempx = random.nextInt(model.getWidth() - model.getDotSize());
			tempy = random.nextInt(model.getHeight() - model.getDotSize());
	
			if(model.getColor(tempx, tempy) == Color.BLACK &&
					model.getColor(tempx, tempy + 1) == Color.BLACK &&
					model.getColor(tempx + 1, tempy) == Color.BLACK &&
					model.getColor(tempx + 1, tempy + 1 ) == Color.BLACK)
				model.setColor(tempx, tempy, Color.WHITE);
			else this.initialize(modelArg);
		}
	
	public void update(ZombieModel modelArg, Direction directionArg) {
		model = modelArg;
		
		direction = directionArg;
		randDirection = random.nextInt(19) + 1; //range = 1-20
		
		switch(direction) { //human movement is somewhat random
			case NORTH:
				if(randDirection < 16) { 
					direction = Direction.NORTH; //75% chance of same direction
					break;
				}
				else if(randDirection < 18) {
					direction = Direction.EAST; //10% chance of right turn
					break;
				}
				else if(randDirection < 20) {
					direction = Direction.WEST; //10% chance of left turn
					break;
				}
				else {
					direction = Direction.SOUTH; //5% chance of total reversal
					break;
				}
			case EAST:
				if(randDirection < 16) {
					direction = Direction.EAST;
					break;
				}
				else if(randDirection < 18) {
					direction = Direction.SOUTH; 
					break;
				}
				else if(randDirection < 20) {
					direction = Direction.NORTH; 
					break;
				}
				else {
					direction = Direction.WEST; 
					break;
				}
			case SOUTH:
				if(randDirection < 16) {
					direction = Direction.SOUTH; 
					break;
				}
				else if(randDirection < 18) {
					direction = Direction.WEST; 
					break;
				}
				else if(randDirection < 20) {
					direction = Direction.EAST; 
					break;
				}
				else {
					direction = Direction.NORTH;
					break;
				}
			case WEST:
				if(randDirection < 16) {
					direction = Direction.WEST;
					break;
				}
				else if(randDirection < 18) {
					direction = Direction.NORTH;
					break;
				}
				else if(randDirection < 20) {
					direction = Direction.SOUTH; 
					break;
				}
				else {
					direction = Direction.EAST; 
					break;
				}
			default:
				break;	
		}
		
		switch(direction) {
			case NORTH:
				if(tempy - 1 < 0 || model.getColor(tempx, tempy - 1) != Color.BLACK)  {
					direction = Direction.SOUTH; //face the opposite direction if you hit an obstacle
					break;
				} else {
					model.setColor(tempx, tempy, Color.BLACK);
					tempy = tempy - 1;
					model.setColor(tempx, tempy, Color.WHITE);
				}
			break;
			case EAST:
				if(tempx + 1 >= model.getWidth() || model.getColor(tempx + 1, tempy) != Color.BLACK) {
					direction = Direction.WEST;
					break;
				} else {
					model.setColor(tempx, tempy, Color.BLACK);
					tempx = tempx + 1;
					model.setColor(tempx, tempy, Color.WHITE);
				}
			break;
			case SOUTH: 
				if(tempy + 1 >= model.getHeight() || model.getColor(tempx, tempy + 1) != Color.BLACK) { 
					direction = Direction.NORTH;
					break;
				} else {
					model.setColor(tempx, tempy, Color.BLACK);
					tempy = tempy + 1;
					model.setColor(tempx, tempy, Color.WHITE);
				}
				break;
			case WEST: 
				if(tempx - 1 < 0 || model.getColor(tempx - 1, tempy) != Color.BLACK) {
					direction = Direction.EAST;
					break;
				} else {
					model.setColor(tempx, tempy, Color.BLACK);
					tempx = tempx - 1;
					model.setColor(tempx, tempy, Color.WHITE);
				}
				break;
		}	
	}
	
	public boolean nextToZombie() {
		if(model.isInBounds(tempx + 1, tempy)) {
			if(model.getColor(tempx + 1, tempy) == Color.RED || //alpha zombie
					model.getColor(tempx + 1, tempy) == Color.ORANGE) {
				return true; //regular zombie
			}
		}
		
		if(model.isInBounds(tempx, tempy + 1)) {
			if(model.getColor(tempx, tempy + 1) == Color.RED ||
					model.getColor(tempx, tempy + 1) == Color.ORANGE){
				return true;
			}
		}
		
		if(model.isInBounds(tempx - 1, tempy)) {
			if(model.getColor(tempx - 1, tempy) == Color.RED ||
					model.getColor(tempx - 1, tempy) == Color.ORANGE) {
				return true;
			}
		}
		
		if(model.isInBounds(tempx, tempy - 1)) {
			if(model.getColor(tempx, tempy - 1) == Color.RED ||
					model.getColor(tempx, tempy - 1) == Color.ORANGE) {
				return true;
			}
		}
		
		return false;
	}
	
	//return true if human sees zombie within ten squares of it
	public boolean seesZombie() {
		switch(direction) { 
		case NORTH:
			for(int i = 1; i < 11; i++) {
				if(!model.isInBounds(tempx, tempy - i)) return false;
				else if(model.getColor(tempx, tempy - i) == Color.ORANGE ||
						model.getColor(tempx, tempy - i) == Color.RED) return true;
			}
		case EAST:
			for(int i = 1; i < 11; i++) {
				if(!model.isInBounds(tempx + i, tempy)) return false;
				else if(model.getColor(tempx + i, tempy) == Color.ORANGE ||
						model.getColor(tempx + i, tempy) == Color.RED) return true;
			}
		case SOUTH:
			for(int i = 1; i < 11; i++) {
				if(!model.isInBounds(tempx, tempy + i)) return false;
				else if(model.getColor(tempx, tempy + i) == Color.ORANGE ||
						model.getColor(tempx, tempy + i) == Color.RED) return true;
			}
		case WEST:
			for(int i = 1; i < 11; i++) {
				if(!model.isInBounds(tempx - i, tempy)) return false;
				else if(model.getColor(tempx - i, tempy) == Color.ORANGE ||
						model.getColor(tempx - i, tempy) == Color.RED) return true;
			}
		default:
			break;	
	}
		return false;	
	}
	
	public void run() {
		//turn in opposite direction
		switch(direction) {
			case NORTH:
				direction = Direction.SOUTH;
				break;
			case EAST:
				direction = Direction.WEST;
				break;
			case SOUTH:
				direction = Direction.NORTH;
				break;
			case WEST:
				direction = Direction.EAST;
				break;
		}
		
		randDirection = random.nextInt(9) + 1; //range = 1-10
		
		//run from zombie
		switch(direction) {
		case NORTH:
				model.setColor(tempx, tempy, Color.MAGENTA); //running/resting color
			
					if((tempy - 2 < 0 || model.getColor(tempx, tempy - 2) != Color.BLACK) &&  //need to account for if an obstacle is blocking the path
							randDirection < 2) { //10% chance of turning around
						direction = Direction.SOUTH; //face the opposite direction if you hit an obstacle
						break;
					}
					if((tempy - 2 < 0 || model.getColor(tempx, tempy - 2) != Color.BLACK) && 
							(randDirection > 1 && randDirection < 6)) { //40% chance of turning left
						direction = Direction.EAST; 
						break;
					}
					if((tempy - 2 < 0 || model.getColor(tempx, tempy - 2) != Color.BLACK) && 
							(randDirection > 5 && randDirection < 10)) { //40% chance of turning right
						direction = Direction.WEST;
						break;
					}
					if((tempy - 2 < 0 || model.getColor(tempx, tempy - 2) != Color.BLACK) && 
							randDirection > 9) { //10% chance of keeping same direction
						direction = Direction.NORTH; 
						break;
					}
					
					model.setColor(tempx, tempy, Color.BLACK);
					tempy = tempy - 2;
					model.setColor(tempx, tempy, Color.MAGENTA); 		
		break;
		case EAST:
			model.setColor(tempx, tempy, Color.MAGENTA); //running/resting color
			
				if((tempx + 2 >= model.getWidth() || model.getColor(tempx + 2, tempy) != Color.BLACK) && 
						randDirection < 2) { //10% chance of turning around
					direction = Direction.WEST; //face the opposite direction if you hit an obstacle
					break;
				}
				if((tempx + 2 >= model.getWidth() || model.getColor(tempx + 2, tempy) != Color.BLACK) && 
						(randDirection > 1 && randDirection < 6)) { //40% chance of turning left
					direction = Direction.NORTH; 
					break;
				}
				if((tempx + 2 >= model.getWidth() || model.getColor(tempx + 2, tempy) != Color.BLACK) && 
						(randDirection > 5 && randDirection < 10)) { //40% chance of turning right
					direction = Direction.SOUTH;
					break;
				}
				if((tempx + 2 >= model.getWidth() || model.getColor(tempx + 2, tempy) != Color.BLACK) && 
						randDirection > 9) { //10% chance of keeping same direction
					direction = Direction.EAST; 
					break;
				}
				
				model.setColor(tempx, tempy, Color.BLACK);
				tempx = tempx + 2;
				model.setColor(tempx, tempy, Color.MAGENTA);
				
		break;
		case SOUTH: 
			model.setColor(tempx, tempy, Color.MAGENTA); //running/resting color
			
				if((tempy + 2 >= model.getHeight() || model.getColor(tempx, tempy + 2) != Color.BLACK) && 
						randDirection < 2) { //10% chance of turning around
					direction = Direction.NORTH; //face the opposite direction if you hit an obstacle
					break;
				}
				if((tempy + 2 >= model.getHeight() || model.getColor(tempx, tempy + 2) != Color.BLACK) && 
						(randDirection > 1 && randDirection < 6)) { //40% chance of turning left
					direction = Direction.EAST; 
					break;
				}
				if((tempy + 2 >= model.getHeight() || model.getColor(tempx, tempy + 2) != Color.BLACK) && 
						(randDirection > 5 && randDirection < 10)) { //40% chance of turning right
					direction = Direction.WEST;
					break;
				}
				if((tempy + 2 >= model.getHeight() || model.getColor(tempx, tempy + 2) != Color.BLACK) && 
						randDirection > 9) { //10% chance of keeping same direction
					direction = Direction.SOUTH; 
					break;
				}

				model.setColor(tempx, tempy, Color.BLACK);
				tempy = tempy + 2;
				model.setColor(tempx, tempy, Color.MAGENTA); 
			
		break;
		case WEST: 
			model.setColor(tempx, tempy, Color.MAGENTA); //running/resting color
			
				if((tempx - 2 < 0 || model.getColor(tempx - 2, tempy) != Color.BLACK) && 
						randDirection < 2) { //10% chance of turning around
					direction = Direction.EAST; //face the opposite direction if you hit an obstacle
					break;
				}
				if((tempx - 2 < 0 || model.getColor(tempx - 2, tempy) != Color.BLACK) && 
						(randDirection > 1 && randDirection < 6)) { //40% chance of turning left
					direction = Direction.NORTH; 
					break;
				}
				if((tempx - 2 < 0 || model.getColor(tempx - 2, tempy) != Color.BLACK) && 
						(randDirection > 5 && randDirection < 10)) { //40% chance of turning right
					direction = Direction.SOUTH;
					break;
				}
				if((tempx - 2 < 0 || model.getColor(tempx - 2, tempy) != Color.BLACK) && 
						randDirection > 9) { //10% chance of keeping same direction
					direction = Direction.WEST; 
					break;
				}
			
				model.setColor(tempx, tempy, Color.BLACK);
				tempx = tempx - 2;
				model.setColor(tempx, tempy, Color.MAGENTA); 
					
		break;	
			
		}		
	}
		
	public int getX() {
		return tempx;
	}
	
	public int getY() {
		return tempy;
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	public void setDirection(Direction directionArg) {
		direction = directionArg;
	}
}
