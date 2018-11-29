package BlockingIO;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SingleThreadServer implements Runnable {
	// initialize socket and input stream
	private Socket socket = null;
	private ServerSocket server = null;
	private DataInputStream in = null;
	protected boolean isStopped = false;
	int port;

	public SingleThreadServer(int p) throws IOException {
		server = new ServerSocket(p);
		
	}
	public synchronized void stop(){
        this.isStopped = true;
        try {
            this.server.close();
        } catch (IOException e) {
            throw new RuntimeException("Error closing server", e);
        }
    }

	@Override
	public void run() {
		while (!isStopped) {
			Socket clientSocket = null;
			try {
				System.out.println("Waiting for a client ...");
				clientSocket = this.server.accept();
				System.out.println("Client accepted");
			} catch (IOException e) {
				if (isStopped) {
					System.out.println("Server Stopped.");
					return;
				}
				throw new RuntimeException("Error accepting client connection",
						e);
			}
			  try {
				System.out.println("the stream "+socket.getInputStream());
				in = new DataInputStream(
				            new BufferedInputStream(socket.getInputStream()));
				 String line = in.readUTF();
                 System.out.println(line);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			  

		}

	}
	public static void main(String[] args) throws IOException {
		SingleThreadServer ser = new SingleThreadServer(8827);
		new Thread(ser).start();
		
		
		
	}
}
