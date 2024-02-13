package object;

import java.io.IOException;
import javax.imageio.ImageIO;


public class OBJ_Door extends SuperObject{
    public OBJ_Door() {
        name = "door";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
    
    public void openDoor() {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/opened_door.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = false;
        isOpened = true;
    }
    
}
