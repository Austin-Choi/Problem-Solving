import java.util.*;
import java.io.*;

/*
1) 입력 i, i+1을 보고 다른게 있으면 서로 우선순위 관계 가지니까 그걸 간선 정보로 입력
2) 위의 정보로 위상정렬을 수행하고 
2-1. cnt == N이면 위상순서대로 알파벳 출력
2-2. cnt >= N이면 -1 출력
2-3. 입력에는 존재하는 알파벳이지만 방문할 수 없는 정점이 있을 때

ss에 등장하는 모든 알파벳 중복없이 저장
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

    static int M,N;
    static String[] cl;
    static int[] indeg;
    static ArrayList<Integer>[] g;

    public static void main(String[] args) throws IOException{
        M = read();
        cl = new String[M];

        Set<Integer> ss = new HashSet<>();
        for(int i = 0; i<M; i++){
            String s = rs();
            char[] t = s.toCharArray();
            for(int j = 0; j<t.length; j++)
                ss.add(t[j]-'a');
            cl[i] = s;
        }

        N = ss.size();
        indeg = new int[26];
        g = new ArrayList[26];
        for(int i = 0; i<26; i++)
            g[i] = new ArrayList<>();

        
        outer:
        for(int i = 0; i<M-1; i++){
            char[] a = cl[i].toCharArray();
            char[] b = cl[i+1].toCharArray();

            int min = Math.min(a.length, b.length);
            int j = 0;
            for(; j<min; j++){
                if(a[j] != b[j]){
                    g[a[j]-'a'].add(b[j]-'a');
                    indeg[b[j]-'a']++;
                    continue outer;
                }
            }

            if(j == 0 || j == min)
                continue;

            g[a[j-1]-'a'].add(b[j]-'a');
            indeg[b[j]-'a']++;
        }

        ArrayList<Integer> ans = new ArrayList<>();
        Queue<Integer> q = new ArrayDeque<>();
        int cnt = 0;
        for(int i : ss){
            if(indeg[i] == 0)
                q.add(i);
        }

        while(!q.isEmpty()){
            if(q.size() > 1){
                System.out.print("inf");
                return;
            }

            int ci = q.poll();
            ans.add(ci);
            cnt++;

            for(int n : g[ci]){
                if(--indeg[n] == 0)
                    q.add(n);
            }
        }

        if(cnt != N)
            System.out.print(-1);
        else{
            StringBuilder sb = new StringBuilder();
            for(int i : ans){
                sb.append((char)('a'+i));
            }
            System.out.print(sb);
        }
    }
}