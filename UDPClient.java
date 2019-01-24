import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPClient {
  public static void main(String [] argv){
    DatagramSocket socket = null;
    DatagramPacket packet = null;
    String text = "{\"name\":\"kangsan Chang\", \"location\": { \"latitude\": \"37.221922\", \"longitude\": \"127.187604\"}}";
    // 나중에 이렇게 json format 만들기 어려우면 gson,jackson 사용고려

    byte[] buffer = text.getBytes(); 
    String serverIP = "127.0.0.1";
    int serverPORT = 18181;

    try {
      socket = new DatagramSocket(); // UDP Socket 생성
      packet = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(serverIP), serverPORT);
      socket.send(packet);

      System.out.println("Sended Message : " + text);
      socket.close();
    } catch (SocketException e) {
      e.printStackTrace();
    } catch (UnknownHostException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
