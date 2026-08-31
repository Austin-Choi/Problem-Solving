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
    static void bt(int[] cur, int cnt, int ci){
        // 추가 가지치기 : cnt >= ans면 볼 필요 없음
        if(cnt >= ans)
            return;
            
        boolean same = true;
        for(int i= 0; i<N; i++){
            if(cur[i] != dest[i]){
                same = false;
                break;
            }
        }
        if(same){
            ans = Math.min(ans, cnt);
            return;
        }

        if(ci == M){
            return;
        }

        bt(cur, cnt, ci+1);
        cur = swap(cur, road[ci][0]-1);
        bt(cur, cnt+1, ci+1);
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

        bt(start, 0, 0);
        System.out.print(ans);
    }
}