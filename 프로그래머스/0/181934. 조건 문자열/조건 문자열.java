class Solution {
    public int solution(String ineq, String eq, int n, int m) {
        boolean flag = false;
        if(ineq.equals("<")){
            flag = n<m;
            if(eq.equals("="))
                flag = n<=m;
        }
        else{
            flag = n>m;
            if(eq.equals("="))
                flag = n>=m;
        }
        
        if(flag)
            return 1;
        else
            return 0;
    }
}