package me.epikglow.game.client;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class ClientGraphics {
    private int width, height;  // Window width and height
    private Texture texture;    // Used for holding textures
    
    public ClientGraphics(int width, int height) {
        this.width = width;
        this.height = height;
        
        setUpDisplay(); // Set up the window upon initialization
        setUpOpenGL();  // Set up OpenGL upon initialization
    }
    
    private void setUpDisplay() {
        try {
            Display.setDisplayMode(new DisplayMode(width, height));
            Display.setTitle("Game Title Goes Here");
            Display.create();
        } catch(LWJGLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
    
    private void setUpOpenGL() {
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, width, 0, height, -1, 1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
    }
    
    // Used to load a PNG as a texture based on filename
    public void loadTexture(String texName) throws FileNotFoundException, IOException {
        texture = TextureLoader.getTexture("PNG", this.getClass().getResourceAsStream(texName + ".png"));
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
}
