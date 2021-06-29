package Helpers;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Handles the reading and writing operations of objects for the application
 */
public class FileReadWrite {
    public static Object readObject(String directory) throws IOException, ClassNotFoundException {
        File file = new File(directory);
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object output = ois.readObject();
        ois.close();
        fis.close();
        return output;

    }
    public static void writeObject(String directory, Object object) throws IOException {
        if(checkFileExists(directory)){
            deleteFile(directory);
        }
        File file = new File(directory);
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(object);
        oos.close();
        fos.close();


    }
    public static boolean checkFileExists(String directory){
        File file = new File(directory);
        return file.exists();
    }
    public static void deleteFile(String directory) throws IOException {
        Files.deleteIfExists(Path.of(directory));
    }
    //TODO - create comparision method that converts two objects to byte arrays and then compares them
    //should hopefully produce more reliable results than casting a saved object to an array and then comparing.
    //Additionally, we may need to revert the read-write capabilites of this class to byte-arrays, should we take that approach.
    //it could simplify things
}
