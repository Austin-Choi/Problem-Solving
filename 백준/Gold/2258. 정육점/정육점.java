/*
가격순으로 정렬하는데 가격이 같으면 무게 무거운걸 우선해야 함
아래 가격 고기들은 다 얻을수 있으니까 누적 고기량으로 무게 업데이트하기

그리고 더 싼 고기만 무료로 얻을 수 있기 때문에
같은 가격의 고기는 무게를 누적하되, 가격은 더해줘야함
 */
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

            // 더 싼 고기만 공짜로 얻을수 있기 때문에
            if(i>0 && board[i].cost == board[i-1].cost){
                totalCost += board[i].cost;
            }
            else{
                totalCost = board[i].cost;
            }

            // 목표 양을 채웠어도 더 싼 고기가 있을 수 있어서 계속 사야함
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
