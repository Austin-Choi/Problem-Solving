import java.util.*;
import java.io.*;
/*
그리드 n,m 크기 봐서 
가능한 가장 큰 롬버스 크기 리밋 구해놓고
for[가장큰롬버스+1][n][m]
해서 완탐하기
dp로는 못하는게 롬버스1이랑 2랑 구성요소가 확장되는게 아니라서 의미가 없고
m,n 커봐야 50이라 크게잡아도 50 * 50 * 50임

롬버스구하기
1-> 칸하나
2-> 꼭짓점 4개만 있음
꼭지점 4개 먼저 구하고 크기-2한만큼 di,dj 정의해놓은거 따라가면서 더하기
중간에 unvalid 한 값 들어오면 -1 반환하고 끝내기

서로다른 3개 내림차순
그냥 다 list에 넣고 sort 할까!
큐에 넣고 크기 3되면 peek해서 빼고 뭐 그것도 되긴할텐데 중복관리가 안될듯
*/

class Solution {
    // 위 아래 점 특정하고 거기서부터 l-2만큼 이동
    int[] di = {1,1,-1,-1};
    int[] dj = {-1,1,-1,1};
    int[][] g;
    int N;
    int M;

    // size는 1부터 들어옴(size = 대각선 칸 수)
    // a
    //b c
    // d
    int getRombusSum(int si, int sj, int size){
        int rst = g[si][sj];
        int[] a = new int[]{si,sj};
        int[] b,c,d;

        // b
        int ni = si + di[0]*(size-1);
        int nj = sj + dj[0]*(size-1);
        if(ni < 0 || nj < 0 || ni >= N || nj >= M)
            return -1;
        else{
            b = new int[]{ni,nj};
            rst += g[ni][nj];
        }

        ni = si + di[1]*(size-1);
        nj = sj + dj[1]*(size-1);
        if(ni < 0 || nj < 0 || ni >= N || nj >= M)
            return -1;
        else{
            c = new int[]{ni,nj};
            rst += g[ni][nj];
        }

        ni = si + 2*(size-1); 
        nj = sj;
        if(ni < 0 || nj < 0 || ni >= N || nj >= M)
            return -1;
        else{
            d = new int[]{ni,nj};
            rst += g[ni][nj];
        }

        for(int s = 1; s<=size-2; s++){
            ni = a[0] + s*di[0];
            nj = a[1] + s*dj[0];
            if(ni < 0 || nj < 0 || ni >= N || nj >= M)
                return -1;
            rst += g[ni][nj];
        }

        for(int s = 1; s<=size-2; s++){
            ni = a[0] + s*di[1];
            nj = a[1] + s*dj[1];
            if(ni < 0 || nj < 0 || ni >= N || nj >= M)
                return -1;
            rst += g[ni][nj];
        }

        for(int s = 1; s<=size-2; s++){
            ni = d[0] + s*di[2];
            nj = d[1] + s*dj[2];
            if(ni < 0 || nj < 0 || ni >= N || nj >= M)
                return -1;
            rst += g[ni][nj];
        }

        for(int s = 1; s<=size-2; s++){
            ni = d[0] + s*di[3];
            nj = d[1] + s*dj[3];
            if(ni < 0 || nj < 0 || ni >= N || nj >= M)
                return -1;
            rst += g[ni][nj];
        }
        return rst;
    }

    public int[] getBiggestThree(int[][] grid) {
        N = grid.length;
        M = grid[0].length;
        int limit =(int) Math.sqrt(N*N + M*M)+1;
        g = grid;

        ArrayList<Integer> ansLi = new ArrayList<>();

        for(int l = 1; l<=limit; l++){
            for(int i = 0; i<N; i++){
                for(int j = 0; j<M; j++){
                    if(l == 1)
                        ansLi.add(grid[i][j]);
                    else{
                        int a = getRombusSum(i,j,l);
                        if(a != -1)
                            ansLi.add(a);
                    }
                }
            }
        }

        Set<Integer> ss = new HashSet<>(ansLi);
        List<Integer> aaa = new ArrayList<>(ss);
        Collections.sort(aaa, Collections.reverseOrder());

        int[] rst = new int[Math.min(aaa.size(), 3)];
        for(int i = 0; i<Math.min(aaa.size(), 3); i++)
            rst[i] = aaa.get(i);
        return rst;
    }
}