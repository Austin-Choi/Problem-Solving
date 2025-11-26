/*
parent[N] 만들고 idx로 초기화
find로 parent[x] = find(parent[x])로 경로압축
유니온 매번 하면 백만*백만이라 터지고
지금의 x값이 다음에 나타날 위치를 parent에 갱신함
-> 어차피 일렬로 union하니까
그리고 그 위치로 점프해서 처리함
 */
import java.util.*;
import java.io.*;
public class Main {
    static int N,M;
    static int[] parent;
    static int find(int x){
        if(parent[x] == x)
            return x;
        return parent[x] = find(parent[x]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        parent = new int[N];
        for(int i = 0; i<N; i++){
            parent[i] = i;
        }
        StringTokenizer st;
        while(M-->0){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;

            int curParent = find(x);
            while(curParent < y){
                parent[curParent] = curParent+1;
                curParent = find(curParent);
            }
        }

        int ans = 0;
        for(int i = 0; i<N; i++){
            if(find(i) == i)
                ans++;
        }
        System.out.println(ans);
    }
}
