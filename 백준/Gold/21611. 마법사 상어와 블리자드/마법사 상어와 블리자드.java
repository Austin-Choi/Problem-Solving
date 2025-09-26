import java.awt.Point;
import java.util.*;
import java.io.*;
// arr은 상어빼고 N*N-1
// 상어는 -1로 하고 빈칸은 0
public class Main {
    static int N,M;
    static int[][] board;
    // 달팽이 모양 쭉 펴서 값만 저장
    static int[] arr;
    // board상 2차원 좌표가 arr의 어떤 인덱스랑 매칭되는지 저장
    static int[][] pos;
    static Point[] ml;
    static int L;
    // 북남서동 1234
    static int[] di = {0,-1,1,0,0};
    static int[] dj = {0,0,0,-1,1};
    static int ans = 0;
    // 전처리 -> 중앙부터 시작해서 달팽이 모양대로 따라가면서
    // arr에 보드 값 넣어주고 pos[][]에 arr의 인덱스 넣어주기
    static void init(){
        arr = new int[N*N];
        pos = new int[N][N];
        L = N*N-1;
        int ci = N/2;
        int cj = N/2;
        for(int[] p : pos) {
            Arrays.fill(p, -1);
        }

        int idx = 0;
        int step = 1;
        //서남동북
        int[] ddi = {0,1,0,-1};
        int[] ddj = {-1,0,1,0};
        int d = 0;
        while(true){
            for(int rpt = 0; rpt < 2; rpt++){
                for(int s = 0; s< step; s++){
                    ci += ddi[d];
                    cj += ddj[d];
                    if(ci < 0 || cj < 0 || ci >= N || cj >= N)
                        break;
                    arr[idx] = board[ci][cj];
                    pos[ci][cj] = idx;
                    idx++;
                    if(idx == L)
                        break;
                }
                d = (d+1)%4;
                if(idx == L)
                    break;
            }
            step++;
            if(idx == L)
                break;
        }
    }
    // 땡기기 (마법 시전 후, 구슬 폭발 직후)
    static void pull(){
        int[] temp = new int[L];
        int idx = 0;
        for(int n : arr){
            if(n != 0)
                temp[idx++] = n;
        }
        arr = temp;
    }

    //마법 시전 -> pos 배열로 인덱스가져오고 0으로 바꿔주기
    static void blizzard(Point ds){
        int d = ds.x;
        int s = ds.y;
        int ci = N/2;
        int cj = N/2;

        for(int i = 1; i<=s; i++){
            int ni = ci + di[d]*i;
            int nj = cj + dj[d]*i;

            if(ni < 0 || nj < 0 || ni >= N || nj >= N)
                break;
            int idx = pos[ni][nj];
            if(idx >= 0)
                arr[idx] = 0;
        }
        pull();
    }

    // 구슬 폭발 -> bombed bool 써서 탈출하기
    // for문 나와서 마지막 il 것도 해주기
    static void bomb(){
        while(true){
            boolean bombed = false;
            int i = 0;

            while(i < L && arr[i] != 0){
                int j = i+1;
                while(j < L && arr[j] == arr[i])
                    j++;
                int count = j-i;
                int id = arr[i];
                if(count >= 4){
                    Arrays.fill(arr, i,j,0);
                    ans += id * count;
                    bombed = true;
                }
                i = j;
            }

            if(!bombed)
                break;
            pull();
        }
    }

    // 구슬 그룹 두개의 구슬 A,B로 쪼개기
    // for문 다 끝나고 마지막 count, id종류도 il에 넣기
    static void divide(){
        // 갯수 번호 순서대로 넣음
        ArrayList<Integer> il = new ArrayList<>();
        int i = 0;
        while(i < L && arr[i] != 0){
            int j = i+1;
            while(j < L && arr[i] == arr[j])
                j++;
            int count = j - i;
            int id = arr[i];
            if(il.size() < L)
                il.add(count);
            if(il.size() < L)
                il.add(id);
            i = j;
        }
        arr = new int[L];
        for(int k = 0; k < Math.min(il.size(), L); k++){
            arr[k] = il.get(k);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][N];

        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ml = new Point[M];
        for(int m = 0; m<M; m++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            ml[m] = new Point(s,e);
        }

        init();
        for(int m = 0; m<M; m++){
            blizzard(ml[m]);
            bomb();
            divide();
        }

        System.out.println(ans);
    }
}