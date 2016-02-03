package org.projectlemon.intenseorange.model.server;

import org.projectlemon.intenseorange.model.utilities.PDU.PDU;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Class: ClientThread
 *
 * Purpose: This is a representation of a client on the server. It contains the connection to
 *          a specific client and methods that allow communication between the server and the
 *          client. As the name implies, this class is meant to be run on it's own thread.
 *          This is in order to allow more than one client to connect.
 *
 * Created by Linus Lagerhjelm on 16-01-18.
 */
public class ClientThread implements Runnable{
    Server server;
    Socket socket;
    String nickname;

    public ClientThread(Server server, Socket socket) {
        this.server = server;
        this.socket = socket;
    }

    /**
     * When running this method the object will register the client, via itself, on the server
     * it will also receive messages from the client to the server. This function call is blocking
     * so it is recommended to be invoked via a new thread as an asynchronous task.
     */
    @Override
    public void run() {

        /*
         * connectClient-method called here instead of inside ConnectionListener (where some people
         * might argue it belongs) in order to make objects from this class take as much
         * responsibility over themselves as possible
         */
        server.connectClient(this);
        try{
            while (true) {
                PDU pdu = PDU.fromInputStream(socket.getInputStream());
            }
        } catch(Exception e) { e.printStackTrace(); }
    }

    /**
     * Sends the given PDU to the current client over a socket connection. Note that if an error
     * occur then the socket connection will be closed and the client will have to reconnect.
     *
     * @param msg The message to send to the client
     */
    public void sendMessage(PDU msg) {
        try {
            OutputStream out = socket.getOutputStream();
            out.write(msg.toByteArray());
        } catch (IOException e) {
            try {
                server.getDebugHelper().dump();
                socket.close();
                server.disconnectClient(this);
            }

            //Ignoring since documentation states that IOException never occurs
            catch(IOException ignore) {}
        }
    }

    public String getNickname() {
        return nickname;
    }
}
