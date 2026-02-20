/*
크레인을 최대한 활용하면서
자기가 들수 있는 최대값에 가까운 값 하나를 처리함
c, box 둘다 정렬하고

매분마다 가능한 최대한 많은 무거운 박스를 제거
 */
import java.util.*;
import java.io.*;
public class Main {
    static int N;
    static int[] c;
    static int M;
    static int[] box;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        c = new int[N];
        for(int i = 0; i<N; i++)
            c[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(c);

        M = Integer.parseInt(br.readLine());
        box = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<M; i++)
            box[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(box);

        // 가장 힘쎈크레인이 박스 제일 무거운거 못 들면 다 못옮기니까 -1
        if(box[M-1] > c[N-1]){
            System.out.print(-1);
            return;
        }

        long ans = 0;
        // 무거운 박스부터 처리해야
        // 약한 크레인까지 최대한 많이 일할 수 있음
        boolean[] moved = new boolean[M];
        int cnt = 0;

        while(cnt < M){
            int idx = M-1;
            for(int i = N-1; i>=0; i--){
                while(idx >= 0){
                    if(!moved[idx] && c[i] >= box[idx]){
                        moved[idx] = true;
                        cnt++;
                        // 다음 박스로
                        idx--;
                        break;
                    }
                    // 다음 박스로
                    idx--;
                }
            }
            ans++;
        }
        System.out.print(ans);
    }
}
