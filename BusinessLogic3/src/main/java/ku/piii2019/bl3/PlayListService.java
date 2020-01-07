/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ku.piii2019.bl3;

import java.util.List;

/**
 *
 * @author joshw
 */
public interface PlayListService {
    List<String> getPlayList(List<MediaItem> inputList, boolean includeMetaData);
     String getMetaDataString(MediaItem item);
}
