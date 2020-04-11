package Game.PacMan.entities.Dynamics;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

import Game.PacMan.World.Map;
import Main.Handler;
import Resources.Images;



public class GhostSpawner {
	
	public static boolean justStarted = true;
	
	public static void spawnGhost(int x, int y, int width, int height, Handler handler, Map map) {
		//System.out.println("Ghost spawned at x: " + x + ", y: " + y);
		System.out.println(width + " " + height);
		if(justStarted) {
			justStarted = false;
			BaseDynamic ghost = new Ghost(x,y,width,height,Images.ghostRed,handler);
			map.addEnemy(ghost);
			ghost = new Ghost(x,y,width,height,Images.ghostPink,handler);
			map.addEnemy(ghost);
			ghost = new Ghost(x,y,width,height,Images.ghostBlue,handler);
			map.addEnemy(ghost);
			ghost = new Ghost(x,y,width,height,Images.ghostOrange,handler);
			map.addEnemy(ghost);
		}
		else {
			Random randColor = new Random();
			int colorType = randColor.nextInt(5)+1;
			BufferedImage ghostImage = ghostImage(colorType);
			BaseDynamic ghost = new Ghost(x,y,width,height,ghostImage,handler);
			handler.getMap().getEnemiesOnMap().add(ghost);
			
		}
		
//		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_C)) {   //Esto fue tratando
//			 int aprete = 2;
//			 BaseDynamic ghost = new Ghost(x,y,width,height,Images.ghostRed,handler);
//			 map.addEnemy(ghost); 
//			 System.out.println(" " + aprete);
//		 }
	}

	private static BufferedImage ghostImage(int colorType) {
		switch(colorType) {
		case 1:
			return Images.ghostRed;
		case 2:
			return Images.ghostBlue;
		case 3:
			return Images.ghostPink;
		case 4:
			return Images.ghostOrange;
		default:
			break;
		}
		return null;
	}
}