package Game.PacMan.entities.Statics;

import Game.PacMan.entities.BaseEntity;
import Main.Handler;

import java.awt.image.BufferedImage;

public class BaseStatic extends BaseEntity {

    public BaseStatic(int x, int y, int width, int height, Handler handler, BufferedImage sprite) {
        super(x, y, width, height, handler, sprite);
    }
    
    boolean fruit =false;
    int fru = 0;
    
    public void setFruit(){   //Setter de fruit
        fruit = true;
    }

    public boolean getIsFruit(){  //Getter de fruit
        return fruit;
    }
    
    public void setFruit(int f){   //Setter de fruit
        fru = f;
    } 
    
    public int getrandFruit(){
    	return fru;
    }
}
