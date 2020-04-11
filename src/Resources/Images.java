package Resources;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by AlexVR on 1/24/2020.
 */
public class Images {


    public static BufferedImage titleScreenBackground;
    public static BufferedImage pauseBackground;
    public static BufferedImage selectionBackground;
    public static BufferedImage galagaCopyright;
    public static BufferedImage galagaSelect;
    public static BufferedImage muteIcon;
    public static BufferedImage galagaPlayerLaser;
    public static BufferedImage[] startGameButton;
    public static BufferedImage[] galagaLogo;
    public static BufferedImage[] pauseResumeButton;
    public static BufferedImage[] pauseToTitleButton;
    public static BufferedImage[] pauseOptionsButton;
    public static BufferedImage[] galagaPlayer;
    public static BufferedImage[] galagaPlayerDeath;
    public static BufferedImage[] galagaEnemyDeath;
    public static BufferedImage[] galagaEnemyBee;

    public static BufferedImage map1;
    public static BufferedImage ghostRed;//(Jeziel)
    public static BufferedImage ghostPink;//(Jeziel)
    public static BufferedImage ghostBlue;//(Jeziel)
    public static BufferedImage ghostOrange;//(Jeziel)
    public static BufferedImage[] ghostFlash;//(Carlos)
    
    public static BufferedImage[] redRight;
    public static BufferedImage[] redLeft;
    public static BufferedImage[] redUp;
    public static BufferedImage[] redDown;
    
    public static BufferedImage[] pinkRight;
    public static BufferedImage[] pinkLeft;
    public static BufferedImage[] pinkUp;
    public static BufferedImage[] pinkDown;
    
    public static BufferedImage[] blueRight;
    public static BufferedImage[] blueLeft;
    public static BufferedImage[] blueUp;
    public static BufferedImage[] blueDown;
    
    public static BufferedImage[] orangeRight;
    public static BufferedImage[] orangeLeft;
    public static BufferedImage[] orangeUp;
    public static BufferedImage[] orangeDown;
    
    public static BufferedImage[] pacmanDots;
    public static BufferedImage[] pacmanRight;
    public static BufferedImage[] pacmanLeft;
    public static BufferedImage[] pacmanUp;
    public static BufferedImage[] pacmanDown;
    public static BufferedImage[] bound;
    public static BufferedImage intro;
    public static BufferedImage start;
    public static BufferedImage pacMenu; //New Menu Picture (Jeziel)
    public static BufferedImage smallDot;
    public static BufferedImage[] pacmanDies;// Animation of PacMan dying (Jeziel)
    public static BufferedImage pacmanLife; // Big PacMan for Life (Jeziel)
    public static BufferedImage[] fruits;


    
    




    public static BufferedImage galagaImageSheet;
    public SpriteSheet galagaSpriteSheet;

    public static BufferedImage pacmanImageSheet;
    public SpriteSheet pacmanSpriteSheet;

    public Images() {

        startGameButton = new BufferedImage[3];
        pauseResumeButton = new BufferedImage[2];
        pauseToTitleButton = new BufferedImage[2];
        pauseOptionsButton = new BufferedImage[2];
        galagaLogo = new BufferedImage[3];
        galagaPlayer = new BufferedImage[8];//not full yet, only has second to last image on sprite sheet
        galagaPlayerDeath = new BufferedImage[8];
        galagaEnemyDeath = new BufferedImage[5];
        galagaEnemyBee = new BufferedImage[8];

        pacmanDots = new BufferedImage[2];
        pacmanRight = new BufferedImage[2];
        pacmanLeft = new BufferedImage[2];
        pacmanUp = new BufferedImage[2];
        pacmanDown = new BufferedImage[2];
        
        redRight = new BufferedImage[2];
        redLeft = new BufferedImage[2];
        redUp = new BufferedImage[2];
        redDown = new BufferedImage[2];
        
        pinkRight = new BufferedImage[2];
        pinkLeft = new BufferedImage[2];
        pinkUp = new BufferedImage[2];
        pinkDown = new BufferedImage[2];
        
        blueRight = new BufferedImage[2];
        blueLeft = new BufferedImage[2];
        blueUp = new BufferedImage[2];
        blueDown = new BufferedImage[2];
        
        orangeRight = new BufferedImage[2];
        orangeLeft = new BufferedImage[2];
        orangeUp = new BufferedImage[2];
        orangeDown = new BufferedImage[2];
        
        ghostFlash = new BufferedImage[4];
        bound = new BufferedImage[16];
        pacmanDies = new BufferedImage[16];
        fruits = new BufferedImage[6];


        try {

            startGameButton[0]= ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Start/NormalStartButton.png"));
            startGameButton[1]= ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Start/HoverStartButton.png"));
            startGameButton[2]= ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Start/ClickedStartButton.png"));

            titleScreenBackground = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Title.png"));

            pauseBackground = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Pause.png"));

            selectionBackground = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Selection(2).png"));

            galagaCopyright = ImageIO.read(getClass().getResourceAsStream("/UI/Misc/Copyright.png"));

            galagaSelect = ImageIO.read(getClass().getResourceAsStream("/UI/Misc/galaga_select.png"));

            muteIcon = ImageIO.read(getClass().getResourceAsStream("/UI/Misc/mute.png"));

            galagaLogo[0] = ImageIO.read(getClass().getResourceAsStream("/UI/Misc/galaga_logo.png"));
            galagaLogo[1] = ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Selection/Galaga/hover_galaga_logo.png"));
            galagaLogo[2] = ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Selection/Galaga/pressed_galaga_logo.png"));

            pauseResumeButton[0] = ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Pause/Resume/NormalHoverResume.png"));
            pauseResumeButton[1] = ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Pause/Resume/PressedResume.png"));

            pauseToTitleButton[0] = ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Pause/ToTitle/NormalHoverToTitleButton.png"));
            pauseToTitleButton[1] = ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Pause/ToTitle/PressedToTitleButton.png"));

            pauseOptionsButton[0] = ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Pause/ToOptions/NormalHoverToOptionsButton.png"));
            pauseOptionsButton[1] = ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Pause/ToOptions/PressedToOptionsButton.png"));

            galagaImageSheet = ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/Galaga/Galaga.png"));
            galagaSpriteSheet = new SpriteSheet(galagaImageSheet);

            galagaPlayer[0] = galagaSpriteSheet.crop(160,55,15,16);

            galagaPlayerDeath[0] = galagaSpriteSheet.crop(209,48,32,32);
            galagaPlayerDeath[1] = galagaSpriteSheet.crop(209,48,32,32);
            galagaPlayerDeath[2] = galagaSpriteSheet.crop(247,48,32,32);
            galagaPlayerDeath[3] = galagaSpriteSheet.crop(247,48,32,32);
            galagaPlayerDeath[4] = galagaSpriteSheet.crop(288,47,32,32);
            galagaPlayerDeath[5] = galagaSpriteSheet.crop(288,47,32,32);
            galagaPlayerDeath[6] = galagaSpriteSheet.crop(327,47,32,32);
            galagaPlayerDeath[7] = galagaSpriteSheet.crop(327,47,32,32);

            galagaEnemyDeath[0] = galagaSpriteSheet.crop(201,191,32,32);
            galagaEnemyDeath[1] = galagaSpriteSheet.crop(223,191,32,32);
            galagaEnemyDeath[2] = galagaSpriteSheet.crop(247,191,32,32);
            galagaEnemyDeath[3] = galagaSpriteSheet.crop(280,191,32,32);
            galagaEnemyDeath[4] = galagaSpriteSheet.crop(320,191,32,32);

            galagaEnemyBee[0] = galagaSpriteSheet.crop(188,178,9,10);
            galagaEnemyBee[1] = galagaSpriteSheet.crop(162,178,13,10);
            galagaEnemyBee[2] = galagaSpriteSheet.crop(139,177,11,12);
            galagaEnemyBee[3] = galagaSpriteSheet.crop(113,176,14,13);
            galagaEnemyBee[4] = galagaSpriteSheet.crop(90,177,13,13);
            galagaEnemyBee[5] = galagaSpriteSheet.crop(65,176,13,14);
            galagaEnemyBee[6] = galagaSpriteSheet.crop(42,178,12,11);
            galagaEnemyBee[7] = galagaSpriteSheet.crop(19,177,10,13);


            galagaPlayerLaser = galagaSpriteSheet.crop(365 ,219,3,8);

            pacmanImageSheet = ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/PacMan/Background.png"));
            
            // New Menu Picture (Jeziel)
            pacMenu = ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/PacMan/pac-menu.png"));
            
            pacmanSpriteSheet = new SpriteSheet(pacmanImageSheet);
            map1 = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/PacManMaps/map1.png"));
            
            ghostRed = pacmanSpriteSheet.crop(456,64,16,16); //Red Ghost
            ghostPink = pacmanSpriteSheet.crop(456,80,16,16); //Pink Ghost 
            ghostBlue = pacmanSpriteSheet.crop(456,96,16,16); //Blue Ghost 
            ghostOrange= pacmanSpriteSheet.crop(456,112,16,16); //Orange Ghost 
            
            ghostFlash[0] = pacmanSpriteSheet.crop(600,64,16,16);
            ghostFlash[1] = pacmanSpriteSheet.crop(600,64,16,16);
            ghostFlash[2] = pacmanSpriteSheet.crop(632,64,16,16);
            ghostFlash[3] = pacmanSpriteSheet.crop(632,64,16,16);
            
            pacmanDots[0] = pacmanSpriteSheet.crop(643,18,16,16); //BigDot
            pacmanDots[1] = pacmanSpriteSheet.crop(548,221,16,16); // Picture for Big Dot Animation (Jeziel)
            smallDot = pacmanSpriteSheet.crop(623,18,16,16); //Small dot
            pacmanLife = pacmanSpriteSheet.crop(519,15,32,34); //Big PacMan for Life (Jeziel)

            bound[0] = pacmanSpriteSheet.crop(603,18,16,16);//single
            bound[1] = pacmanSpriteSheet.crop(615,37,16,16);//right open
            bound[2] = pacmanSpriteSheet.crop(635,37,16,16);//down open
            bound[3] = pacmanSpriteSheet.crop(655,37,16,16);//left open
            bound[4] = pacmanSpriteSheet.crop(655,57,16,16);//up open
            bound[5] = pacmanSpriteSheet.crop(655,75,16,16);//up/down
            bound[6] = pacmanSpriteSheet.crop(656,116,16,16);//left/Right
            bound[7] = pacmanSpriteSheet.crop(656,136,16,16);//up/Right
            bound[8] = pacmanSpriteSheet.crop(655,174,16,16);//up/left
            bound[9] = pacmanSpriteSheet.crop(655,155,16,16);//down/Right
            bound[10] = pacmanSpriteSheet.crop(655,192,16,16);//down/left
            bound[11] = pacmanSpriteSheet.crop(664,232,16,16);//all
            bound[12] = pacmanSpriteSheet.crop(479,191,16,16);//left
            bound[13] = pacmanSpriteSheet.crop(494,191,16,16);//right
            bound[14] = pacmanSpriteSheet.crop(479,208,16,16);//top
            bound[15] = pacmanSpriteSheet.crop(479,223,16,16);//bottom
            
            //PacMan Dead Animation (Jeziel)
            pacmanDies[0] = pacmanSpriteSheet.crop(488,0,15,15);//Complete
            pacmanDies[1] = pacmanSpriteSheet.crop(504,0,15,15);//Opening
            pacmanDies[2] = pacmanSpriteSheet.crop(520,0,15,15);//Opening
            pacmanDies[3] = pacmanSpriteSheet.crop(536,0,15,15);//Opening
            pacmanDies[4] = pacmanSpriteSheet.crop(552,0,15,15);//Opening
            pacmanDies[5] = pacmanSpriteSheet.crop(568,0,15,15);//Opening
            pacmanDies[6] = pacmanSpriteSheet.crop(584,0,15,15);//Opening
            pacmanDies[7] = pacmanSpriteSheet.crop(600,0,15,15);//Opening
            pacmanDies[8] = pacmanSpriteSheet.crop(616,0,15,15);//Opening
            pacmanDies[9] = pacmanSpriteSheet.crop(632,0,15,15);//Opening
            pacmanDies[10] = pacmanSpriteSheet.crop(648,0,15,15);//Opening
            pacmanDies[11] = pacmanSpriteSheet.crop(664,4,15,15);//Explosion
            pacmanDies[12] = pacmanSpriteSheet.crop(548,221,15,15);//Black for finishing animation
            pacmanDies[13] = pacmanSpriteSheet.crop(548,221,15,15);//Black for finishing animation
            pacmanDies[14] = pacmanSpriteSheet.crop(548,221,15,15);//Black for finishing animation
            pacmanDies[15] = pacmanSpriteSheet.crop(548,221,15,15);//Black for finishing animation


            //Fruits (Jeziel)
            fruits[0] = pacmanSpriteSheet.crop(489,49,15,15); //Cherry
            fruits[1] = pacmanSpriteSheet.crop(504,49,15,15); //Strawberry
            fruits[2] = pacmanSpriteSheet.crop(521,49,15,15); //Peach
            fruits[3] = pacmanSpriteSheet.crop(537,49,15,15); //Apple
            fruits[4] = pacmanSpriteSheet.crop(553,49,15,15); //Pear
            
            // Right Anims
            pacmanRight[0] = pacmanSpriteSheet.crop(473,1,12,13);
            pacmanRight[1] = pacmanSpriteSheet.crop(489,1,13,13);
            
            redRight[0] = pacmanSpriteSheet.crop(473,65,16,16);
            redRight[1] = pacmanSpriteSheet.crop(489,65,16,16);
            
            pinkRight[0] = pacmanSpriteSheet.crop(473,81,16,16);
            pinkRight[1] = pacmanSpriteSheet.crop(489,81,16,16);
            
            blueRight[0] = pacmanSpriteSheet.crop(473,97,16,16);
            blueRight[1] = pacmanSpriteSheet.crop(489,97,16,16);
            
            orangeRight[0] = pacmanSpriteSheet.crop(473,113,16,16);
            orangeRight[1] = pacmanSpriteSheet.crop(489,113,16,16);
            
            // Left Anims
            pacmanLeft[0] = pacmanSpriteSheet.crop(474,17,12,13);
            pacmanLeft[1] = pacmanSpriteSheet.crop(489,1,13,13);
            
            redLeft[0] = pacmanSpriteSheet.crop(505,65,16,16);
            redLeft[1] = pacmanSpriteSheet.crop(521,65,16,16);
            
            pinkLeft[0] = pacmanSpriteSheet.crop(505,81,16,16);
            pinkLeft[1] = pacmanSpriteSheet.crop(521,81,16,16);
            
            blueLeft[0] = pacmanSpriteSheet.crop(505,97,16,16);
            blueLeft[1] = pacmanSpriteSheet.crop(521,97,16,16);
            
            orangeLeft[0] = pacmanSpriteSheet.crop(505,113,16,16);
            orangeLeft[1] = pacmanSpriteSheet.crop(521,113,16,16);

            // Up Anims
            pacmanUp[0] = pacmanSpriteSheet.crop(473,34,13,12);
            pacmanUp[1] = pacmanSpriteSheet.crop(489,1,13,13);
            
            redUp[0] = pacmanSpriteSheet.crop(537,65,16,16);
            redUp[1] = pacmanSpriteSheet.crop(553,65,16,16);
            
            pinkUp[0] = pacmanSpriteSheet.crop(537,81,16,16);
            pinkUp[1] = pacmanSpriteSheet.crop(553,81,16,16);
            
            blueUp[0] = pacmanSpriteSheet.crop(537,97,16,16);
            blueUp[1] = pacmanSpriteSheet.crop(553,97,16,16);
            
            orangeUp[0] = pacmanSpriteSheet.crop(537,113,16,16);
            orangeUp[1] = pacmanSpriteSheet.crop(553,113,16,16);

            // Down Anims
            pacmanDown[0] = pacmanSpriteSheet.crop(473,48,13,12);
            pacmanDown[1] = pacmanSpriteSheet.crop(489,1,13,13);
            
            redDown[0] = pacmanSpriteSheet.crop(569,65,16,16);
            redDown[1] = pacmanSpriteSheet.crop(585,65,16,16);
            
            pinkDown[0] = pacmanSpriteSheet.crop(569,81,16,16);
            pinkDown[1] = pacmanSpriteSheet.crop(585,81,16,16);
            
            blueDown[0] = pacmanSpriteSheet.crop(569,97,16,16);
            blueDown[1] = pacmanSpriteSheet.crop(585,97,16,16);
            
            orangeDown[0] = pacmanSpriteSheet.crop(569,113,16,16);
            orangeDown[1] = pacmanSpriteSheet.crop(585,113,16,16);

            intro = ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/PacMan/intro.png"));
            start = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/startScreen.png"));



        }catch (IOException e) {
        e.printStackTrace();
    }


    }

    public static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(Images.class.getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

}
