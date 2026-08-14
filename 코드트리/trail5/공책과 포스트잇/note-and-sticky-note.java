import java.util.*;
import java.io.*;

/*
N = 처음 주어지는 언급 갯수
K*L = 추가할수 있는 언급 갯수

can(X)
최대 K*L개의 언급을 사용해서 언급 수가 X 이상인개 X개 이상이 되게 만들 수 있는지
내림차순 정렬해놓기
X 이상인건 poll하고 그 아래인건 tot -= X-poll 해서 X개 도달 가능한지 
그전에 음수되면 false
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,K,L;
    static int[] C;

    static boolean can(int X){
        int cnt = 0;
        long tot = (long)K*L;
        for(int i = N-1; i>=0; i--){
            if(C[i] >= X){
                cnt++;
            }
            else{
                int need = X-C[i];
                // !! : 조건상 포스트잇당 공책은 1번만 추가 가능
                if(need > K)
                    continue;
                if(need > tot)
                    return false;
                tot -= need;
                cnt++;
            }
            if(cnt == X)
                return true;
        }
        return false;
    }
    

    public static void main(String[] args) throws IOException{
        N = read();
        K = read();
        L = read();
        C = new int[N];
        for(int i = 0; i<N; i++){
            C[i] = read();
        }
        Arrays.sort(C);

        int l = 0;
        int r = N;
        int ans = 0;
        while(l<=r){
            int mid = (l+r)/2;
            if(can(mid)){
                ans = mid;
                l = mid+1;
            }
            else
                r = mid -1;
        }
        System.out.print(ans);
    }
}