/*
연결된 집만 저장해서 isStation에서 읽어오면 20만 * 20만이라 터질거같음
..
조회 많이하면 터지니까
조회 비싼 노드만 저장해놓고 조회 싼노드는 그냥 순회함
지하철 건설 여러번 가능함
 */
import java.util.*;
import java.io.*;
public class Main {
    static int N,M,Q;
    static ArrayList<Integer>[] board;
    static boolean[] isStation;
    static boolean[] isHeavy;
    static int[] heavyCnt;
    static final int B = (int) Math.sqrt(200_000) + 1;

    static int op(int type, int i){
        if(type == 1){
            if(isStation[i])
                return -1;
            isStation[i] = true;
            for(int next : board[i]){
                if(isHeavy[next])
                    heavyCnt[next]++;
            }
            return -1;
        }

        if(isHeavy[i])
            return heavyCnt[i];
        int cnt = 0;
        for(int next : board[i]){
            if(isStation[next])
                cnt++;
        }

        return cnt;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        board = new ArrayList[N+1];
        for(int i = 1; i<=N; i++){
            board[i] = new ArrayList<>();
        }

        while(M-->0){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            board[s].add(e);
            board[e].add(s);
        }

        isStation = new boolean[N+1];
        // 조회비용 무거운거만 캐싱함
        isHeavy = new boolean[N+1];
        for(int i=1; i<=N; i++){
            if(board[i].size()>=B)
                isHeavy[i] = true;
        }
        heavyCnt = new int[N+1];

        while(Q-->0){
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int i = Integer.parseInt(st.nextToken());
            int rst = op(t,i);
            if(rst != -1)
                sb.append(rst).append("\n");
        }
        System.out.println(sb);
    }
}
