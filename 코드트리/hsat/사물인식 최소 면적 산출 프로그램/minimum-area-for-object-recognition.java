/*
점이 최대 100개고 2개씩 묶어서 직사각형 만들어서 단순 비교하는건 좀? 그래 
-> 기본 골조는 이걸로 잡지만 같은 pos에 다른 점 여러개 겹쳐있는거랑 
직사각형 영역 잡는거랑 안에 K개의 색깔을 다 가지는지 봐야함

겹치는 점끼리 놔서 둘 다 같은 곳에 있어도 0짜리 정사각형이며 이런 경우도 카운팅해야함.
이런거 있으면 바로 0 출력하고 끝
-> 넓이가 0인 직사각형은 수직, 수평 선에 모든 점이 있을때도 넓이가 0임.

x축을 고정하고 y축을 투포인터로 탐색하기 
1) pos를 y좌표 오름차순으로 정렬
2) 두 점을 잡고 maxI, maxI 를 구하기
3) 투포인터로 모든 색이 들어가는 최소 y높이 구하기

dfs로도 풀 수 있는데 이건 O(N/K)^K 이라 
테스트 데이터가 빡빡하면 걸러짐.
*/
import java.io.*;
import java.util.*;
public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static int N,K;
    static int[][] pos;

    public static void main(String[] args) throws IOException{
        N = read();
        K = read();
        
        pos = new int[N][3];

        int offset = 1000;
        for(int idx = 0; idx<N; idx++){
            int i = read() + offset;
            int j = read() + offset;
            int k = read();
            pos[idx] = new int[]{i,j,k};
        }

        Arrays.sort(pos, Comparator.comparingInt(a->a[1]));

        int ans = 4_000_001;
        for(int i = 0; i<N; i++){
            for(int j = i; j<N; j++){
                int minI = Math.min(pos[i][0], pos[j][0]);
                int maxI = Math.max(pos[i][0], pos[j][0]);

                // minI, maxI 인 pos 점 추출
                ArrayList<int[]> li = new ArrayList<>();
                for(int idx = 0; idx<N; idx++){
                    int ci = pos[idx][0];
                    if(ci >= minI && ci <= maxI)
                        li.add(pos[idx]);
                }

                //투포인터 x축을 고정한 두 점을 잡고 O(N*N)
                // y축 기준으로 오름차순 정렬되어있으니 
                // 모든 색을 포함하는 영역을 투포인터로 줄여나감 O(N)
                int l = 0;
                int[] count = new int[K+1];
                int colorCnt = 0;
                for(int r = 0; r<li.size(); r++){
                    int color = li.get(r)[2];

                    if(count[color]++ == 0)
                        colorCnt++;
                    
                    while(colorCnt == K){
                        int h = li.get(r)[1] - li.get(l)[1];
                        int w = maxI - minI;

                        ans = Math.min(ans, h*w);

                        if(--count[li.get(l)[2]] == 0)
                            colorCnt--;
                        
                        l++;
                    }
                }
            }
        }
        System.out.print(ans);
    }
}