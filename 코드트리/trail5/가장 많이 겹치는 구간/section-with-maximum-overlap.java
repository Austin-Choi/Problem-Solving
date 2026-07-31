import java.util.*;
import java.io.*;

/*
입력받으면서 최소 최댓값 구하고
그 구간에서 지금 시작점 만나면 +1, 끝점 만나면 -1
-> 끝 포함이니까 -1은 끝점 +1에

이벤트 기준으로 정렬해야 함. 
-> 구간이 열림, 구간이 닫힘
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

            pos[2*i] = new int[]{a, 1};
            pos[2*i+1] = new int[]{b, -1};
        }

        Arrays.sort(pos, Comparator.comparingInt(a->a[0]));
        int cnt = 0;
        int ans = 0;
        for(int i = 0; i<2*N; i++){
            cnt += pos[i][1];
            ans = Math.max(ans, cnt);
        }
        System.out.print(ans);
    }
}