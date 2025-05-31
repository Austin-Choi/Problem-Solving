
import java.util.*;
import java.io.*;

public class Main {
    private static int N;
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i = 0; i<N; i++){
            int cmd = Integer.parseInt(br.readLine());
            if(cmd == 0){
                if(!pq.isEmpty()){
                    sb.append(pq.poll());
                }
                else
                    sb.append(0);
                sb.append("\n");
            }
            else{
                pq.add(cmd);
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
