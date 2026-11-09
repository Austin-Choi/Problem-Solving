/*
당연히 모든 n*m 자리에서 최대 20만번 시뮬할수는 없음.
아래 조건때문에 여러 위치로 분기할수 있을거같음.

단, 공은 격자 바깥으로 이동할 수 없으며, 목적지가 격자 바깥인 경우 공은 이동하다가 더 이상 이동할 수 없을 때 멈추게 됩니다
쿼리 종류
0: 왼쪽, 1: 오른쪽, 2:북쪽, 3:남쪽
-> 주어진 쿼리를 x,y에서 시작해서 반대 방향으로 가면서 처리하는데 항상 가능한 영역은 직사각형모양이므로
maxcol mincol, maxrow minrow 4가지로 직사각형 영역 쿼리마다 바꿔가며 처리하기

*/
import java.util.*;

class Solution {
    // n 세로 길이, m 가로 길이
    public long solution(int n, int m, int x, int y, int[][] queries) {
        long maxRow = x, minRow = x;
        long maxCol = y, minCol = y;
        
        for(int i = queries.length-1; i>=0; i--){            
            int type = queries[i][0];
            int size = queries[i][1];
            // 각 움직임별로 후보군 직사각형 영역 역방향 쿼리 따라가며 갱신
            // 왼쪽 역방향
            if(type == 0){
                if(minCol != 0)
                    minCol += size;
                maxCol = Math.min(m-1L, maxCol + size);
            }
            // 오른쪽 역방향
            else if(type == 1){
                if(maxCol != m-1L)
                    maxCol -= size;
                minCol = Math.max(0L, minCol - size);
            }
            // 북쪽 역방향
            else if(type == 2){
                if(minRow != 0)
                    minRow += size;
                maxRow = Math.min(n-1L, maxRow + size);
            }
            // 남쪽 역방향
            else{
                if(maxRow != n-1L)
                    maxRow -= size;
                minRow = Math.max(0L, minRow - size);
            }
            
            if(minRow > maxRow || minCol > maxCol)
                return 0;
            if(minRow >= n || maxRow < 0 || minCol >= m || maxCol < 0)
                return 0;
        }
        return (maxCol - minCol +1) * (maxRow - minRow +1);
    }
}