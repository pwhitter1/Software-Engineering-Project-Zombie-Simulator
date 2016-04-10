package zombie;

	import java.awt.Color;
	import java.util.Random;
	
	public class Zombie {

		private ZombieModel model; //instance variables
		private int tempx;
		private int tempy;
		private Direction direction = Direction.NORTH; //default direction
		private Random random = new Random();
		private int randDirection;
		Human human = new Human(); 
		Color prevColor;
		
		public void initialize(ZombieModel modelArg, int humanX, int humanY) {
			//this method will only be called when a human has transformed into zombie
			//no normal zombies are placed initially
			model = modelArg;
			
			tempx = humanX;
			tempy = humanY;

			model.setColor(humanX, humanY, Color.ORANGE);
		}
		
		public void update(ZombieModel modelArg, Direction directionArg) {
			model = modelArg;
			direction = directionArg;
			randDirection = random.nextInt(9) + 1;
			
			//check if you see a human, 
			//if so, follow
			direction = seesHuman();	
			
			switch(direction) { //zombie movement is somewhat random
				case NORTH:
					if(randDirection < 6) {
						direction = Direction.NORTH; //50% chance of same direction
						break;
					}
					else if(randDirection < 8) {
						direction = Direction.EAST; //20% chance of right turn
						break;
					}
					else if(randDirection < 10) {
						direction = Direction.WEST; //20% chance of left turn
						break;
					}
					else {
						direction = Direction.SOUTH; //10% chance of total reversal
						break;
					}
				case EAST:
					if(randDirection < 6) {
						direction = Direction.EAST; 
						break;
					}
					else if(randDirection < 8) {
						direction = Direction.SOUTH; 
						break;
					}
					else if(randDirection < 10) {
						direction = Direction.NORTH; 
						break;
					}
					else {
						direction = Direction.WEST;
						break;
					}
				case SOUTH:
					if(randDirection < 6) {
						direction = Direction.SOUTH; 
						break;
					}
					else if(randDirection < 8) {
						direction = Direction.WEST; 
						break;
					}
					else if(randDirection < 10) {
						direction = Direction.EAST; 
						break;
					}
					else {
						direction = Direction.NORTH;
						break;
					}
				case WEST:
					if(randDirection < 6) {
						direction = Direction.WEST;
						break;
					}
					else if(randDirection < 8) {
						direction = Direction.NORTH; 
						break;
					}
					else if(randDirection < 10) {
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
			
			switch(direction) { //zombies can walk on water
				case NORTH:
					if(tempy - 1 < 0 || model.getColor(tempx, tempy - 1) != Color.BLACK && 
						model.getColor(tempx, tempy) != Color.BLUE)  {
						direction = Direction.SOUTH; //face the opposite direction if you hit an obstacle
						break;
					} else {
						model.setColor(tempx, tempy, prevColor); //color could be black or blue
						tempy = tempy - 1;
						prevColor = model.getColor(tempx, tempy);
						model.setColor(tempx, tempy, Color.ORANGE);
					}
				break;
				case EAST:
					if(tempx + 1 >= model.getWidth() || model.getColor(tempx + 1, tempy) != Color.BLACK && 
						model.getColor(tempx + 1, tempy) != Color.BLUE) {
						direction = Direction.WEST;
						break;
					} else {
						model.setColor(tempx, tempy, prevColor);
						tempx = tempx + 1;
						prevColor = model.getColor(tempx, tempy);
						model.setColor(tempx, tempy, Color.ORANGE);
					}
				break;
				case SOUTH: 
					if(tempy + 1 >= model.getHeight() || model.getColor(tempx, tempy + 1) != Color.BLACK && 
							model.getColor(tempx, tempy + 1) != Color.BLUE) { 
						direction = Direction.NORTH;
						break;
					} else {
						model.setColor(tempx, tempy, prevColor);
						tempy = tempy + 1;
						prevColor = model.getColor(tempx, tempy);
						model.setColor(tempx, tempy, Color.ORANGE);
					}
					break;
				case WEST: 
					if(tempx - 1 < 0 || model.getColor(tempx - 1, tempy) != Color.BLACK && 
							model.getColor(tempx - 1, tempy) != Color.BLUE) {
						direction = Direction.EAST;
						break;
					} else {
						model.setColor(tempx, tempy, prevColor);
						tempx = tempx - 1;
						prevColor = model.getColor(tempx, tempy);
						model.setColor(tempx, tempy, Color.ORANGE);
					}
					break;
			}	
		}
		
		//change direction if the zombie sees a human within 10 squares in front of it
		public Direction seesHuman() {
			switch(direction) { 
			case NORTH:
				for(int i = 0; i < 11; i++) {
					//if the human is about to move out of bounds, zombie should continue in the same direction it was going before
					if(!model.isInBounds(tempx, tempy - i)) return direction; 
					//otherwise, follow the human
					else if(model.getColor(tempx, tempy - i) == Color.WHITE) return Direction.NORTH;
				}
			case EAST:
				for(int i = 0; i < 11; i++) {
					if(!model.isInBounds(tempx + i, tempy)) return direction;
					else if(model.getColor(tempx + i, tempy) == Color.WHITE) return Direction.EAST;
				}
			case SOUTH:
				for(int i = 0; i < 11; i++) {
					if(!model.isInBounds(tempx, tempy + i)) return direction;
					else if(model.getColor(tempx, tempy + i) == Color.WHITE) return Direction.SOUTH;
				}
			case WEST:
				for(int i = 0; i < 11; i++) {
					if(!model.isInBounds(tempx, tempy - i)) return direction;
					else if(model.getColor(tempx, tempy - i) == Color.WHITE) return Direction.WEST;
				}
			default:
				break;	
		}
			return direction;	
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
