/*
양방향 그래프로 모델링
정렬 전처리 해주고
dfs로 백트래킹
 */
import java.util.*;
import java.io.*;
public class Main {
    static int K,N,F;
    static ArrayList<Integer>[] board;
    static boolean found = false;
    static ArrayList<Integer> out = new ArrayList<>();
    static void dfs(int depth, ArrayList<Integer> cands){
        if(found)
            return;

        if(depth == K){
            for(int x : out)
                System.out.println(x);
            found = true;
            return;
        }

        if(cands.size() < K - depth)
            return;

        for(int v : cands){
            out.add(v);

            ArrayList<Integer> n = new ArrayList<>();
            for(int u : board[v]){
                if(cands.contains(u))
                    n.add(u);
            }
            dfs(depth+1, n);
            out.remove(out.size()-1);

            if(found)
                return;
        }

    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        F = Integer.parseInt(st.nextToken());

        board = new ArrayList[N+1];
        for(int i = 1; i<=N; i++){
            board[i] = new ArrayList<>();
        }
        while(F-->0){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            board[u].add(v);
            board[v].add(u);
        }

        for(int i= 1; i<=N; i++){
            Collections.sort(board[i]);
        }

        for(int i = 1; i<=N; i++){
            ArrayList<Integer> cand = new ArrayList<>();
            for(int v : board[i])
                if(v>i)
                    cand.add(v);

            out.clear();
            out.add(i);
            dfs(1, cand);
            if(found)
                break;
        }

        if(!found)
            System.out.println(-1);
    }
}
