package storage;

import java.util.HashMap;
import java.util.Set;

public abstract class FileRegistry {
    // Hold the file name and the owner
    public static final HashMap<String, Set<String>> fileRegistryRecords = new HashMap<>();
}
