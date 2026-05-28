/*
자식->부모 순서로 전파하기?

    3-6
1-2-3-5
  2-4
    4-7
    4-8
    4-9
4=9, 나머지 0

둘다 0이거나 N이면 연산 수행 불가
    0-0
0-0-0-0
  0-6
    6-1
    6-1
    6-1
+3
    0-0
0-5-0-0
  5-1
    1-1
    1-1
    1-1
    +5

diff[u] = A[u]-1 (1이 목표니까)
post-order -> 자식들을 먼저 처리하고 부모로 남는 연산량을 올려보내기
-> 자식 서브트리를 모두 1로 처리했을때 올려보낼 연산량 리턴?
-> 이부분을 리턴처리해서 합해주기? dfs??
트리라서 사이클이 없어서 부모로밖에 못감

*/
import java.util.*;
import java.io.*;

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N;
    static int[] A;
    static ArrayList<Integer>[] g;
    static long ans = 0;

    /*
    필요한 연산량을 방향성으로 구별함
    현재 전이 방향은 dfs 구현상 자식 - 부모니까 
    diff가 양수면 처리할게 남았으니 부모로 올려보내고
    음수면 연산량이 서브트리에서 부족해서 부모에서 데려와야해서 누적량에서 빠짐 
    (부호 붙어서 리턴되니까 그냥 더하기로 표현)
    */
    static long dfs(int ci, int parent){
        int cur = A[ci]-1;
        for(int n : g[ci]){
            if(n == parent)
                continue;
            cur += dfs(n,ci);
        }
        ans += Math.abs(cur);
        return cur;
    }


    public static void main(String[] args) throws IOException{
        N = read();
        A = new int[N+1];
        for(int i=1; i<=N; i++){
            A[i] = read();
        }

        g = new ArrayList[N+1];
        for(int i = 1; i<=N; i++){
            g[i] = new ArrayList<>();
        }

        int M = N-1;
        while(M-->0){
            int u = read();
            int v = read();
            g[u].add(v);
            g[v].add(u);
        }

        dfs(1,-1);
        System.out.print(ans);
    }
}