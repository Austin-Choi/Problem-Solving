
import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] board = new int[N];

        for(int i = 0; i<N;  i++){
            board[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(board);

        int l = 0;
        int r = N-1;
        int minSum = Integer.MAX_VALUE;
        int ansl = l;
        int ansr = r;
        while(l<r){
            int sum = board[l]+board[r];
            if(minSum > Math.abs(sum)){
                minSum = Math.abs(sum);
                ansl = l;
                ansr = r;
            }
            if(sum > 0){
                r--;
            }
            else if(sum < 0){
                l++;
            }
            else{
                ansl = l;
                ansr = r;
                break;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(board[ansl]).append(" ").append(board[ansr]);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}