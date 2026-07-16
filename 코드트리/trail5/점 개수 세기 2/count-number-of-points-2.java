import java.util.*;
import java.io.*;

/*
x오름차순, y오름차순으로 각각 정렬한 좌표리스트 2개 만들고
-> 중복 제거해야하니까 treeSet활용
쿼리에 o(1)에 대답해야 함.
갯수 구하는거니까 누적합을 구하는데 좌표 범위가 매우 크니까 
좌표 인덱스로 압축을 한번 더 하고 
-> x로 정렬하고 <x,인덱스> 맵, y로 정렬하고 <y, 인덱스> 맵 2개 만들기
쿼리마다 처음으로 x1이상인 인덱스, 마지막으로 x2 이하인 인덱스 구해서 2차원누적합으로 갯수 구하기
-> lb ub 각 2번씩 4번 이분탐색후 2차원 ps에서 갯수 구하기
*/

public class Main {
    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    // pivot 이상인 최솟값 idx 찾기
    static int lb(int pivot, ArrayList<Integer> li){
        int ans = li.size();
        int l = 0;
        int r = li.size()-1;
        while(l<=r){
            int mid = (l+r)/2;
            if(li.get(mid) >= pivot){
                ans = mid;
                r = mid-1;
            }
            else
                l = mid+1;
        }
        return ans;
    }

    // pivot 이하인 최댓값 idx 찾기
    static int ub(int pivot, ArrayList<Integer> li){
        int ans = li.size();
        int l = 0;
        int r = li.size()-1;
        while(l<=r){
            int mid = (l+r)/2;
            if(li.get(mid) > pivot){
                ans = mid;
                r = mid -1;
            }
            else
                l = mid+1;
        }
        return ans;
    }

    public static void main(String[] args) throws IOException{
        int N = read();
        int Q = read();

        // 중복제거 + 정렬 -> x,y좌표 각각해주고 map에 인덱스 압축해서 넣기
        TreeSet<Integer> tx = new TreeSet<>();
        TreeSet<Integer> ty = new TreeSet<>();
        int[][] arr = new int[N][2];
        for(int i = 0; i<N; i++){
            arr[i] = new int[]{read(), read()};
            tx.add(arr[i][0]);
            ty.add(arr[i][1]);
        }
        Map<Integer, Integer> mx = new HashMap<>();
        int idx = 1;
        for(int xx : tx){
            mx.put(xx, idx++);
        }
        Map<Integer, Integer> my = new HashMap<>();
        idx = 1;
        for(int yy : ty)
            my.put(yy, idx++);
        ArrayList<Integer> xl = new ArrayList<>(tx);
        ArrayList<Integer> yl = new ArrayList<>(ty);

        // 중복제거해서 N+1크기가 아니라 tx,ty 기준으로 잡기
        // 2차원 ps
        int[][] ps = new int[tx.size()+1][ty.size()+1];
        for(int i = 0; i<N; i++){
            int cx = mx.get(arr[i][0]);
            int cy = my.get(arr[i][1]);
            ps[cx][cy]++;
        }
        
        for(int i =1; i<=tx.size(); i++){
            for(int j = 1; j<=ty.size(); j++){
                ps[i][j] = ps[i-1][j] + ps[i][j-1] - ps[i-1][j-1] + ps[i][j];
            }
        }

        StringBuilder sb = new StringBuilder();
        while(Q-->0){
            int x1 = read();
            int y1 = read();
            int x2 = read();
            int y2 = read();
            // x1 <= ? <= x2 
            // lb는 x 이상인 첫 0-based 인덱스
            // ub는 x 초과인 첫 0-based 인덱스인데 
            // ps에서 쓰는건 1-based 인덱스라 그대로 쓰면 -1 효과가 있어서 그대로 
            int xlow = lb(x1, xl)+1;
            int xhigh = ub(x2, xl);
            int ylow = lb(y1, yl)+1;
            int yhigh = ub(y2, yl);

            if(xlow > xhigh || ylow > yhigh){
                sb.append(0).append("\n");
            }
            else{
                sb.append(ps[xhigh][yhigh] - ps[xlow-1][yhigh] - ps[xhigh][ylow-1] + ps[xlow-1][ylow-1]);
                sb.append("\n");
            }
        }
        System.out.print(sb);
    }
}