package tcps;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author Dora Di
 *
 * 1. Create a server socket and bind it to a specific port number 2. Listen for
 * a connection from the client and accept it. This results in a client socket,
 * created on the server, for the connection. 3. Read data from the client via
 * an InputStream obtained from the client socket 4. Send data to the client via
 * the client socket’s OutputStream. 5. Close the connection with the client.
 *
 * The steps 3 and 4 can be repeated many times depending on the protocol agreed
 * between the server and the client.
 */
public class TCPS {

    public static final int PORT = 6666;
    public static ServerSocket serverSocket = null; // Server gets found
    public static ServerSocket socket = null;         // Server communicates with the client

    public static ServerSocket configureServer() throws UnknownHostException, IOException {
        // get server's own IP address
        String serverIP = InetAddress.getLocalHost().getHostAddress();
        System.out.println("Server ip: " + serverIP);

        // create a socket at the predefined port
        serverSocket = new ServerSocket(PORT);

        // Start listening and accepting requests on the serverSocket port, blocked until a request arrives
//        openSocket = serverSocket.accept();  
//        System.out.println("Server accepts requests at: " + socket);

        return serverSocket;
    }

    public static void main(String[] args) throws IOException {
        socket = configureServer();
        int counter = 0;
        try {
            while (true) {
                

                Socket newSocket = socket.accept();
                counter++;
                
                System.out.println("New Connection made number " + counter);

                Thread t = new ClientHandler(newSocket);
                t.start();
            }

        } catch (Exception e) {
            System.out.println(" Connection fails: " + e);
        } finally {
            socket.close();
            System.out.println("Connection to client closed");

            serverSocket.close();
            System.out.println("Server port closed");
        }

    }
}
