import java.util.*;
import java.io.*;

/*
루트노드에서 시작해서 이진트리 따라서 공 떨구는데
서브트리의 공 수 합을 업데이트해서.. 세그트리까지는 아닌거같은데..

K가 홀수면 왼쪽 K가 짝수면 오른쪽으로 감
실제 갯수 다 시뮬레이션 필요없고 K만 보면 될듯 
-> 진짜 트리 채우면 시간 터질거같고

부모 기준 K를 자식 기준 K로 압축하기??
*/

public class Main {
    static int N;
    static long K;
    static int[] left;
    static int[] right;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        left = new int[N+1];
        right = new int[N+1];

        for(int i = 1; i<=N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            left[i] = Integer.parseInt(st.nextToken());
            right[i] = Integer.parseInt(st.nextToken());
        }

        K = Long.parseLong(br.readLine());
        int ci = 1;

        while(true){
            // 리프노드일때
            if(left[ci] == -1 && right[ci] == -1){
                System.out.print(ci);
                return;
            }

            // 자식 하나면 그냥 떨어지고
            if(left[ci] == -1)
                ci = right[ci];
            else if(right[ci] == -1)
                ci = left[ci];
            else{
                // 짝수번째 공은 오른쪽
                if(K % 2 == 0){
                    ci = right[ci];
                    K /= 2;
                }
                else{
                    ci = left[ci];
                    K = (K+1)/2;
                }
            }
        }
    }
}