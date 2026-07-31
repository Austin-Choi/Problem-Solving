import java.util.*;
import java.io.*;

/*
R = prev +1 prev+a -1
L = prev-a +1 prev -1

K개 이상 겹친다 
-> 한 겹쳐진 덩어리의 구성 선분 갯수가 K개 이상이고 이때 시작과 끝
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static char rc() throws IOException{
        sst.nextToken();
        return (char) sst.sval.charAt(0);
    }

    public static void main(String[] args) throws IOException{
        int N = read();
        int K = read();

        int[][] pos = new int[2*N][2];

        int prev = 0;
        for(int i = 0; i<N; i++){
            int a = read();
            char cmd = rc();
            if(cmd == 'R'){
                pos[2*i] = new int[]{prev, 1};
                pos[2*i+1] = new int[]{prev+a, -1};
                prev += a;
            }
            else{
                pos[2*i] = new int[]{prev-a, 1};
                pos[2*i+1] = new int[]{prev, -1};
                prev -= a;
            }
        }

        Arrays.sort(pos, Comparator.comparingInt(a->a[0]));
        int cnt = 0;
        long sum = 0;

        // 길이는 항상 현재 이벤트 - 다음 이벤트
        for(int i = 0; i<2*N-1; i++){
            cnt += pos[i][1];
            int len = pos[i+1][0] - pos[i][0];
            if(cnt >= K){
                sum += len;
            }  
        }
        System.out.print(sum);
    }
}