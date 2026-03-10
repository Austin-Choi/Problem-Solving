/*
웰논
스택에 인덱스를 저장하고 단조감소스택으로
-> peek보다 cur가 크면 오른쪽 벽
-> 바닥을 pop하면서 물 계산
 */
import java.util.*;
import java.io.*;
public class Main {
    static int H,W;
    static int[] t;

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        t = new int[W];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<W; i++){
            t[i] = Integer.parseInt(st.nextToken());
        }
        Stack<Integer> s = new Stack<>();
        int ans = 0;
        for(int i = 0; i<W; i++){
            while(!s.isEmpty() && t[s.peek()] < t[i]){
                int floor = s.pop();
                if(s.isEmpty())
                    break;
                int left = s.peek();
                int hh = Math.min(t[left], t[i]) - t[floor];
                // 가로 구간 전체에 물 참
                int ww = i - left - 1;
                ans += hh*ww;
            }
            s.push(i);
        }
        System.out.print(ans);
    }
}
