import java.util.*;
import java.io.*;

/*
최초로 모순이 발생한 단서라는거면 
한번 모순이 발생하면 그 뒤는 다 모순이 된다는 거임
-> 단조성


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
    static int[][] clue;

    static boolean can(int x){
        indeg = new int[N+1];
        g = new ArrayList[N+1];
        for(int i=1 ;i<=N; i++){
            g[i] = new ArrayList<>();
        }

        for(int i = 1; i<=x; i++){
            int a = clue[i][0];
            int b = clue[i][1];
            g[a].add(b);
            indeg[b]++;
        }

        Queue<Integer> q = new ArrayDeque<>();
        int cnt = 0;
        for(int i =1 ; i<=N; i++)
            if(indeg[i] == 0)
                q.add(i);
        
        while(!q.isEmpty()){
            int ci = q.poll();
            cnt++;

            for(int n : g[ci]){
                if(--indeg[n] == 0)
                    q.add(n);
            } 
        }

        return cnt == N;
    }

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        clue = new int[M+1][2];

        for(int i=1; i<=M; i++){
            int a = read();
            int b = read();
            // a가 b보다 크다
            // -> a가 우선순위가 높다 
            clue[i] = new int[]{a,b};
        }

        // 중요! 단서가 M개라서 1~M으로 해야함
        int l = 1;
        int r = M;
        int ans = -1;
        while(l<=r){
            int mid = (l+r)/2;
            // mid 번째 단서까지 봤을때 모순이 발생하지 않는가?
            // -> 모순이 발생하지 않았으면 더 뒤에서 봐야함
            if(can(mid)){
                ans = mid;
                l = mid + 1;
            }
            else
                r = mid - 1;
        }
        System.out.print(ans == M ? "Consistent" : (ans+1));
    }
}