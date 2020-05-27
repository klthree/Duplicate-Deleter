/*
 * Creates a FileCheck object out of a Path, then compares the file
 * referenced by that Path to a file whose path is provided as an
 * argument to the fileChecker method. Returns 'True' if the two files
 * are equal, 'False' if not.
 */
package duplicatedeleter;

import java.io.InputStream;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.Scanner;


public class FileCheck {

    private Path file1;

    // Constructor
    public FileCheck(Path p) {
        this.file1 = p;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        

        System.out.println("Second file to compare: ");
        Path file2 = Paths.get(in.nextLine());

        boolean isSame = fileChecker(file2);

        if(isSame) {
            System.out.println(file1 + " and " + file2 + " match.");
        }
        else {
            System.out.println(file1 + " and " + file2 + " do not match.");
        }
    }

    // Compares byte by byte with this.Path using BufferedInputStream
    public boolean fileChecker(Path file2) {
        
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
        

        String fileFirst = file1.toString();
        String fileSecond = file2.toString();

        FileInputStream in = null;
        InputStream is = null;

        try(BufferedInputStream bis1 = new BufferedInputStream(new FileInputStream(fileFirst)); BufferedInputStream bis2 = new BufferedInputStream(new FileInputStream(fileSecond))) {
            if (Files.size(file1) != Files.size(file2)) {
                return false;
            }

            int content1 = 0;
            int content2 = 0;

            while(((content1 = bis1.read()) != -1) && (content2 = bis2.read()) != -1) {
                if(content1 != content2){
                    return false;
                }
            }
        
        
        } catch(IOException e) {
            System.err.println("Error:\n" + e);
        }

        return true;
    }
}
