import java.util.*;

class Solution {
    class Pair{
        int process;
        int priority;
        Pair(int process, int priority){
            this.process = process;
            this.priority = priority;
        }
        int getPriority(){
            return this.priority;
        }
        int getProcess(){
            return this.process;
        }
        boolean equals(Pair p){
            if(this.process == p.getProcess() && this.priority == p.getPriority())
                return true;
            return false;
        }
    }
    private int getMaxPriority(Queue<Pair> q){
        int maxPriority = -1;
        for(Pair p : q){
            maxPriority = Math.max(maxPriority, p.getPriority());
        }
        return maxPriority;
    }
    public int solution(int[] priorities, int location) {
        int answer = 0;
        // idx : 프로세스 종류, priorities[idx] : 중요도
        // idx = location
        Pair target = new Pair(location, priorities[location]);
        Queue<Pair> q = new LinkedList<>();
        ArrayList<Pair> l = new ArrayList<>();
        for(int i = 0; i<priorities.length; i++){
            q.add(new Pair(i, priorities[i]));
        }
        while(!q.isEmpty()){
            Pair curP = q.poll();
            int curMaxP = getMaxPriority(q);
            if(curP.getPriority() < curMaxP){
                q.add(curP);
            }
            else{
                if(curP.equals(target))
                    break;
                answer++;
            }
        }
        //입력값 〉 [1, 3, 1, 2, 1], 2 기댓값 〉 5
        return answer+1;
    }
}