import java.io.*;
import java.util.*;
public class Main {
    static int T, K;
    static PriorityQueue<Long> pq;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        T = Integer.parseInt(br.readLine());
        for(int t = 0; t<T; t++){
            pq = new PriorityQueue<>();
            K = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int k = 0; k<K; k++){
                pq.add(Long.parseLong(st.nextToken()));
            }

            long ans = 0;
            while(pq.size() > 1){
                long f = pq.poll();
                long s = pq.poll();
                ans += f+s;
                pq.add(f+s);
            }

            bw.write(ans+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
