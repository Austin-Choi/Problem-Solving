import java.util.*;
import java.io.*;

/*
2이내여야함 한자리만 가능하면됨
총 3자리
-> 현재 자리에서 가능한 범위 * 전체 * 전체
-> min(N,A[i]+2) - max(0, A[i]-2) + 1 * N * N
-> 이렇게 하면 중복되는게 생김
-> 포함배제의 원리를 여기서,,??

대우로 조건을 변경하면
모든 자리의 차가 2를 벗어나는 경우엔 풀리지 않는다...
*/

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        long ans = 1;
        for(int i = 0; i<3; i++){
            int cur = Integer.parseInt(st.nextToken());
            int a = (Math.min(N, cur+2) - Math.max(1, cur-2)+1);
            ans *= (N-a);
        }
        System.out.print(N*N*N - ans);
    }
}