/**
 *
 */
package socket.server;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * <p>
 * Title:SocketServer
 * </p>
 * <p>
 * 服务器端，接受客户端发送的消息，并且做处理:
 * </p>
 * 
 * @author Edwin
 * @since 2016-12-23
 * @version 1.0
 */
public class SocketServer {
	private static ServerSocket serverSocket = null;
	private static Socket socket = null;
	static {
		while (!initSocket()) {
			System.out.println("socket服务器初始化失败，等待重新初始化...");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("socket服务器已与客户端实现连接");
	}

	public static boolean initSocket() {
		int port = 8080;
		try {
			serverSocket = new ServerSocket(port, 10, InetAddress.getByName("localhost")); //最大连接队列为10 
			//serverSocket.
			System.out.println("socket服务器准备就绪,等待客户端连接...");
			socket = serverSocket.accept();
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			new Thread() {
				public void run() {
					try {
						Reader reader = new InputStreamReader(
								socket.getInputStream());
						char[] buffer = new char[1024];
						int len = 0;
						while ((len = reader.read(buffer)) != -1) {
							System.out.print(new String(buffer, 0, len));
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}.start();
			Writer writer = new OutputStreamWriter(socket.getOutputStream());
			Scanner scanner = new Scanner(System.in);
			String toWrite = null;
			while ((toWrite = scanner.nextLine()) != null) {
				writer.write(toWrite + "\n");
				writer.flush(); // 冲刷缓冲
			}
			writer.close();
			socket.close();
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
