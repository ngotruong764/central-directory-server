import service.DirectoryServiceImpl;
import service.IDirectoryService;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CentralDirectoryServerMain {
    private static final Logger logger = Logger.getLogger(CentralDirectoryServerMain.class.getName());

    private static String RMI_HOST = "RMI_HOST";
    private static String RMI_PORT = "RMI_PORT";

    public static void main(String[] args) {
        // Get environment variables
        Map<String, String> env = System.getenv();
        String rmiHost = env.getOrDefault(RMI_HOST, "localhost");
        int rmiPort = Integer.parseInt(env.getOrDefault(RMI_PORT, "1099"));

        try {
            // Instantiate the remote object
            IDirectoryService obj = new DirectoryServiceImpl();

            // Export the object (create stub for remote access)
            IDirectoryService stub = (IDirectoryService) UnicastRemoteObject.exportObject(obj, 0);

            // Get the RMI registry
            Registry registry = LocateRegistry.getRegistry(rmiHost, rmiPort);

            // Bind the remote object (stub) to the registry
            registry.rebind("IDirectoryService", stub);

            logger.info("Server ready");
        } catch (RemoteException e) {
            logger.log(Level.FINER, e.toString());
            throw new RuntimeException(e);
        }
    }
}
