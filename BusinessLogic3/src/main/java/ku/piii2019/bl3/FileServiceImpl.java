/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ku.piii2019.bl3;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author James
 */
public class FileServiceImpl implements FileService {

    @Override
    public Set<MediaItem> getAllMediaItems(String rootFolder) {
        Path p = Paths.get(rootFolder);
        if (!p.isAbsolute()) {
            Path currentWorkingFolder = Paths.get("").toAbsolutePath();
            rootFolder = Paths.get(currentWorkingFolder.toString(), rootFolder).toString();
        }
        Set<MediaItem> items = new HashSet<>();
        SimpleFileVisitor myVisitor = new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (file.toString().endsWith("mp3")) {
                    MediaItem m = new MediaItem();
                    m.setAbsolutePath(file.toString());
                    items.add(m);
                }
                return FileVisitResult.CONTINUE;
            }
        };
        try {
            Files.walkFileTree(Paths.get(rootFolder), myVisitor);
        } catch (IOException ex) {}
        return items;
    }

    

    @Override
    public Set<MediaItem> getItemsToRemove(Set<Set<MediaItem>> duplicates) {
        Set<MediaItem> outputSet = new HashSet<>();
        for(Set<MediaItem> s : duplicates) {
            
            MediaItem firstItem = s.iterator().next();
            outputSet.addAll(s);
            outputSet.remove(firstItem);
        }
        return outputSet;
    }

    @Override
    public boolean removeFiles(Set<MediaItem> listToRemove) {
        boolean retVal = true;
        for(MediaItem m : listToRemove) {
            try {
                Files.delete(Paths.get(m.getAbsolutePath()));
            }
            catch(Exception e) {
                e.printStackTrace();
                retVal = false;
            }
        }
        return retVal;
    }
    
    public static void copyFiles(File from, File to) throws IOException{
        String files[] = from.list();
        if(from.isDirectory()){
            if(!to.exists()){
                to.mkdir();
                //System.out.println("Directory created :: "+to);
            }
                
            //System.out.println(files);
            for(String file:files){
                File srcFile = new File(from, file);
                File destFile = new File(to, file);
                    
                copyFiles(srcFile, destFile);
            }
        }else{
            Files.copy(from.toPath(),to.toPath(),StandardCopyOption.COPY_ATTRIBUTES);
            //System.out.println("File copied :: "+to);
        }
    }
    
    public static void copyFilesByOrder(File from, File to) throws IOException{
        String tempFiles[] = from.list();
        List<String> tempList = new ArrayList<>();
        for(String file: tempFiles){
            tempList.add(file);
        }
        Collections.sort(tempList);
        String[] files = new String[tempList.size()];
        for(int i=0;i<tempList.size();i++){
            files[i]=tempList.get(i);
        }
        
        if(from.isDirectory()){
            if(!to.exists()){
                to.mkdir();
                //System.out.println("Directory created :: "+to);
            }
                
            //System.out.println(files);
            for(String file:files){
                File srcFile = new File(from, file);
                File destFile = new File(to, file);
                    
                copyFiles(srcFile, destFile);
            }
        }else{
            Files.copy(from.toPath(),to.toPath(),StandardCopyOption.COPY_ATTRIBUTES);
            //System.out.println("File copied :: "+to);
        }
    }


}
