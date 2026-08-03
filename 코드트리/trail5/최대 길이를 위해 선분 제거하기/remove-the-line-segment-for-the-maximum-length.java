import java.util.*;
import java.io.*;

/*
수평선에 평행한 N개의 선분의 그어진 영역의 길이가 최대가 되려면
다른 선분들과 가장 많이 겹치는 선분을 찾아서 빼야함?

-> 일단 N log N으로 이벤트 시각 정렬을 하고
다음 이벤트 발생 까지가 len이고 이때 cnt가 1이면 활성화된 선분 1개임
이때 현재 선분 idx에 합산해서 저장함

cnt의 의미 
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    public static void main(String[] args) throws IOException{
        int N = read();
        // etime, delta, idx
        int[][] E = new int[2*N][3];
        for(int i = 0; i<N; i++){
            int a = read();
            int b = read();

            E[2*i] = new int[]{a,1,i};
            E[2*i+1] = new int[]{b,-1,i};
        }

        Arrays.sort(E, (a,b)->{
            if(a[0]!=b[0])
                return a[0] - b[0];
            return b[1] - a[1];
        });

        int cnt = 0;
        int[] lens = new int[N];
        int total = 0;

        // cnt = 1 활성화 된 선분 자기 하나
        // cnt > 0 선분 활성화되어 있음 len 전부 더하면 합집합 길이 됨.
        // -> cnt로만으로는 어떤 선분에 이어야할지 불명확함

        TreeSet<Integer> ts = new TreeSet<>();
        for(int i = 0; i<2*N-1; i++){
            int len = E[i+1][0] - E[i][0];
            cnt += E[i][1];

            // 시작이벤트는 활성화된 선분으로 등록
            if(E[i][1] == 1)
                ts.add(E[i][2]);
            // 끝이벤트는 활성화된 선분에서 빼기
            else
                ts.remove(E[i][2]);

            if(cnt != 0)
                total += len;
            if(cnt == 1){
                int idx = ts.iterator().next();
                lens[idx] += len;
            }
                
        }

        int ans = 0;
        for(int i = 0; i<N; i++){
            ans = Math.max(ans, total - lens[i]);
        }
        System.out.print(ans);
    }
}