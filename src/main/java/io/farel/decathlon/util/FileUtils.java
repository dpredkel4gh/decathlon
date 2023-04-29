package io.farel.decathlon.util;

public class FileUtils {
    public static String getExtension(String filename) {
        if (!filename.contains(".")) {
            throw new IllegalArgumentException("file format is not supported");
        }
        return filename.substring(filename.lastIndexOf(".") + 1);
    }
}
