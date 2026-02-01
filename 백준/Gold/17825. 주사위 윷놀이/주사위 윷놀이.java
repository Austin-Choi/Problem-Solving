/*
pool = 10개의 턴 주사위 값
노드별 목적지는 하나씩밖에 없고 점수값도 유일함
파란색 이동은 따로 배열로
백트래킹으로 가능한 이동 전부 체크하면서 시뮬하기
 */
import java.util.*;
import java.io.*;
public class Main {
    static int[] dice = new int[10];
    static int[] score = new int[33];
    static int[] next = new int[33];
    static int[] blue = new int[33];
    // [i]번 노드에서 1칸 이동했을때 가는 다음 칸
    // ArrayList로 처리하면 깨짐
    // 빨간 길만 모델링
    static void init() {
        // 기본 빨간길
        for (int i = 0; i < 20; i++) {
            next[i] = i + 1;
            score[i] = i * 2;
        }
        next[20] = 32; score[20] = 40;

        // 파란길
        blue[5] = 21;
        blue[10] = 24;
        blue[15] = 26;

        // 10 파랑
        score[21] = 13; next[21] = 22;
        score[22] = 16; next[22] = 23;
        score[23] = 19; next[23] = 29;

        // 20 파랑
        score[24] = 22; next[24] = 25;
        score[25] = 24; next[25] = 29;

        // 30 파랑
        score[26] = 28; next[26] = 27;
        score[27] = 27; next[27] = 28;
        score[28] = 26; next[28] = 29;

        // 중앙
        score[29] = 25; next[29] = 30;
        score[30] = 30; next[30] = 31;
        score[31] = 35; next[31] = 20;

        score[32] = 0; next[32] = 32;
    }
    // 4개의 말의 현재 위치한 노드 값 저장하는 배열
    static int[] curPos = new int[4];
    static int move(int start, int diceNum) {
        if(start == 32)
            return 32;
        if(blue[start] != 0)
            start = blue[start];
        else
            start = next[start];
        diceNum--;
        while(diceNum-->0 && start!=32)
            start = next[start];
        return start;
    }

    static int ans = 0;
    // 현재 고른 말 번호, 진행한 dice 턴 수, 점수 총 합
    static void bt(int depth, int tot){
        if(depth == 10) {
            ans = Math.max(ans, tot);
            return;
        }

        for(int i = 0; i<4; i++){
            if(curPos[i] == 32)
                continue;

            int dest = move(curPos[i], dice[depth]);
            // 말이 이동을 마치는 칸에 다른 말이 있는지
            boolean is = false;
            for(int j = 0; j<4; j++){
                if(i!=j && curPos[j] == dest && dest != 32){
                    is = true;
                    break;
                }
            }
            if(is)
                continue;

            int prev = curPos[i];
            curPos[i] = dest;
            bt(depth+1,tot+score[dest]);
            curPos[i] = prev;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 10; i++) {
            dice[i] = Integer.parseInt(st.nextToken());
        }
        init();
        bt(0,0);
        System.out.print(ans);
    }
}