import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] board = new int[N];
        int[] left = new int[N];
        int[] right = new int[N];

        for(int n = 0; n<N; n++){
            board[n] = Integer.parseInt(st.nextToken());
        }

        left[0] = 1;
        right[N-1] = 1;

        for(int n = 0; n<N; n++){
            left[n] = 1;
            for(int m = 0; m<n; m++){
                if(board[m] < board[n])
                    left[n] = Math.max(left[n], left[m]+1);
            }
        }

        for(int n = N-1; n>=0; n--){
            right[n] = 1;
            for(int m = N-1; m>n; m--){
                if(board[m] < board[n])
                    right[n] = Math.max(right[n], right[m]+1);
            }
        }

        int max = 0;
        for(int n = 0; n<N; n++){
            int temp = left[n] + right[n];
            if(max < temp)
                max = temp;
        }
        max -= 1;

        bw.write(String.valueOf(max));
        bw.flush();
        bw.close();
        br.close();
    }
}
