/*
금지된 단어가 주어짐.
금지단어는 모든 블럭을 사용해서 만드는 거임
-> 금지단어 알파벳 단위로 잘라서 정렬하기

반례 = BCAA

금지단어 하나씩 보면서 그거보다 앞에 올수 있는 알파벳들 보면서
ans += 자릿수! / (쓸수 있는 알파벳 각자 갯수 ! *= )
-> 조합 기반 사전순 순열 랭크 매기기
 */
import java.util.*;
import java.io.*;
public class Main {
    static int T;
    static long[] fac = new long[21];
    static void init(){
        fac[1] = 1;
        fac[0] = 1;
        for(int i = 2; i<=20; i++){
            fac[i] = fac[i-1]*i;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        init();

        StringBuilder sb = new StringBuilder();
        while(T-->0) {
            char[] in = br.readLine().toCharArray();
            int n = in.length;
            int[] cnt = new int[26];
            for (int i = 0; i < in.length; i++) {
                cnt[in[i] - 'A']++;
            }
            long ans = 0;

            for (int i = 0; i < n; i++) {
                int cur = in[i] - 'A';

                //현재 문자보다 작은 문자들 검사
                for (int j = 0; j < cur; j++) {
                    if (cnt[j] == 0)
                        continue;

                    cnt[j]--;
                    long ff = fac[n - i - 1];
                    for (int k = 0; k < 26; k++) {
                        if (cnt[k] > 1)
                            ff /= fac[cnt[k]];
                    }
                    ans += ff;
                    // 문자 사용 복구
                    cnt[j]++;
                }
                // 현재 문자 사용함
                cnt[cur]--;
            }
            sb.append(ans).append("\n");
        }
        System.out.print(sb);
    }
}