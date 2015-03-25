package cn.linqinli;
import java.net.*;
import java.io.*;

public class Server {

	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(9999);
			Socket socket = server.accept();
			
			FileInputStream fis = new FileInputStream("D:\\新建文本文档.txt");
			OutputStream ops = socket.getOutputStream();
			
			byte buffer[] = new byte[1024];
			int len = 0;
			while((len = fis.read(buffer)) > 0) {
				ops.write(buffer, 0, len);
			}
			fis.close();
			ops.close();
			socket.close();
			server.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
