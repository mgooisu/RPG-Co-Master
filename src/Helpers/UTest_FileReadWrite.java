package Helpers;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Serializable;

/**
 * This class includes unit tests for the file reading and writing functions, as well as testing the integrity of
 * serialized objects.
 * Objects should be generated and saved to file, and the compiler should be able to recognize it as the same object
 * when loaded from file.
 */
public class UTest_FileReadWrite {
    SerializableObjectHandlerTest handler;
    void setup() {
        handler = new SerializableObjectHandlerTest(69);
    }
    @Test
    void saveToFile() throws IOException, ClassNotFoundException {
        setup();
        handler.writeObject();
        Assertions.assertTrue(handler.compareData());
    }


    @AfterAll
    static void cleanup() throws IOException {
        FileReadWrite.deleteObjectFile("./testFile.test");
    }

    /**
     * Object for testing serialization functions
     */
    private static class SerializableObject implements Serializable{
        private final int initialValue;
        public SerializableObject(int initialValue){
            this.initialValue = initialValue;
        }
        public int getInitialValue() {
            return initialValue;
        }
    }

    /**
     * Object for testing the standard control scheme for serialized objects
     */
    private static class SerializableObjectHandlerTest implements SerializedObjectHandler{

        String path =  "./testFile.test";
        SerializableObject localObject;
        SerializableObject storedObject;

        /**
         * Constructor should create the serializable object or read it from file.
         * @param intialValue
         */
        public SerializableObjectHandlerTest(int intialValue){
            localObject = new SerializableObject(intialValue);
        }

        @Override
        public SerializableObject readObject() throws IOException, ClassNotFoundException {
            storedObject = (SerializableObject) FileReadWrite.readObjectFromFile(path);
            return storedObject;
        }

        @Override
        public void writeObject() throws IOException {
            FileReadWrite.writeObjectToFile(path, localObject);

        }

        @Override
        public boolean compareData() throws IOException, ClassNotFoundException {
            readObject();
            return localObject.getInitialValue() == storedObject.getInitialValue();
        }
    }
}
