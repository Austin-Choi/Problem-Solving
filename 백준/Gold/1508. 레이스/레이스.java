import java.io.*;
import java.util.*;

public class Main {
    static int N,M,K;
    static int[] board;
    static boolean canPlace(int dist){
        int count = 1;
        int lastPos = board[0];

        for(int i = 1; i<K; i++){
            if(board[i] - lastPos >= dist){
                count++;
                lastPos = board[i];
            }
        }
        return count >= M;
    }
    static int[] buildLatestPlacement(int dist) {
        int[] res = new int[K];
        int count = 1;
        int last = board[0];
        res[0] = 1;

        for (int i = 1; i < K; i++) {
            if (board[i] - last >= dist) {
                res[i] = 1;
                last = board[i];
                count++;
                if (count == M) break;
            }
        }
        return res;
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new int[K];

        st = new StringTokenizer(br.readLine());
        for(int k = 0; k<K; k++){
            board[k] = Integer.parseInt(st.nextToken());
        }

        int l = 1;
        int r = N;
        int dist = 0;

        while(l<=r){
            int mid = (l+r)/2;
            if(canPlace(mid)){
                dist = mid;
                l = mid+1;
            }
            else
                r = mid -1;
        }

        int[] result = buildLatestPlacement(dist);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < K; i++) {
            sb.append(result[i]);
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}