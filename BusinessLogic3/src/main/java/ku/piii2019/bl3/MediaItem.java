/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ku.piii2019.bl3;

import java.util.Comparator;
import java.util.Collections;
//import org.apache.commons.lang3.builder.*;
/**
 *
 * @author James
 */
public class MediaItem implements Comparable<MediaItem> {

    private String absolutePath;
    private String title;
    private String album;
    private int duration;

    public String getTitle() {
        return title;
    }

    public String getAlbum() {
        return album;
    }

    public String getArtist() {
        return artist;
    }
    private String artist;
            
    public String getAbsolutePath() {
        return absolutePath;
    }

    public MediaItem setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
        return this;
    }
    
    public String getDuration() {
        return ""+duration;
    }

    public MediaItem setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof MediaItem)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        return this.absolutePath.equals(((MediaItem) obj).getAbsolutePath());
    }
    @Override
    public int hashCode() {
        return this.absolutePath.hashCode();
    }

    public MediaItem  setTitle(String title) {
        this.title = title;
        return this;
    }

    public MediaItem  setAlbum(String album) {
        this.album = album;
        return this;
    }

    public MediaItem setArtist(String artist) {
        this.artist = artist;
        return this;
    }

    @Override
    public int compareTo(MediaItem mi) {
        if(mi==null){
            return -1;
        }
        /*CompareToBuilder builder = new CompareToBuilder();
        
        return builder
                .append(this.getArtist(), mi.getArtist())
                .append(this.getAlbum(), mi.getAlbum())
                .toComparison();*/
        return 0;
    }
}
