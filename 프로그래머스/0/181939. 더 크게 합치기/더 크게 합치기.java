class Solution {
    public int solution(int a, int b) {
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        sb.append(a).append(b);
        sb2.append(b).append(a);
        
        int lhs = Integer.parseInt(sb.toString());
        int rhs = Integer.parseInt(sb2.toString());
        
        if(rhs >= lhs)
            return rhs;
        return lhs;
    }
}