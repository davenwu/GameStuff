package me.epikglow.game.server;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.epikglow.game.network.GameNetwork;
import me.epikglow.game.network.GameNetwork.AddPlayer;
import me.epikglow.game.network.GameNetwork.AddPlayerFail;
import me.epikglow.game.network.GameNetwork.AddPlayerSuccess;
import me.epikglow.game.network.GameNetwork.IDPacket;
import me.epikglow.game.network.GameNetwork.IDRequest;
import me.epikglow.game.network.GameNetwork.RemovePlayer;
import me.epikglow.game.network.GameNetwork.UpdatePlayer;
import me.epikglow.game.network.GameNetwork.UpdatePlayerPositions;
import me.epikglow.game.network.Player;

public class GameServer {
    Server server;
    Player[] players;   // For holding player information
    final int maxPlayers = 8;   // Max players

    public GameServer() {
        server = new Server() {
            protected Connection newConnection() {
                return new PlayerConnection();
            }
        };
        
        // Register appropriate classes for Kryo/KryoNet
        GameNetwork.register(server);
        
        players = new Player[maxPlayers];
        // Fill Player array with default constructor players
        fillNullPlayers();

        server.addListener(new Listener() {
            public void received(Connection connection, Object object) {
                // Handling ID requests for new players
                if(object instanceof IDRequest) {
                    IDPacket packet = new IDPacket();
                    
                    // Sending client next open slot (if any are available)
                    packet.id = nextOpenSlot();
                    
                    // Sending over TCP because it is essential for the client to start playing
                    connection.sendTCP(packet);
                }
                // Update player changes
                else if(object instanceof UpdatePlayer) {
                    UpdatePlayer receivedPacket = (UpdatePlayer) object;
                    
                    players[receivedPacket.player.id].nick = receivedPacket.player.nick;
                    players[receivedPacket.player.id].x = receivedPacket.player.x;
                    players[receivedPacket.player.id].y = receivedPacket.player.y;
                    
                    // Send all connected clients updated player positions
                    UpdatePlayerPositions packet = new UpdatePlayerPositions();
                    packet.players = players;
                    
                    server.sendToAllUDP(packet);
                }
                // Add player into the Player array
                else if(object instanceof AddPlayer) {
                    AddPlayer receivedPacket = (AddPlayer) object;
                    
                    // If player slot is open, add the player into the Player array
                    if(players[receivedPacket.player.id].openSlot) {
                        players[receivedPacket.player.id] = receivedPacket.player;
                        connection.sendTCP(new AddPlayerSuccess());
                    }
                    else
                        connection.sendTCP(new AddPlayerFail());
                }
                // Remove the player that occupies the slot that is specified in the RemovePlayer packet
                // **************** NEED TO FIX THIS FOR SECURITY****************
                else if(object instanceof RemovePlayer) {
                    RemovePlayer receivedPacket = (RemovePlayer) object;
                    
                    players[receivedPacket.id] = new Player();
                }
            }
                
            // For finding the index of the first available slot if any are available
            private int nextOpenSlot() {
                int index = 0;
                
                // Cycle through Player array players for an open slot
                while(index < players.length) {
                    if(players[index].openSlot) {
                        return index;
                    }
                    
                    index++;
                }
                
                // Value to return if no slots are open
                return -1;
            }
        });
        
        try {
            server.bind(GameNetwork.port);
        } catch (IOException ex) {
            Logger.getLogger(GameServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        server.start();
    }

    // Fill Player array with null players--essentially initializing the Player array
    private void fillNullPlayers() {
        int index = 0;
        Player nullPlayer = new Player();
        
        while(index < players.length) {
            players[index] = nullPlayer;
        }
    }

    // For knowing what player each connection is associated with
    static class PlayerConnection extends Connection {
        public Player player;
    }
}

