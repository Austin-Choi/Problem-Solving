import java.util.*;
import java.io.*;

/*
어떻게 모든 종류가 있는지 확인?
-> in, out 갱신할때 종류 갯수 처음 초기화해주고 l,r 따라가면서 바꾸기

조건 만족하면 구간 최소화해야하니까 l++하면서
in--, out++
이외에는
out--, in++
-> in, out으로 하면 단조성 관리 안됨
-> in만 관리하면서 out = tot - in으로 
*/
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] A = new int[N];
        int[] tot = new int[M + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            tot[A[i]]++;
        }

        int[] in = new int[M + 1];
        // 구간 안에 존재하는 종류 수
        int inCnt = 0;   
        // 전체 등장 횟수가 모두 구간 안에 있는 종류 수 
        int fullCnt = 0;  

        int l = 0;
        int ans = N + 1;

        for (int r = 0; r < N; r++) {
            int x = A[r];

            if (in[x] == 0)
                inCnt++;

            in[x]++;

            if (in[x] == tot[x])
                fullCnt++;


            // 밖에 없는 숫자가 생기는 동안 왼쪽 이동
            while (fullCnt > 0) {
                int y = A[l++];

                if (in[y] == tot[y])
                    fullCnt--;

                in[y]--;

                if (in[y] == 0)
                    inCnt--;
            }


            // 현재 구간이 조건 만족
            while (inCnt == M && fullCnt == 0) {
                ans = Math.min(ans, r - l + 1);

                int y = A[l++];

                if (in[y] == tot[y])
                    fullCnt--;

                in[y]--;

                if (in[y] == 0)
                    inCnt--;
            }
        }

        System.out.print(ans == N + 1 ? -1 : ans);
    }
}