import java.util.*;

/*
미리 set(shop)으로 종류 구해놓고
1번으로 left right 해놓고 
현재 left right 안에 요소 set(bag)에 추가해놓고 만약 bag가 shop보다 작으면 right++
만약 bag == shop 이면 right - left +1 한거 일단 저장하고 Integer.max 보다 작은지 min으로 해서 갱신하고
left가 contains = true 면 left++, contains = false면 right++

어...이렇게 하면 contains = true일때 제거할때 애매해짐 

그래서 map이용하기로 함. size() 하면 Set의 크기 활용할 수 있고, 
제거할땐 map.get(gems[left]) 해가지고 해당 value -1 해주면 됨. 
*/

class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        Set<String> shop = new HashSet<>(Arrays.asList(gems));
        int shopSize = shop.size();
        int left = 0;
        int right = 0;
        
        List<String> realBag = new ArrayList<>();
        realBag.add(gems[0]);
        
        int min = Integer.MAX_VALUE;
        
        Map<String, Integer> m = new HashMap<>();
        
        while(right < gems.length){
            // 일단 오른쪽에 있는거 담아주고
            m.put(gems[right], m.getOrDefault(gems[right],0)+1);
            
            // left가 이미 들어있다면 map에서 해당 보석 제거해줌.
            // while로 1개 이상 있으면 계속 제거해서 left 땡겨줌
            while(m.get(gems[left])>1){
                m.put(gems[left], m.get(gems[left])-1);
                left++;
            }
            
            // 정답처리 조건문
            if(m.size() == shopSize){
                if((right - left) < min){
                    min = right - left;
                    answer[0] = left+1;
                    answer[1] = right+1;
                }
            }
            right++;
        }
        return answer;
    }
}