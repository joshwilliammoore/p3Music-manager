/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ku.piii2019.bl3;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author James
 */
public class SimpleSearch implements SearchService{

    @Override
    public Set<MediaItem> find(String thisSearchTerm, Set<MediaItem> inhere) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Set<MediaItem> result = new HashSet<>();
        Set<MediaItem> temp = new HashSet<>();
        try{
        for(int i=0;i<thisSearchTerm.length();i++){
            if(thisSearchTerm.charAt(i)==' '){
                break;
            }
            for(MediaItem item : inhere){
                if(item.getArtist().toLowerCase().contains(thisSearchTerm)||
                    item.getAlbum().toLowerCase().contains(thisSearchTerm)||
                    item.getTitle().toLowerCase().contains(thisSearchTerm)){
                    result.add(item);
                }
                if(item.getArtist().contains(thisSearchTerm)||
                    item.getAlbum().contains(thisSearchTerm)||
                    item.getTitle().contains(thisSearchTerm)){
                    result.add(item);
                }
            }
        }
        /*if(thisSearchTerm==" "||thisSearchTerm==""){
            return temp;
        }*/
        }catch(NullPointerException e){
            
        }
        return result;
    }
    
    
    public Set<MediaItem> findArtist(String thisSearchTerm, Set<MediaItem> inhere) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Set<MediaItem> result = new HashSet<>();
        Set<MediaItem> temp = new HashSet<>();
        try{
        //for(int i=0;i<thisSearchTerm.length();i++){
            /*if(thisSearchTerm.charAt(i)==' '){
                break;
            }*/
            for(MediaItem item : inhere){
                if(item.getArtist().toLowerCase().contains(thisSearchTerm.toLowerCase())){
                    result.add(item);
                }
                /*if(item.getArtist().contains(thisSearchTerm)){
                    result.add(item);
                }*/
            }
        //}
        /*if(thisSearchTerm==" "||thisSearchTerm==""){
            return temp;
        }*/
        }catch(NullPointerException e){
            
        }
        return result;
    }
    
}
