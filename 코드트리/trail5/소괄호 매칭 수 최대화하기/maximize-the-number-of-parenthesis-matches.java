import java.util.*;
import java.io.*;

/*
dp로는 안돼 N 10만이라서
순서 중요해서 정렬하면안됨
한번 훑고 지나가면서 순간의 정보로 어떻게 누적해나가야할거같음

문자열 N개를 적절히 붙이기
A+B가 될건지 B+A가 될건지
openA, closeA, openB, closeB 면
A+B 가 되는건 openA*closeB > openB*closeA 임

문자열 정보 압축 -> 최적 문자열 붙이기 순서대로 정렬 -> 점수계산
*/

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException{
        int N = Integer.parseInt(br.readLine());

        int[][] info = new int[N][3];
        for(int i = 0; i<N; i++){
            char[] t = br.readLine().toCharArray();
            int lc = 0;
            int rc = 0;
            // )()()( 이런거 주어질땐 이미 내부에 ()() 있음 이것도 세야함
            // 이 경우엔 inside는 3 그래서 += 로
            int inside = 0;
            for(int ci = 0; ci<t.length; ci++){
                if(t[ci] == '(')
                    lc++;
                else{
                    inside += lc;
                    rc++;
                }
            }
            info[i] = new int[]{lc, rc, inside};
        }

        Arrays.sort(info, (a,b)->{
            return Long.compare((long)b[0]*a[1], (long)a[0]*b[1]);
        });

        // 정렬된 순서대로 (갯수 누적하면서 점수계산하기
        // 1) 문자열 내부 점수
        // 2) a+b 되면서 생기는 점수
        long left = 0;
        long ans = 0;
        for(int i = 0; i<N; i++){
            ans += info[i][2];
            ans += (left * info[i][1]);
            left += info[i][0];
        }
        System.out.print(ans);
    }
}