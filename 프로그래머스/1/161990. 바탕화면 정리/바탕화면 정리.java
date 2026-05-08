/*
어차피 한칸에 하나 들어가니까 wp 파싱해서 
파싱점은 왼쪽위 시작점, 
*/
class Solution {
    // 0번이 왼쪽위 좌표
    int[] di = {0,1,0,1};
    int[] dj = {0,0,1,1};
    
    public int[] solution(String[] wp) {
        int N = wp.length+1;
        int M = wp[0].length()+1;
        
        int maxI = -1;
        int maxJ = -1;
        int minI = N+2;
        int minJ = M+2;
        
        for(int i = 0; i<N-1; i++){
            char[] cur = wp[i].toCharArray();
            for(int j = 0; j<M-1; j++){
                if(cur[j] == '#'){
                    for(int k = 0; k<4; k++){
                      maxI = Math.max(maxI, i + di[k]);
                      minI = Math.min(minI, i + di[k]);
                      maxJ = Math.max(maxJ, j + dj[k]);
                      minJ = Math.min(minJ, j + dj[k]);
                    }
                }  
            }
        }
        return new int[]{minI, minJ, maxI, maxJ};
    }
}