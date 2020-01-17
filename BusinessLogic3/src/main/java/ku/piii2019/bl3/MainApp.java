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
        /*System.out.println("hello world!");
        Set<MediaItem> list = new HashSet<>();
        FileService f = new FileServiceImpl();
        final String inputPath =  ".." +  File.separator + 
                                                  "test_folders";
        Set<MediaItem> items = f.getAllMediaItems(args[0]);
        for(MediaItem m : items) {
            //System.out.println("item " + Paths.get(m.getAbsolutePath()).getFileName());
            list.add(m);
            System.out.println(m.getAbsolutePath());
        }
        for(MediaItem d:list){
            System.out.println(d.getArtist());
        }
        System.out.println("/////////////////////////////////////////");
        System.out.println(args[1]);
        Set<MediaItem> temp = new SimpleSearch().findArtist(args[1], list);
        List<MediaItem> result = new ArrayList<>();
        for(MediaItem i:temp){
            System.out.println(i.getAbsolutePath());
            if(!result.contains(i)){
                result.add(i);
                System.out.println(i.getAbsolutePath());
            }else{
                System.out.println(i.getAbsolutePath());
            }
        }
        //SAVE PLAYLIST HERE
        File path = new File(args[2]);
        savePlayList(result, path);*/
        
        //COPY FILES TO NEW FOLDER
        FileServiceImpl f = new FileServiceImpl();
        DuplicateFinder df = new DuplicateFindFromFilename();
        /*File from = new File(args[0]);
        File to = new File(args[1]);
        f.copyFiles(from, to);*/
        
        Set<MediaItem> all = f.getAllMediaItems(args[1]);
        /*for(MediaItem a: all){
            System.out.println(Paths.get(a.getAbsolutePath()).getFileName());
        }*/
        Set<Set<MediaItem>> temp = df.getAllDuplicates(all);
        System.out.println(temp);
        Set<MediaItem> temp2 = f.getItemsToRemove(temp);
        for(MediaItem i : temp2){
            System.out.println("item " + Paths.get(i.getAbsolutePath()).getFileName());
        }
        f.removeFiles(temp2);
        
        /*FileService f = new FileServiceImpl();
        MediaInfoSource ms = new MediaInfoSourceFromID3();
        DuplicateFinder df = new DuplicateFindFromID3();
        Set<MediaItem> items = f.getAllMediaItems(args[1]);
        for(MediaItem item:items){
            ms.addMediaInfo(item);
            df.getDuplicates(items, item);
        }*/
        
        /*for(String i:args){
            System.out.println(i);
        }*/
        
        /*        int a = 0;
        String input = "";
        String output = "";
        for(String i:args){
            if(a==0){
                input = i;
            }else if(a==1){
                output = i;
            }
            a++;
        }*/
        /*String cwd = System.getProperty("user.dir");
            System.out.println(cwd);
            input = Paths.get(cwd,
                    "..",
                    input).toString();
        System.out.println(input);*/
        
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
