package FileRepositroy;

import java.io.File;

public class GetType {
    public static String getType(File file){
        String extension = "";
        int i = file.getName().lastIndexOf('.');
        if (i > 0)
            extension = file.getName().substring(i+1);
        return extension;
    }
}
