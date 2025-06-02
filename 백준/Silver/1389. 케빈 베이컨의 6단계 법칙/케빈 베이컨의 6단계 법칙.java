
import java.io.*;
import java.util.*;

public class Main {
    private static int N;
    private static int M;

    private static int[][] board;


    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N+1][N+1];

        for(int i = 1; i<=N; i++){
            for(int j = 1; j<=N; j++){
                if(i==j)
                    board[i][j] = 0;
                else
                    board[i][j] = Integer.MAX_VALUE;
            }
        }

        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            board[a][b] = 1;
            board[b][a] = 1;
        }

        for(int k = 1; k <= N; k++){
            for(int i = 1; i<= N; i++){
                for(int j = 1; j<=N; j++){
                    if(board[i][k] != Integer.MAX_VALUE && board[k][j] != Integer.MAX_VALUE)
                        board[i][j] = Math.min(board[i][j], board[i][k]+board[k][j]);
                }
            }
        }

        Map<Integer, Integer> m = new HashMap<>();
        for(int i = 1; i<=N; i++){
            int sum = 0;
            for(int j = 1; j<=N; j++){
                //m.put(i, m.getOrDefault(i,0)+board[i][j]);
                if(board[i][j] != Integer.MAX_VALUE)
                    sum += board[i][j];
            }
            m.put(i, sum);
        }

        int min = Integer.MAX_VALUE;
        int minNode = Integer.MAX_VALUE;
        for(Map.Entry<Integer, Integer> e : m.entrySet()){
            if(e.getValue() < min){
                min = e.getValue();
                minNode = e.getKey();
            }
            else if(e.getValue() == min && e.getKey() < minNode)
                minNode = e.getKey();
        }

        sb.append(minNode);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
