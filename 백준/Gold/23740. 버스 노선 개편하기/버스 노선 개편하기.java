import java.util.*;
import java.io.*;
/*
출발점 기준으로 입력 정렬하면
겹치는 조건은 다음 시작이 현재 끝 이하인지 보는것
만약 겹치지 않는다면 출발점 기준으로 정렬했기 때문에
다다음 시작은 현재끝 이하일 수가 없기 때문에
O(N)이 가능한 스위핑이 성립함
-> 시작점 단조 증가이기 때문에 겹침 여부가 앞->뒤 한 방향으로만 결정

시복 -> 정렬(TimSort(N log N) + 스위핑(O(N))
= O(N log N)
 */

public class Main {
    static int N;
    static int[][] input;

    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        input = new int[N][3];
        for(int i = 0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            input[i] = new int[]{s,e,c};
        }

        Arrays.sort(input, Comparator.comparingInt(a->a[0]));
        ArrayList<int[]> rst = new ArrayList<>();
        int cs = input[0][0];
        int ce = input[0][1];
        int cc = input[0][2];

        for(int i = 1; i<N; i++){
            int ns = input[i][0];
            int ne = input[i][1];
            int nc = input[i][2];

            if(ns <= ce){
                ce = Math.max(ce, ne);
                cc = Math.min(cc, nc);
            }
            else {
                rst.add(new int[]{cs, ce, cc});
                cs = ns;
                ce = ne;
                cc = nc;
            }
        }

        //막구간 추가
        rst.add(new int[]{cs,ce,cc});

        StringBuilder sb = new StringBuilder();
        sb.append(rst.size()).append("\n");
        for(int[] r : rst){
            sb.append(r[0]).append(" ").append(r[1]).append(" ").append(r[2]).append("\n");
        }
        System.out.print(sb);
    }
}
