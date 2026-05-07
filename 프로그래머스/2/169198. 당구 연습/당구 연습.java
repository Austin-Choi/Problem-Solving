/*
좌측 하단이 0,0 -> (x,y)
오른쪽으로 가면 i 증가, 위로 가면 j 증가

시작위치는 고정 목표위치만 달라짐
원쿠션이니까 목적공을 뚫고 꼭지점을 치고 목적공을 침..
(같은 직선에 있을때)

네변 축으로 뒤집고 거리 계산?
코너는 어차피 찍고 다시 오는게 손해라서??

세로 n, 가로 m
0의 축에 대해서는 각자 - 하면됨
x,y -> m-x+m,y
x,y -> x,n-y+n
*/
class Solution {
    int getDist(int ax, int ay, int bx, int by){
        int b = by-ay;
        int a = bx-ax;
        return b*b + a*a;
    }
    public int[] solution(int m, int n, int sx, int sy, int[][] balls) {
        int[] rst = new int[balls.length];
        for(int i = 0; i<balls.length; i++){
            int[] b = balls[i];
            
            int min = Integer.MAX_VALUE;
            int bx = b[0];
            int by = b[1];
            
            int nx, ny;
            // 반사된 목적 점 - 기준 축 - 목적 점 - 시작점 이면 안됨
            //왼쪽
            if(!(sy == by && bx <= sx)){
                nx = -bx;
                ny = by;
                min = Math.min(min, getDist(sx,sy,nx,ny));
            }
            
            //아래쪽
            if(!(bx == sx && by <= sy)){
                nx = bx;
                ny = -by;
                min = Math.min(min, getDist(sx,sy,nx,ny)); 
            }
            
            //오른쪽
            if(!(by == sy && sx <= bx)){
                nx = 2*m-bx;
                ny = by;
                min = Math.min(min, getDist(sx,sy,nx,ny));
            }
            
            //위쪽
            if(!(bx == sx && sy <= by)){
                nx = bx;
                ny = 2*n-by;
                min = Math.min(min, getDist(sx,sy,nx,ny));
            }
            
            rst[i] = min;
        }
        
        return rst;
    }
}