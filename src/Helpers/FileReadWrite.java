package Helpers;

import java.io.*;

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
    //TODO - create comparision method that converts two objects to byte arrays and then compares them
}
