import java.util.*;
import java.io.*;

/*
부등호는 간선 방향같은데 
a<b a->b
a>b b->a

큰 순서는 반대로하나??
1->2
2->3
4->3
5->4
1 2 5 4 3

1 2 5 4 3

2->1
3->2
3->4
4->5

3->2->1->4->5

3 4 5 2 1

부등호 간선 방향인데 사전순으로 앞선 경우는 정방향으로 그래프 작성하고 
1번자리에 숫자 작은것부터 순서대로 넣는거니까 위상정렬 그대로 출력
사전순으로 가장 뒤에 있는 경우는 반대방향으로 그래프 작성하고 
1번 자리에 큰 숫자부터 순서대로 넣기
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }
    static char rc() throws IOException{
        sst.nextToken();
        return (char) sst.ttype;
    }

    static int N;
    static int[] indeg;
    static int[] indegR;
    static ArrayList<Integer>[] g;
    static ArrayList<Integer>[] gR;
    static ArrayList<Integer> ans = new ArrayList<>();
    static ArrayList<Integer> ansR = new ArrayList<>();

    static String itos(int x){
        String s = String.valueOf(x);
        int l = s.length();
        if(l == 3)
            return s;
        if(l == 2)
            return "0"+s;
        return "00"+s;
    }

    static void topoSort(boolean isR){
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for(int i = 1; i<=N; i++){
            if(isR){
                if(indegR[i] == 0)
                    q.add(i);
            }
            else{
                if(indeg[i] == 0)
                    q.add(i);
            }
        }

        while(!q.isEmpty()){
            int ci = q.poll();
            if(isR){
                ansR.add(ci);
            }
            else{
                ans.add(ci);
            }

            if(isR){
                for(int n : gR[ci]){
                    if(--indegR[n] == 0)
                        q.add(n);
                }
            }
            else{
                for(int n : g[ci]){
                    if(--indeg[n] == 0)
                        q.add(n);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException{
        N = read();
        indeg = new int[N+1];
        indegR = new int[N+1];
        g = new ArrayList[N+1];
        gR = new ArrayList[N+1];
        for(int i = 1; i<=N; i++){
            g[i] = new ArrayList<>();
            gR[i] = new ArrayList<>();
        }

        for(int i =1; i<N; i++){
            char cmd = rc();
            // 정방향 a->b
            // 역방향 b->a
            if(cmd == '<'){
                g[i].add(i+1);
                indeg[i+1]++;
                gR[i+1].add(i);
                indegR[i]++;
            }
            else{
                g[i+1].add(i);
                indeg[i]++;
                gR[i].add(i+1);
                indegR[i+1]++;
            }
        }
        topoSort(false);
        topoSort(true);

        StringBuilder sb = new StringBuilder();
        for(int x : ans){
            sb.append(itos(x));
        }
        sb.append("\n");
        for(int x : ansR){
            sb.append(itos(N-x+1));
        }
        System.out.print(sb);
    }
}