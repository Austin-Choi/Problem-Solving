import java.util.*;
import java.io.*;

/*
사분면 만드는데 가장 많은 영역에 위차한 점 갯수를 최소화하려면
-> 최대한 균등하게 나눠야한다는 것임.

x 오름차순 정렬하고 y좌표 압축
펜윅트리 써서 rightBIT에 삽입하고
x를 스위핑하면서 leftBIT, rightBIT 갱신
모든 y 후보에 대해 최대 사분면 크기를 계산
-> 모든 y후보에 대해 계산해서 O (N^2 log N)
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    // 값 하나씩 수적하면서 prefix 빠르게 구하기
    // 현재 x=a의 왼쪽 점과 오른쪽 점을 동시에 알아야함
    static class Fenwick{
        int[] tree;

        Fenwick(int n){
            tree =new int[n+2];
        }

        void add(int idx, int val){
            while(idx < tree.length){
                tree[idx] += val;
                idx += idx & -idx;
            }
        }

        int sum(int idx){
            int rst = 0;
            while(idx>0){
                rst += tree[idx];
                idx -= idx & -idx;
            }
            return rst;
        }
    }

    public static void main(String[] args) throws IOException{
        int N = read();

        int[][] p = new int[N][2];
        int[] ys = new int[N];

        for(int i = 0; i<N; i++){
            p[i][0] = read();
            p[i][1] = read();
            ys[i] = p[i][1];
        }

        // y좌표압축
        Arrays.sort(ys);
        HashMap<Integer, Integer> yMap = new HashMap<>();
        int idx = 1;
        for(int y : ys){
            if(!yMap.containsKey(y))
                yMap.put(y, idx++);
        }

        int size = idx;
        // x오름차순 정렬
        Arrays.sort(p, Comparator.comparingInt(a->a[0]));

        Fenwick left = new Fenwick(size);
        Fenwick right = new Fenwick(size);

        for(int[] point : p){
            right.add(yMap.get(point[1]), 1);
        }

        int lCnt = 0;
        int rCnt = N;
        int ans = N;

        int i = 0;
        while(i<N){
            int cx = p[i][0];

            //같은 x를 가진 점들은 한번에 이동함
            while(i<N && p[i][0] == cx){
                int y = yMap.get(p[i][1]);
                // 스위핑
                right.add(y, -1);
                left.add(y, 1);
                lCnt++;
                rCnt--;
                i++;
            }

            for(int y = 1; y<size; y++){
                // y는 좌표압축 결과 map.y에 해당하는 y좌표 이하의 점 갯수
                int lLow = left.sum(y);
                // 그 위는 전체에서 빼면됨
                int lHigh = lCnt - lLow;

                int rLow = right.sum(y);
                int rHigh = rCnt - rLow;

                // lL, lH, rL, rH 로 이루어진 사분면중 최대 점 갯수 구하고
                // 그것의 최솟값으로 ans 갱신
                int max = Math.max(Math.max(lLow, lHigh), Math.max(rLow, rHigh));
                ans = Math.min(ans, max);
            }
        }
        System.out.print(ans);
    }
}