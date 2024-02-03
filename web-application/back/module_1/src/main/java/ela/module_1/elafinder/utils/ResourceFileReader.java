package ela.module_1.elafinder.utils;

import java.io.InputStream;

public class ResourceFileReader {
    public static InputStream getFileInputStreamReader(String resourceFile) {

        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        return loader.getResourceAsStream(resourceFile);
    }
}
