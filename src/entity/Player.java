package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;
import main.Sound;
import object.OBJ_Chest;
import object.OBJ_Door;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    int hasKey = 0;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle();
        solidArea.x = 10;
        solidArea.y = 20;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 44;
        solidArea.height = 44;

        setDefaultValue();
        getPlayerImage();
    }

    public void setDefaultValue() {
        worldX = gp.tileSize * 25;
        worldY = gp.tileSize * 26;

        speed = gp.scale;
        direction = "down";
        prevDirection = direction;
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/rabbit_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/rabbit_up_2.png"));
            standUp1 = ImageIO.read(getClass().getResourceAsStream("/player/rabbit_stand_up_1.png"));
            standUp2 = ImageIO.read(getClass().getResourceAsStream("/player/rabbit_stand_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/rabbit_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/rabbit_down_2.png"));
            standDown1 = ImageIO.read(getClass().getResourceAsStream("/player/rabbit_stand_down_1.png"));
            standDown2 = ImageIO.read(getClass().getResourceAsStream("/player/rabbit_stand_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/rabbit_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/rabbit_left_2.png"));
            standLeft1 = ImageIO.read(getClass().getResourceAsStream("/player/rabbit_stand_left_1.png"));
            standLeft2 = ImageIO.read(getClass().getResourceAsStream("/player/rabbit_stand_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/rabbit_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/rabbit_right_2.png"));
            standRight1 = ImageIO.read(getClass().getResourceAsStream("/player/rabbit_stand_right_1.png"));
            standRight2 = ImageIO.read(getClass().getResourceAsStream("/player/rabbit_stand_right_2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyH.upPressed == true || keyH.downPressed == true
                || keyH.leftPressed == true || keyH.rightPressed == true) {
            if (keyH.upPressed == true) {
                direction = "up";
            } else if (keyH.downPressed == true) {
                direction = "down";
            } else if (keyH.leftPressed == true) {
                direction = "left";
            } else if (keyH.rightPressed == true) {
                direction = "right";
            }

            //CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            //IF COLLISION IS FALSE, PLAYER CAN MOVE
            if (!collisionOn) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }
            prevDirection = direction;

            spriteCounter++;
            //player image change every 10 iteration
            if (spriteCounter > 10) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
        //condition for stand sprite
        if (keyH.upPressed == false && keyH.downPressed == false
                && keyH.leftPressed == false && keyH.rightPressed == false) {
            if (prevDirection.equals("up")) {
                direction = "standUp";
            } else if (prevDirection.equals("down")) {
                direction = "standDown";
            } else if (prevDirection.equals("left")) {
                direction = "standLeft";
            } else if (prevDirection.equals("right")) {
                direction = "standRight";
            }

            spriteCounter++;
            //player image change every 10 iteration
            if (spriteCounter > 10) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void pickUpObject(int i) {
        if (i != 999) {
            String objectName = gp.obj[i].name;
            switch (objectName) {
                case "key":
                    hasKey++;
                    gp.obj[i] = null;
                    gp.playSE(3);
                    break;
                case "door":
                    if (!((OBJ_Door) gp.obj[i]).isOpened) {
                        if (hasKey > 0) {
                            ((OBJ_Door) gp.obj[i]).openDoor();
                            hasKey--;
                        }
                        gp.playSE(2);
                    }

                    break;
                case "chest":
                    if (!((OBJ_Chest) gp.obj[i]).isOpened) {
                        if (hasKey > 0) {
                            ((OBJ_Chest) gp.obj[i]).openChest();
                            hasKey--;
                        }
                        gp.playSE(1);
                    }
                    break;
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                break;
            case "standUp":
                if (spriteNum == 1) {
                    image = standUp1;
                }
                if (spriteNum == 2) {
                    image = standUp2;
                }
                break;
            case "standDown":
                if (spriteNum == 1) {
                    image = standDown1;
                }
                if (spriteNum == 2) {
                    image = standDown2;
                }
                break;
            case "standLeft":
                if (spriteNum == 1) {
                    image = standLeft1;
                }
                if (spriteNum == 2) {
                    image = standLeft2;
                }
                break;
            case "standRight":
                if (spriteNum == 1) {
                    image = standRight1;
                }
                if (spriteNum == 2) {
                    image = standRight2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
