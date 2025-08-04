
/*
스택 + 그리디

모든 가능한 볼수 있는 쌍 갯수 세기
스택은 현재 사람보다 작은 키를 가진 사람들을 관리하면서,
그 사람들과 볼 수 있는 쌍을 세기 위한 용도

스택은 (키, 같은 키 갯수)로 저장
 */
import java.util.*;
import java.io.*;
public class Main {
    static int N;
    static class Info{
        int h;
        int cnt;

        Info(int h, int cnt){
            this.h = h;
            this.cnt = cnt;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        int[] board = new int[N];
        for(int i = 0; i<N; i++){
            board[i] = Integer.parseInt(br.readLine());
        }

        Stack<Info> s = new Stack<>();
        long ans = 0;
        for(int i = 0; i<N; i++){
            int curh = board[i];
            int samecnt = 1;

            // top < cur -> pop하면서 쌍추가
            while(!s.isEmpty() && s.peek().h < curh){
                ans += s.peek().cnt;
                s.pop();
            }

            // top == cur
            // 같은 키끼리는 모두 볼 수 있음
            if(!s.isEmpty() && s.peek().h == curh){
                Info top = s.pop();
                ans += top.cnt;
                // 그 외에 나보다 큰 사람이 있으면 그것도 볼 수 있음
                if(!s.isEmpty())
                    ans += 1;
                // 같은 키면 해당 키 갯수에 +1
                s.push(new Info(curh, top.cnt+1));
            }
            // top > cur
            // top 하나만 볼 수 있음
            else{
                if(!s.isEmpty())
                    ans+=1;
                s.push(new Info(curh, samecnt));
            }
        }

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
}
