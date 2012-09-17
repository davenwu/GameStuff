package me.epikglow.game.client;

import java.util.ArrayList;
import me.epikglow.game.network.Player;

public class ClientPhysics {
    private int numProjectiles;
    private ArrayList objects = new ArrayList();
    public ClientMain main;
    
    public ClientPhysics() {
        numProjectiles = 0;
    }
    
    // For binding ClientMain class to ClientPhysics
    public void bind(ClientMain main) {
        this.main = main;
    }
    
    // Update Updateable object's position based on delta
    public void update(int delta) {
        int index = 0;
        Object[] objectsArray = objects.toArray();
        
        while(index < objectsArray.length) {
            if(objectsArray[index] instanceof Player) {
                Player player = (Player) objectsArray[index];
                
                //if((player.x < main.getWidth() && player.x > 0) && (player.y < main.getHeight() && player.y > 0)) {
                    player.update(delta);
                    objects.set(index, player);
                    
                    // Update objectsArray to current/updated objects ArrayList
                    objectsArray = objects.toArray();
                    index++;
                /*}
                else {
                    player = null;
                    remove(index);
                    objects.trimToSize();
                    objectsArray = objects.toArray();
                }*/
            }
            else if(objectsArray[index] instanceof Bullet) {
                Bullet bullet = (Bullet) objectsArray[index];
                
                if((bullet.getX() < main.getWidth() && bullet.getX() > 0) && (bullet.getY() < main.getHeight() && bullet.getY() > 0)) {
                    bullet.update(delta);
                    
                    objects.set(index, bullet);
                    
                    // Update objectsArray to current/updated objects ArrayList
                    objectsArray = objects.toArray();
                    index++;
                }
                else {
                    bullet = null;
                    remove(index);
                    objects.trimToSize();
                    objectsArray = objects.toArray();
                }
            }
            
        }
        
    }
    
    // Add Bullet to the ArrayList projectiles
    public void add(Updateable object) {
        objects.add(object);
        numProjectiles++;
    }
    
    // Remove Bullet from ArrayList projectiles at the provided index
    public void remove(int index) {
        objects.remove(index);
        numProjectiles--;
    }
    
    public void destroy() {
        objects = null;
        main = null;
    }
}
