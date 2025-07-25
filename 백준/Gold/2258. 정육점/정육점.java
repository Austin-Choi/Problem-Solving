import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static Meat[] board;

    static class Meat implements Comparable<Meat> {
        int weight;
        int cost;

        Meat(int w, int c) {
            this.weight = w;
            this.cost = c;
        }

        @Override
        public int compareTo(Meat o) {
            if (this.cost == o.cost)
                return o.weight - this.weight;
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new Meat[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            board[i] = new Meat(w, c);
        }

        Arrays.sort(board); // cost 오름차순, weight 내림차순

        int totalWeight = 0;
        int totalCost = -1;
        int answer = Integer.MAX_VALUE;
        boolean isPossible = false;

        for (int i = 0; i < N; i++) {
            totalWeight += board[i].weight;

            if (i > 0 && board[i].cost == board[i - 1].cost) {
                totalCost += board[i].cost; // 같은 가격이면 더 돈이 듬
            } else {
                totalCost = board[i].cost; // 새로운 가격이면 처음부터 그 가격 지불
            }

            if (totalWeight >= M) {
                isPossible = true;
                answer = Math.min(answer, totalCost);
            }
        }

        bw.write((isPossible ? answer : -1) + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
