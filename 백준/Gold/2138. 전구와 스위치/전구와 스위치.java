
/*
전구가 i-1, i, i+1 전구들이 i를 눌렀을때 바뀜
i+1을 누르면 i-1은 조작할 수 없지만 i랑 i+1만 조작할 수 있게됨
그러면
i-1의 기댓값과 입력값이 다르면 누르고 아니면 안누름
i는 2부터 시작하는데 그럼
맨앞은 킨거 안킨거 나눠서 cnt 세면됨
킨거 안킨거 중에 최소값으로 출력
 */

import java.util.*;
import java.io.*;
public class Main {
    static void toggle(char[] arr, int idx) {
        for (int i = idx - 1; i <= idx + 1; i++) {
            if (i >= 0 && i < arr.length) {
                arr[i] = (arr[i] == '0') ? '1' : '0';
            }
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        char[] input = br.readLine().toCharArray();
        char[] target = br.readLine().toCharArray();

        // Case 1: 안 누름
        char[] case1 = input.clone();
        int cnt1 = 0;
        for (int i = 1; i < N; i++) {
            if (case1[i - 1] != target[i - 1]) {
                toggle(case1, i);
                cnt1++;
            }
        }

        // Case 2: 처음 누름
        char[] case2 = input.clone();
        int cnt2 = 1;
        toggle(case2, 0);
        for (int i = 1; i < N; i++) {
            if (case2[i - 1] != target[i - 1]) {
                toggle(case2, i);
                cnt2++;
            }
        }

        int answer = Integer.MAX_VALUE;
        if (Arrays.equals(case1, target)) answer = Math.min(answer, cnt1);
        if (Arrays.equals(case2, target)) answer = Math.min(answer, cnt2);
        
        bw.write(String.valueOf(answer == Integer.MAX_VALUE ? -1 : answer));
        bw.flush();
        bw.close();
        br.close();
    }
}