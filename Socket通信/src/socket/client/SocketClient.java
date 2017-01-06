/**
 *
 */
package socket.client;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

/**
 * <p>
 * Title:SocketServer
 * </p>
 * <p>
 * 客户端向服务器发送信息和接受回显服务器端消息
 * </p>
 * 
 * @author Edwin
 * @since 2016-12-23
 * @version 1.0
 */
public class SocketClient {
	private static Socket socket = null;
	static {
		while (!initSocket()) {
			System.out.println("socket连接失败，等待重连...");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("socket连接成功，准备就绪");
	}

	public static boolean initSocket() {
		int port = 8080; // 0-1023被保留
		String host = "localhost"; // 或者写成 "127.0.0.1"
		try {
			socket = new Socket(host, port);
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		printSocketInfo(); //打印socket相关信息
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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static void printSocketInfo() {
		if (socket == null) {
			System.out.println("socket is null");
			return;
		}
		System.out.println("socket.getInetAddress() : "
				+ socket.getInetAddress()); // 远程ip
		try {
			System.out.println("socket.getKeepAlive() : "
					+ socket.getKeepAlive()); // 是否可以保持连接，如果不能会抛出异常
		} catch (SocketException e) {
			e.printStackTrace();
		}
		System.out.println("socket.getLocalAddress() : "
				+ socket.getLocalAddress()); // 本地ip
		System.out.println("socket.getLocalSocketAddress() : "
				+ socket.getLocalSocketAddress()); // 本地绑定这个socket的地址，如果没有设置绑定，返回null
		System.out.println("socket.getPort() : " + socket.getPort()); //远程端口
		System.out.println("socket.getLocalPort() : " + socket.getLocalPort()); //远程端口
	}
}
