import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        int[] board = new int[N];
        Arrays.fill(board, 1);

        st = new StringTokenizer(br.readLine());
        while(st.hasMoreTokens()){
            board[Integer.parseInt(st.nextToken())-1] -= 1;
        }

        st = new StringTokenizer(br.readLine());
        while(st.hasMoreTokens()){
            board[Integer.parseInt(st.nextToken())-1] += 1;
        }

        for(int i = 0; i<N; i++){
            if(board[i] == 0){
                int left = i-1;
                int right = i+1;
                if(left > -1){
                    if(board[left] == 2){
                        board[left] -= 1;
                        board[i] += 1;
                        continue;
                    }
                }
                if(right < N){
                    if(board[right] == 2){
                        board[right] -= 1;
                        board[i] += 1;
                        continue;
                    }
                }
            }
        }

        int count = 0;
        for(int i= 0; i<N; i++){
            if(board[i] == 0)
                count++;
        }

        bw.write(String.valueOf(count));
        bw.flush();
        bw.close();
        br.close();
    }
}
