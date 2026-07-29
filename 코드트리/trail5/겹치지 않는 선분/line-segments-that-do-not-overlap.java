import java.util.*;
import java.io.*;

/*
2차원 평면에서 겹치려면
여기서는 x1,0 x2,1 로 주어지니까 
일단 시작지점 기준으로 정렬함

현재 선분이 이전과 겹치려면 이전의 x2가 지금의 x2보다 긴게 있으면 겹침
그리고 현재 선분의 x2가 앞으로의 x2들보다 길다면 겹침 

pre[i] = i 이전 x2 최대값 (i-1까지 본거)
suf[i] = i 이후 x2 최댓값
-> curx2 < pre[i] 교차 , curx2 > suf[i]  교차

*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    public static void main(String[] args) throws IOException{
        int N = read();
        int[][] pos = new int[N][2];
        for(int i = 0; i<N; i++){
            pos[i] = new int[]{read(), read()};
        }

        Arrays.sort(pos, Comparator.comparingInt(a->a[0]));
        
        int[] pre = new int[N+1];
        Arrays.fill(pre, -1_000_001);
        for(int i =0 ; i<N; i++){
            pre[i+1] = Math.max(pre[i], pos[i][1]);
        }

        int[] suf = new int[N+1];
        Arrays.fill(suf, 1_000_001);
        for(int i = N-1; i>=0; i--){
            suf[i] = Math.min(suf[i+1], pos[i][1]);
        }

        int ans = 0;
        for(int i = 0; i<N; i++){
            int cur = pos[i][1];
            if(cur < pre[i])
                continue;
            if(cur > suf[i+1])
                continue;
            ans++;
        }
        System.out.print(ans);
    }
}