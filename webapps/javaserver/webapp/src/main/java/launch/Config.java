package launch;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by JAMES on 9/27/2016.
 */
public class Config {

    private static Config config = null;
    private Path filesPath;
    private File filesDir;

    public static Config get() {
        if(Config.config == null) {
            Config.config = new Config();
        }
        return Config.config;
    }
    private Config() {
        String strFilesDir = System.getenv("OUTFILESDIR");

        if(strFilesDir == null) {
            strFilesDir = System.getProperty("java.io.tmpdir");
            System.err.println(String.format("OUTFILESDIR not defined. Using this instead: %s", strFilesDir));
        }
        filesPath = Paths.get(strFilesDir);
        filesDir = Paths.get(strFilesDir).toFile();
    }


    public Path getFilesPath() {
        return filesPath;
    }

    public File getFilesDir() {
        return filesDir;
    }
}
