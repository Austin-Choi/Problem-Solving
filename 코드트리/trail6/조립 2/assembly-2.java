import java.util.*;
import java.io.*;

/*
a를 만들기 위한 선행작업 K개 주어짐
현재 가지고있는 조각 -> 이걸 다 시작 큐에 넣고 위상정렬?

같은 조립법이 여러번 들어올 수 있음.
5 2 
1 3
5 2
1 2
면 5를 만들기 위해서 1 2 3이 필요하다는 의미임

*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,M;
    static ArrayList<Integer>[] g;
    static int[] indeg;
    static ArrayList<Integer> ans = new ArrayList<>();

    public static void main(String[] args) throws IOException{
        N = read();
        M = read();
        indeg = new int[N+1];
        g = new ArrayList[N+1];
        for(int i=1; i<=N; i++){
            g[i] = new ArrayList<>();
        }

        // 같은 조립법 여러번 들어와서 중복없이 합집합으로 만들어야 함
        // need[a] = a를 만들기 위한 부품 리스트
        Set<Integer>[] need = new HashSet[N+1];
        for(int i = 1; i<=N; i++){
            need[i] = new HashSet<>();
        }

        while(M-->0){
            int a = read();
            int K = read();

            while(K-->0){
                int b = read();
                need[a].add(b);
            }
        }
        int K = read();
        ArrayList<Integer> li = new ArrayList<>();
        while(K-->0)
            li.add(read());

        for(int i = 1; i<=N; i++){
            for(int b : need[i]){
                g[b].add(i);
                indeg[i]++;
            }
        }


        // 위상정렬 시작
        // 시작점으로는 가지고 있는 조각 전부 다 넣기
        boolean[] have = new boolean[N+1];
        Queue<Integer> q = new ArrayDeque<>();
        for(int n : li){
            have[n] = true;
            q.add(n);
        }

        while(!q.isEmpty()){
            int ci = q.poll();

            for(int n : g[ci]){
                // have로 중복방지하고 중복 기여 막음
                if(--indeg[n] == 0 && !have[n]){
                    have[n] = true;
                    li.add(n);
                    q.add(n);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(li.size()).append("\n");
        Collections.sort(li);
        for(int n : li){
            sb.append(n).append(" ");
        }
        System.out.print(sb);
    }
}