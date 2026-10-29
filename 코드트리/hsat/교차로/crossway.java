/*
우선순위 A,B,C,D +4 -1 idx 확인해서 그거 time 차량 확인하고 
만약 해당 큐가 비어있다면 현재꺼 time, 차량 진행
큐가 비어있지 않고 time이 같거나 빠르다면 그걸 현재로 지정하고 이전 현재 차량의 time을 +1을 하고 다시 그 큐에 넣음 
큐가 비어있지 않고 time이 더 크다면 현재 차량 진행하고 다음 차량을 현재로 지정
차량이 진행될때까지 진행 
-----------------------------------
매번 돌리면 시간초과나고 사실 4교차로만 보면 교착 여부 알수 있으니까 

*/
import java.util.*;
import java.io.*;

public class Main {

    static StreamTokenizer sst = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int read() throws IOException{
        sst.nextToken();
        return (int) sst.nval;
    }

    static char rc() throws IOException{
        sst.nextToken();
        return sst.sval.charAt(0);
    }

    static int N;
    
    public static void main(String[] args) throws IOException{
        N = read();
        ArrayDeque<int[]>[] cross = new ArrayDeque[4];
        for(int i = 0; i<4; i++)
            cross[i] = new ArrayDeque<>();

        // !! : input에 car 자체도 저장해야 함 나중에 정렬하기때문에
        int[][] input =new int[N][3];
        for(int i = 0; i<N; i++){
            int cur = read();
            int type = (int) rc() - 'A';
            input[i] = new int[]{cur, type, i};
        }

        Arrays.sort(input, Comparator.comparingInt(a->a[0]));

        int time = 0;
        for(int i = 0; i<N; i++){
            int curTime = input[i][0];
            int curType = input[i][1];
            int curCar = input[i][2];

            if(curCar == 0)
                time = curTime;
            
            cross[curType].add(new int[]{curTime, curCar});
        }

        // 각 자동차별로 통과 시각 저장
        int[] ans = new int[N];
        Arrays.fill(ans, -1);

        int count = 0;
        StringBuilder sb = new StringBuilder();

        while(count < N){
            // 1. 현재 시각에 차량이 존재하는 차선 4방향 모두 확인
            boolean[] exist = new boolean[4];
            int existCount = 0;
            for(int d = 0; d<4; d++){
                // 방향 d 에 차가 존재하고 그 차가 온 시각이 현재 time보다 빨리 도착해야 존재하는것
                if(!cross[d].isEmpty() && cross[d].peek()[0] <= time){
                    exist[d] = true;
                    existCount++;
                }
            }

            // 2. 네 차선 모두 차있으면 교착상태이므로 break;
            if(existCount == 4)
                break;
            
            // 3. 현재 시각에 차가 없다면 다음 차가 있는 시각이 시작 시각
            // time++로 구현하면 시간초과나서 바로 jump
            if(existCount == 0){
                int nextTime = 10_000_000_01;
                for(int d = 0; d<4; d++){
                    if(!cross[d].isEmpty()){
                        nextTime = Math.min(nextTime, cross[d].peek()[0]);
                    }
                }
                time = nextTime;
                continue;
            }

            // 4. 현재 차선이 차있고 다음 차선이 차있지 않은 것 현재 time으로 빼내기
            for(int d = 0; d<4; d++){
                int right = (d+3)%4;
                if(exist[d] && !exist[right]){
                    ans[cross[d].poll()[1]] = time;
                    count++; 
                }
            }
            time++;
        }
        

        for(int aa : ans){
            sb.append(aa).append("\n");
        }
        System.out.print(sb);
    }
}