/*
감소 구간 ( 뒤집힌 구간)을 찾아서 s,e로 설정
그 구간을 뒤집고
1~N 수열을 만들기 위해 필요한 만큼 밀기 작업 수행
+
비정상 인덱스를 visited로 마킹하고
그걸 기반으로 뒤집을 구간의 시작과 끝을 탐색한 뒤,
구간이 split된 경우를 위해 회전을 먼저 수행하고
뒤집고 다시 1이 맨 앞으로 오도록 회전해서 정답을 만듬
 */

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int[] pushRight(int[] arr, int k, int N) {
        int[] temp = new int[N];
        for(int i=0; i<N; i++) {
            int next = (i + k) % N;
            temp[next] = arr[i];
        }
        return temp;
    }

    static boolean[] pushRight(boolean[] arr, int k, int N) {
        boolean[] temp = new boolean[N];
        for(int i=0; i<N; i++) {
            int next = (i + k) % N;
            temp[next] = arr[i];
        }
        return temp;
    }

    static void reverse(int[] arr, int start, int end) {
        int[] temp = arr.clone();

        int idx = end;
        for(int i=start; i<=end; i++) {
            arr[i] = temp[idx];
            idx--;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 이 수열이 순서가 끊긴 곳인지 확인
        // 양 옆 숫자가 연속인지 아닌지 판단
        // -> 끊긴 곳이면 flag[i] = true, cnt++로 끊긴 지점 갯수 기록
        boolean[] flag = new boolean[N];
        int cnt = 0;
        for(int i=0; i<N; i++) {
            int prev = i - 1 == -1 ? N-1 : i-1;
            int next = (i + 1) % N;
            if(arr[i] + 1 == arr[next]) continue;
            if(arr[i] == N && arr[next] == 1) continue;
            if(arr[prev] + 1 == arr[i]) continue;
            if(arr[prev] == N && arr[i] == 1) continue;

            cnt++;
            flag[i] = true;
        }

        // 가능한 모든 k에 대해 수열을 오른쪽으로 회전
        for(int k=1; k<N; k++) {
            int[] arrResult = pushRight(arr, k, N);
            boolean[] flagResult = pushRight(flag, k, N);

            // 연속된 true 구간 찾기
            // -> 끊긴 부분의 시작 지점을 찾음
            int start = 0;
            for(int i=0; i<N; i++) {
                if(flagResult[i]) {
                    start = i;
                    break;
                }
            }

            // 이 구간이 실제로 연속된 cnt 개의 끊긴 부분인지 확인
            boolean check = true;
            for(int i=start; i<start+cnt; i++) {
                if(i >= N) {
                    check = false;
                    break;
                }
                if(!flagResult[i]) {
                    check = false;
                    break;
                }
            }

            // p < q가 아닌경우
            if(!check) continue;


            // 조건을 만족하는 경우 그 구간을 뒤집고
            reverse(arrResult, start, start + cnt - 1);

            // 이 수열을 [1~N] 순서로 만들기 위해
            // N이 마지막에 오도록 또 한번 밀기
            int firstPushCnt = 0;
            for(int i=N-1; i>=0; i--) {
                if(arrResult[i] == N) {
                    firstPushCnt = N - 1 - i;
                }
            }

            if(firstPushCnt == 0) continue;


            sb.append(firstPushCnt).append("\n");
            sb.append((start + 1) + " " + (start + cnt)).append("\n");
            sb.append(k);
            bw.write(sb.toString());;
            bw.flush();
            return;
        }
    }
}