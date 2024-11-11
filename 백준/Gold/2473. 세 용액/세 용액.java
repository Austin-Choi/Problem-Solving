import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static long curSum = 0L;
    private static long minSum = Long.MAX_VALUE;
    private static long[] answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] arr = new long[N];
        answer = new long[3];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++){
            arr[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(arr);
        for(int idx = 0; idx < N-2; idx++){
            int left = idx+1;
            int right = arr.length -1;
            while(left < right){
                curSum = arr[idx] + arr[left] + arr[right];

                // 세수의 특성값이 0에 가장 가깞다는건 절대값이 가장 작거나 0이라는 것
                if(Math.abs(curSum) < Math.abs(minSum)){
                    minSum = curSum;
                    answer[0] = arr[idx];
                    answer[1] = arr[left];
                    answer[2] = arr[right];
                }

                // 1번 값을 제외하고 나머지 두 값에서 
                // 현재 값이 0 보다 작다면 0이랑 가까워지려면 
                // 둘중 작은 수를 키워야함 -> left + 1
                if(curSum < 0)
                    left++;
                // 1번 값을 제외하고 나머지 두 값에서
                // 현재 값이 0보다 크다면 0이랑 가까워지려면
                // 둘중 큰 수를 줄여야 함 -> right - 1
                else if(curSum > 0)
                    right--;
                // curSum = 0이면 걍 출력하면 됨 
                else
                    break;
            }
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        for(long l : answer){
            sb.append(l).append(" ");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
