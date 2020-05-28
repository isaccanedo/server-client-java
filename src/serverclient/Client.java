package serverclient;

//Um programa Java para um cliente 
import java.net.*;
import java.util.Arrays;
import java.io.*;

public class Client {
	// inicializar fluxos de saída de soquete e entrada
	private Socket socket = null;
	private OutputStream out = null;
	private InputStream in = null;

	// construtor para colocar o endereço IP e porta
	public Client(String address, int port) {
		// estabelecer uma conexão
		try {
			socket = new Socket(address, port);
			if (socket.isConnected()) {
				System.out.println("Conectado!");
			}

			// envia a saída para o soquete
			out = new DataOutputStream(socket.getOutputStream());
			// recebe entrada do soquete
			in = new DataInputStream(socket.getInputStream());
		} catch (UnknownHostException u) {
			System.out.println(u);
		} catch (IOException i) {
			System.out.println(i);
		}

		
		try {
			byte[] arr = {(byte)0x00, (byte)0x01, (byte)0x00, (byte)0x10, (byte)0x00, (byte)0x01,
					  (byte)0x00, (byte)0x1F, (byte)0x60, (byte)0x1D, (byte)0xA1, (byte)0x09,
					  (byte)0x06, (byte)0x07, (byte)0x60, (byte) 0x85, (byte)0x74, (byte)0x05,
					  (byte) 0x08, (byte)0x01, (byte)0x01, (byte)0xBE, (byte)0x10, (byte)0x04,
					  (byte)0x0E, (byte)0x01, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x06,
					  (byte)0x5F, (byte)0x1F, (byte)0x04, (byte)0x00, (byte)0x00, (byte)0x18,
					  (byte)0x1D, (byte)0xFF, (byte)0xFF};

			// enviando dados para o servidor
			out.write(arr);

			String req = Arrays.toString(arr);
			// solicitação de impressão para o console
			System.out.println("Enviado para o servidor : " + req);

			// Recebendo resposta do servidor
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte buffer[] = new byte[1024];
			baos.write(buffer, 0 , in.read(buffer));
			
			byte result[] = baos.toByteArray();

			String res = Arrays.toString(result);

			// resposta de impressão para o console
			System.out.println("Recebido do servidor : " + res);
		} catch (IOException i) {
			System.out.println(i);
		}
		// }

		// fechar a conexão
		try {
			// input.close();
			in.close();
			out.close();
			socket.close();
		} catch (IOException i) {
			System.out.println(i);
		}
	}

	public static void main(String args[]) {
		new Client("127.0.0.1", 5000);
	}
}
