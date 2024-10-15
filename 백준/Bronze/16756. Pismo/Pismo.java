import java.io.*;
import java.util.*;

/*
    N개의 정수로 이루어진 배열 A
    구간 L, R의 값 = 해당 구간 L,R의 최대값 - 해당 구간 L,R의 최소값
    L은 항상 R보다 작은것이 보장된다.
    구간 L,R의 최소값을 출력

    2 <= N <= 100,000
    A를 구성하는 정수는 -10^9 ~ 10^9

    Collections.max min 쓰고
    전역변수로 input 받아서 그때그때 list 생성하고 max min 계산?
*/
public class Main {
    private static int[] A;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = new int[N];
        for(int i = 0; i<N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }

        int ans = Integer.MAX_VALUE;
        for(int i = 1; i<N; i++){
            ans = Math.min(ans, Math.abs(A[i] - A[i-1]));
        }
        
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(ans+"");
        bw.flush();
        bw.close();
        br.close();
    }
}
