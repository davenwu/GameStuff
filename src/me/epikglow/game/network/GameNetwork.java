package me.epikglow.game.network;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;

public class GameNetwork {
    public static final int port = 51896;

    static public void register (EndPoint endPoint) {
        Kryo kryo = endPoint.getKryo();
        kryo.register(Player.class);
        kryo.register(IDRequest.class);
        kryo.register(IDPacket.class);
        kryo.register(UpdatePlayer.class);
        kryo.register(AddPlayer.class);
        kryo.register(AddPlayerSuccess.class);
        kryo.register(AddPlayerFail.class);
        kryo.register(RemovePlayer.class);
    }
    
    static public class IDRequest {
    }
    
    static public class IDPacket {
        public int id;
    }

    static public class UpdatePlayer {
        public Player player;
    }
    
    static public class UpdatePlayerPositions {
        public Player[] players;
    }
    
    static public class AddPlayer {
        public Player player;
    }
    
    static public class AddPlayerSuccess {
    }
    
    static public class AddPlayerFail {
    }
    
    static public class RemovePlayer {
        public int id;
    }
}
