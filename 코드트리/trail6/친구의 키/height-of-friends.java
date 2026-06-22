import java.util.*;
import java.io.*;


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

    static void topologySort(){
        Queue<Integer> q = new ArrayDeque<>();
        for(int i = 1; i<=N; i++){
            if(indeg[i] == 0)
                q.add(i);
        }

        while(!q.isEmpty()){
            int ci = q.poll();
            ans.add(ci);

            for(int n : g[ci]){
                if(--indeg[n] == 0){
                    q.add(n);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        indeg = new int[N+1];
        g = new ArrayList[N+1];
        for(int i = 1; i<=N; i++)
            g[i] = new ArrayList<>();

        while(M-->0){
            int a = read();
            int b = read();
            g[a].add(b);
            indeg[b]++;
        }

        topologySort();
        StringBuilder sb = new StringBuilder();
        for(int a : ans){
            sb.append(a).append(" ");
        }
        System.out.print(sb);
    }
}