package BlockingIO;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SingleThreadClient {
	 private Socket socket = null;	
	 private DataOutputStream out = null;
	 public SingleThreadClient(int port) throws IOException, InterruptedException{
	    
	        // establish a connection
	        try
	        {
	            socket = new Socket("127.0.0.1",port);
	            System.out.println("Connected");
	        }catch (Exception e){
	        	e.printStackTrace();
	        	
	        }
	        System.out.println("In the client about to write ");
		out = new DataOutputStream(socket.getOutputStream());
		 System.out.println("In the client about getting stream  "+out);
		out.write(new String("How are you").getBytes());
		out.flush();
		Thread.sleep(5000);
	/*	Thread.sleep(500);
		out.write(new String("This is good").getBytes());
		out.flush();
		Thread.sleep(500);
		out.write(new String("Bye").getBytes());
		out.flush();
		Thread.sleep(500);*/
		out.close();
		socket.close();
	        
	 }
	 public static void main(String[] args) throws IOException, InterruptedException {
		 SingleThreadClient client = new SingleThreadClient(8827);
		 
		
	}
}


