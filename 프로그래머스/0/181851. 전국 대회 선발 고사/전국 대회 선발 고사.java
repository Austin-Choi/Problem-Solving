import java.util.*;

class Solution {
    public int solution(int[] rank, boolean[] attendance) {
        int answer = 0;
        //result에 a b c 는 0번째부터 시작하는 인덱스값
        //rankMap k : 등수 값, v : 원래 인덱스값
        //rankMap key로 오름차순 정렬하고 탑 3 value 가져와서 계산하고리턴
        Map<Integer, Integer> rankMap = new HashMap<>();
        for(int i = 0; i<rank.length; i++){
            if(attendance[i]){
                rankMap.put(rank[i],i);
            }
        }
        //key 리스트로 먼저 뽑아오기
        ArrayList<Integer> keyList = new ArrayList<>(rankMap.keySet());
        //key 사용해서 정렬
        Collections.sort(keyList);
        
        
        return 10000*rankMap.get(keyList.get(0))
            +100*rankMap.get(keyList.get(1))
            +rankMap.get(keyList.get(2));
    }
}