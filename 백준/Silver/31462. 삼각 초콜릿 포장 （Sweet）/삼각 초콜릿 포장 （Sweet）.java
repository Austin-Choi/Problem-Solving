/*
현재가 r이면 i+1,j; i+1,j+1이 r이어야 함
현재가 b이면 i,j+1; i+1,j이 b여야 함 -> 아

4
R
RR
RRR
RRRR
1
 */
import java.io.*;
public class Main {
    static boolean[][] tri;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        tri = new boolean[N][N];
        visited = new boolean[N][N];
        for(int i = 1; i<=N; i++){
            char[] temp = br.readLine().toCharArray();
            for(int j = 0; j<i; j++){
                if(temp[j]=='R')
                    tri[i-1][j] = true;
                else
                    tri[i-1][j] = false;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i + 1; j++) {
                if (visited[i][j])
                    continue;
                visited[i][j] = true;
                if(i+1 >= N){
                    System.out.println(0);
                    return;
                }
                if (tri[i][j]) {
                    if(visited[i+1][j] || visited[i+1][j+1]){
                        System.out.println(0);
                        return;
                    }
                    visited[i + 1][j] = true;
                    visited[i + 1][j + 1] = true;
                    if (!(tri[i + 1][j] && tri[i + 1][j + 1])) {
                        System.out.println(0);
                        return;
                    }
                } else {
                    if(visited[i][j+1] || visited[i+1][j+1]){
                        System.out.println(0);
                        return;
                    }
                    visited[i][j+1] = true;
                    visited[i+1][j+1] = true;
                    if (!(!tri[i][j+1] && !tri[i+1][j+1])) {
                        System.out.println(0);
                        return;
                    }
                }
            }
        }

        System.out.println(1);
    }
}
