package me.epikglow.game.network;

public class Player {
    public String nick;
    public int id;  // ID to go by for this player. Essentially the index of the
                    // player in the Player array stored on the server
    public int x;
    public int y;
    public boolean openSlot;

    public Player() {
        nick = "Player";    // Default name will be Player unless nick is changed in config
        id = -1;
        x = 0;
        y = 0;
        openSlot = true;
    }
}
