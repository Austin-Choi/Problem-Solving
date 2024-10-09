import java.io.*;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingDeque;

/*
일단 한 면을 잡고 그걸 앞으로 해서 돌리면
인접한 4개의 면의 가장자리가 같이 돌아감.

그러면 u, d, f, b, l, r 에다가 0,1,2,3,4, ... 53까지 숫자 순서대로 넣고
각 면을 잡고 돌릴때 영향받는 4개의 3칸 저장해놓고 그거 돌리기
어차피 순서대로 돌아가니까 queue에 넣는데 앞에서 3개씩 빼고 뒤에 넣고 해야하니까 deque 활용

그리고 반시계방향은 오른쪽으로 3번 돌린거고 시계방향은 오른쪽 1번 돌린거임

그럼 한 변당 크기는 3이니까 4개 있으니까 각 칸을 각 면마다 숫자로 부여하고
십자가형 전개도로 하고 생각해보면 일종의 큐로 생각해볼 수 있음
시계방향이면 3칸씩 뒤로 가고 반시계방향이면 9칸씩 뒤로 감

구현해야할 것
1. 돌리는 면 오른쪽으로 돌아가는거
2. 인접한 4변 오른쪽으로 돌아가는거
3. 윗면 출력

돌리는 함수
잡고 돌릴면, 얼마나 돌릴지(시계방향이면 3칸, 반시계면 9칸)
 */
// up = w(0~8), down = y(9~17), front = r(18~26),
// back = o(27~35), left = g(36~44), right = b(45~53)
/*
         up

 left   front    right     back

         down

 왼쪽 위 기준으로 시계방향으로 작성
 */
public class Main {
    // u, d, f, b, l, r 순서로 인접한 4변 순서대로 담아놓은것
    public static int[][] rotateRows = new int[6][12];
    public static int[][][] cube = new int[6][3][3]; // 각 면의 위치 저장
    public static char[] cubeStatus = new char[54]; // 큐브 상태 배열
    public static char[] cubeInit = {'w', 'y', 'r', 'o', 'g', 'b'}; // 큐브 색
    public static char[][] tmp = new char[3][3]; // 잡고 돌리는 면 회전시킬때 필요

    public static void rotate(int face, int count){
        //
        Deque<Character> dq = new LinkedBlockingDeque<>();
        while(count-->0){
            // 변 회전
            // 현재 관련된 4개의 면의 자리를 불러와서 현재 들어가있는 char값 dq에 넣음
            for(int i = 0; i<12; i++){
                dq.add(cubeStatus[rotateRows[face][i]]);
            }
            // 1,2,3, 4,5,6, 7,8,9, 10,11,12
            // 가 있으면
            // 10, 11, 12, 1,2,3, 4,5,6, 7,8,9 가 되야함
            for(int i = 0; i<3; i++){
                if(!dq.isEmpty()) {
                    char c = dq.pollLast();
                    dq.addFirst(c);
                }
            }
            for(int i = 0; i<12; i++){
                if(!dq.isEmpty())
                    cubeStatus[rotateRows[face][i]] = dq.pollFirst();
            }
            // 면을 회전
            /*
               1 2 3      7 4 1
               4 5 6  =>  8 5 2
               7 8 9      9 6 3

               2,0 -> 0,0
               0,0 -> 0,2
               1,2 -> 2,1
               2,2 -> 2,0
             */
            // i, j -> j, 2-i
            for(int i = 0; i<3; i++){
                for(int j = 0; j<3; j++){
                    tmp[j][2-i] = cubeStatus[cube[face][i][j]];
                }
            }
            for(int i = 0; i<3; i++){
                for(int j = 0; j<3; j++){
                    cubeStatus[cube[face][i][j]] = tmp[i][j];
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        /*
                    0  1  2
                    3  4  5
          0  3  6   6  7  8    8  5   2   2 1  0
                    --------
    29 | 36 37 38 | 18 19 20 | 45 46 47 | 27 28 29 | 36
    32 | 39 40 41 | 21 22 23 | 48 49 50 | 30 31 32 | 39
    35 | 42 43 44 | 24 25 26 | 51 52 53 | 33 34 35 | 42
                    --------
         15 12  9   9  10 11  11 14 17    17 16 15
                    12 13 14
                    15 16 17
         */
        rotateRows[0] = new int[]{29, 28, 27, 47, 46, 45, 20, 19, 18, 38, 37, 36}; //u
        rotateRows[1] = new int[]{24, 25, 26, 51, 52, 53, 33, 34, 35, 42, 43, 44}; // d
        rotateRows[2] = new int[]{6, 7, 8, 45, 48, 51, 11, 10, 9, 44, 41, 38}; // f
        rotateRows[3] = new int[]{2, 1, 0, 36, 39, 42, 15, 16, 17, 53, 50, 47}; // b
        rotateRows[4] = new int[]{0, 3, 6, 18, 21, 24, 9, 12, 15, 35, 32, 29}; // l
        rotateRows[5] = new int[]{8, 5, 2, 27, 30, 33, 17, 14, 11, 26, 23, 20}; // r

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int testcase = Integer.parseInt(br.readLine());

        // 너무 긴 if-else문 대신 map으로 미리 선언해두고 치환
        Map<Character, Integer> interpret = new HashMap<>();
        interpret.put('+', 1);
        interpret.put('-', 3);
        interpret.put('U', 0);
        interpret.put('D', 1);
        interpret.put('F', 2);
        interpret.put('B', 3);
        interpret.put('L', 4);
        interpret.put('R', 5);

        // 큐브 인덱스 부여하기
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    cube[i][j][k] = i * 9 + j * 3 + k;
                }
            }
        }

        while(testcase-->0){
            // 큐브 초기화
            for(int i = 0; i<6; i++){
                for(int j = 0; j<9; j++){
                    cubeStatus[i*9+j] = cubeInit[i];
                }
            }

            // bufferedReader로 읽어서 n 필요없음
            br.readLine();
            // u d f b l r
            String[] cmds = br.readLine().split(" ");
            for(String cmd : cmds){
                char[] cmdChars = cmd.toCharArray();
                rotate(interpret.get(cmdChars[0]), interpret.get(cmdChars[1]));
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    sb.append(cubeStatus[cube[0][i][j]]);
                }
                sb.append("\n");
            }
            bw.write(sb.toString());
            bw.flush();
        }
        bw.close();
        br.close();
    }
}
