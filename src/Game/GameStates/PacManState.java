package Game.GameStates;

import Display.UI.UIManager;
import Game.PacMan.World.MapBuilder;
import Game.PacMan.entities.Dynamics.BaseDynamic;
import Game.PacMan.entities.Dynamics.Ghost;
import Game.PacMan.entities.Statics.BaseStatic;
import Game.PacMan.entities.Statics.BigDot;
import Game.PacMan.entities.Statics.Dot;
import Main.Handler;
import Resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class PacManState extends State {

    private UIManager uiManager;
    public String Mode = "Intro";
    public int startCooldown = 60*4;//seven seconds for the music to finish
    
    //Ghosts are edible
    public boolean ghostFlash = false;
    boolean gameOver = false;
    public boolean reset = false;
    public int reset_cooldown = 5*60;
    public PacManState(Handler handler){
        super(handler);
        handler.setMap(MapBuilder.createMap(Images.map1, handler));

    }


    @Override
    public void tick() {
        if (Mode.equals("Stage")){
            if (startCooldown<=0) {
                if(!handler.getPacman().isSpawningGhost()) {
                	for (BaseDynamic entity : handler.getMap().getEnemiesOnMap()) {
                        entity.tick();
                    }
                }
                ArrayList<BaseStatic> toREmove = new ArrayList<>();
                for (BaseStatic blocks: handler.getMap().getBlocksOnMap()){
                    if (blocks instanceof Dot){
                        if (blocks.getBounds().intersects(handler.getPacman().getBounds())){
                        	
                        	if (blocks.getIsFruit()){
                        		handler.getMusicHandler().playEffect("pacman_chomp.wav");
                                toREmove.add(blocks);
                                handler.getScoreManager().addPacmanCurrentScore(120);	
                        	}
                        	else {
                            handler.getMusicHandler().playEffect("pacman_chomp.wav");
                            toREmove.add(blocks);
                            handler.getScoreManager().addPacmanCurrentScore(10);
                        	}
                        }
                        
                    
                        
                    }else if (blocks instanceof BigDot){
                        if (blocks.getBounds().intersects(handler.getPacman().getBounds())){
                            handler.getMusicHandler().playEffect("pacman_chomp.wav");
                            toREmove.add(blocks);
                            handler.getScoreManager().addPacmanCurrentScore(100);
                            ghostFlash = true;
                            for(BaseDynamic e: handler.getMap().getEnemiesOnMap()) {
                            	if(e instanceof Ghost)
                            		((Ghost) e).resetEdibleTimer();
                            }
                        }
                    }
                }
                for (BaseStatic removing: toREmove){
                    handler.getMap().getBlocksOnMap().remove(removing);
                }
                
              //Phase 2, Give one life to PacMan
                if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_N)) {
                	handler.getPacman().pacLife ++;
                	
                	if(handler.getPacman().pacLife >= 3) {
                    	handler.getPacman().pacLife = 3;
                    }
                }
                
                if(reset) {
                	gameOver = true;
                	handler.getPacman().pacLife = 3;
                	Mode = "Intro"; // Change State to "Intro" when the game is over (Jeziel)	
                	handler.getPacman().setX(handler.getPacman().spawnx); //Spawn PacMan on his original coordinates (x)
                	handler.getPacman().setY(handler.getPacman().spawny); //Spawn PacMan on his original coordinates (y)
                	handler.getScoreManager().setPacmanCurrentScore(0); //The score is set equal to 0
                	handler.getPacman().facing = "Left";	
                	startCooldown = 4*60;
                	reset = false;
                }
                
            }else{
                startCooldown--;
            }
            
        }else if (Mode.equals("Menu")){
            if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)){
                Mode = "Stage";
                handler.getMusicHandler().playEffect("pacman_beginning.wav");
            }
        }else{
            if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)){
                Mode = "Menu";
            }
        }
        
    }

    @Override
    public void render(Graphics g) {
    	    	
        if (Mode.equals("Stage")){
            Graphics2D g2 = (Graphics2D) g.create();
            handler.getMap().drawMap(g2);
                      
            if(handler.getPacman().pacLife == 1) {
            	g.drawImage(Images.pacmanLife,1150,800,handler.getWidth()/10,handler.getHeight()/10,null); //Foto de vida de PacMan
            }
            
            else if(handler.getPacman().pacLife == 2) {
            	g.drawImage(Images.pacmanLife,1150,800,handler.getWidth()/10,handler.getHeight()/10,null); //Foto de vida de PacMan
            	g.drawImage(Images.pacmanLife,1400,800,handler.getWidth()/10,handler.getHeight()/10,null); 
            }
            else if(handler.getPacman().pacLife <= 0) { // Game Over
            	reset = true;
            	
                
            }
            
            else {
            	g.drawImage(Images.pacmanLife,1150,800,handler.getWidth()/10,handler.getHeight()/10,null); //Foto de vida de PacMan
            	g.drawImage(Images.pacmanLife,1400,800,handler.getWidth()/10,handler.getHeight()/10,null); 
            	g.drawImage(Images.pacmanLife,1650,800,handler.getWidth()/10,handler.getHeight()/10,null);
            	
            }
            
            g.setColor(Color.WHITE);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 32));
            g.drawString("Score: " + handler.getScoreManager().getPacmanCurrentScore(),(handler.getWidth()/2) + handler.getWidth()/6, 25);
            g.drawString("High-Score: " + handler.getScoreManager().getPacmanHighScore(),(handler.getWidth()/2) + handler.getWidth()/6, 75);
        }else if (Mode.equals("Menu")){
            g.drawImage(Images.start,0,0,handler.getWidth()/2,handler.getHeight(),null);
            
        	g.setColor(Color.RED);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 60));
            g.drawString("" + handler.getScoreManager().getPacmanHighScore(), 310, 70);
            
        }else{
            g.drawImage(Images.intro,0,0,handler.getWidth()/2,handler.getHeight(),null);
            g.setColor(Color.RED);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 60));
            g.drawString("" + handler.getScoreManager().getPacmanHighScore(), 310, 70);
            
            if(gameOver) {
            	 g.setColor(Color.RED);
                 g.setFont(new Font("TimesRoman", Font.PLAIN, 120));
                 g.drawString("GAME OVER!!", 1100, 500);
            }
            
            
            
            

        }
    }

    @Override
    public void refresh() {

    }


	public boolean isGhostFlash() {
		return ghostFlash;
	}


	public void setGhostFlash(boolean ghostFlash) {
		this.ghostFlash = ghostFlash;
	}


}
