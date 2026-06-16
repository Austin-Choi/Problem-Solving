import java.util.*;
import java.io.*;

/*
union할때 노드 번호가 작은쪽을 루트로 가게 하면 
parent[x] = [parent[x],x]구간의 대표값
*/


public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int find(int x){
        if(parent[x] == x)
            return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b){
        int pa = find(a);
        int pb = find(b);
        if(pa == pb)
            return;
        if(size[pb] < size[pa]){
            int t = pa;
            pa = pb;
            pb = t;
        }
        parent[pb] = pa;
        size[pa] += size[pb];
        // 두개가 합쳐져서 1개됬으니 전체 컴포넌트수 -1
        cnt--;
    }

    static int findNext(int x){
        if(next[x] == x)
            return x;
        return next[x] = findNext(next[x]);
    }

    static int N,M;
    static int[] parent;
    static int[] next;
    static int[] size;
    static int cnt;

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        parent = new int[N+1];
        next = new int[N+1];
        size = new int[N+1];
        for(int i = 1; i<=N; i++){
            parent[i] = i;
            next[i] = i;
            size[i] = 1;
        }

        /*
        next에 i를 볼때 처리해야할 다음 위치를 저장
        [next[pa], b]
        */
        cnt = N;
        StringBuilder sb = new StringBuilder();
        while(M-->0){
            int a = read();
            int b = read();
            int na = findNext(a);
            while(na < b){
                union(na, na+1);
                next[na] = findNext(na+1);
                na = findNext(na);
            }
            sb.append(cnt).append("\n");
        }
        System.out.print(sb);
    }
}