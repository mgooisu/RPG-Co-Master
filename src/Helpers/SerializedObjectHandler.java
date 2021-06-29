package Helpers;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Classes that implement this interface must include a way to compare data structures of serialized objects.
 */
public interface SerializedObjectHandler {
    /**
     * Determines whether the data contained within the local instance of the object is the same as that of the
     * stored instance.
     */
    boolean compareData() throws IOException, ClassNotFoundException;
}
