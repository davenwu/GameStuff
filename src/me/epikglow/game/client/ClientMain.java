package me.epikglow.game.client;

import java.util.ArrayList;
import me.epikglow.game.network.Player;
import org.lwjgl.Sys;

public class ClientMain {
    private Player mainPlayer = new Player();   // User's player
    private ClientGraphics graphics;
    private ClientSound sound;
    private ClientInput input;
    private ClientPhysics physics;
    private Timer timer;
    private ClientState state;
    
    // Window dimension variables
    private final int width = 1024;
    private final int height = 576;
    private final int fps = 120;
    private boolean isRunning = true;
    
    public void init() {
        // Initialize all parts of client
        graphics = new ClientGraphics(width, height);
        sound = new ClientSound();
        input = new ClientInput();
        physics = new ClientPhysics();

        // Bind all components to ClientMain
        graphics.bind(this);
        sound.bind(this);
        input.bind(this);
        physics.bind(this);
        
        // Set up timer after all initializations
        timer = new Timer();
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public int getFPS() {
        return fps;
    }
    
    public Player getMainPlayer() {
        return mainPlayer;
    }
    
    public ArrayList getObjects() {
        return physics.getObjects();
    }
    
    public ClientMain() {
        state = ClientState.INIT;
        init();
        
        mainPlayer.x = width / 2;
        mainPlayer.y = height / 2;
        
        physics.add(mainPlayer);
        mainPlayer.bind(physics);
        mainPlayer.bind(sound);
        
        state = ClientState.GAME;
        
        while(isRunning) {
            if(state == ClientState.MENU) {
                /*
                 * Insert main menu code here
                 * ADD:
                 *  buttons
                 *  background
                 *  title
                 */
                
                if(graphics.isCloseRequested()) {
                    isRunning = false;
                }
            }
            else if(state == ClientState.GAME) {
                input.pollInput();
                physics.update(timer.getDelta());
                graphics.clearDisplay();
                graphics.render(mainPlayer);
                graphics.renderAllObjects();
                graphics.update();

                if(graphics.isCloseRequested()) {
                    isRunning = false;
                }
            }
        }
        
        // Destroy all parts of client
        graphics.destroy();
        physics.destroy();
        sound.destroy();
    }
    
    public static void main(String[] args) {
        ClientMain main = new ClientMain();
    }
}
