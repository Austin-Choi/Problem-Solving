import java.util.*;
import java.io.*;
public class Main {
    static int N;
    static Quest[] board;
    static class Quest implements Comparable<Quest>{
        int dead;
        int ramen;
        Quest(int d, int r){
            this.dead = d;
            this.ramen = r;
        }

        @Override
        public int compareTo(Quest o){
            return this.dead - o.dead;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new Quest[N];
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            set.add(s);
            board[i] = new Quest(s,e);
        }
        Arrays.sort(board);

        // 컵라면 작은 순으로 관리함
        PriorityQueue<Integer> pq
                = new PriorityQueue<>();
        for(Quest cur : board){
            int ramen = cur.ramen;
            int dead = cur.dead;

            // 마감일에 여유가 있음
            if(pq.size()<dead){
                pq.add(ramen);
            }
            else if(!pq.isEmpty() && ramen > pq.peek()) {
                pq.poll();
                pq.add(ramen);
            }
        }

        int ans = 0;
        while(!pq.isEmpty()){
            ans += pq.poll();
        }
        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
}
