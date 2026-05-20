import java.util.*;
import java.io.*;

/*
dp[i][j][max][min] 이러면 터지지 않나 크기가 1억인데;

어.. 파라메트릭 서치??
모든 가능한 경로에 대해서 [최소, 최대] 구간크기가 mid를 만족하는지

근데 이러면 중복연산 많아지니까
투포인터로 ..
투포인터 쓰려면 정렬해야함. 
-> 값다 받아와서 arr

bfs(min, max)
-> 1,1 - n,n 경로에 있는 모든 값이 해당 구간을 만족하는지

*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N;
    static int[][] board;
    static List<Integer> arr = new ArrayList<>();
    static int[] di = {1,0};
    static int[] dj = {0,1};

    static boolean bfs(int min, int max){
        boolean[][] v = new boolean[N][N];
        Queue<int[]> q = new ArrayDeque<>();
        if(board[0][0] < min || board[0][0] > max)
            return false;
        q.add(new int[]{0,0});
        v[0][0] = true;

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int ci = cur[0];
            int cj = cur[1];

            if(ci == N-1 && cj == N-1)
                return true;

            for(int d = 0; d<2; d++){
                int ni = ci + di[d];
                int nj = cj + dj[d];
                if(ni < 0 || nj < 0 || ni >= N || nj >= N)
                    continue;
                if(board[ni][nj] < min || board[ni][nj] > max)
                    continue;
                if(v[ni][nj])
                    continue;
                v[ni][nj] = true;
                q.add(new int[]{ni,nj});
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException{
        N = read();
        board = new int[N][N];

        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                board[i][j] = read();
                arr.add(board[i][j]);
            }
        }
        Collections.sort(arr);
        
        int l = 0;
        int r = 0;
        int ans = 101;
        while(l<arr.size() && r<arr.size()){
            int min = arr.get(l);
            int max = arr.get(r);
            // 지금 값으로 가능하면 
            // l++를 해야 max-min이 더 작아짐
            // 최소로 갱신하는거니까 
            if(bfs(min, max)){
                ans = Math.min(ans, max-min);
                l++;
            }
            else{
                r++;
            }
        }
        System.out.print(ans);
    }
}