/*
최대한 세공장 먼저
4
2 3 2 1
-> 19

i = 0 -> continue
i+1 = 0 -> i--, ans += 3
i+2 = 0 -> i--, i+1-- ans+=5

i+2가 충분히 크면 3묶음 계속 돌림
근데 i+1이 커지면 단품 처리되서 비싸짐
(i+1 > i+2)
i, i+1 처리
else
i, i+1, i+2 처리
 */
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int i = 0;
        long ans = 0;
        for(;i<N-2;i++){
            // i = 0 넘어감
            if(arr[i] == 0){
                continue;
            }
            // i+1 = 0 중간에 끊겨서 단품처리
            // i만큼 *3 +=
            if(arr[i+1] == 0){
                ans += 3L*arr[i];
                arr[i] = 0;
                continue;
            }
            // i+2 = 0 3번째 0
            // i,i+1 처리해야함
            // 나머지는 단품
            if(arr[i+2] == 0){
                int n = Math.min(arr[i], arr[i+1]);
                ans += 5L*n;
                arr[i] -= n;
                arr[i+1] -= n;
                ans += 3L * arr[i];
                arr[i] = 0;
                continue;
            }
            // 3묶음의 맨 뒤가 중간보다 큰지 비교함
            // 아니라면 (3묶음으로 먼저 처리 못한다면)
            if(arr[i+1] > arr[i+2]){
                int n = Math.min(arr[i], arr[i+1] - arr[i+2]);
                ans += 5L*n;
                arr[i] -= n;
                arr[i+1] -= n;
            }

            // 3묶음 처리
            int m = Math.min(arr[i], Math.min(arr[i+1], arr[i+2]));
            ans += 7L * m;
            arr[i] -= m;
            arr[i+1] -= m;
            arr[i+2] -= m;

            // 2묶음 처리
            int n = Math.min(arr[i], arr[i+1]);
            ans += 5L*n;
            arr[i] -= n;
            arr[i+1] -= n;

            // 1묶음 처리
            ans += 3L * arr[i];
            arr[i] = 0;
        }

        for(; i<N; i++){
            if(arr[i] == 0){
                continue;
            }

            // 버그 : 길이 3일때 i+1이 3됨

            if(i+1 >= N){
                ans += 3L*arr[i];
                arr[i] = 0;
                break;
            }

            if(arr[i+1] == 0){
                ans += 3L*arr[i];
                arr[i] = 0;
                continue;
            }

            // 2묶음 처리
            int n = Math.min(arr[i], arr[i+1]);
            ans += 5L*n;
            arr[i] -= n;
            arr[i+1] -= n;

            // 1묶음 처리
            if(arr[i] > 0){
                ans += 3L * arr[i];
                arr[i] = 0;
            }
        }

        System.out.println(ans);

    }
}
