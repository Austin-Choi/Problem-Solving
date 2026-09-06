import java.util.*;
/*
주어진 정수들을 binaryString으로 나타내고
si, ei 로 dfs적으로 왼쪽 오른쪽 분할해서
이진트리가 되려면 루트가 0인데 자식노드가 있을때 그게 1이라면 안됨
*/

class Solution {
    // 현재 구간 시작점, 끝점, 조상노드 더미노드 여부
    boolean dfs(int si, int ei, boolean prevRt, char[] c){
        // !!! 빈 서브트리는 정상적인 상태
        if(si > ei)
            return true;
        int mid = (si+ei)/2;
        // 조상이 0이고 현재 루트가 1이라면 안됨
        if(prevRt && c[mid] == '1'){
            return false;
        }
        // and로 해야 전파됨
        return dfs(si, mid-1, c[mid] == '0', c)
            && dfs(mid+1, ei, c[mid] == '0', c);
    }
    
    public int[] solution(long[] ns) {
        int[] rst = new int[ns.length];
        for(int ii = 0; ii<ns.length; ii++){
            long n = ns[ii];
            char[] nc = Long.toBinaryString(n).toCharArray();
            int len = nc.length;
            // 패딩 크기 구하기 (포화이진트리 크기)
            int k = 0;
            while((1L<<k) -1 < len)
                k++;
            int fLen = (1<<k) - 1;
            // 앞에 0으로 채워줌
            char[] c = new char[fLen];
            for(int i = 0; i<fLen-len; i++){
                c[i] = '0';
            }
            for(int i = fLen - len; i<fLen; i++){
                c[i] = nc[i-(fLen-len)];
            }
            
            if(dfs(0, fLen-1, false, c))
                rst[ii] = 1;
        }
        return rst;
    }
}