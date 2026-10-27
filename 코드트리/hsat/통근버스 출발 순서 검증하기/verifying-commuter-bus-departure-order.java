/*
i<j<k
ak < ai < aj 이면 안됨.
5
4 2 5 3 1
Ai  4 5 3, 4 5 1, 2 5 1, 2 3 1
idx 1 3 4, 1 3 5, 2 3 5, 2 4 5
-> 이러한 쌍이 해당 수열에서 몇 개인지만 세기 
*/

import java.util.*;
import java.io.*;
public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N;
    static int[] A;

    public static void main(String[] args) throws IOException{
        N = read();
        A = new int[N];
        for(int i = 0; i<N; i++){
            A[i] = read();
        }

        // Ai의 오른쪽에 존재하면서 Ai값보다 작은 수 갯수 저장
        int[] count= new int[N];
        for(int i = 0; i<N; i++){
            int cur = A[i];
            for(int j = i+1; j<N; j++){
                if(cur > A[j])
                    count[i]++;
            }
        }

        long ans = 0;
        // i의 right 값을 고정하고 j를 움직이면서 j의 count값에서 
        // 만약 ai < aj 이면 count[ai]에는 aj가 없을 것이므로 count 그대로 누적
        // 만약 ai > aj 이면 count[ai]에 해당 aj가 포함되므로 ai > aj 는 정답 세는 조건을 만족하지 않으므로
        // 현재 right에서 하나(aj 값) 제거해줘야함
        for(int i = 0; i<N; i++){
            int right = count[i];

            for(int j = i+1; j<N; j++){
                if(A[i] < A[j])
                    ans += right;
                else if(A[i] > A[j])
                    right--;
            }
        }
        System.out.print(ans);
    }
}