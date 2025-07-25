import java.util.*;
import java.io.*;
public class Main {
    static int N, M;
    static Meat[] board;
    static class Meat implements Comparable<Meat>{
        int weight;
        int cost;
        Meat(int w, int c){
            this.weight = w;
            this.cost = c;
        }

        @Override
        public int compareTo(Meat o){
            if(this.cost == o.cost)
                return o.weight - this.weight;
            return this.cost - o.cost;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new Meat[N];
        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            board[i] = new Meat(w,c);
        }
        Arrays.sort(board);

        int ans = Integer.MAX_VALUE;
        long sumWeight = 0;
        int totalCost = 0;
        boolean found = false;

        for(int i = 0; i<N; i++){
            sumWeight += board[i].weight;

            if(i>0 && board[i].cost == board[i-1].cost){
                totalCost += board[i].cost;
            }
            else{
                totalCost = board[i].cost;
            }

            if (sumWeight >= M) {
                found = true;
                ans = Math.min(ans, totalCost);
            }
        }
        
        if(!found)
            ans = -1;

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
}
