public class L1C1_SolarDoomsday {
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

    public static void main(String[] args) {
        int[] myTests = new int[]{12, (15324) };
        for(int i=0 ; i< myTests.length ; i++){
            int[] result = solution(myTests[i]);
            System.out.print(myTests[i] + ": ");
            for(int j = 0 ; j < result.length ; j++){
                System.out.print(result[j] + " ");
            }
            System.out.println("");

        }

    }
}
