package duplicatedelete;

import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.IOException;

public static class FileCheck {
    public static void main(String[] args) {
    
    }

    private boolean fileChecker(Path file1, Path file2) {
        
        if(!Files.exists(file1) || !Files.exists(file2)) {
            System.err.println("The existence of one or both provided files cannot be confirmed.");
            System.exit(1);
        }


        if(Files.isDirectory(file1)) {
            System.err.println(file1 + " is a directory. Both pathnames must point to files.");
            System.exit(2);
        }
        if(Files.isDirectory(file2)) {
            System.err.println(file2 + " is a directory. Both pathnames must point to files.");
            System.exit(2);
        }
        
        if (Files.size(file1) != Files.size(file2)) {
            return false;
        }

        String fileFirst = file1.toString();
        String fileSecond = file2.toString();

        FileInputStream in = null;
        InputStream is = null;

        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileFirst))) {
            int content1 = 0;
            int content2 = 0;

            while(content1 = bis.read() != -1 && content2 = bis.read() != -1) {
                if(content1 != content2){
                    return false;
                }
            }
        
        
        } catch(IOException e) {
            System.err.println("Error:\n" + e);
        }
    }
}
