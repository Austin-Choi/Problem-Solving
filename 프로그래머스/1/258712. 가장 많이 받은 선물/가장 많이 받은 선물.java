import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        // sent 준사람, 받은사람, 갯수 
        Map<String, Map<String, Integer>> sent = new HashMap<>();
        // 선물지수: 사람, 지수
        Map<String, Integer> giftScore = new HashMap<>();
        
        for(int i = 0; i<friends.length; i++){
            sent.put(friends[i], new HashMap<>());
            giftScore.put(friends[i], 0);
        }
        
        for(int i = 0; i<friends.length; i++){
            for(int j = 0; j<friends.length; j++){
                sent.get(friends[i]).put(friends[j], 0);
            }
        }
        
        for(int i = 0; i<gifts.length; i++){
            // [0] 준사람, [1] 받은사람
            String[] temp = gifts[i].split(" ");
            sent.get(temp[0])
                .put(temp[1], sent.get(temp[0]).getOrDefault(temp[1],0)+1);
            //선물 지수는 이번 달까지 자신이 친구들에게 준 선물의 수에서 받은 선물의 수를 뺀 값
            giftScore.put(temp[0], giftScore.get(temp[0])+1);
            giftScore.put(temp[1], giftScore.get(temp[1])-1);
        }
        
        // 선물수 저장
        Map<String, Integer> answer = new HashMap<>();
        for(String s : sent.keySet()){
            for(String r : sent.get(s).keySet()){
                if(!s.equals(r)){
                    // 두사람 간 준거 각자 비교
                    int send1 = sent.get(s).get(r);
                    int send2 = sent.get(r).get(s);
                    
                    if(send1 > send2){
                        answer.put(s, answer.getOrDefault(s,0) + 1); // s가 더 많이 줬을 때 s가 받음
                    }
                    else if(send1 < send2){
                        answer.put(r, answer.getOrDefault(r,0) + 1); // r이 더 많이 줬을 때 r이 받음
                    }
                    else { // 주고받은 횟수가 같을 때
                        if(giftScore.get(s) > giftScore.get(r)){
                            answer.put(s, answer.getOrDefault(s,0) + 1); // 선물 지수가 더 큰 사람이 받음
                        } else if(giftScore.get(s) < giftScore.get(r)) {
                            answer.put(r, answer.getOrDefault(r,0) + 1);
                        }
                    }
                }
            }
        }
        return answer.isEmpty() ? 0 : Collections.max(answer.values())/2;
    }
}
/*
    두 사람이 선물을 주고 받은 기록이 있다면,
    둘 중 더 많이 받은 사람이 더 많이 준 사람한테 선물을 1개 줌
    주고받은 내용이 같다면 
    선물지수가 작은 사람이 선물 지수가 큰 사람에게 선물을 1개 줌
    선물을 주고 받은 기록이 없다면 
    선물지수가 작은 사람이 선물 지수가 큰 사람에게 선물을 1개 줌
    
    Map<String(준사람), Map<String(받은사람), Integer>>
*/