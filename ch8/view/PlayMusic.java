package ch8.view;

import java.applet.*;
import java.io.File;
import java.net.URI;
import java.net.URL;


public class PlayMusic{
    AudioClip clip;
    public void setClipFile(String str){
        try {
            File file=new File(str);
            URI uri=file.toURI();
            URL url=uri.toURL();
            clip=Applet.newAudioClip(url);
        } catch (Exception e) {
           
        }
    }
    public void playMusic(){
        clip.play();

    }
}