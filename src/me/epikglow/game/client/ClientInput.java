package me.epikglow.game.client;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class ClientInput {
    private boolean leftButtonDown;
    public ClientMain main;
    
    public ClientInput() {
        leftButtonDown = Mouse.isButtonDown(0);
    }
    
    // For binding ClientMain class to ClientInput
    public void bind(ClientMain main) {
        this.main = main;
    }
    
    public void pollInput() {
        if(Mouse.isButtonDown(0)) {
            if(!leftButtonDown) {
                // Shoot each time that the left mouse button is clicked, but not for every instance that it is being held down
                // Only applicable for semi-auto guns; will have to implement shooting for automatic guns when the time comes for it
                main.getMainPlayer().shoot();
                leftButtonDown = true;
            }
        }
        else {
            // Toggle left mouse button status each time it is "unclicked"
            leftButtonDown = false;
        }
        
        if(Keyboard.isKeyDown(Keyboard.KEY_W) && Keyboard.isKeyDown(Keyboard.KEY_S))
            main.getMainPlayer().dy = 0;
        else if(Keyboard.isKeyDown(Keyboard.KEY_W))
            main.getMainPlayer().dy = 200;
        else if(Keyboard.isKeyDown(Keyboard.KEY_S))
            main.getMainPlayer().dy = -200;
        else
            main.getMainPlayer().dy = 0;
        
        if(Keyboard.isKeyDown(Keyboard.KEY_D) && Keyboard.isKeyDown(Keyboard.KEY_A))
            main.getMainPlayer().dx = 0;
        else if(Keyboard.isKeyDown(Keyboard.KEY_D))
            main.getMainPlayer().dx = 200;
        else if(Keyboard.isKeyDown(Keyboard.KEY_A))
            main.getMainPlayer().dx = -200;
        else
            main.getMainPlayer().dx = 0;
    }
    
    public void destroy() {
        main = null;
    }
}
