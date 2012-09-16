package me.epikglow.game.network;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.epikglow.game.client.Bullet;
import me.epikglow.game.client.ClientGraphics;
import me.epikglow.game.client.ClientPhysics;
import me.epikglow.game.client.Renderable;
import me.epikglow.game.client.Updateable;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class Player implements Renderable, Updateable {
    public String nick; // Display name in-game
    public int id;  // ID to go by for this player (i.e. the index of the
                    // player in the Player array stored on the server)
    public int x;
    public int y;
    public boolean openSlot;
    public final int dx = 200; // Rate of change in Player's x
    public final int dy = 200; // Rate of change in Player's y
    private ClientPhysics physics;

    public Player() {
        nick = "Player";    // Default name will be Player unless nick is changed in config
        id = -1;
        x = 0;
        y = 0;
        openSlot = true;
    }

    // Bind Player class to ClientPhysics class
    public void bind(ClientPhysics physics) {
        this.physics = physics;
        
        physics.add(this);
    }
    
    // Shoot a bullet towards the coordinates x and y by adding it to the ArrayList in the ClientPhysics class
    public void shoot() {
        Bullet bullet = new Bullet(x, y);
        bullet.setAngle(Math.atan2(Mouse.getY() - y, Mouse.getX() - x));
        
        physics.add(bullet);
    }
    
    // Render the player on the screen
    @Override
    public void render(ClientGraphics screen, int delta) {
        // Load the "player" texture
        try {
            screen.loadTexture("player");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Begin drawing GL_QUADS
        GL11.glBegin(GL11.GL_QUADS);
            // Bottom-left corner
            GL11.glTexCoord2f(0, 0);
            GL11.glVertex2f((float) x - 6, (float) y - 4);
            // Top-left corner
            GL11.glTexCoord2f(0, 1);
            GL11.glVertex2f((float) x - 6, (float) y + 4);
            // Top-right corner
            GL11.glTexCoord2f(1, 1);
            GL11.glVertex2f((float) x + 6, (float) y + 4);
            // Bottom-right corner
            GL11.glTexCoord2f(1, 0);
            GL11.glVertex2f((float) x + 6, (float) y - 4);
        // Finish drawing
        GL11.glEnd();
    }
    
    // Updates player position based on the delta (time elapsed since last frame)
    @Override
    public void update(int delta) {
        x += delta;
        y += delta;
    }
}
