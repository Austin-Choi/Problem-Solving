import java.util.*;
import java.io.*;

/*
각 그룹 내 수들끼리 차가 K 넘지 않기
-> l1, l2 관리하는데 각자 그룹의 시작점을 의미하고 그룹의 끝은 오름차순으로 정렬되어있으면
같은 그룹의 최대 - 최소가 K 이하면 다 만족하는 조건임
한 수는 최대 한 그룹에만 속할 수 있다하니 l<r 조건 있어야 함

그룹 속해있는 수 최대로 늘리기
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    public static void main(String[] args) throws IOException{
        int N = read();
        int K = read();
        int[] A = new int[N];
        for(int i= 0; i<N; i++)
            A[i] = read();
        Arrays.sort(A);

        // 미리 구간 전처리
        int r = 0;
        // len[i] = i 이후에 만들수 있는 조건 만족하는 최대 구간 크기
        int[] len = new int[N];
        for(int l = 0; l<N; l++){
            while(r<N && (A[r] - A[l] <= K))
                r++;
            
            len[l] = r-l;
        }

        // i부터 N-1까지 만들수 있는 최대 구간 크기
        int[] best = new int[N+1];
        for(int i = N-1; i>=0; i--){
            best[i] = Math.max(best[i+1], len[i]);
        }

        int ans = 0;
        for(int l = 0; l<N; l++){
            int rr = l + len[l];
            ans = Math.max(ans, len[l]+best[rr]);
        }
        System.out.print(ans);
    }
}