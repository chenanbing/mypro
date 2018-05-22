package com.cab.common.framework.enums;

import java.util.HashMap;
import java.util.Map;

/**
 */
public class UploadConfig {

    public static final String FILE = "file";
    public static final String IMAGE = "image";
    public static final String MEDIA = "media";

    private static Map<String, Long> fileMaxSize = new HashMap<String, Long>();
    private static Map<String, String> fileTypeMap = new HashMap<String, String>();

    static {
        Map<String, Long> suffixSizeMap = new HashMap<String, Long>();
        suffixSizeMap.put(IMAGE, 1024 * 1024L);
        suffixSizeMap.put(MEDIA, 100 * 1024 * 1024L);
        suffixSizeMap.put(FILE, 1024 * 1024L);

        Map<String, String> suffixMap = new HashMap<String, String>();
        suffixMap.put(IMAGE, "webp,gif,jpg,jpeg,png,bmp");
        suffixMap.put(MEDIA, "swf,flv,mp3,mp4,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
        suffixMap.put(FILE, "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2,js");

        for (Map.Entry<String, String> entry : suffixMap.entrySet()) {
            String key = entry.getKey().toLowerCase().trim();
            String[] suffixs = entry.getValue().split(",");
            for (int i = 0; i < suffixs.length; i++) {
                String suffix = suffixs[i].toLowerCase().trim();
                fileMaxSize.put(suffix, suffixSizeMap.get(key));
                fileTypeMap.put(suffix,key);
            }
        }
    }

    public static String getFileType(String suffix){
        return fileTypeMap.get(suffix);
    }

    public static boolean isAllow(String suffix) {
        return fileMaxSize.containsKey(suffix);
    }

    /**
     * 是否符合上传条件
     *
     * @param suffix
     * @param fileSize
     * @return
     */
    public static boolean isAllow(String suffix, Long fileSize) {
        return fileMaxSize.containsKey(suffix) && fileSize <= fileMaxSize.get(suffix);
    }

    public static void main(String[] args) {
        System.err.println(fileMaxSize.containsKey("png"));
    }

}
