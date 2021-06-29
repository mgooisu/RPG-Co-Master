package Helpers;

import javax.tools.JavaFileManager;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Utility classes for the reading and writing of objects to the disk
 */
public class FileReadWrite {
    /**
     * Writes an object to file as a generic java object
     * @param path the directory and filename of the file
     * @param object the data to be saved
     * @throws IOException - throws a standard Java IO exception
     */
    public static void writeObjectToFile(String path, Object object) throws IOException {
        File file = new File(path);
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(object);
        oos.close();
        fos.close();
    }

    /**
     * Reads an object from file and returns it to be cast to the appropriate object type
     * @param path the directory and filenameof the file
     * @return The generic Object interpretation of the saved data
     * @throws IOException -Throws a standard Java Io exception
     * @throws ClassNotFoundException - Throws if the data can't be interpreted as a java object
     */
    public static Object readObjectFromFile(String path) throws IOException, ClassNotFoundException {
        File file = new File(path);
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object object = ois.readObject();
        fis.close();
        ois.close();
        return object;
    }

    /**
     * Deletes the file at a given path
     * @param path the location of the file
     * @throws IOException - throws an IO exception if something goes wrong.
     */
    public static void deleteObjectFile(String path) throws IOException {
        Files.deleteIfExists(Path.of(path));
    }

    /**
     * Defines whether a file at the provided path exists
     * @param path the location of the file
     * @return boolean - true if the file exists, false otherwise
     */
    public static boolean FileExists(String path){
        return Files.exists(Path.of(path));
    }
}
