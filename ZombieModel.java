package zombie;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class ZombieModel {

	private final Color[][] matrix;
	private final int width;
	private final int height;
	private final int dotSize;
	private List<Human> humanList = new ArrayList<>();
	private List<Zombie> zombieList = new ArrayList<>();
	
	Human human;
	River river = new River();
	Tree tree = new Tree();
	Rock rock = new Rock();
	AlphaZombie alphaZombie = new AlphaZombie();
	Zombie zombie;
	AbandonedHouse house = new AbandonedHouse();
	
	public ZombieModel(int widthArg, int heightArg, int dotSizeArg) {
		width = widthArg;
		height = heightArg;
		dotSize = dotSizeArg;
		matrix = new Color[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				matrix[i][j] = Color.BLACK;
			}
		}
	}
	
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	public int getDotSize() { return dotSize; }
	public Color getColor(int x, int y) { return matrix[x][y]; }
	public void setColor(int x, int y, Color color) { matrix[x][y] = color; }
	public Human getHuman() { return human; }
	public AlphaZombie getAlphaZombie() { return alphaZombie; }
	
	public boolean isInBounds(int x, int y) {
		if(x < 0 || x >= width || y < 0 || y >= height) return false;
		else return true;
	}
	
	public void initialize() {
		tree.initialize(this);
		rock.initialize(this);
		house.initialize(this);
		river.initialize(this); 
		alphaZombie.initialize(this);
		
		for(int i = 0; i < 30; i++) { //30 humans
			human = new Human();
			human.initialize(this);
			humanList.add(human);
		}
	}
	
	public void update() {
		
		for(int i = 0; i < humanList.size(); i++) {
			human = humanList.get(i);
				
			if(human.nextToZombie()) { //turn human into a zombie
				zombie = new Zombie();
				humanList.remove(human);
				zombie.initialize(this, human.getX(), human.getY());
				zombieList.add(zombie);
				break;
			}		
			if(human.seesZombie()) {
				setColor(human.getX(), human.getY(), Color.MAGENTA);
				
				human.run(); //run from zombie for three turns
				human.run();
				human.run();
				
				setColor(human.getX(), human.getY(), Color.MAGENTA); //rest for two turns
				setColor(human.getX(), human.getY(), Color.MAGENTA);
			}
		
			human.update(this, human.getDirection());
		}
		
		for(Zombie zombie: zombieList) zombie.update(this, zombie.getDirection());
		alphaZombie.update(this, alphaZombie.getDirection());
	}
}
