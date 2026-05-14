import java.util.*;
import java.io.*;

public class Main {
    static int N,M;
    static boolean[] v;
    static ArrayList<int[]>[] g;

    static int getDist(int si, int dest){
        Queue<int[]> q = new ArrayDeque<>();
        boolean[] v = new boolean[N+1];
        q.add(new int[]{si, 0});
        v[si] = true;

        if(si == dest)
            return 0;
        
        while(!q.isEmpty()){
            int[] cur= q.poll();
            int ci = cur[0];
            int cd = cur[1];

            if(ci == dest)
                return cd;

            for(int[] n : g[ci]){
                if(v[n[0]])
                    continue;
                v[n[0]] = true;
                q.add(new int[]{n[0], cd+n[1]});
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        g = new ArrayList[N+1];
        for(int i= 1; i<=N; i++)
            g[i] = new ArrayList<>();
        
        int k = N-1;
        while(k-->0){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w= Integer.parseInt(st.nextToken());
            g[u].add(new int[]{v,w});
            g[v].add(new int[]{u,w});
        }

        StringBuilder sb = new StringBuilder();
        while(M-->0){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            sb.append(getDist(u,v)).append("\n");
        }
        System.out.print(sb);
    }
}