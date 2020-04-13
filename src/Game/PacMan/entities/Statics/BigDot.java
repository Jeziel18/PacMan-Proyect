package Game.PacMan.entities.Statics;

import Main.Handler;
import Resources.Animation;
import Resources.Images;

public class BigDot extends BaseStatic{
	
	public Animation blinkBigDot; //Blinking Dot
	
    public BigDot(int x, int y, int width, int height, Handler handler) {
        super(x, y, width, height, handler, Images.pacmanDots[0]);
        blinkBigDot = new Animation(330, Images.pacmanDots); //Blinking Big Dot animation
    }
    public void tick() {
    	blinkBigDot.tick(); //Blinking Big Dot animation
    }
}
