import java.util.*;
import java.io.*;

/*
진입차수가 0인게 시작이라 그게 갯수
-> (x,y) y는 x의 조상

indeg가 0이 되는 순간의 ci가 직계 부모

stoi hashmap 사용
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static String rs() throws IOException{
        sst.nextToken();
        return sst.sval;
    }

    static int N, M;
    static ArrayList<Integer>[] g;
    static ArrayList<Integer>[] child;
    static String[] node;
    static int[] indeg;
    static ArrayList<String> s = new ArrayList<>();

    public static void main(String[] args) throws Exception{
        N = read();
        node = new String[N];
        for(int i = 0; i < N; i++){
            node[i] = rs();
        }

        Arrays.sort(node);
        HashMap<String, Integer> stoi = new HashMap<>();

        for(int i = 0; i < N; i++){
            stoi.put(node[i], i);
        }

        indeg = new int[N];
        g = new ArrayList[N];
        child = new ArrayList[N];

        for(int i = 0; i < N; i++){
            g[i] = new ArrayList<>();
            child[i] = new ArrayList<>();
        }

        M = read();

        while(M-- > 0){
            String a = rs();
            String b = rs();

            int ai = stoi.get(a);
            int bi = stoi.get(b);

            indeg[ai]++;
            g[bi].add(ai);
        }

        PriorityQueue<Integer> q = new PriorityQueue<>();

        for(int i = 0; i < N; i++){
            if(indeg[i] == 0){
                q.add(i);
                s.add(node[i]);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(s.size()).append("\n");
        for(String str : s){
            sb.append(str).append(" ");
        }
        sb.append("\n");

        while(!q.isEmpty()){
            int ci = q.poll();

            for(int n : g[ci]){
                if(--indeg[n] == 0){
                    child[ci].add(n);
                    q.add(n);
                }
            }
        }

        for(int i = 0; i < N; i++){
            child[i].sort(Comparator.comparingInt(a -> a));

            sb.append(node[i]).append(" ");
            sb.append(child[i].size());
            for(int c : child[i]){
                sb.append(" ").append(node[c]);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}