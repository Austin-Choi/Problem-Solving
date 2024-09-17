import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Item{
    int w;
    int v;

    public Item(int w, int v){
        this.w = w;
        this.v = v;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        ArrayList<Item> items = new ArrayList<>();
        // 점화식에서 i-1로 접근해야하는데 헷갈려서 dummy data 넣음
        items.add(new Item(-1,-1));
        for (int nn = 0; nn < N; nn++) {
            st = new StringTokenizer(br.readLine());
            int tempW = Integer.parseInt(st.nextToken());
            int tempV = Integer.parseInt(st.nextToken());
            items.add(new Item(tempW, tempV));
        }

        // N번째 물건을 넣었을 때(i), 총 무게(j)가 w이면 해당 상태의 가치합의 최대 값
        // n번째 물건을 넣거나 안 넣거나 의 문제
        // 넣으면 안 넣은 결과와 넣은 결과를 비교해서 최대 값을 저장하면 되고
        // 안 넣으면 이전 값 그대로 유지함
        // -> 즉, 각 물건을 순차적으로 고려하면서 최대 허용 무게에 따른 최대 가치합을 저장
        int[][] dp = new int[N+1][K + 1];
        dp[0][0] = 0; // 아무것도 선택하지 않은 상태인 i=0, 총 무게는 0이니까 j=0, 가치합도 0으로 초기화

        for(int i = 1; i<=N; i++){
            for(int j = 1; j<=K; j++){
                // 만약 가방에 자리가 남았으면
                if(j - items.get(i).w >= 0){
                    // 전의 가치 최대 합과 i번째 물건을 넣었을 때 가치합을 비교해서 둘 중 큰 값을 저장
                    // i번째 물건을 넣으면 배낭에 남는 공간은 j - items.get(i).w 임
                    // 이전 물건들 중에서 무게가 w-items.get(i).w 인 배낭을 채울 수 있는 최대 가치 => dp[i-1][j-items.get(i).w]
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-items.get(i).w] + items.get(i).v);
                }
                // 가방에 자리가 없으면 기존 값을 계속 유지
                else
                    dp[i][j] = dp[i-1][j];

            }
        }
        // dp[N][K] = 모든 물건을 고려하고, 배낭의 최대 허용 무게일 때
        // 가치합의 최대 값
        bw.write(dp[N][K] +"\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
