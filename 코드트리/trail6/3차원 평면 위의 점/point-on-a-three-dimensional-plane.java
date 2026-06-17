import java.util.*;
import java.io.*;


public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N;
    static int[][] pos;
    static int[] parent;
    static int cnt = 0;
    static long sum = 0;
    static final int INF = 2_000_000_000+1;

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

    static int getDist(int[] p1, int[] p2){
        int min = INF;
        for(int i = 0; i<3; i++){
            min = Math.min(min, Math.abs(p1[i] - p2[i]));
        }
        return min;
    }

    public static void main(String[] args) throws IOException{
        N = read();
        // pos가 0~N이라 0,0,0,0이 계속 정렬에 섞이는 문제있음
        pos = new int[N][4];
        parent = new int[N+1];
        for(int i = 0; i<N; i++){
            parent[i+1] = i+1;
            pos[i] = new int[]{i+1,read(), read(), read()};
        }

        // a,b,w
        ArrayList<int[]> li = new ArrayList<>();
        // x
        Arrays.sort(pos, (a, b) -> a[1] - b[1]);
        for(int i = 0; i < N-1; i++){
            li.add(new int[]{
                    pos[i][0],
                    pos[i + 1][0],
                    Math.abs(pos[i][1] - pos[i + 1][1])
            });
        }

        // y
        Arrays.sort(pos, (a, b) -> a[2] - b[2]);
        for(int i = 0; i < N-1; i++){
            li.add(new int[]{
                    pos[i][0],
                    pos[i + 1][0],
                    Math.abs(pos[i][2] - pos[i + 1][2])
            });
        }

        // z
        Arrays.sort(pos, (a, b) -> a[3] - b[3]);
        for(int i = 0; i < N-1; i++){
            li.add(new int[]{
                    pos[i][0],
                    pos[i + 1][0],
                    Math.abs(pos[i][3] - pos[i + 1][3])
            });
        }

        // li를 x,y,z 정렬한걸로 간선 생성했으니까 전체 크루스칼용으로 정렬
        Collections.sort(li, (a,b)->{
            return Integer.compare(a[2], b[2]);
        });
        for(int[] ll : li){
            union(ll[0], ll[1], ll[2]);
            if(cnt == N-1){
                break;
            }
        }
        System.out.print(sum);
    }
}