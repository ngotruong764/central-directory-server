package service;

import storage.FileRegistry;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DirectoryServiceImpl implements IDirectoryService{

    @Override
    public void registryFile(String clientId, List<String> files) {
        for (String file: files) {
            Set<String> owners = FileRegistry.fileRegistryRecords.computeIfAbsent(file, k -> new HashSet<>());
            owners.add(clientId);
        }
    }
}
