import java.util.*;
import java.io.*;

/*  
연산을 최소한으로 해서 사이클이 없는 연결 그래프 만들기
특정 두 점을 연결하는 간선은 유일함

1-2
    4-5
      5-3

--------
반례 : 사이클이 있으면 그걸 끊어주는 것도 있어야함
-> 입력 그래프가 이미 존재하고 union 으로 지금 새로 만드는 상황이 아님
size를 통한 밸런싱은 성능 최적화를 위해 추가
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N, M;
    static int[] parent;
    static int[] size;
    static int cycle=0;

    static int find(int x){
        if(parent[x] == x)
            return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b){
        int pa = find(a);
        int pb = find(b);
        if(pa == pb){
            cycle++;
            return;
        }
        if(size[pb] < size[pa]){
            int t = pa;
            pa = pb;
            pb = t;
        }
        parent[pb] = pa;
        size[pa] += size[pb];
    }

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        parent = new int[N+1];
        size = new int[N+1];
        for(int i = 1; i<=N; i++){
            parent[i] = i;
            size[i] = 1;
        }
        while(M-->0){
            int a= read();
            int b = read();
            union(a,b);
        }
        
        Set<Integer> ss = new HashSet<>();
        for(int i = 1; i<=N; i++){
            ss.add(find(i));
        }
        System.out.print(ss.size()-1 + cycle);
    }
}