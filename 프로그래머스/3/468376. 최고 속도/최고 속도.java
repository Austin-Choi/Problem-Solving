/*
1번에서시작해서 도로들을 이용해 2~N번 도시로 갈때 가능한 최고 속도를 N-1길이의 배열에 담아 리턴
-> 카메라가 없는 도로는 최고 속도 대신 0을 담기
-> 한번의 탐색으로 구해야할거같으니 다익스트라 활용.
-> 속도제한 최대 1백만이므로 int

현재 위치에서 도로에 해당하는 점은 갈수 있는 점임.
도로 입력받을때 중앙 점은 속도 제한이 있음. 
-> 가는 경로 상에서 만나는 카메라가 영향을 줌. 
도로가 아닌 점은 갈수 없음.
좌표의 절댓값이 10^9 미만이라 도로 점들을 분리해서 저장하고 매번 주변에 도로점이 있는지 탐색하는건 불가능함
-> 교차점은 서로 다른 두 도로에 대햇 1개씩이므로 시작점, 교차점, 끝점으로 저장?
-> 사이에 카메라가 있는지 검사해서 카메라가 있으면 해당 제한속도 적용

정점의 종류는 도시, 임의의 두 도로의 교차점, 한 도로의 시작점과 끝점, 카메라가 있는 점
그래프 구성 

--------------------------
정점 기준으로 하니까 너무빡센데 구현이 레벨3이아닌데 ㅅㅂ;
좌표압축으로 다시 품

x좌표랑 y좌표를 따로 압축하면 단순히 좌표 범위가 줄어드는 것이 아니라
수평 수직 도로의 관계를 자동으로 나타낼수 있음.
-> 별도로 교차점을 계산하지 않아도 
*/
import java.util.*;

class Solution {
    static final int INF = 1_000_001;

    HashMap<Integer, Integer> xMap = new HashMap<>();
    HashMap<Integer, Integer> yMap = new HashMap<>();

    // 압축된 격자에서 인접한 정점 사이에 도로가 존재하는지 저장함.
    boolean[] right, up;

    // 정점별 카메라 제한 속도 (0이면 카메라 없음)
    int[] cameraLimit;
    // 정점별 도달 가능한 최고 속도
    int[] speed;

    int X, Y;

    // 압축된 x,y를 정점 번호로 변환
    int getId(int x, int y){
        return x * Y + y;
    }

    // 중앙 좌표 계산
    int mid(int a, int b){
        return (int)(((long)a + b) / 2);
    }
    
    // 다음 정점으로 이동할 때 최고 속도 갱신
    void relax(int next, int sp, PriorityQueue<int[]> pq){
        int nextSpeed = sp;

        if(cameraLimit[next] != 0)
            nextSpeed = Math.min(nextSpeed, cameraLimit[next]);

        if(nextSpeed > speed[next]){
            speed[next] = nextSpeed;
            pq.offer(new int[]{next, nextSpeed});
        }
    }

    public int[] solution(int[][] city, int[][] road) {
        int N = city.length;
        int M = road.length;

        TreeSet<Integer> xSet = new TreeSet<>();
        TreeSet<Integer> ySet = new TreeSet<>();

        // 도시 좌표 수집
        for(int[] ci : city){
            xSet.add(ci[0]);
            ySet.add(ci[1]);
        }

        // 도로 양 끝점과 카메라 좌표 수집
        for(int[] ri : road){
            xSet.add(ri[0]);
            xSet.add(ri[2]);
            xSet.add(mid(ri[0], ri[2]));

            ySet.add(ri[1]);
            ySet.add(ri[3]);
            ySet.add(mid(ri[1], ri[3]));
        }

        X = xSet.size();
        Y = ySet.size();

        // 좌표 압축
        int idx = 0;
        for(int x : xSet){
            xMap.put(x, idx++);
        }

        idx = 0;
        for(int y : ySet){
            yMap.put(y, idx++);
        }

        int V = X * Y;

        right = new boolean[V];
        up = new boolean[V];
        cameraLimit = new int[V];
        speed = new int[V];

        // 카메라 등록 (동일 위치라면 최소 제한 속도)
        for(int[] ri : road){
            int cx = mid(ri[0], ri[2]);
            int cy = mid(ri[1], ri[3]);

            int id = getId(xMap.get(cx), yMap.get(cy));
            int limit = ri[4];

            if(cameraLimit[id] == 0){
                cameraLimit[id] = limit;
            }else{
                cameraLimit[id] = Math.min(cameraLimit[id], limit);
            }
        }

        // 도로를 압축 격자에 연결
        for(int[] ri : road){
            int sx = xMap.get(ri[0]);
            int sy = yMap.get(ri[1]);
            int ex = xMap.get(ri[2]);
            int ey = yMap.get(ri[3]);

            if(sy == ey){
                // 수평 도로
                for(int x=sx; x<ex; x++){
                    right[getId(x, sy)] = true;
                }
            }else{
                // 수직 도로
                for(int y=sy; y<ey; y++){
                    up[getId(sx, y)] = true;
                }
            }
        }

        // 최대 병목 경로 다익스트라
        int start = getId(xMap.get(city[0][0]),yMap.get(city[0][1]));
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(b[1], a[1]));
        speed[start] = INF;
        pq.offer(new int[]{start, INF});

        while(!pq.isEmpty()){
            int[] cur = pq.poll();

            int v = cur[0];
            int sp = cur[1];

            if(sp < speed[v])
                continue;

            int x = v / Y;
            int y = v % Y;

            // 오른쪽
            if(x+1 < X && right[v])
                relax(v+Y, sp, pq);
            // 왼쪽
            if(x > 0 && right[v-Y])
                relax(v-Y, sp, pq);
            // 위쪽
            if(y+1 < Y && up[v])
                relax(v+1, sp, pq);
            // 아래쪽
            if(y > 0 && up[v-1])
                relax(v-1, sp, pq);
        }

        // 결과
        int[] answer = new int[N-1];
        for(int i=1; i<N; i++){
            int id = getId(xMap.get(city[i][0]), yMap.get(city[i][1]));
            answer[i-1] = speed[id] == INF ? 0 : speed[id];
        }
        return answer;
    }
}