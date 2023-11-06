import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int count;
    public static int[] link;
    public static boolean[] finished, visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());
            finished = new boolean[n+1];
            visited = new boolean[n+1];
            count = 0;

            link = new int[n+1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=1; i<=n; i++){
                link[i] = Integer.parseInt(st.nextToken());
            }

            for(int i=1; i<=n; i++){
                if(!finished[i]){
                    dfs(i);
                }
            }
            sb.append(n-count);
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
    public static void dfs(int now){
        if(visited[now]){	//cycle에서 두번째 방문이라면 이미 순환구조의 팀에 포함되었음
            finished[now] = true;
            count++;	//팀에 포함되었으므로 count++
        }
        else{	//첫방문
            visited[now] = true;
        }

        int next = link[now];
        if(!finished[next]){	//cycle에 두번째 방문을 한 node는 또 dfs를 돌릴 필요없음
            dfs(next);
        }

        visited[now] = false;
        finished[now] = true;	//만약 cycle에 포함되지 않았다면 cycle경험만 충족하고 count는 올라가지 않으며 dfs가 종료됨
    }
}