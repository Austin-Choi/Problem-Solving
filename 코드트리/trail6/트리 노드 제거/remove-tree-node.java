import java.util.*;
import java.io.*;

/*
주어진 부모 노드 배열 보고 그래프 재구성해서 
지울 노드 번호 루트로 타고 내려가면서 정보 지우기 
그리고 리프노드 재탐색

리프노드
-> 자식이 아예 없거나
-> 모든 자식이 삭제 상태임.
*/

public class Main {
    static int N;
    static int[] parent;
    static ArrayList<Integer>[] g;
    static boolean[] isDeleted;

    static void dfs(int ci){
        if(isDeleted[ci])
            return;
        isDeleted[ci] = true;
        for(int n : g[ci]){
            dfs(n);
        }
    }

    static int ans = 0;
    static void findLeaf(int si){
        Queue<Integer> q = new ArrayDeque<>();
        q.add(si);

        while(!q.isEmpty()){
            int ci = q.poll();

            boolean found = false;
            for(int n : g[ci]){
                if(isDeleted[n])
                    continue;
                found = true;
                q.add(n);
            }
            if(!found)
                ans++;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        parent = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0 ;i<N; i++){
            parent[i] = Integer.parseInt(st.nextToken());
        }

        g = new ArrayList[N];
        for(int i = 0; i<N; i++){
            g[i] = new ArrayList<>();
        }

        int root = -1;
        for(int i = 0; i<N; i++){
            if(parent[i] == -1)
                root = i;
            else
                g[parent[i]].add(i);
        }

        int d = Integer.parseInt(br.readLine());
        isDeleted = new boolean[N];
        if(d == root){
            System.out.print(0);
            return;
        }
        else{
            dfs(d);
            findLeaf(root);
            System.out.print(ans);
        }
    }
}