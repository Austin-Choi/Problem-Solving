import java.util.*;
import java.io.*;

/*
1명 일단 잡고 걔만 제외하고 백트래킹해서 구성한담에 비교?
그 한명을 max min 초기값으로 주면 될듯
*/

public class Main {
    // 2명, 2명 합 
    static int[] rst;
    static int N;
    static int[] skill;
    static boolean[] visited;
    static final int INF = 5*1000+1;
    static int ans = INF;

    static void dfs(int depth, int base){
        if(depth == 2){
            int min = base;
            int max = base;
            
            // 모든팀 능력치 달라야함
            if(base == rst[0]) 
                return;
            if(base == rst[1]) 
                return;
            if(rst[0] == rst[1]) 
                return;
            
            for(int n : rst){
                min = Math.min(min, n);
                max = Math.max(max, n);
            }
            ans = Math.min(ans, max - min);
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
                dfs(depth+1, base);

                visited[j] = false;
            }
            
            visited[i] = false;

            break;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = 5;
        skill = new int[N];
        visited = new boolean[N];
        rst= new int[(N-1) / 2];
        for(int i = 0 ; i<N; i++){
            skill[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i<N; i++){
            visited[i] = true;
            dfs(0, skill[i]);
            visited[i] = false;
        }

        System.out.print(ans == INF ? -1 : ans);
    }
}