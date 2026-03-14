# central-directory-server

## Overview
The Central Directory Server is a component of the distributed file downloading system.
It maintains metadata about files shared by clients and allows peers to discover which nodes host specific files.

Clients register the files they share with the server. Other clients can then query the server to obtain a list of available sources for a requested file and download file parts directly from those peers.

The server itself does not store files. It only manages file location information.

## Running the Server
2. Start RMI Registry  
   ``export CLASSPATH=.:lib/common-service-1.0-SNAPSHOT.jar``   
   ``rmiregistry``