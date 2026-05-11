import java.util.*;
import java.io.*;

/*
한번 쭉 계산하고 슬라이딩 윈도
시계반대방향은 말장난, 순환형

시작점을 한칸 옮기면 
거리
0 1 2 3 4 
->
N-1 0 1 2 3
모든 거리가 -1씩 줄어들지만 맨 뒤에 있던건 N만큼 움직여야 함.
->
new = prev - sum + N*A[s-1]
*/

public class Main {
    static int N;
    static int[] A;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N];

        int total = 0;
        // 계속 미는거라서 거리가 각각 1씩 줄어들어서 
        // 전체 사람 수만큼 다음거 계산할때 빼줘야함
        int sum = 0;
        for(int i = 0; i<N; i++){
            A[i] = Integer.parseInt(br.readLine());
            total += i * A[i];
            sum += A[i];
        }

        int ans = total;
        int prev = total;
        for(int s = 1; s<N; s++){
            int next = prev - sum + N*A[s-1];
            prev = next;
            ans = Math.min(ans, next);
        }
        System.out.print(ans);
    }
}