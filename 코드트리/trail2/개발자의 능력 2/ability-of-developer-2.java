import java.util.*;
import java.io.*;

/*
재귀적으로 2명을 뽑으면 또 들어가서 두명 뽑고 
또 들어가서 2명 뽑고 반복
-> 중복하면 안되니까 visited랑 백트래킹

본문 for문에서 바깥쪽에 break문 걸어야 중복으로 뽑히지 않음
*/

public class Main {
    static int N;
    static int[] skill;
    static int[] rst;
    static boolean[] visited = new boolean[6];
    static final int INF = 6 * 1_000_000 + 1;
    static int ans = INF;
    static int total = 0;

    static void dfs(int depth){
        if(depth == 3){
            int max = -1;
            int min = INF;
            for(int n : rst){
                max = Math.max(max, n);
                min = Math.min(min, n);
            }
            ans = Math.min(ans, max-min);
            return;
        }

        for(int i = 0; i<N; i++){
            if(visited[i])
                continue;

            visited[i] = true;
            for(int j = i+1; j<N; j++){
                if(visited[j])
                    continue;

                visited[j] = true;
                rst[depth] = skill[i] + skill[j];
                dfs(depth+1);
                visited[j] = false;
            }
            visited[i] = false;

            break;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = 6;
        skill = new int[N];
        rst = new int[N/2];
        for(int i = 0; i<N; i++){
            skill[i] = Integer.parseInt(st.nextToken());
            total += skill[i];
        }

        dfs(0);
        System.out.print(ans);
    }
}