import java.util.*;
class Solution {
    public int solution(int[][] d) {
        int sx = d[0][0];
        int sy = d[0][1];
        int row = 0;
        int col = 0;
        for(int i = 1; i<4; i++){
            if(sx == d[i][0]){
                col = Math.abs(d[i][1] - d[0][1]);
            }
            if(sy == d[i][1]){
                row = Math.abs(d[i][0] - d[0][0]);
            }
        }
        return row * col;
    }
}