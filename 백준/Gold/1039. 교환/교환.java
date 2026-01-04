/*
그래프 모델링?
K번 교환을 했을때 숫자값 String으로 저장?
Set<String>[K+1] visited 
cost =1 -> bfs
교환을 간선으로 해서 그래프로 생성하고
교환 일단하고 맨 앞이 0이면 set에 추가하지 않고
visited[K]의 string들 중 가장 큰 값 반환함
아무것도 없으면 -1
 */
import java.util.*;
import java.io.*;
public class Main {
    static String N;
    static int K;
    static int[] input;
    static int len;
    static HashSet<String>[] visited;

    static String swap(String s, int i,int j){
        char[] arr = s.toCharArray();
        char t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
        return new String(arr);
    }

    static class State{
        String str;
        int depth;
        State(String s, int d){
            this.str = s;
            this.depth = d;
        }
    }

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = st.nextToken();
        K = Integer.parseInt(st.nextToken());
        char[] temp = N.toCharArray();
        len = temp.length;
        if(len == 1){
            System.out.println(-1);
            return;
        }

        input = new int[len];
        for(int i = 0; i<len; i++){
            input[i] = temp[i]-'0';
        }

        visited = new HashSet[K+1];
        for(int i = 0; i<=K; i++){
            visited[i] = new HashSet<>();
        }
        Queue<State> q = new ArrayDeque<>();
        q.add(new State(N,0));
        visited[0].add(N);

        while(!q.isEmpty()){
            State cur = q.poll();
            String cs = cur.str;
            int cd = cur.depth;

            if(cd == K)
                continue;

            for(int i = 0; i<len; i++){
                for(int j = i+1; j<len; j++){
                    String next = swap(cs,i,j);

                    if(next.charAt(0) == '0')
                        continue;

                    if(!visited[cd+1].contains(next)){
                        visited[cd + 1].add(next);
                        q.add(new State(next, cd+1));
                    }
                }
            }
        }

        if(visited[K].isEmpty())
            System.out.println(-1);
        else{
            int max = 0;
            for(String s : visited[K])
                max = Math.max(max, Integer.parseInt(s));
            System.out.println(max);
        }
    }
}
