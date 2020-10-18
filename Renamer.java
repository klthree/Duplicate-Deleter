/*
 * Very specific code that scans a directory using a SimpleFileVisitor,
 * looks for filenames that meet a certain regex pattern (here a consistently
 * formatted String specifying date and time), and renames the file to
 * eliminate the matching part of the name by replacing it with an empty String.
 *
 */

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.FileVisitResult;
import java.nio.file.SimpleFileVisitor;

import java.io.IOException;
import java.util.Scanner;

public class Renamer {
    public static void main(String[] args) {
        Scanner user = new Scanner(System.in);

        System.out.print("Enter directory to scan: ");
        Path directory = Paths.get(user.nextLine());

        rename(directory);

    }

    public static void rename(Path dir) {
        try {
            Files.walkFileTree(dir, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attr) throws IOException {

                    String dateRegex = "(\\s{1}[(]{1}\\d{4}_\\d{2}_\\d{2}\\s{1}\\d{2}_\\d{2}_\\d{2}\\s{1}UTC[)]){1}";
                    
                    String fNameRegex = ".+" + dateRegex + "\\.\\w{3,4}";

                    Path filename = file.getFileName();
                    System.out.println("Filename: " + filename);
                    String fName = filename.toString();

                    if (fName.matches(fNameRegex)) {
                        System.out.println(fName);
//                        System.out.println("Filename matches pattern.");
                        
                        String newFileName = fName.replaceAll(dateRegex, "");
                        
//                        System.out.println("Filename: " + newFileName);

                        if (!Files.exists(file.resolveSibling(newFileName))) {
//                            System.out.println("\nRenaming file...");
                            file = Files.move(file, file.resolveSibling(newFileName));
                        } else {
                            System.out.println("Target filename already exists. No action taken");
                        }
                         
                    }
                    return FileVisitResult.CONTINUE;
                }
            }); 
        } catch(IOException e) {
            System.err.println("Error: " + e);
        }
    }
}
