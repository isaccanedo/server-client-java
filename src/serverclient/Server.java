package serverclient;


// Servidor
import java.net.*;
import java.util.Arrays;
import java.io.*; 

public class Server 
{ 
	//inicializar soquete e fluxo de entrada
	private Socket		 socket = null; 
	private ServerSocket server = null; 
	private InputStream in	 = null; 
	private OutputStream out = null;

	// construtor com porta 
	public Server(int port) 
	{ 
		// inicia o servidor e aguarda uma conexão 
		try
		{ 
			server = new ServerSocket(port); 
			System.out.println("Servidor iniciado..."); 

			System.out.println("À espera de um cliente ..."); 

			socket = server.accept(); 

			System.out.println("Cliente aceito"); 

			// recebe entrada do soquete do cliente 
			in = new DataInputStream(socket.getInputStream()); 
			// escreve no soquete do cliente
			out = new DataOutputStream(socket.getOutputStream());

			// Recebendo dados do cliente
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte buffer[] = new byte[1024];
			baos.write(buffer, 0 , in.read(buffer));
			
			byte result[] = baos.toByteArray();

			String res = Arrays.toString(result);
			System.out.println("Recebido do cliente : "+res); 
			
			//echoing back to client
			out.write(result);
			
			System.out.println("Fechando a conexão"); 

			// Conexão fechada 
			socket.close(); 
			in.close(); 
		} 
		catch(IOException i) 
		{ 
			System.out.println(i); 
		} 
	} 

	public static void main(String args[]) 
	{ 
		new Server(5000); 
	} 
}