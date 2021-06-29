package Helpers;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Serializable;

/**
 * This class includes unit tests for the file reading and writing functions, as well as testing the integrity of
 * serialized objects.
 * Objects should be generated and saved to file, and the compiler should be able to recognize it as the same object
 * when loaded from file.
 */
public class UTest_FileReadRight {
    SerializableObject localObject;
    SerializableObject storedObject;
    String testFile = "./testFile.test";
    void setup() {
        localObject = new SerializableObject (69);
    }
    @Test
    void readWriteIntegrity() throws IOException, ClassNotFoundException {
        setup();
        FileReadWrite.writeObject(testFile,localObject);
        storedObject = (SerializableObject) FileReadWrite.readObject(testFile);
        Assertions.assertEquals(storedObject.getInitialValue(), localObject.getInitialValue());
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
}
