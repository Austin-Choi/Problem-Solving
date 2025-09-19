/*
가중치 분리 집합
parent[], weight[]
-> 부모 인덱스까지 무게차이 누적시켜서 한번에 구하게 하기
-> find할때 weight[x] += weight[압축 전 parent[x]] 갱신함
 */
import java.util.*;
import java.io.*;
public class Main {
    static int N,M;
    static int[] parent;
    static long[] weight;

    static int find(int x){
        if(parent[x] == x)
            return x;
        int px = parent[x];
        parent[x] = find(parent[x]);
        weight[x] += weight[px];
        return parent[x];
    }

    static void union(int a, int b, long w){
        int ra = find(a);
        int rb = find(b);
        if(ra == rb)
            return ;
        // 방향은 상관없고 그 방향에 맞춰서 갱신해야함
        // 알기 쉽게 b의 root를 a로 둠
        parent[ra] = rb;
        weight[ra] = weight[b] - weight[a] - w;
//        parent[rb] = ra;
//        weight[rb] = weight[a] - weight[b] + w;
    }

    static void query(int a, int b){
        if(find(a) != find(b))
            System.out.println("UNKNOWN");
        else
            System.out.println(weight[b] - weight[a]);
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            if(N == 0 && M == 0)
                break;
            parent = new int[N+1];
            for(int i = 1; i<=N; i++){
                parent[i] = i;
            }
            weight = new long[N+1];
            for(int m = 0; m<M; m++){
                st = new StringTokenizer(br.readLine());
                String cmd = st.nextToken();
                if(cmd.equals("!")){
                    int a = Integer.parseInt(st.nextToken());
                    int b = Integer.parseInt(st.nextToken());
                    long w = Long.parseLong(st.nextToken());
                    union(a,b,w);
                }
                else{
                    int a = Integer.parseInt(st.nextToken());
                    int b = Integer.parseInt(st.nextToken());
                    query(a,b);
                }
            }
        }
    }
}
