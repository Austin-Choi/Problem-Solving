
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] inputs = new int[N];
        for(int i = 0; i<N;i++){
            inputs[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(inputs);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int i = 1; i<N; i++){
            pq.add(Math.abs(inputs[i] - inputs[i-1]));
        }

        for(int i = 0; i<K-1; i++){
            pq.poll();
        }

        long ans = 0;
        while(!pq.isEmpty()){
            ans += pq.poll();
        }

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
}
