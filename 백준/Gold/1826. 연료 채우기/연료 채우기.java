import java.util.*;
import java.io.*;
class Station implements Comparable<Station>{
    int pos;
    int oil;
    public Station(int p, int o){
        this.pos = p;
        this.oil = o;
    }

    @Override
    public int compareTo(Station o){
        return this.pos - o.pos;
    }
}
public class Main {
    static int N, L, P;
    static Station[] board;
    public static void main(String[] args) throws IOException{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        board = new Station[N];
        for(int i = 0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int o = Integer.parseInt(st.nextToken());
            board[i] = new Station(p,o);
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        Arrays.sort(board);

        int ans = 0;
        PriorityQueue<Station> pq
                = new PriorityQueue<>(Comparator.comparingInt((Station o)->o.oil).reversed());
        int idx = 0; // 주유소 인덱스

        // 아직 도착 못했으면
        while(P<L){
            // 현재 연료로 도달 가능한 모든 주유소 PQ에 넣기
            while(idx < N && board[idx].pos <= P){
                pq.add(board[idx]);
                idx++;
            }

            // 갈 수 있는 주유소 없으면 -1
            if(pq.isEmpty()){
                ans = -1;
                break;
            }

            // 기름 가장 많은 주유소에서 주유함
            P += pq.poll().oil;
            ans++;
        }

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
}