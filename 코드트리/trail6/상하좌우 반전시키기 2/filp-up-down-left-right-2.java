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
            // -> cnt는 Integer.bitCount로 대체가능

            // j 순회 업는 버전
            int cur = m;
            // & ((1<<m)-1)로 j 경계 자동으로 잘려나감
            // 해결 : | | 로 하던거 xor로 연결, 인접해서 눌리면 중간 비트가 두번 xor됨
            // parity 고려해서 xor로
            int flip = ((cur<<1) ^ cur ^ cur>>1) & ((1<<M)-1);
            temp[0] ^= flip;
            if(N>1)
                temp[1] ^= cur;
            cnt += Integer.bitCount(cur);

            for(int i =1 ; i<N; i++){
                // 위 행에서 0인 위치를 1로 바꿔야하니까 
                // not으로 0/1 뒤집으면 1이 눌러야 할 위치가 되고 mask로 M개 비트만 유지
                int mask = ~(temp[i-1]) & ((1<<M)-1);

                if(mask != 0){
                    int flip2 = ((mask << 1) ^ mask ^ (mask >> 1)) & ((1<<M)-1);
                    temp[i] ^= flip2;
                    temp[i-1] ^= mask;
                    if(i < N-1)
                        temp[i+1] ^= mask;
                    cnt += Integer.bitCount(mask);
                }
            }

            if(temp[N-1] == ((1<<M)-1))
                ans = Math.min(ans, cnt);
        }

        System.out.print((ans == INF) ? -1 : ans);
    }
}