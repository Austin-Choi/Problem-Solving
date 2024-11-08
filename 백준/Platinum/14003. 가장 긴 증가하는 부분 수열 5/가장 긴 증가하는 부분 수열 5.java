import java.io.*;
import java.util.*;

public class Main {
    private static int[] arr;
    private static int[] lis;        // 현재 LIS 값들을 저장
    private static int[] position;   // 각 위치에 실제 인덱스를 기록하여 나중에 역추적
    private static int[] parent;     // 역추적용 부모 배열

    private static int binarySearch(int left, int right, int target) {
        int mid;
        while (left < right) {
            mid = (left + right) / 2;
            if (lis[mid] < target) {
                left = mid + 1;
            } else {
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
        position = new int[N];
        parent = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int length = 0;
        // lis 배열는 arr의 첫번째 원소로 초기화함
        lis[0] = arr[0];
        position[0] = 0;
        parent[0] = -1;

        for (int i = 1; i < N; i++) {
            if (lis[length] < arr[i]) {
                // arr[i]가 LIS의 마지막 원소보다 크면 끝에 추가
                parent[i] = position[length];
                lis[++length] = arr[i];
                position[length] = i;
            } else {
                // arr[i]가 LIS 내부에 들어갈 위치를 이분 탐색으로 찾음
                int idx = binarySearch(0, length, arr[i]);
                lis[idx] = arr[i];
                position[idx] = i;
                parent[i] = (idx > 0) ? position[idx - 1] : -1;
            }
        }

        // LIS의 길이 출력
        bw.write((length + 1) + "\n");

        // 역추적하여 LIS 배열을 구성
        Stack<Integer> stack = new Stack<>();
        int k = position[length];
        // 첫번째 원소를 만나기 전까지 스택에 넣음
        while (k != -1) {
            stack.push(arr[k]);
            k = parent[k];
        }
        // 스택을 사용하여 역순으로 출력
        while (!stack.isEmpty()) {
            bw.write(stack.pop() + " ");
        }
        
        bw.flush();
        bw.close();
        br.close();
    }
}
