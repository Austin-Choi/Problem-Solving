import java.util.*;
import java.io.*;
public class Main {
    static class State{
        int idx;
        int p;
        State(int idx, int p){
            this.idx = idx;
            this.p = p;
        }
    }

    static Queue<State> q;
    // true = 더 중요한 다른 문서 있음
    // false = 바로 뽑음
    static boolean check(State cur){
        for (State state : q) {
            if (cur.p < state.p) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(T-->0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int targetIdx = Integer.parseInt(st.nextToken());

            q = new ArrayDeque<>();
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i<N; i++){
                State temp = new State(i, Integer.parseInt(st.nextToken()));
                q.add(temp);
            }

            int cnt = 0;
            while(!q.isEmpty()){
                State cur = q.poll();
                if(check(cur))
                    q.add(cur);
                else{
                    cnt++;
                    if(cur.idx == targetIdx)
                        break;
                }
            }
            sb.append(cnt).append("\n");
        }
        System.out.print(sb);
    }
}