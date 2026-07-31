import java.util.*;
import java.io.*;

/*
이벤트 시점으로 정렬하고 cnt 0 될때마다 구간 최대값 더해서 전체 N에서 빼기?
-------------------

모든 구간이 합쳐진 이후 서로 다른 구간 
-> 합쳐진 결과의 덩어리 총 갯수 
-> 0에서 1로 되는거 덩어리 시작 갯수
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    public static void main(String[] args) throws IOException{
        int N = read();
        int[][] pos = new int[2*N][2];
        for(int i = 0; i<N; i++){
            int a = read();
            int b = read();
            pos[2*i] = new int[]{a,1};
            pos[2*i+1] = new int[]{b,-1};
        }

        Arrays.sort(pos, Comparator.comparingInt(a->a[0]));
        int cnt = 0;
        int prev = 0;
        int s = 0;
        long ans = 0;
        for(int i = 0; i<2*N; i++){
            cnt += pos[i][1];
            if(prev == 0 && cnt == 1)
                s = pos[i][0];
            if(prev == 1 && cnt == 0){
                ans += pos[i][0] - s;
            }
            prev = cnt;
        }
        System.out.print(ans);
    }
}