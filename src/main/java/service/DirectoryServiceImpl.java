package service;

import model.FileInfo;
import storage.FileRegistry;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DirectoryServiceImpl implements IDirectoryService{

    @Override
    public void registryFile(String clientId, List<FileInfo> files) {
        for (FileInfo file: files) {
            Set<String> owners = FileRegistry.fileRegistryRecords.computeIfAbsent(file.getFileName(), k -> new HashSet<>());
            owners.add(clientId);
        }
    }
}
