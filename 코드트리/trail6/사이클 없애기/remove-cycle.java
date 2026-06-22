import java.util.*;
import java.io.*;

/*
간선이 10만개라 각자 놓고 안놓고 백트래킹 이런건 못함 
단방향은 고정이고 양방향에서 잘 선택해서 사이클이 없게 가능한지에 대해 판단하는건데 
이미 지나온 정점을 visited에 넣어서 아닌거만 다음 큐에 넣게끔 하고 
-> 사이클이 있으면 또 들어갈거니까
-> 이것도 사실 필요없음

단방향에서 사이클이 없다면 양방향은 위상정렬 결과를 따라 잘 연결하면 되므로 
단방향에서만 위상정렬 했을 때 사이클 여부만 보기?
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,M1,M2;
    static int[] indeg;
    static ArrayList<Integer>[] g;


    public static void main(String[] args) throws IOException{
        N = read();
        M1 = read();
        M2 = read();
        indeg = new int[N+1];
        g = new ArrayList[N+1];
        for(int i =1; i<=N; i++)
            g[i] = new ArrayList<>();
        
        while(M1-->0){
            int a = read();
            int b = read();
            g[a].add(b);
            indeg[b]++;
        }

        Queue<Integer> q = new ArrayDeque<>();
        boolean[] v = new boolean[N+1];
        for(int i = 1; i<=N; i++){
            if(indeg[i] == 0)
                q.add(i);
        }

        int cnt = 0;
        while(!q.isEmpty()){
            int ci = q.poll();
            if(v[ci])
                continue;
            v[ci] = true;

            for(int n : g[ci]){
                if(--indeg[n] == 0 && !v[n]){
                    q.add(n);
                }
            }
        }

        for(int i =1 ;i<=N; i++){
            if(!v[i]){
                System.out.print("No");
                return;
            }
        }
        System.out.print("Yes");
    }
}