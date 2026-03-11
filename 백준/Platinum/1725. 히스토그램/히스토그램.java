/*
어떤 연속된 구간의 최소 높이값 * 구간 길이의 최대값 구하기
넓이 구하려면 인덱스끼리 차이 알아야 하니까 스택에는 인덱스 저장
-> nearest smaller left/right 찾는 문제
 */
import java.util.*;
import java.io.*;
public class Main {
    static int N;
    static long[] h;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        // 스택 마지막에 0을 넣어서 스택이 비도록 해야함
        // 전부 계산해야하니까
        h = new long[N+1];
        for(int i = 0; i<N; i++){
            h[i] = Long.parseLong(br.readLine());
        }

        Stack<Integer> s = new Stack<>();
        long ans = 0;
        for(int i = 0; i<=N; i++){
            // 현재 막대보다 낮은 막대를 만나면 이전 막대들의 최대 직사각형 확정
            while(!s.isEmpty() && h[s.peek()] > h[i]){
                int top = s.pop();
                long hh = h[top];
                int ww;
                // 현재 peek는 top보다 낮은 1번째 막대
                // peek가 없으면 처음부터 지금까지 모두가 가능해서 ww = i
                if(s.isEmpty())
                    ww = i;
                else
                    ww = i - s.peek() -1;
                ans = Math.max(ans, ww*hh);
            }
            s.push(i);
        }
        System.out.print(ans);
    }
}
