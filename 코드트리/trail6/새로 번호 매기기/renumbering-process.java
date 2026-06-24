import java.util.*;
import java.io.*;

/*
번호를 새로 매기려함 x->y 에서 x가 더 작아야하니까 priorityqueue 
사이클 있으면 못 매기니까 -1

2->3
3->4
3->1
4->1

위상 결과 2 3 4 1
값       1 2 3 4
4 1 2 3

위상정렬을 하고 순서대로 1,2,3,4 매기고 

2->3
3->4
3->1
위상 결과 2 3 1 4
값       1 2 3 4
위상 결과를 인덱스로 보고 순서대로 값 출력하면 
3 1 2 4

-----------------------
근데 작은->큰 순서로 하면 답이 여러개로 찢어질수 있어서 
역그래프로 만들고 sink 기준으로 그리디하게 뽑아야 한다. 
1) 역그래프 2) priorityqueue도 revese 3) N--로 큰 숫자부터 위상순서 1에 부여
*/


public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,M;
    static int[] indeg;
    static ArrayList<Integer>[] g;
    static ArrayList<Integer> ans = new ArrayList<>();

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        indeg = new int[N+1];
        g = new ArrayList[N+1];
        for(int i = 1; i<=N; i++)
            g[i] = new ArrayList<>();

        while(M-->0){
            int a = read();
            int b = read();
            g[b].add(a);
            indeg[a]++;
        }

        int cnt = 0;
        PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
        for(int i=1; i<=N; i++){
            if(indeg[i] == 0)
                q.add(i);
        }

        while(!q.isEmpty()){
            int ci = q.poll();
            ans.add(ci);
            cnt++;

            for(int n : g[ci]){
                if(--indeg[n] == 0)
                    q.add(n);
            }
        }

        if(cnt != N){
            System.out.print(-1);
            return;
        }

        int[] t = new int[N+1];
        int x = N;
        for(int i : ans){
            t[i] = x--;
        }
        StringBuilder sb = new StringBuilder();
        for(int i= 1; i<=N; i++){
            sb.append(t[i]).append(" ");
        }
        System.out.print(sb);
    }
}