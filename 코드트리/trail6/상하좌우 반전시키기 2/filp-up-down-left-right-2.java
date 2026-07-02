import java.util.*;
import java.io.*;

/*
m이 10인건 이유가 있는데..
가로가 m

위에서 아래로 진행하는데 위가 0이면 아래에서 눌러줄수밖에 없고 위가 1이면 아래에서 누르면 안됨(누르면 다음에 못바꿈)
위가 없는 첫번째 행은 누르거나 안누르거나 자유롭게 선택 가능 
마지막 행이 모두 1이라면 성공 아니면 -1출력

비트마스크로 표현하면 보드는 int[]로 가능
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,M;
    static int[] board;
    static int[] temp;
    static final int INF = Integer.MAX_VALUE;

    static void press(int i, int j){
        int mask = 1<<j;
        int cur = mask;
        // 먼저 가로 3개 바꿔줌 j-1, j, j+1
        if(j > 0)
            cur |= (1<<(j-1));
        if(j < M-1)
            cur |= (1<<(j+1));
        temp[i] ^= cur;
        // 위아래 바꿔줌 i-1, i+1
        if(i > 0)
            temp[i-1] ^= mask;
        if(i < N-1)
            temp[i+1] ^= mask;
    }

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        board = new int[N];

        for(int i = 0; i<N; i++){
            int mask = 0;
            for(int j = 0; j<M; j++){
                if(read() == 1){
                    mask |= (1<<j);
                }
            }
            board[i] = mask;
        }

        int ans = INF;
        for(int m = 0; m < (1<<M); m++){
            temp = board.clone();
            int cnt = 0;

            // 마스크는 누른 상태를 나타내는거니까
            // c가 마스크에 존재하면 해당 버튼을 누른거임
            // 그러면 cnt++
            for(int c = 0; c<M; c++){
                if(((m >> c) & 1) != 0){
                    press(0, c);
                    cnt++;
                }
            }

            // 지금 여기서 누르면 
            for(int i = 1; i<N; i++){
                for(int j = 0; j<M; j++){
                    if(((temp[i-1] >> j) & 1) == 0){
                        press(i,j);
                        cnt++;
                    }
                }
            }

            if(temp[N-1] == (1<<M)-1)
                ans = Math.min(ans, cnt);
        }

        System.out.print((ans == INF) ? -1 : ans);
    }
}