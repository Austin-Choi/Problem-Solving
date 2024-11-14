import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

// n이 개커서 이분탐색, long
// 각 요소에 대해 요소/mid 값을 모두 합한게 N이 되어야 하고
// mid 값이 최대가 될때의 mid 값 구하기
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        long N = Long.parseLong(st.nextToken());
        long[] arr = new long[K];
        long ans = 0;
        for(int i = 0; i<K; i++){
            arr[i] = Long.parseLong(br.readLine());
        }
        Arrays.sort(arr);
        // 모든 요소가 같더라도 돌리긴해야함
        long l = 0;
        // zerodivide 에러 해결용
        // 0만 존재할때 l=0, r=0이라서 0인데 이러면 이분탐색 수행도 안하고
        // 그러면 r을 1올려서 l=0, r=0 범위에서 해주면 됨
        // 이거때문에 좀 걸림
        long r = arr[K-1]+1;
        long sum = 0;
        while(l<r){
            long mid = (l+r)/2;
            sum = 0;
            for(long ll : arr){
                sum += (ll / mid);
            }
            // mid가 아직 좀 작음, 그래도 같으니까 저장
            if(sum >= N){
                l = mid+1;
                ans = mid;
            }
            // mid가 너무 큼
            else{
                r = mid;
            }
        }

        bw.write(ans+"");
        bw.flush();
        bw.close();
        br.close();
    }
}
