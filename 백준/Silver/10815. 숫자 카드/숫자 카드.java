import java.util.*;
import java.io.*;

public class Main {
    private static int N,M;
    public static void main(String[] args) throws IOException{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        int[] board = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++){
            board[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(board);

        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<M; i++){
            int num = Integer.parseInt(st.nextToken());
            int idx = Arrays.binarySearch(board, num);
            if(idx < 0)
                bw.write(0+" ");
            else
                bw.write(1+" ");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}