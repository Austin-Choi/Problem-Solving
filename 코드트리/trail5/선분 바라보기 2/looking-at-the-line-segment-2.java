import java.util.*;
import java.io.*;

/*
모든 직선은 x축에 대해 평행함
y축 오름차순 기준인 treeSet 활용해서 활성화된 부분만 유지하면서 이벤트 시간순으로 순회
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    public static void main(String[] args) throws IOException{
        int N = read();
        // y, 이벤트 시각, 델타값, 색깔
        int[][] E = new int[2*N][4];
        // 삭제를 위해 저장
        int[][] line = new int[N + 1][2];

        for(int i = 0; i<N; i++){
            int y = read();
            int x1 = read();
            int x2 = read();

            line[i + 1] = new int[]{y, i + 1};
            E[2*i] = new int[]{y, x1, 1, i+1};
            E[2*i+1] = new int[]{y, x2, -1, i+1};
        }

        Arrays.sort(E, Comparator.comparingInt(a->a[1]));
        HashSet<Integer> ans = new HashSet<>();
        // y, color
        TreeSet<int[]> ts = new TreeSet<>((a,b)->{
            if(a[0] != b[0])
                return a[0] - b[0];
            return a[1] - b[1];
        });
        
        // 문제에서는 중복 값 없지만 있다고 가정하면 idx++해줘야함
        int idx = 0;
        while(idx < 2*N){
            int x = E[idx][1];

            // 같은 x에서 끝나는 이벤트
            while (idx < 2 * N && E[idx][1] == x && E[idx][2] == -1) {
                ts.remove(line[E[idx][3]]);
                idx++;
            }

            // 같은 x에서 시작하는 이벤트
            while (idx < 2 * N && E[idx][1] == x && E[idx][2] == 1) {
                ts.add(line[E[idx][3]]);
                idx++;
            }

            if (!ts.isEmpty()) {
                ans.add(ts.first()[1]);
            }
        }
        System.out.print(ans.size());
    }
}