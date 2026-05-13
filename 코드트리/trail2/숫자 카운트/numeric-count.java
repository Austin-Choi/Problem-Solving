import java.util.*;
import java.io.*;

// 이거 숫자야구 구현같음
/*

*/

public class Main {
    // B의 각 자릿수 0:100, 1:10, 2:1
    static int[] B;
    // 쿼리마다 각 자릿수 여기다가 넣고 ball 비교함
    static Set<Integer> bs;

    static int strikeCount(int a, int b, int c){
        int rst = 0;
        if(B[0] == a)
            rst++;
        if(B[1] == b)
            rst++;
        if(B[2] == c)
            rst++;
        return rst;
    }

    // 후보 숫자 abc의 구성요소가 A에 있지만 다른 자리의 수
    static int ballCount(int a, int b, int c){
        int rst = 0;
        if(bs.contains(a) && B[0] != a)
            rst++;
        if(bs.contains(b) && B[1] != b)
            rst++;
        if(bs.contains(c) && B[2] != c)
            rst++;
        return rst;
    }

    static boolean check(int strike, int ball, int a, int b, int c){
        return (strikeCount(a,b,c) == strike) && (ballCount(a,b,c) == ball);
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] queries = new int[N][3];
        for(int i = 0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int curB = Integer.parseInt(st.nextToken());
            int curStrike = Integer.parseInt(st.nextToken());
            int curBall = Integer.parseInt(st.nextToken());
            queries[i] = new int[]{curB, curStrike, curBall};
        }

        int ans = 0;
        for(int a = 1; a<=9; a++){
            for(int b= 1; b<=9; b++){
                if(a == b)
                    continue;
                for(int c = 1; c<=9; c++){
                    if(b == c || a == c)
                        continue;
                    boolean ok = true;

                    // 지금 뽑은 서로다른숫자로 이루어진 abc가 
                    // 쿼리를 모두 만족해야 후보 조건이 성립함.
                    for(int[] q : queries){
                        int cur = q[0];
                        int cs = q[1];
                        int cb = q[2];

                        B = new int[3];
                        B[0] = (cur/100) %10;
                        B[1] = (cur/10) % 10;
                        B[2] = cur % 10;

                        bs = new HashSet<>();
                        for(int bb : B)
                            bs.add(bb);

                        if(!check(cs,cb,a,b,c)){
                            ok = false;
                            break;
                        }
                    }
                    if(ok)
                        ans++;
                }
            }
        }
        System.out.print(ans);
    }
}