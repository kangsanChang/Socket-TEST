import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.BufferedReader;

public class TCPClient {
  public static void main(String[] args) {
    int serverPort = 9999;
    String serverIP = "127.0.0.1";
    Socket server = null;
    BufferedReader getMsgFromUser;
    PrintWriter printWriter;
    BufferedReader bufferedReader;
    String msg = "";

    try {
      server = new Socket(serverIP, serverPort);
      getMsgFromUser = new BufferedReader(new InputStreamReader(System.in));
      printWriter = new PrintWriter(server.getOutputStream(), true);
      bufferedReader = new BufferedReader(new InputStreamReader(server.getInputStream()));
      if(server != null) {
        System.out.println(server.toString());

        while(true) {
          // 서버에 쓸 메시지 입력받고 전ㄴ달. bye 면 종료 
          System.out.print("To Server : ");
          msg = getMsgFromUser.readLine();
          printWriter.println(msg);
          if(msg.equals("bye")) {
            msg = bufferedReader.readLine();
            break;
          }
          // server 로 부터 받은 메시지 출력
          msg = bufferedReader.readLine();
          System.out.println(msg);
        }
      }
      System.out.println(msg);
      System.out.println("Now client disconnect");
      server.close();
    } catch (UnknownHostException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (server != null) {
        try {
          server.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
}