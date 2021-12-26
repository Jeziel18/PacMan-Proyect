package Game.PacMan.entities.Dynamics;

import Game.PacMan.entities.Statics.BaseStatic;
import Game.PacMan.entities.Statics.BoundBlock;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class Ghost extends BaseDynamic{

	protected double velX,velY,speed = 1;
	public String facing = "Left";
	public String[] toFace = {"Left", "Right", "Up", "Down"};
	public boolean moving = true,turnFlag = false;
	public Animation leftAnim,rightAnim,upAnim,downAnim,ghostFlashing;
	int turnCooldown = 30; 
	int[] ogCoords = new int[2];
	boolean justSpawned;

	BufferedImage ogImg;
	
	boolean isEdible = false;
	boolean ghostDied = false;
	int spawnTimer = 0;
	int edibleTimer = 10*60;
	
	boolean atSpawn = false;
	int failSafe = 10*60;
	
	//String name = "";

	public Ghost(int x, int y, int width, int height, BufferedImage ghostImage, Handler handler) {
		super(x, y, width, height, handler, ghostImage);
		leftAnim = new Animation(128,Images.pacmanLeft);
		rightAnim = new Animation(128,Images.pacmanRight);
		upAnim = new Animation(128,Images.pacmanUp);
		downAnim = new Animation(128,Images.pacmanDown);
		ghostFlashing = new Animation(128, Images.ghostFlash);

		ogCoords[0] = x;
		ogCoords[1] = y;
		justSpawned = true;
		velX = 1;
		velY = 1;
		if(ghostImage == Images.ghostRed) {
			speed = 1.25;
			ogImg = Images.ghostRed;
			//name = "Red";
		}
		else if(ghostImage == Images.ghostPink) {
			speed = 1.50;
			ogImg = Images.ghostPink;
			//name = "Pink";
		}
		else if(ghostImage == Images.ghostBlue) {
			speed = 1.75;
			ogImg = Images.ghostBlue;
			//name = "Blue";
		}
		else if(ghostImage == Images.ghostOrange) {
			speed = 2;
			ogImg = Images.ghostOrange;
			//name = "Orange";
		}
	}


	@Override
	public void tick(){
		
		if(justSpawned) {
			velY = speed;
			velX = speed;
			y-=velY;
			upAnim.tick();
			if(y <= 288)
				justSpawned = false;
			

		}
		else if(spawnTimer <= 0){
			if(ghostDied) {
				
				ghostDied = false;
				justSpawned = true;
			}
			else {
				switch (facing){
				case "Right":
					x+=velX;
					rightAnim.tick();
					break;
				case "Left":
					x-=velX;
					leftAnim.tick();
					break;
				case "Up":
					y-=velY;
					upAnim.tick();
					break;
				case "Down":
					y+=velY;
					downAnim.tick();
					break;
				}

				if (turnCooldown<=0){
					turnFlag= false;
					turnCooldown = 30;
				}
				if (turnFlag){
					turnCooldown--;
				}

				// Make sure they stay inside bounds
				if(x >= 666) {
					facing = "Left";
					turnFlag = true;
					turnCooldown = 30;
				}else if(x <= 0) {
					facing = "Right";
					turnFlag = true;
					turnCooldown = 30;
				}else if(y <= 0) {
					facing = "Down";
					turnFlag = true;
					turnCooldown = 30;
				}else if(y >= 684) {
					facing = "Up";
					turnFlag = true;
					turnCooldown = 30;
				}

				if (facing.equals("Right") || facing.equals("Left")){
					checkHorizontalCollision();
				}else{
					checkVerticalCollisions();
				}
				
				// Check if ghost is edible
				isEdible = handler.getPacManState().isGhostFlash();
				if(isEdible) {
					if(edibleTimer <= 0) {
						handler.getPacManState().setGhostFlash(false);
						edibleTimer = 10*60;
						System.out.println("Resetting edible...");
					}
					else {
						this.sprite = ghostFlashing.getCurrentFrame();
						ghostFlashing.tick();
						System.out.println("Ghosts are edible for: " + edibleTimer);
						edibleTimer--;
					}
				}
				else
					this.sprite = ogImg;
				
			}	
		}
		else {
			spawnTimer--;
			
		}
		
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_P)){
			
            
        }
	}

	public void resetEdibleTimer() {
		edibleTimer = 15*60;
	}

	public void checkVerticalCollisions() {
		Ghost ghost = this;
		ArrayList<BaseStatic> bricks = handler.getMap().getBlocksOnMap();
		CopyOnWriteArrayList<BaseDynamic> enemies = handler.getMap().getEnemiesOnMap();

		boolean ghostDies = false;
		boolean toUp = moving && facing.equals("Up");

		Rectangle ghostBounds = toUp ? ghost.getTopBounds() : ghost.getBottomBounds();

		velY = speed;
		for (BaseStatic brick : bricks) {
			if (brick instanceof BoundBlock) {
				Rectangle brickBounds = !toUp ? brick.getTopBounds() : brick.getBottomBounds();
				if (ghostBounds.intersects(brickBounds)) {
					velY = 0;
					if (toUp)
						ghost.setY(brick.getY() + ghost.getDimension().height);
					else
						ghost.setY(brick.getY() - brick.getDimension().height);
					rotate();
				}
			}
		}

		for(BaseDynamic enemy : enemies){
			Rectangle enemyBounds = !toUp ? enemy.getTopBounds() : enemy.getBottomBounds();
			if(isEdible) {
				if(enemy instanceof PacMan) {
					if (ghostBounds.intersects(enemyBounds)) {
						ghostDies = true;
						break;
					}
				}
			}
		}

		if(ghostDies) {
			x = ogCoords[0];
			y = ogCoords[1];
			facing = "Left";
			spawnTimer = 5*60;
			ghostDied = true;
			handler.getScoreManager().addPacmanCurrentScore(500);
			
			
		}
	}


	public boolean checkPreVerticalCollisions(String facing) {
		Ghost ghost = this;
		ArrayList<BaseStatic> bricks = handler.getMap().getBlocksOnMap();

		boolean ghostDies = false;
		boolean toUp = moving && facing.equals("Up");

		Rectangle ghostBounds = toUp ? ghost.getTopBounds() : ghost.getBottomBounds();

		velY = speed;
		for (BaseStatic brick : bricks) {
			if (brick instanceof BoundBlock) {
				Rectangle brickBounds = !toUp ? brick.getTopBounds() : brick.getBottomBounds();
				if (ghostBounds.intersects(brickBounds)) {
					
					return false;
				}
			}
		}
		return true;

	}



	public void checkHorizontalCollision(){
		Ghost ghost = this;
		ArrayList<BaseStatic> bricks = handler.getMap().getBlocksOnMap();
		CopyOnWriteArrayList<BaseDynamic> enemies = handler.getMap().getEnemiesOnMap();
		velX = speed;
		boolean ghostDies = false;
		boolean toRight = moving && facing.equals("Right");

		Rectangle ghostBounds = toRight ? ghost.getRightBounds() : ghost.getLeftBounds();

		for(BaseDynamic enemy : enemies){
			Rectangle enemyBounds = !toRight ? enemy.getRightBounds() : enemy.getLeftBounds();
			if(isEdible) {
				if(enemy instanceof PacMan) {
					if (ghostBounds.intersects(enemyBounds)) {
						ghostDies = true;
						break;
					}
				}
			}
		}

		if(ghostDies) {
			x = ogCoords[0];
			y = ogCoords[1];
			facing = "Left";
			spawnTimer = 5*60;
			ghostDied = true;
			handler.getScoreManager().addPacmanCurrentScore(500);
			
			
			
		}else {

			for (BaseStatic brick : bricks) {
				if (brick instanceof BoundBlock) {
					Rectangle brickBounds = !toRight ? brick.getRightBounds() : brick.getLeftBounds();
					if (ghostBounds.intersects(brickBounds)) {
						velX = 0;
						if (toRight)
							ghost.setX(brick.getX() - ghost.getDimension().width);
						else
							ghost.setX(brick.getX() + brick.getDimension().width);
						rotate();
					}
				}
			}
		}
	}


	public boolean checkPreHorizontalCollision(String facing){
		Ghost ghost = this;
		ArrayList<BaseStatic> bricks = handler.getMap().getBlocksOnMap();
		velX = speed;
		boolean toRight = moving && facing.equals("Right");

		Rectangle ghostBounds = toRight ? ghost.getRightBounds() : ghost.getLeftBounds();

		for (BaseStatic brick : bricks) {
			if (brick instanceof BoundBlock) {
				Rectangle brickBounds = !toRight ? brick.getRightBounds() : brick.getLeftBounds();
				if (ghostBounds.intersects(brickBounds)) {
					
					return false;
				}
			}
		}
		return true;
	}


	public double getVelX() {
		return velX;
	}
	public double getVelY() {
		return velY;
	}

	public int[] getOGCoords() {
		return ogCoords;
	}

	public void rotate() {
		String newFace;
		Random rand = new Random();
		int i = 0;
		do {
			i = rand.nextInt(4);
			newFace = toFace[i];
		}while(toFace[i].equals(facing));
		facing = newFace;
		turnFlag = true;
		turnCooldown = 30;
	}
}
