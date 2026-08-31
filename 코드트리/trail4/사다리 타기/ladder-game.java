import java.util.*;
import java.io.*;

/*
이미 주어진 사다리 구조에서 최소의 사다리를 이용해서 원래의 사다리와 같은 결과를 내게 하기
처음에 사다리를 모든 시작점에서 돌려보고 -> 나중에 백트래킹하면서 모든 시작점에서 최소사다리 구해서 매칭해보기 
-> 이건 아닐듯??


처음 배열 1,2,3,4를 가로줄을 만나면 swap을 진행하고 dfs그리고 swap 풀고
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,M;
    static int[] dest;
    static int[][] road;
    // idx는 주어진 가로줄 번호 -1
    static int[] swap(int[] A, int idx){
        int t = A[idx];
        A[idx] = A[idx+1];
        A[idx+1] = t;
        return A;
    }
    // 최소로 갱신하기
    static int ans = 16;
    // 현재 수열 상태, 현재 사용한 가로줄, 현재 road idx
    // N번 수열 상태 비교하지 말고 diff로 관리
    static void bt(int[] cur, int cnt, int ci, int diff){
        // 추가 가지치기 : cnt >= ans면 볼 필요 없음
        if(cnt >= ans)
            return;

        if(diff == 0){
            ans = Math.min(ans, cnt);
            return;
        }

        if(ci == M){
            return;
        }

        bt(cur, cnt, ci+1, diff);

        int idx = road[ci][0] -1;
        // cur 상태에서 diff 측정
        int before = 0;
        if(cur[idx] != dest[idx])
            before++;
        if(cur[idx+1] != dest[idx+1])
            before++;

        cur = swap(cur, road[ci][0]-1);
        // 가로선을 선택한(swap후) diff 측정
        int after = 0;
        if(cur[idx] != dest[idx])
            after++;
        if(cur[idx+1] != dest[idx+1])
            after++;
            
        bt(cur, cnt+1, ci+1, diff-before+after);

        cur = swap(cur, road[ci][0]-1);
    }

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        road = new int[M][2];
        for(int i = 0; i<M; i++){
            road[i] = new int[]{read(), read()};
        }
        //!! 높이 순서대로 정렬해야 함.
        Arrays.sort(road, (a,b)->{
            return a[1] - b[1];
        });

        dest = new int[N];
        int[] start = new int[N];
        for(int i = 0; i<N; i++){
            dest[i] = i+1;
            start[i] = i+1;
        }

        for(int i = 0; i<M; i++){
            int idx = road[i][0] - 1;
            dest = swap(dest, idx);
        }

        int diff = 0;
        for(int i = 0; i<N; i++){
            if(start[i] != dest[i])
                diff++;
        }

        bt(start, 0, 0, diff);
        System.out.print(ans);
    }
}