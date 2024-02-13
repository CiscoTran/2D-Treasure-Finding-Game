package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class Entity {
    public int worldX, worldY;
    public int speed;
    
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, 
            standUp1, standDown1, standLeft1, standRight1, standUp2, standDown2, standLeft2, standRight2;
    public String direction;
    public String prevDirection;
    
    public int spriteCounter = 0;
    public int spriteNum = 1;
    
    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
}
