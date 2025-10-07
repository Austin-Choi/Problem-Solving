// 최소값이 가장 커지려면 난이도 차이가 가장 큰 두 수 사이의 중간에 넣어야함
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++){
            int n = Integer.parseInt(st.nextToken());
            arr[i] = n;
        }
        Arrays.sort(arr);

        int max = 0;
        int ans = -1;
        for(int i = 1; i<N; i++){
            int diff = (arr[i] - arr[i-1]) /2;
            if(max < diff){
                max = diff;
                ans = (arr[i]+arr[i-1])/2;
            }
        }
        System.out.println(ans);
    }
}
