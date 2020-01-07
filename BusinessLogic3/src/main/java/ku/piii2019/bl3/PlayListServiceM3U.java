/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ku.piii2019.bl3;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joshw
 */
public class PlayListServiceM3U implements PlayListService {
    @Override
    public List<String> getPlayList(List<MediaItem> inputList, boolean includeMetaData) {
            List<String> outputList = new ArrayList<>();
            outputList.add("#EXTM3U");
            for(MediaItem item : inputList)
            {
                if(includeMetaData) {
                    outputList.add(getMetaDataString(item));
                }
                outputList.add(item.getAbsolutePath());
                
            }
            return outputList;
        }

    @Override
    public String getMetaDataString(MediaItem item) {
        return "#EXTINF:"+item.getDuration()+","+item.getArtist()+" - "+item.getTitle();
    }
}
