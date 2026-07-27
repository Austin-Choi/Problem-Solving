import java.util.*;
import java.io.*;



public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int getDist(int x1, int y1, int x2, int y2){
        return Math.abs(x1-x2) + Math.abs(y1-y2);
    }

    public static void main(String[] args) throws IOException{
        int N = read();
        int[][] pos = new int[N][2];
        for(int i = 0; i<N; i++){
            pos[i] = new int[]{read(), read()};
        }
        int[] dist = new int[N];
        for(int i = 1; i<N; i++){
            dist[i] = dist[i-1] + getDist(pos[i][0], pos[i][1], pos[i-1][0], pos[i-1][1]);
        }

        int ans = Integer.MAX_VALUE;
        for(int i = 1; i<N-1; i++){
            ans = Math.min(ans, dist[i-1] + getDist(pos[i+1][0], pos[i+1][1], pos[i-1][0], pos[i-1][1]) + (dist[N-1] - dist[i+1]));
        }
        System.out.print(ans);
    }
}