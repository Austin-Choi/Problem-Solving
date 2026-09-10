import java.util.*;
/*
result에는 root 제외하고 출력
양방향 그래프로 표현
셀러로부터 시작해서 prev가 아닌 연결 노드로 root까지 profit 전파하기 
-> 트리라서 그래프까지 필요없음

*/
class Solution {
    HashMap<String, Integer> m = new HashMap<>();
    
    int[] parent;
    int[] result;
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int N = enroll.length;
        
        for(int i = 0; i<N; i++){
            m.put(enroll[i], i+1);
        }
        parent = new int[N+1];
        result = new int[N+1];
        // 0번노드는 가상노드 (루트노드 역할)
        for(int i = 0; i<N; i++){           
            if(referral[i].equals("-"))
                parent[i+1] = 0;
            else
                parent[i+1] = m.get(referral[i]);
        }
        
        //이익분배
        for(int i = 0; i<seller.length; i++){
            int cur = m.get(seller[i]);
            int profit = amount[i] * 100;
            
            while(cur != 0){
                int toGive = profit / 10;
                result[cur] += profit - toGive;
                
                if(toGive == 0)
                    break;
                
                cur = parent[cur];
                profit = toGive;
            }
        }
        
        int[] ans = new int[N];
        for(int i = 0; i<N; i++){
            ans[i] = result[i+1];
        }
        return ans;
    }
}