package me.epikglow.game.client;

import java.util.ArrayList;
import me.epikglow.game.network.Player;

public class ClientPhysics {
    private int numProjectiles;
    private ArrayList objects = new ArrayList();
    private int width, height;
    public ClientMain main;
    
    public ClientPhysics(int width, int height) {
        numProjectiles = 0;
        this.width = width;
        this.height = height;
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
                
                if((player.x < main.width && player.x > 0) && (player.y < main.height && player.y > 0)) {
                    player.x += delta * player.dx;
                    player.y += delta * player.dy;
                    objects.set(index, player);
                    
                    // Update objectsArray to current/updated objects ArrayList
                    objectsArray = objects.toArray();
                    index++;
                }
                else {
                    player = null;
                    remove(index);
                    objects.trimToSize();
                    objectsArray = objects.toArray();
                }
            }
            else if(objectsArray[index] instanceof Bullet) {
                Bullet bullet = (Bullet) objectsArray[index];
                
                if((bullet.getX() < main.width && bullet.getX() > 0) && (bullet.getY() < main.height && bullet.getY() > 0)) {
                    bullet.setX(bullet.getX() + (delta * bullet.getVelocity() * Math.cos(bullet.getAngle())) / 1000);
                    bullet.setY(bullet.getY() + (delta * bullet.getVelocity() * Math.sin(bullet.getAngle())) / 1000);
                    
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
}
