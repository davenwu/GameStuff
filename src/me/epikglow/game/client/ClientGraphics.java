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
    private Texture texture;    // Used for holding textures
    private int width;
    private int height;
    public ClientMain main;
    
    public ClientGraphics(int width, int height) {
        this.width = width;
        this.height = height;
        
        setUpDisplay(); // Set up the window upon initialization
        setUpOpenGL();  // Set up OpenGL upon initialization
    }
    
    // For binding ClientMain class to ClientGraphics
    public void bind(ClientMain main) {
        this.main = main;
    }
    
    // Sets up the window display
    private void setUpDisplay() {
        try {
            Display.setDisplayMode(new DisplayMode(width, height));
            Display.setTitle("Game Title Goes Here");
            Display.create();
        } catch(LWJGLException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    // Sets up OpenGL
    private void setUpOpenGL() {
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, width, 0, height, -1, 1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
    }
    
    // Determines whether the display is requested to be closed ("x'd out")
    public boolean isCloseRequested() {
        return Display.isCloseRequested();
    }
    
    // Used to load a PNG as a texture based on filename
    public void loadTexture(String texName) throws FileNotFoundException, IOException {
        texture = TextureLoader.getTexture("PNG", this.getClass().getResourceAsStream(texName + ".PNG"));
    }
    
    public int getWindowWidth() {
        return width;
    }
    
    public int getWindowHeight() {
        return height;
    }
    
    // Return ID of the currently loaded texture
    public int getTexture() {
        return texture.getTextureID();
    }
    
    // Render a Renderable object
    public void render(Renderable object) {
        object.render(this);
    }
    
    // Update screen/display
    public void update() {
        Display.update();
        Display.sync(main.getFPS());
    }
    
    public void destroy() {
        Display.destroy();
        main = null;
    }

    // Clears display/screen
    public void clearDisplay() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
        
        GL11.glPushMatrix();
    }
}
