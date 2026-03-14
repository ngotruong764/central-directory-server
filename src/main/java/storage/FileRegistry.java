package storage;

import model.FileOwner;

import java.util.HashMap;
import java.util.Map;

public abstract class FileRegistry {
    // Hold the file name and the owner
    public static final Map<String, FileOwner> fileOwner = new HashMap<>();
}
