package me.epikglow.game.client;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Listener.ThreadedListener;
import me.epikglow.game.network.GameNetwork;
import me.epikglow.game.network.GameNetwork.AddPlayerFail;
import me.epikglow.game.network.GameNetwork.AddPlayerSuccess;
import me.epikglow.game.network.GameNetwork.IDPacket;
import me.epikglow.game.network.GameNetwork.UpdatePlayerPositions;
import me.epikglow.game.network.Player;

public class ClientNetwork {
    public Client client;
    public Player[] players = new Player[8];
    public ClientMain main;

    public ClientNetwork(final Player player) {
        client = new Client();
        
        client.start();
        
        // Register appropriate classes for Kryo/KryoNet
        GameNetwork.register(client);
        
        client.addListener(new ThreadedListener(new Listener() {
            public void connected(Connection connection) {
            }
            
            public void received(Connection connection, Object object) {
                if(object instanceof IDPacket) {
                    IDPacket receivedPacket = (IDPacket) object;
                    
                    // Set player ID to ID received from server
                    player.id = receivedPacket.id;
                }
                else if(object instanceof UpdatePlayerPositions) {
                    UpdatePlayerPositions receivedPacket = (UpdatePlayerPositions) object;
                    
                    int index = 0;
                    
                    // Update local Player array with the one sent by the server
                    while(index < players.length) {
                        players[index] = receivedPacket.players[index];
                        
                        index++;
                    }
                }
                else if(object instanceof AddPlayerSuccess) {
                    // Handle AddPlayerSuccess packet
                }
                else if(object instanceof AddPlayerFail) {
                    // Handle AddPlayerFail packet
                }
            }
        }));
    }
    
    // For binding ClientMain class to ClientGraphics
    public void bind(ClientMain main) {
        this.main = main;
    }
}
