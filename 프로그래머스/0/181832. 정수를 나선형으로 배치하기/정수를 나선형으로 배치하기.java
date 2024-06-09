class Solution {
    //동 남 서 북
    int[] di = {0,1,0,-1};
    int[] dj = {1,0,-1,0};
    public int[][] solution(int n) {
        int[][] board = new int[n][n];
        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                board[i][j] = 0;
            }
        }
        
        // 00 01 02 03
        // 13 23 33
        // 32 31 30
        // 20 10
        // 11 12
        // 22
        // 21
        
        // 00 01 02 03 04 
        // 14 24 34 44
        // 43 42 41 40
        // 30 20 10
        // 11 12 13
        // 23 33
        // 32 31
        // 21
        // 22
        
        //행
        int curI = 0;
        //열
        int curJ = 0;
        //방향 
        int dir = 0;
        board[0][0]=1;
        for(int curN = 2; curN<=n*n;){
        	//System.out.println("dir:"+dir);
            int nextI = curI + di[dir%4];
            int nextJ = curJ + dj[dir%4];
            if(nextI>-1 && nextI < n && nextJ > -1 && nextJ < n ){
                if(board[nextI][nextJ] == 0){
                    curI = nextI;
                    curJ = nextJ;
                    board[curI][curJ] = curN;
                    //System.out.println(curI+" "+curJ+" = "+curN);
                    curN++;
                }
                else{
                    dir++;
                }
            }
            else{
                dir++;
            }
        }
        
        return board;
    }
}