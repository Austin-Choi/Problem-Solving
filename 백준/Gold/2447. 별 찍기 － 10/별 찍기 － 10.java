/*
재귀적으로 박스 사이즈는
1,3,9,27
이렇게 커지고
dfs(n)
n==1 별찍고 return
그 외에는
base 작업하고
dfs(i+칸수, j+칸수, n/3)
 */
import java.util.*;
import java.io.*;
public class Main {
    static char[][] board;
    // 왼쪽위 시작
    static void dfs(int i,int j, int n){
        if(n == 1) {
            board[i][j] = '*';
            return;
        }

        for(int x = 0; x<3; x++){
            for(int y = 0; y<3; y++){
                if(x == 1 && y == 1)
                    continue;
                dfs(i + x * (n/3), j + y * (n/3), n/3);
            }
        }

    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        board = new char[N][N];
        for(int i = 0; i<N; i++){
            Arrays.fill(board[i], ' ');
        }
        dfs(0,0,N);

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                sb.append(board[i][j]);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
