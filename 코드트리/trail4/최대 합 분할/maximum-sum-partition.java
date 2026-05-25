import java.util.*;
import java.io.*;

/*
a,b,c에 정확히 한개씩 모든 수가 들어가 있어야 함
a의 합 = b의 합, tot - 2*a의 합 = c의 합
dp[tot - cur] == dp[cur] 

C는 사실상 의미 없고 기준점으로 잡고 돌리는 위치고 
A와 tot-A가 둘다 가능할 때 합의 최댓값으로 갱신?
-> subset sum으로 하면 3개라 합만으로 보면 내부 구성 요소가 겹칠 수 있음.

dp[d] = (A합-B합) 을 d라 할때 d를 만들때 가능한 A합의 최댓값
-> 차이를 들고 있어야 원소를 한번 쓰는게 보장됨

dp[tot] = 0 
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    public static void main(String[] args) throws IOException{
        int N = read();
        int[] A = new int[N];
        int tot = 0;
        for(int i = 0; i<N; i++){
            A[i] = read();
            tot += A[i];
        }

        int INF = -2*(tot+1);
        // -tot ~ tot
        int[] dp = new int[2*tot+1];
        Arrays.fill(dp, INF);
        dp[tot] = 0;

        for(int cur : A){
            int[] next = dp.clone();
            for(int d = -tot; d<=tot; d++){
                //dp[d+tot] = 현재 차이 d를 만들때 A 합의 최댓값
                if(dp[d+tot] == INF)
                    continue;
                
                // A에 현재 숫자 넣기
                // A에 넣으면 A합-B합이니까 양수로 작용
                int nd = d + cur;
                if(nd >= -tot && nd <= tot){
                    // A에 넣을때만 A합 증가
                    next[nd+tot] = Math.max(next[nd+tot], dp[d+tot] + cur);
                }

                // B에 넣기
                // B에 넣으면 A합-B합이니까 음수로 작용
                nd = d - cur;
                if(nd >= -tot && nd <= tot){
                    next[nd+tot] = Math.max(next[nd+tot], dp[d+tot]);
                }

                // C에 넣기
                // C에 넣으면 A합-B합 변화 없음
                next[d+tot] = Math.max(next[d+tot], dp[d+tot]);
            }
            dp = next;
        }
        
        System.out.print(dp[tot]);
    }
}