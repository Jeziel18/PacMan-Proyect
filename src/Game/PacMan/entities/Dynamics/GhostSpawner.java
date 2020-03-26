package Game.PacMan.entities.Dynamics;

import Game.PacMan.World.Map;
import Main.Handler;

public class GhostSpawner {
	public static void spawnGhost(int x, int y, int width, int height, Handler handler, Map map) {
		BaseDynamic ghost = new Ghost(x,y,width,height,handler);
		map.addEnemy(ghost);
	}
}