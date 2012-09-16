package me.epikglow.game.client;

import me.epikglow.game.network.Player;

public class ClientMain {
    public Player mainPlayer = new Player();   // User's player
    public ClientGraphics graphics;
    public ClientSound sound;
    public ClientInput input;
    public ClientPhysics physics;
    public Timer timer;
    
    // Window dimension variables
    public final int width = 1280;
    public final int height = 720;
    
    public void init() {
        // Initialize all parts of client
        graphics = new ClientGraphics(width, height);   // Window size set to 1280 pixels by 576 pixels for 16:9 "widescreen" ratio
        sound = new ClientSound();
        input = new ClientInput();
        physics = new ClientPhysics(width, height);

        // Bind all components to ClientMain
        graphics.bind(this);
        sound.bind(this);
        input.bind(this);
        physics.bind(this);
        
        // Set up timer after all initializations
        timer = new Timer();
    }
    
    public void main(String[] args) {
        init();
    }
}
