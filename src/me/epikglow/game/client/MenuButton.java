package me.epikglow.game.client;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.opengl.GL11;

public class MenuButton implements Renderable {
    public int x;
    public int y;
    
    public MenuButton(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public void render(ClientGraphics graphics) {
        // Load the "menubutton" texture
        try {
            graphics.loadTexture("menubutton");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Bullet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Bullet.class.getName()).log(Level.SEVERE, null, ex);
        }

        GL11.glBindTexture(GL11.GL_TEXTURE_2D, graphics.getTexture());

        // Begin drawing GL_QUADS
        GL11.glBegin(GL11.GL_QUADS);
            // Bottom-left corner
            GL11.glTexCoord2f(0, 1);
            GL11.glVertex2f((float) x - 128, (float) y - 79);
            // Top-left corner
            GL11.glTexCoord2f(0, 0);
            GL11.glVertex2f((float) x - 128, (float) y + 79);
            // Top-right corner
            GL11.glTexCoord2f(1, 0);
            GL11.glVertex2f((float) x + 128, (float) y + 79);
            // Bottom-right corner
            GL11.glTexCoord2f(1, 1);
            GL11.glVertex2f((float) x + 128, (float) y - 79);
        // Finish drawing
        GL11.glEnd();
    }
}
