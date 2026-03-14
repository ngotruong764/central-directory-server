package service;

import model.ClientInfo;
import model.FileInfo;
import model.FileOwner;
import storage.FileRegistry;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class DirectoryServiceImpl implements IDirectoryService{
    private static final Logger logger = Logger.getLogger(DirectoryServiceImpl.class.getName());

    @Override
    public void registryFile(ClientInfo clientInfo, List<FileInfo> files) {
        for (FileInfo file : files) {
            FileOwner owner = FileRegistry.fileOwner.computeIfAbsent(
                    file.getFileName(),
                    key -> {
                        FileOwner fo = new FileOwner();
                        fo.setFileInfo(file);
                        fo.setClientInfos(new ArrayList<>());
                        return fo;
                    }
            );

            boolean exists = owner.getClientInfos().stream()
                    .anyMatch(c -> c.getId().equals(clientInfo.getId()));

            if (!exists) {
                owner.getClientInfos().add(clientInfo);
            }
        }

        System.out.printf(
                FileRegistry.fileOwner.toString()
        );

    }

    @Override
    public List<FileOwner> getAllAvailableFiles() throws RemoteException {
        return FileRegistry.fileOwner.values()
                .stream()
                .toList();
    }
}
