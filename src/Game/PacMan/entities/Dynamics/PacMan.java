package Game.PacMan.entities.Dynamics;

import Game.PacMan.World.MapBuilder;
import Game.PacMan.entities.Statics.BaseStatic;
import Game.PacMan.entities.Statics.BigDot;
import Game.PacMan.entities.Statics.BoundBlock;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class PacMan extends BaseDynamic{

    protected double velX,velY,speed = 1;
    public String facing = "Left";
    public boolean moving = true,turnFlag = false;
    public Animation leftAnim,rightAnim,upAnim,downAnim, pacmanDedAnim;
    int turnCooldown = 20;
    public int spawnx = 126 , spawny = 648; // Original spawn of PacMan (Jeziel)
    public int ghostx = 342, ghosty = 342; // Spawn of ghosts (Carlos)
    public int pacLife = 3; // Life of PacMan (Jeziel)
    int spawncooldown = 5*60; // Cooldown for PacMan to spawn again (Jeziel)
    public boolean pacmandied = false; // Boolean for pacmanDied (vertical y horizontal collision) (Jeziel)
    public boolean resetpacman = false;
    
    public boolean spawningGhost = false;
    public int spawningGhostCD = 60;
    
    
   
    

    public PacMan(int x, int y, int width, int height, Handler handler) {
        super(x, y, width, height, handler, Images.pacmanRight[0]);
        leftAnim = new Animation(128,Images.pacmanLeft);
        rightAnim = new Animation(128,Images.pacmanRight);
        upAnim = new Animation(128,Images.pacmanUp);
        downAnim = new Animation(128,Images.pacmanDown);  
        pacmanDedAnim = new Animation(350, Images.pacmanDies); //PacMan dead animation
    }

    @Override
    public void tick(){
   
    	for (BaseStatic block:handler.getMap().getBlocksOnMap()) {  //Making the BigDot blink
            if(block instanceof BigDot){
                ((BigDot)block).blinkBigDot.tick();
            }
        }
    	

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
        }
        if (turnFlag){
            turnCooldown--;
        }

        if ((handler.getKeyManager().keyJustPressed(KeyEvent.VK_RIGHT)  || handler.getKeyManager().keyJustPressed(KeyEvent.VK_D)) && !turnFlag && checkPreHorizontalCollision("Right")){
            facing = "Right";
            turnFlag = true;
            turnCooldown = 20;
        }else if ((handler.getKeyManager().keyJustPressed(KeyEvent.VK_LEFT) || handler.getKeyManager().keyJustPressed(KeyEvent.VK_A)) && !turnFlag&& checkPreHorizontalCollision("left")){
            facing = "Left";
            turnFlag = true;
            turnCooldown = 20;
        }else if ((handler.getKeyManager().keyJustPressed(KeyEvent.VK_UP)  ||handler.getKeyManager().keyJustPressed(KeyEvent.VK_W)) && !turnFlag&& checkPreVerticalCollisions("Up")){
            facing = "Up";
            turnFlag = true;
            turnCooldown = 20;
        }else if ((handler.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN)  || handler.getKeyManager().keyJustPressed(KeyEvent.VK_S)) && !turnFlag&& checkPreVerticalCollisions("Down")){
            facing = "Down";
            turnFlag = true;
            turnCooldown = 20;
        }

        if (facing.equals("Right") || facing.equals("Left")){
            checkHorizontalCollision();
        }else{
            checkVerticalCollisions();
        }  
        
        if(handler.getPacManState().startCooldown <= 0) {
        	speed = 1;
        }
        else {
        	speed = 0;
        }
       
        if(pacmandied) {
        	
        	if(spawncooldown <= 0) {
        		pacLife --;
        		x = spawnx;
            	y = spawny;
            	pacmanDedAnim.reset();
            	spawncooldown = 5*60;
            	speed = 1;
            	pacmandied = false;	
        	}
        	else {
        		speed = 0;
        		pacmanDedAnim.tick();
        		spawncooldown --;
        		
        	}
        	
        	
        }
        
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_K)) {
        	spawningGhost = true;
        	GhostSpawner.spawnGhost(ghostx, ghosty, 18, 18, handler, handler.getMap());
        }
        
        if(spawningGhost) {
        	if(spawningGhostCD <= 0) {
        		spawningGhostCD = 60;
        		spawningGhost = false;
        	}
        	else
        		spawningGhostCD--;
        }
        
    }
    
    

    public void checkVerticalCollisions() {
        PacMan pacman = this;
        ArrayList<BaseStatic> bricks = handler.getMap().getBlocksOnMap();
        ArrayList<BaseDynamic> enemies = handler.getMap().getEnemiesOnMap();
        
        boolean pacmanDies = false;
        boolean toUp = moving && facing.equals("Up");

        Rectangle pacmanBounds = toUp ? pacman.getTopBounds() : pacman.getBottomBounds();

        velY = speed;
        for (BaseStatic brick : bricks) {
            if (brick instanceof BoundBlock) {
                Rectangle brickBounds = !toUp ? brick.getTopBounds() : brick.getBottomBounds();
                if (pacmanBounds.intersects(brickBounds)) {
                    velY = 0;
                    if (toUp)
                        pacman.setY(brick.getY() + pacman.getDimension().height);
                    else
                        pacman.setY(brick.getY() - brick.getDimension().height);
                }
            }
        }
        
        
        for(BaseDynamic enemy : enemies){
            Rectangle enemyBounds = !toUp ? enemy.getTopBounds() : enemy.getBottomBounds();
            if(!handler.getPacManState().isGhostFlash()) {
            	
            	if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_P)){  //Phase 2: Implementing letter P to kill PacMan (Jeziel)
                    pacmanDies = true;
                }
            	
            	if (pacmanBounds.intersects(enemyBounds)){
                    pacmanDies = true;
                    break;
                }
            }
        }
                
        if(pacmanDies) {
        	handler.getMap().reset();
          	pacmandied = true;
          	
          	
        }
    }


    public boolean checkPreVerticalCollisions(String facing) {
        PacMan pacman = this;
        ArrayList<BaseStatic> bricks = handler.getMap().getBlocksOnMap();

        boolean pacmanDies = false;
        boolean toUp = moving && facing.equals("Up");

        Rectangle pacmanBounds = toUp ? pacman.getTopBounds() : pacman.getBottomBounds();

        velY = speed;
        for (BaseStatic brick : bricks) {
            if (brick instanceof BoundBlock) {
                Rectangle brickBounds = !toUp ? brick.getTopBounds() : brick.getBottomBounds();
                if (pacmanBounds.intersects(brickBounds)) {
                    return false;
                }
            }
        }
        return true;

    }



    public void checkHorizontalCollision(){
        PacMan pacman = this;
        ArrayList<BaseStatic> bricks = handler.getMap().getBlocksOnMap();
        ArrayList<BaseDynamic> enemies = handler.getMap().getEnemiesOnMap();
        velX = speed;
        boolean pacmanDies = false;
        boolean toRight = moving && facing.equals("Right");

        Rectangle pacmanBounds = toRight ? pacman.getRightBounds() : pacman.getLeftBounds();
        
      
        for(BaseDynamic enemy : enemies){
            Rectangle enemyBounds = !toRight ? enemy.getRightBounds() : enemy.getLeftBounds();
            if(!handler.getPacManState().isGhostFlash()) {
            	
            	if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_P)){ //Phase 2: Implementing letter P to kill PacMan (Jeziel)
                    pacmanDies = true;  
                }
            	
            	if (pacmanBounds.intersects(enemyBounds)) {
                    pacmanDies = true;
                    break;
                }
            }
        }

        if(pacmanDies) {
        	handler.getMap().reset();
        	pacmandied = true;
        	
        	    	
        }

            for (BaseStatic brick : bricks) {
                if (brick instanceof BoundBlock) {
                    Rectangle brickBounds = !toRight ? brick.getRightBounds() : brick.getLeftBounds();
                    if (pacmanBounds.intersects(brickBounds)) {
                        velX = 0;
                        if (toRight)
                            pacman.setX(brick.getX() - pacman.getDimension().width);
                        else
                            pacman.setX(brick.getX() + brick.getDimension().width);
                    }
                }
            }
        }
    



    public boolean checkPreHorizontalCollision(String facing){
        PacMan pacman = this;
        ArrayList<BaseStatic> bricks = handler.getMap().getBlocksOnMap();
        velX = speed;
        boolean toRight = moving && facing.equals("Right");

        Rectangle pacmanBounds = toRight ? pacman.getRightBounds() : pacman.getLeftBounds();

            for (BaseStatic brick : bricks) {
                if (brick instanceof BoundBlock) {
                    Rectangle brickBounds = !toRight ? brick.getRightBounds() : brick.getLeftBounds();
                    if (pacmanBounds.intersects(brickBounds)) {
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

	public boolean isSpawningGhost() {
		return spawningGhost;
	}

	public void setSpawningGhost(boolean spawningGhost) {
		this.spawningGhost = spawningGhost;
	}


}
