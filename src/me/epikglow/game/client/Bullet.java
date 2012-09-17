package me.epikglow.game.client;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.opengl.GL11;

public class Bullet implements Renderable, Updateable {
    private double x;
    private double y;
    private final int speed = 1000;
    private double angle;
    
    public Bullet(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public double getX() {
        return x;
    }
    
    public double getY() {
        return y;
    }
    
    public double getAngle() {
        return angle;
    }
    
    public int getSpeed() {
        return speed;
    }
    
    public void setX(double x) {
        this.x = x;
    }
    
    public void setY(double y) {
        this.y = y;
    }
    
    public void setAngle(double angle) {
        this.angle = angle;
    }

    @Override
    public void render(ClientGraphics graphics) {
        // Load the "bullet" texture
        try {
            graphics.loadTexture("bullet");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Bullet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Bullet.class.getName()).log(Level.SEVERE, null, ex);
        }

        GL11.glBindTexture(GL11.GL_TEXTURE_2D, graphics.getTexture());

        // Begin drawing
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

    // Update bullet position based on the delta (time elapsed since last frame)
    @Override
    public void update(int delta) {
        x += (delta * speed * Math.cos(angle)) / 1000;
        y += (delta * speed * Math.sin(angle)) / 1000;
    }
}