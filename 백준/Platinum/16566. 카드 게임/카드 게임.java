import java.io.*;
import java.util.*;

public class Main {
    private static int N, M, K;
    private static int[] board;
    private static int[] targets;
    private static int[] parent;
    private static int find(int x){
        if(x >= parent.length)
            return parent.length-1;
        if(parent[x] != x)
            parent[x] = find(parent[x]);
        return parent[x];
    }
    private static void union(int x, int y){
        if(y>= parent.length)
            return;
        x = find(x);
        y = find(y);
        parent[x] = y;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new int[M];
        parent = new int[M];
        targets = new int[K];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<M; i++){
            board[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<K; i++){
            targets[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(board);
        for(int i = 0; i<M; i++){
            parent[i] = i;
        }

        for(int i =0; i<K; i++){
            int idx = Arrays.binarySearch(board, targets[i]+1);
            if(idx < 0){
                idx = -(idx+1);
            }
            
            // 다음 쓸 카드 포인터 찾고 disjoint set활용해서 이어줌
            int next = find(idx);
            bw.write(String.valueOf(board[next]));
            bw.newLine();
            union(next, next+1);
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
