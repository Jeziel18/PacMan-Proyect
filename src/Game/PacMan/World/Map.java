package Game.PacMan.World;

import Game.PacMan.entities.Dynamics.BaseDynamic;
import Game.PacMan.entities.Dynamics.Ghost;
import Game.PacMan.entities.Dynamics.PacMan;
import Game.PacMan.entities.Statics.BaseStatic;
import Game.PacMan.entities.Statics.BigDot;
import Game.PacMan.entities.Statics.Dot;
import Main.Handler;
import Resources.Images;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class Map {

    ArrayList<BaseStatic> blocksOnMap;
    CopyOnWriteArrayList<BaseDynamic> enemiesOnMap;
    Handler handler;
    private double bottomBorder;
    private Random rand;
    private int mapBackground;
   
   
    
    public Map(Handler handler) {
        this.handler=handler;
        this.rand = new Random();
        this.blocksOnMap = new ArrayList<>();
        this.enemiesOnMap = new CopyOnWriteArrayList<>();
        bottomBorder=handler.getHeight();
        this.mapBackground = this.rand.nextInt(6);
    }

    public void addBlock(BaseStatic block){
        blocksOnMap.add(block);
    }

    public void addEnemy(BaseDynamic entity){

        enemiesOnMap.add(entity);

    }

    public void drawMap(Graphics2D g2) {
    	
    	
        for (BaseStatic block:blocksOnMap) {

            g2.drawImage(block.sprite, block.x, block.y, block.width, block.height, null);
                       
            if(block instanceof BigDot) {
            	g2.drawImage(((BigDot) block).blinkBigDot.getCurrentFrame(), block.x, block.y, block.width, block.height, null);
            }
            
            if(block.getIsFruit() && block instanceof Dot) {
                g2.drawImage(Images.fruits[block.getrandFruit()], block.x, block.y, block.width, block.height, null);
                
               
            }


        }
        for (BaseDynamic entity:enemiesOnMap) {
            if (entity instanceof PacMan) {            	
            	
                switch (((PacMan) entity).facing){
                    case "Right":
                        g2.drawImage(((PacMan) entity).rightAnim.getCurrentFrame(), entity.x, entity.y, entity.width, entity.height, null);
                        break;
                    case "Left":
                        g2.drawImage(((PacMan) entity).leftAnim.getCurrentFrame(), entity.x, entity.y, entity.width, entity.height, null);
                        break;
                    case "Up":
                        g2.drawImage(((PacMan) entity).upAnim.getCurrentFrame(), entity.x, entity.y, entity.width, entity.height, null);
                        break;
                    case "Down":
                        g2.drawImage(((PacMan) entity).downAnim.getCurrentFrame(), entity.x, entity.y, entity.width, entity.height, null);
                        break;
                }
                
                //If that makes pacman dead animation run (Jeziel)
                if(handler.getPacman().pacmandied) {
                    g2.drawImage(((PacMan) entity).pacmanDedAnim.getCurrentFrame(), entity.x, entity.y, entity.width, entity.height, null);
                }
                
            }
            else {
                g2.drawImage(entity.sprite, entity.x, entity.y, entity.width, entity.height, null);
            }
       
        }

    }

    public ArrayList<BaseStatic> getBlocksOnMap() {
        return blocksOnMap;
    }

    public CopyOnWriteArrayList<BaseDynamic> getEnemiesOnMap() {
        return enemiesOnMap;
    }

    public double getBottomBorder() {
        return bottomBorder;
    }

    public void reset() {
      	handler.getMusicHandler().playEffect("pacman_death.wav"); //When pacman dies play music
      	
      	if(handler.getPacman().pacLife <= 0) { //Cap the life of pacman to 0
    		handler.getPacman().pacLife = 0;
    		
    	}
      	handler.getPacman().facing = "Left"; //Change the facing to left when pacman spawn again

    }
}
