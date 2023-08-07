import java.util.*;

class Solution {
    //풀이 2
    //Str1.startsWith(str2) 이용
    public boolean solution(String[] pb) {
        Arrays.sort(pb);
        //정렬 안하면 버블소팅처럼 O(n^2)복잡도인데
        //정렬하면 바로 뒤에꺼만 비교해주면 됨 O(n)
        for(int i = 0; i<pb.length-1; i++){
            if(pb[i+1].startsWith(pb[i]))
                return false;
        }
        return true;
    }
}