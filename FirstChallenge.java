public class Solution {
    public static int[] solution(int area) {

        // if input is invalid return null
        if(area < 1 || area > 1000000) return null;
        
        int sqrt, toBeAdded, tempArea = area, count = 0;
        // find the result array size
        while(tempArea > 0){
            sqrt = (int)Math.sqrt(tempArea);
            toBeAdded = (int) Math.pow(sqrt,2);
            tempArea = tempArea - toBeAdded;
            count++;
        }

        int[] result = new int[count];
        // loop: find square root of area (the largest square fit)
        for(int i=0;i<count;i++){
            sqrt = (int)Math.sqrt(area);
            toBeAdded = (int) Math.pow(sqrt,2);
            result[i] = toBeAdded;
            area = area - toBeAdded;
        }

        return result;
    }
    
}