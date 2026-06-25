import java.util.*;
import java.io.*;

/*
각 지층마다 받는 압력도 저장해야 하고
현재 압력도 값 최대값 저장해줘야 하고 갯수도 그때그때 저장해야 함.
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

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        indeg = new int[N+1];
        g = new ArrayList[N+1];
        for(int i= 1 ;i<=N; i++)
            g[i] = new ArrayList<>();

        while(M-->0){
            int a = read();
            int b = read();
            g[a].add(b);
            indeg[b]++;
        }

        Queue<Integer> q = new ArrayDeque<>();
        // power[i] = i 지층이 받는 압력도
        int[] power = new int[N+1];
        Arrays.fill(power, 1);
        // maxpower[i] = i가 받는 현재 가장 큰 압력도 
        int[] mp = new int[N+1];
        // cnt[i] = i에 영향을 행사하는 가장 큰 압력도의 갯수
        int[] cnt = new int[N+1];
        for(int i = 1; i<=N; i++){
            if(indeg[i] == 0)
                q.add(i);
        }

        while(!q.isEmpty()){
            int ci = q.poll();

            for(int n : g[ci]){
                if(power[ci] > mp[n]){
                    mp[n] = power[ci];
                    cnt[n] = 1;
                }
                else if(power[ci] == mp[n])
                    cnt[n]++;

                if(--indeg[n] == 0){
                    q.add(n);
                    if(cnt[n] > 1)
                        power[n] = mp[n]+1;
                    else if(cnt[n] == 1)
                        power[n] = mp[n];
                }
            }
        }

        int ans = -1;
        for(int p : power){
            ans = Math.max(ans, p);
        }
        System.out.print(ans);
    }
}