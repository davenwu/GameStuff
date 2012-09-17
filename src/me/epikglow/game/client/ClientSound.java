package me.epikglow.game.client;

import java.io.BufferedInputStream;
import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.util.WaveData;

public class ClientSound {
    private int buffer;
    private int source;
    public ClientMain main;
    
    public ClientSound() {
        // Set up OpenAL upon initializing ClientSound
        setUpOpenAL();
        
        buffer = AL10.alGenBuffers();
        source = AL10.alGenSources();
    }
    
    // For binding ClientMain class to ClientGraphics
    public void bind(ClientMain main) {
        this.main = main;
    }
    
    private void setUpOpenAL() {
        try {
            AL.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    public void loadSound(String wavName) {
        WaveData data = WaveData.create(new BufferedInputStream(this.getClass().getResourceAsStream(wavName + ".wav")));
        AL10.alBufferData(buffer, data.format, data.data, data.samplerate);
        data.dispose();
        
        AL10.alSourcei(source, AL10.AL_BUFFER, buffer);
        AL10.alSourcef(source, AL10.AL_GAIN, 0.2f);
    }
    
    public void playSound() {
        AL10.alSourcePlay(source);
    }
    
    public void destroy() {
        AL10.alDeleteBuffers(buffer);
        AL10.alDeleteSources(source);
        AL.destroy();
        main = null;
    }
}
