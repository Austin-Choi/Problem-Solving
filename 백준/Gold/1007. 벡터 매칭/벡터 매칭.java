/*
2그룹으로 나눠서 합벡터 구하고 상쇄시킬때 벡터값 최소화하기
-> 재귀로 그룹 나누고 N/2되면 최소값 갱신
 */
import java.util.*;
import java.io.*;
public class Main {
    static int T;
    static int N;
    static class Vector{
        double x;
        double y;
        Vector(double x, double y){
            this.x = x;
            this.y = y;
        }
    }
    static Vector[] v;
    // 조합에 포함된 인덱스 저장함
    static boolean[] visited;
    static double calcAddVec(){
        Vector v1 = new Vector(0,0);
        for(int i = 0; i<N; i++){
            if(!visited[i]){
                v1.x -= v[i].x;
                v1.y -= v[i].y;
            }
            else{
                v1.x += v[i].x;
                v1.y += v[i].y;
            }
        }

        return Math.sqrt(Math.pow(v1.x, 2) + Math.pow(v1.y,2));
    }
    static double ans;
    static void dfs(int idx, int cnt){
        if(cnt == N/2){
            ans = Math.min(ans, calcAddVec());
            return;
        }

        for(int i = idx; i<N; i++){
            visited[i] = true;
            dfs(i+1, cnt+1);
            visited[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(T-->0){
            ans = Double.MAX_VALUE;
            N = Integer.parseInt(br.readLine());
            v = new Vector[N];
            for(int i = 0; i<N; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                double x = Double.parseDouble(st.nextToken());
                double y = Double.parseDouble(st.nextToken());
                v[i] = new Vector(x,y);
            }
            visited = new boolean[N];
            dfs(0,0);
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }
}