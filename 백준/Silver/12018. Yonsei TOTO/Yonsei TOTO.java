
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] minCosts = new int[N];

        for(int n = 0; n<N; n++){
            st = new StringTokenizer(br.readLine());
            int P = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
            for(int p = 0; p<P; p++){
                pq.add(Integer.parseInt(st.nextToken()));
            }
            // 만약 수강인원이 신청한 사람보다 많다면
            // 최소 마일리지 값을 채워줌
            for(int p = 0; p<L-P; p++){
                pq.add(1);
            }

            int minCost = 37;
            for(int l = 0; l<L; l++){
                if(!pq.isEmpty()){
                    minCost = pq.poll();
                }
            }
            minCosts[n] = minCost;
        }

        Arrays.sort(minCosts);

        int ans = 0;
        int costSum = 0;
        for(int i = 0; i<N; i++){
            costSum += minCosts[i];
            ans++;
            if(costSum == M){
                break;
            }
            if(costSum > M){
                ans -= 1;
                break;
            }
        }

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
}
