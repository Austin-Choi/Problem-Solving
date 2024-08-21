import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;

/*
새로 이동한 칸에 적혀있는 알파벳은 지금까지 지나온 모든 칸에 적혀있는 알파벳과는 달라야 한다
-> 리스트로 추가하고 contains로 체크하기
상하좌우로 인접한 네칸 중 하나로 이동
-> di dj 배열 이용하기
말이 지나는 칸은 좌측 상단의 칸도 포함된다
-> length = 1로 시작하기 But answer는 1로 시작 (움직이지 못하는 경우 있을 수 있음)
=> 0,0부터 시작할거고 방문리스트의 길이가 곧 정답인데 빈 방문 리스트를 넣을 것이므로 그냥 length = 0 으로 시작함
R, C가 20이하이므로 그냥 naive한 DFS 써도 됨 (조건 안맞으면 돌아가야하니까 bt)
 */
public class Main {
    //북 동 남 서
    public static int[] di = {0, 1, 0, -1};
    public static int[] dj = {-1, 0, 1, 0};
    public static int R;
    public static int C;
    public static int answer = 0;

    //List<String> l = new ArrayList<>(); 이거 쓰면 시간초과남
    //그래서 알파벳 종류 26개만큼의 visited 배열 v 를 만듬.
    public static boolean[] v = new boolean[26];

    public static int[][] board;
    public static void bt(int curI, int curJ, int length){
        //리스트에 있다는건 현재 가야할 곳이 못 간다는 것이므로 정답조건 처리
        if(v[board[curI][curJ]]){
            if(answer < length)
                answer = length;
            return;
        }

        else {
            //리스트에 없다는건 현재 가야할 곳이 갈 수 있다는 거니까 일단 담아줌
            // -> 리스트로 하니까 시간초과나서 visited boolean 배열로 바꿈
            v[board[curI][curJ]] = true;
            //di, dj 배열을 통해 북 동 남 서 순으로 다음 갈 곳 탐색
            for (int i = 0; i < 4; i++) {
                int nextI = curI + di[i];
                int nextJ = curJ + dj[i];
                // 다음 갈 곳이 조건에 맞으면 length 하나 올리고 바로 ㄱ
                if (nextI < R && nextI >= 0 && nextJ < C && nextJ >= 0) {
                    bt(nextI, nextJ, length+1);
                }
            }
            //여기까지 온거면 해당 위치는 영 아닌 것이기 때문에 방문 리스트에서 지워줌
            v[board[curI][curJ]] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new int[R][C];
        for(int i = 0; i<R; i++) {
            int j = 0;
            for (char c : br.readLine().toCharArray()) {
                board[i][j] = c; // 그냥 이렇게 넣으니까 'A'가 visited에서 인덱스 0으로 들어가지 않음
                board[i][j] -= 'A'; // -> 그래서 'A' 만큼 빼줌
                j++;
            }
        }
        //List<String> l = new ArrayList<>(); 이거 쓰면 시간초과남
        //그래서 알파벳 종류 26개만큼의 visited 배열 v 를 만듬.
        bt(0, 0, 0);
        if(answer == 0)
            System.out.println(1);
        else
            System.out.println(answer);
    }
}