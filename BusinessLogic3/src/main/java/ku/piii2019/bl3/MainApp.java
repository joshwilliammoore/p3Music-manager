/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ku.piii2019.bl3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author ku14009
 */
public class MainApp {
    public static void main(String[] args) throws IOException, Exception {
        //System.out.println("hello world!");
        
        //SAVE PLAYLIST HERE
        String path = "";
        String artist = "";
        for(int i=0;i<args.length;i++){
            if(i==0){
                path = args[i];
            }else{
                if(i==args.length){
                    artist=artist+args[i];
                }else{
                    artist=artist+args[i]+" ";
                }
            }
        }
        File filePath = new File(path);
        FileServiceImpl f = new FileServiceImpl();
        MediaItem m = new MediaItem();
        Set<MediaItem> all = f.getAllMediaItems(path);

        SearchService s = new SimpleSearch();
        Set<MediaItem> itemsByArtist = s.findArtist(artist,all);
        for(MediaItem i:itemsByArtist){
            System.out.println("itemsByArtist"+i);
        }
        List<MediaItem> items = new ArrayList<>(itemsByArtist);
        for(MediaItem a:items){
            System.out.println("Items"+m);
        }
        List<String> fileLines = new PlayListServiceM3U().getPlayList(items, true);
        /*File path = new File(args[1]);
        savePlayList(result, path);*/
        
        
        
        //COPY FILES TO NEW FOLDER AND ARRANGE ORDER BY ARTIST AND ALBUM
        /*FileServiceImpl f = new FileServiceImpl();
        DuplicateFinder df = new DuplicateFindFromFilename();
        File from = new File(args[0]);
        File to = new File(args[1]);
        f.copyFilesByOrder(from, to);*/
        
        //
        
        //COPY FILES TO NEW FOLDER
        /////////////////////////////////////////////////
        /*FileServiceImpl f = new FileServiceImpl();
        DuplicateFinder df = new DuplicateFindFromFilename();
        File from = new File(args[0]);
        File to = new File(args[1]);
        f.copyFiles(from, to);
        
        Set<MediaItem> all = f.getAllMediaItems(args[1]);
        for(MediaItem a: all){
            System.out.println(Paths.get(a.getAbsolutePath()).getFileName());
        }
        Set<Set<MediaItem>> temp = df.getAllDuplicates(all);
        System.out.println(temp);
        Set<MediaItem> temp2 = f.getItemsToRemove(temp);
        for(MediaItem i : temp2){
            System.out.println("item " + Paths.get(i.getAbsolutePath()).getFileName());
        }
        f.removeFiles(temp2);*/
        //////////////////////////////////////////////////////
        
    }
        
    
    private static void savePlayList(List<MediaItem> mediaItems, File path){
            List<String> fileLines
                    = new PlayListServiceM3U().getPlayList(mediaItems, true);

            Charset charset = Charset.forName("US-ASCII");
            try (BufferedWriter writer = Files.newBufferedWriter(path.toPath(), charset)) {
                /*for (int i = 0; i < fileLines.size(); i++) {
                    writer.append(fileLines.get(i));*/
                int i=0;
                for(String mi : fileLines){
                    i++;
                    writer.append(mi);
                    if (i < fileLines.size() - 1) {
                        writer.newLine();
                    }
                }
            } catch (IOException x) {
                System.err.format("IOException: %s%n", x);
                System.out.println("Somwthing");
            }
        
    }
    
}
