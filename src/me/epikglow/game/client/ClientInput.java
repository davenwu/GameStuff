package me.epikglow.game.client;

import org.lwjgl.input.Mouse;

public class ClientInput {
    private boolean leftButtonDown;
    public ClientMain main;
    
    public void ClientInput() {
        leftButtonDown = Mouse.isButtonDown(0);
    }
    
    // For binding ClientMain class to ClientInput
    public void bind(ClientMain main) {
        this.main = main;
    }
    
    public boolean checkLeftMouseButton() {
        return leftButtonDown;
    }
    
    public void pollInput() {
        if(Mouse.isButtonDown(0)) {
            if(!leftButtonDown) {
                // Shoot each time that the left mouse button is clicked, but not for every instance that it is being held down
                // Only applicable for semi-auto guns; will have to implement shooting for automatic guns when the time comes for it
                main.mainPlayer.shoot();
                leftButtonDown = true;
            }
        }
        else {
            // Toggle left mouse button status each time it is "unclicked"
            leftButtonDown = false;
        }
        
        
    }
}
