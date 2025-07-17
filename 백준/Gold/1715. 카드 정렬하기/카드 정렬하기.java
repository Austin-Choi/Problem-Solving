import java.util.*;
import java.io.*;
public class Main {
    static int N;
    static long ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int n = 0; n<N; n++){
            pq.add(Integer.parseInt(br.readLine()));
        }

        if(N != 1) {
            while(pq.size()>1){
                int f = pq.poll();
                int s = pq.poll();
                ans += f+s;
                pq.add(f+s);
            }
            bw.write(String.valueOf(ans));

        }
        else{
            bw.write("0");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
