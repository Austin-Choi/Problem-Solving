/*
그리드 n,m 크기 봐서 
가능한 가장 큰 롬버스 크기 리밋 구해놓고
for[가장큰롬버스+1][n][m]
해서 완탐하기
-> 누적합으로 더 빠르게 가능

롬버스구하기
1-> 칸하나
2-> 꼭짓점 4개만 있음
꼭지점 4개 먼저 구하고 크기-2한만큼 di,dj 정의해놓은거 따라가면서 더하기
중간에 unvalid 한 값 들어오면 -1 반환하고 끝내기

누적합으로 O(1)에 구하기 -> / \ 방향으로 2개
limit 동적으로 구하기
*/
import java.util.*;

class Solution {
    int[][] g;
    int N, M;

    int[][] d1; // \ 방향
    int[][] d2; // / 방향

    int getRombusSum(int si, int sj, int k){
        // si,sj = top
        int ux = si;
        int uy = sj;

        int dx = si + k;
        int dy = sj;

        int lx = (si + dx) / 2;
        int ly = sj - (dx - si) / 2;

        int rx = (si + dx) / 2;
        int ry = sj + (dx - si) / 2;

        if(ly < 0 || ry >= M || dx >= N) return -1;

        int sum =
            (d2[lx + 1][ly + 1] - d2[ux][uy + 2]) +
            (d1[rx + 1][ry + 1] - d1[ux][uy]) +
            (d1[dx + 1][dy + 1] - d1[lx][ly]) +
            (d2[dx + 1][dy + 1] - d2[rx][ry + 2]) -
            (g[ux][uy] + g[dx][dy] + g[lx][ly] + g[rx][ry]);

        return sum;
    }

    public int[] getBiggestThree(int[][] grid) {
        g = grid;
        N = grid.length;
        M = grid[0].length;

        d1 = new int[N + 1][M + 2];
        d2 = new int[N + 1][M + 2];

        // \ 방향 prefix
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= M; j++){
                d1[i][j] = d1[i-1][j-1] + g[i-1][j-1];
                d2[i][j] = d2[i-1][j+1] + g[i-1][j-1];
            }
        }

        Set<Integer> set = new HashSet<>();

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){

                // size 0
                set.add(g[i][j]);

                for(int k = 2; ; k += 2){ // 반드시 2씩 증가
                    int sum = getRombusSum(i, j, k);
                    if(sum == -1) break;
                    set.add(sum);
                }
            }
        }

        List<Integer> list = new ArrayList<>(set);
        Collections.sort(list, Collections.reverseOrder());

        int[] rst = new int[Math.min(3, list.size())];
        for(int i = 0; i < rst.length; i++)
            rst[i] = list.get(i);

        return rst;
    }
}