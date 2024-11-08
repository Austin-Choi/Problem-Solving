import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static int[] arr;
    private static int[] lis;
    private static int binarySearch(int left, int right, int target){
        int mid;
        while(left < right){
            mid = (left + right) / 2;
            if(lis[mid] < target){
                left = mid + 1;
            }
            else{
                right = mid;
            }
        }
        return right;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        arr = new int[N];
        lis = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int j = 0;
        lis[0] = arr[0];
        int i = 1;

        // arr의 두번째부터 마지막까지 하나씩 lis와 비교하면서 넣어줌
        while(i < N){
            // lis 배열의 맨 뒤 값보다 arr[i] 가 더 크면 극ㄹ lis 배열 맨 뒤에 넣어줌
            if(lis[j] < arr[i]){
                lis[j+1] = arr[i];
                j += 1;
            }
            // arr[i] 값이 더 작으면, arr[i]의 값이 lis 배열 중 어느 곳에 들어올지 이분탐색함
            else{
                //0부터 j까지 탐색하면서 arr[i]가 들어갈 수 있는 위치를 찾아서 idx에 반환
                int idx = binarySearch(0, j, arr[i]);
                lis[idx] = arr[i];
            }
            i += 1;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(j+1).append("\n");
//        for(int jj = 0; jj <= j; jj++){
//            sb.append(lis[jj]).append(" ");
//        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
