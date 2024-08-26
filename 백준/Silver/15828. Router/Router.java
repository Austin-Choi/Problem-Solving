import java.util.*;
import java.io.*;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        // BlockingQueue를 쓰면 사이즈 체크 안해도 됨
        // 가득 차면 스레드를 Block 해서 offer하려 하면 false가 반환되기 때문에
        Queue<Integer> q = new LinkedBlockingQueue<>(N);

        while(true){
            int temp = Integer.parseInt(br.readLine());
            if(temp < 0){
                break;
            }
            else if(temp == 0){
                q.remove();
            }
            else{
                q.offer(temp);
            }
        }
        if(q.isEmpty()) {
            System.out.println("empty");
            return;
        }
        while(!q.isEmpty()){
            System.out.print(q.remove() + " ");
        }
    }
}
