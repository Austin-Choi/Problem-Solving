/*
뒤집어진 상태가 종료 조건
T의 갯수가 N이 되면 종료

BFS + visited
현재 T의 갯수, dist
ct, ch
nt, nh
pool은 0~N

nextT = ct - nt + nh
다음 상태 ( 턴 종료시 T 갯수)
ct - nt -> 현재 T 중 H로 바꾼 수를 빼줌
+ nh -> H 중 T로 바꾼 수를 더함
 */
import java.util.*;
import java.io.*;
public class Main {
    static int N, K;
    static int bfs(int t){
        boolean[] visited = new boolean[N+1];
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{t, 0});
        visited[t] = true;

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int ct = cur[0];
            int cd = cur[1];
            int ch = N-ct;

            if(N == ct)
                return cd;

            for(int i = 0; i<=K; i++){
                int nt = i;
                int nh = K-i;

                if(nt > ct || nh > ch)
                    continue;

                if(!visited[ct - nt + nh]){
                    visited[ct - nt + nh] = true;
                    q.add(new int[]{ct - nt + nh, cd + 1});
                }
            }
        }

        return -1;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        char[] tmp = br.readLine().toCharArray();
        int t = 0;
        for(int i = 0; i<N; i++){
            if(tmp[i] == 'T')
                t++;
        }

        if(t == N)
            System.out.println(0);
        else
            System.out.println(bfs(t));
    }
}
