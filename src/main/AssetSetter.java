package main;

import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;


public class AssetSetter {
    GamePanel gp;
    
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }
    
    public void setObject() {
        gp.obj[0] = new OBJ_Key();
        gp.obj[0].worldX = 28 * gp.tileSize;
        gp.obj[0].worldY = 25 * gp.tileSize;
        
        gp.obj[1] = new OBJ_Chest();
        gp.obj[1].worldX = 24 * gp.tileSize;
        gp.obj[1].worldY = 37 * gp.tileSize;
        
        gp.obj[2] = new OBJ_Door();
        gp.obj[2].worldX = 26 * gp.tileSize;
        gp.obj[2].worldY = 36 * gp.tileSize;
        
        gp.obj[3] = new OBJ_Key();
        gp.obj[3].worldX = 21 * gp.tileSize;
        gp.obj[3].worldY = 26 * gp.tileSize;
        
    }
}
