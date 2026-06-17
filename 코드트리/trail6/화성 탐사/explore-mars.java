import java.util.*;
import java.io.*;

/*

priorityqueue 하나 밖에 두고 bfs로 지금 정점에서 나머지 정점 dist 구하고

*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N;
    static int M = 1;
    static int[][] board;
    static int[][] id;

    static int[] di = {-1,0,1,0};
    static int[] dj = {0,1,0,-1};

    static ArrayList<int[]> li = new ArrayList<>();
    static Map<Integer, int[]> m = new HashMap<>();
    static final int INF = 50 * 50 + 1;

    static void bfs(int s){
        int si = m.get(s)[0];
        int sj = m.get(s)[1];
        boolean[][] v = new boolean[N][N];
        v[si][sj] = true;
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{si,sj,0});

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int ci= cur[0];
            int cj = cur[1];
            int cd = cur[2];

            if(id[ci][cj] != 0 && id[ci][cj] > s){
                li.add(new int[]{s, id[ci][cj], cd});
            }

            for(int d= 0; d<4;d++){
                int ni = ci + di[d];
                int nj = cj + dj[d];
                if(ni < 0 || nj < 0 || ni >= N || nj >= N)
                    continue;
                if(board[ni][nj] == -1)
                    continue;
                if(v[ni][nj])
                    continue;
                v[ni][nj] = true;
                q.add(new int[]{ni,nj,cd+1});
            }
        }
    }
    
    static int sum = 0;
    static int cnt = 0;
    static int[] parent;
    static int find(int x){
        if(parent[x] == x)
            return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b, int w){
        int pa = find(a);
        int pb = find(b);
        if(pa == pb)
            return;
        parent[pb] = pa;
        sum += w;
        cnt++;
    }

    public static void main(String[] args) throws IOException{
        N = read();
        

        board = new int[N][N];
        // map 대신 사용
        id = new int[N][N];
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                board[i][j] = read();
                if(board[i][j] == 1 || board[i][j] == 2){
                    id[i][j] = M;
                    m.put(M++, new int[]{i,j});
                }
            }
        }

        for(int p =1; p<M; p++){
            bfs(p);
        }

        parent = new int[M];
        for(int i = 1; i<M; i++)
            parent[i] = i;

        Collections.sort(li, Comparator.comparingInt(a->a[2]));
        for(int[] ll : li){
            union(ll[0], ll[1], ll[2]);
            if(cnt == M-2)
                break;
        }

        int prev = find(1);
        for(int p = 2; p<M; p++){
            if(prev != find(p)){
                System.out.print(-1);
                return;
            }
        }
        System.out.print(sum);
    }
}