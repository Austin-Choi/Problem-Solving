import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int c = Integer.parseInt(br.readLine());
        int[] m = new int[c];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < c; i++) {
            m[i] = Integer.parseInt(st.nextToken());
        }

        // 각 레벨에서의 번호 저장
        int[] levels = new int[1000001];
        // 출력 결과 저장
        int[] result = new int[c];

        // 첫 번째 항목의 레벨이 1이 아닌 경우 잘못된 구조
        if (m[0] != 1) {
            bw.write("-1\n");
            bw.flush();
            return;
        }

        // 첫 항목은 1로 시작
        levels[1] = 1;
        result[0] = 1;

        for (int i = 1; i < c; i++) {
            int currentLevel = m[i];
            int previousLevel = m[i - 1];

            // 레벨 차이가 1보다 크면 잘못된 보고서
            if (currentLevel > previousLevel + 1) {
                bw.write("-1\n");
                bw.flush();
                return;
            }

            // 이전 레벨보다 더 높은 레벨인 경우
            if (currentLevel > previousLevel) {
                // 새로운 레벨 시작이므로 1부터 시작
                levels[currentLevel] = 1;
            } else if (currentLevel == previousLevel) {
                // 같은 레벨인 경우 번호를 증가시킴
                levels[currentLevel]++;
            } else {
                // 이전보다 작은 레벨로 돌아간 경우, 해당 레벨의 번호를 1 증가
                levels[currentLevel]++;
            }

            result[i] = levels[currentLevel];
        }

        // 결과 출력
        for (int i = 0; i < c; i++) {
            bw.write(result[i] + " ");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}