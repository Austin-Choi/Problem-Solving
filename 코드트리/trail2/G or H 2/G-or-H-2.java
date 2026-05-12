import java.util.*;
import java.io.*;

/*
사진 크기 = j-i;
어차피 A가 위치상 정렬이니까 
그냥 누적합 2개 써서 써먹기???????

*/

public class Main {
    static int N;
    // 위치 저장
    static int[] pos;
    // 해당 위치에 각 알파벳 몇개 있는지
    static int[] g = new int[101];
    static int[] h = new int[101]; 

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        pos = new int[N];
        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            pos[i] = idx;
            char c = st.nextToken().charAt(0);
            if(c == 'G')
                g[idx]++;
            else
                h[idx]++;
        }

        Arrays.sort(pos);
        // 위치 종류 갯수만큼 해야함 + 1
        int[] psG = new int[N+1];
        int[] psH = new int[N+1];

        for(int i = 0; i<N; i++){
            psG[i+1] = psG[i] + g[pos[i]];
            psH[i+1] = psH[i] + h[pos[i]];
        }

        int ans = 0;
        for(int i = 0; i<N-1; i++){
            for(int j= i; j<N; j++){
                int countG = psG[j+1] - psG[i];
                int countH = psH[j+1] - psH[i];
                if((countH == 0 && countG != 0) || (countH != 0 && countG == 0) || (countH == countG && countH != 0))
                    ans = Math.max(ans, pos[j]-pos[i]);   
            }
        }
        System.out.print(ans);
    }
}