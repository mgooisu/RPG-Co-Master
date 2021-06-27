package Helpers;

import java.io.*;

/**
 * Handles the reading and writing operations of objects for the application
 */
public class FileReadWrite {
    public static Object readObject(String directory) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(directory);
        byte[] byteArray = fis.readAllBytes();
        ByteArrayInputStream bis = new ByteArrayInputStream(byteArray);
        ObjectInputStream ois = new ObjectInputStream(bis);
        Object object = ois.readObject();
        fis.close();
        bis.close();
        ois.close();
        return object;
    }
    public static void writeObject(String directory, Object object) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(object);
        oos.flush();
        FileOutputStream fos = new FileOutputStream(directory);
        fos.write(bos.toByteArray());
        oos.close();
        bos.close();
        fos.close();
    }
}
