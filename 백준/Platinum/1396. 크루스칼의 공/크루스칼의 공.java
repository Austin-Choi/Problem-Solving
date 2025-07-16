import java.util.*;
import java.io.*;
class Edge implements Comparable<Edge>{
    int from;
    int to;
    int cost;
    public Edge(int f, int t, int c){
        this.from = f;
        this.to = t;
        this.cost = c;
    }

    @Override
    public int compareTo(Edge o){
        return this.cost - o.cost;
    }
}
class Query{
    int x;
    int y;
    int idx;

    public Query(int x, int y, int idx){
        this.x = x;
        this.y = y;
        this.idx = idx;
    }
}
public class Main {
    static int N, M, Q, X, Y;
    static int[] parent;
    static int[] size;
    static ArrayList<Edge> board;
    // 각 정점에 대해
    // 이 정점이 포함된 쿼리들을 저장
    static ArrayList<Query>[] queries;
    static boolean[] done;
    static int[][] result;
    static void setup(){
        parent = new int[N+1];
        size = new int[N+1];
        queries = new ArrayList[N+1];
        for(int i = 0; i <= N; i++){
            parent[i] = i;
            size[i] = 1;
            queries[i] = new ArrayList<>();
        }
    }
    static int find(int x){
        if(parent[x] != x)
            parent[x] = find(parent[x]);
        return parent[x];
    }
    static void union(int x, int y, int curCost){
        x = find(x);
        y = find(y);
        if(x == y)
            return;

        if(size[x] < size[y]){
            int tmp = x;
            x = y;
            y = tmp;
        }
        parent[y] = x;
        size[x] += size[y];

        // 두 루트(x, y)에 걸린 쿼리를 병합하며 처리
        for(Query q : queries[y]){
            int nx = q.x;
            int ny = q.y;

            if(find(nx) == find(ny) && !done[q.idx]){
                result[q.idx][0] = curCost;
                result[q.idx][1] = size[x];
                done[q.idx] = true;
            }
            else{
                queries[x].add(q);
            }
        }
        queries[y].clear();
    }

    public static void main(String[] args) throws Exception {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new ArrayList<>();

        for(int m = 0; m<M; m++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            board.add(new Edge(a,b,c));
        }
        Collections.sort(board);

        Q = Integer.parseInt(br.readLine());
        result = new int[Q][2];
        done = new boolean[Q];
        setup();

        for(int q = 0; q<Q; q++){
            st = new StringTokenizer(br.readLine());
            X = Integer.parseInt(st.nextToken());
            Y = Integer.parseInt(st.nextToken());
            queries[X].add(new Query(X,Y,q));
            queries[Y].add(new Query(X,Y,q));
        }


        // 간선 하나씩 붙이면서
        // 각 쿼리 X,Y가 연결됬는지 확인
        for(Edge e : board){
            union(e.from, e.to, e.cost);
        }

        // 연결 안된 쿼리들 후처리
        for(int q = 0; q<Q; q++){
            if(!done[q]){
                result[q][0] = -1;
                result[q][1] = -1;
            }
        }

        for(int q = 0; q<Q; q++){
            if(result[q][1] == -1)
                sb.append("-1\n");
            else
                sb.append(result[q][0]).append(" ").append(result[q][1]+"\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
