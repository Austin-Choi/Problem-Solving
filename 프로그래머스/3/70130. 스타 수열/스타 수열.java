/*
a의 최대 길이가 50만이라 O(N)에 처리해야함 최소한
한번에 읽으면서 a의 모든 수는 a의 길이 미만이므로 
prev[x] = 이전 등장위치
pair[x] = x를 공통요소로 놓고 만들수 있는 쌍 갯수
state[x] = 지금까지 x를 처리했을때 아직 짝을 만들지 않고 남아있는 원소가 뭐인지 상태 3가지 저장
*/
import java.util.*;

class Solution {
    public int solution(int[] a) {
        int N = a.length;
        if (N < 2)
            return 0;

        int[] prev = new int[N];
        int[] pair = new int[N];

        // 0 = 아무것도 없음
        // 1 = 짝을 못 이룬 x
        // 2 = 짝을 못 이룬 x가 아닌 값
        int[] state = new int[N];

        Arrays.fill(prev, -1);

        for (int i = 0; i < N; i++) {
            int x = a[i];
            int p = prev[x];

            // 처음 등장한 x
            if (p == -1) {
                // x 앞에 x가 아닌 값이 하나라도 있다면
                // 그 중 하나를 짝이 안 된 상태로 생각
                if (i > 0) {
                    pair[x]++;
                    state[x] = 0;
                } else {
                    state[x] = 1;
                }

                prev[x] = i;
                continue;
            }

            int gap = i - p - 1;

            // 이전 x와 현재 x 사이의 x가 아닌 값들을 처리
            if (gap > 0) {
                if (state[x] == 1) {
                    // 남아있던 x와 가운데의 x가 아닌 값 하나를 매칭
                    pair[x]++;
                    state[x] = 0;

                    // x가 아닌 값이 2개 이상이면 하나가 남음
                    if (gap >= 2)
                        state[x] = 2;
                }
                else if (state[x] == 0 || state[x] == 2) {
                    // x가 아닌 값 하나를 남김
                    state[x] = 2;
                }
            }

            // 현재 x 처리
            if (state[x] == 2) {
                // 남아있던 x가 아닌 값 + 현재 x
                pair[x]++;
                state[x] = 0;
            }
            else if (state[x] == 0) {
                // 현재 x를 짝이 안 된 상태로 남김
                state[x] = 1;
            }
            // state == 1이면 x + x이므로 아무것도 못 함

            prev[x] = i;
        }

        // 마지막 x 뒤에 x가 아닌 값이 있는 경우
        for (int x = 0; x < N; x++) {
            if (prev[x] == -1)
                continue;

            int gap = N - 1 - prev[x];

            if (gap > 0 && state[x] == 1)
                pair[x]++;
        }

        int ans = 0;
        // 최대 길이는 pair 수 * 2
        for (int x = 0; x < N; x++)
            ans = Math.max(ans, pair[x] * 2);

        return ans;
    }
}