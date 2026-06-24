import java.util.*;
import java.io.*;

/*
3->2
1->5
2->1
3->4
4->2

3->4->2->1->5

위상 순서 사전순 가장 앞선것
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,M;
    static int[] indeg;
    static ArrayList<Integer>[] g;
    static ArrayList<Integer> ans = new ArrayList<>();

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        indeg = new int[N+1];
        g = new ArrayList[N+1];
        for(int i =1  ;i<=N; i++)
            g[i] = new ArrayList<>();

        while(M-->0){
            int a = read();
            int b = read();
            g[a].add(b);
            indeg[b]++;
        }

        int cnt = 0;
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for(int i = 1; i<=N; i++){
            if(indeg[i] == 0)
                q.add(i);
        }

        while(!q.isEmpty()){
            int ci = q.poll();
            ans.add(ci);
            cnt++;

            for(int n : g[ci]){
                if(--indeg[n] == 0)
                    q.add(n);
            }
        }

        if(cnt != N){
            System.out.print(-1);
            return;
        }

        StringBuilder sb = new StringBuilder();
        for(int x : ans){
            sb.append(x).append(" ");
        }
        System.out.print(sb);
    }
}