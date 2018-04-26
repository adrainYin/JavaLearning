class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int index1 = -1;
        int index2 = -1;
        int data[] = new int[2];
        for(int i = 0 ; i < numbers.length ; i++){
            index1 = i;
            int temp = target - numbers[index1];
            index2 = Solution.binarySearch(numbers , index1+1 , numbers.length-1,temp);
            if(index2 != -1){
                data[0] = index1;
                data[1] = index2;
                return data;
            }
        }
        return data;
    }

    public static int binarySearch(int[] numbers , int low , int high , int key){
        while(low <= high){
            int mid = (low + high) / 2;
            if(numbers[mid] == key){
                return mid;
            }
            else if(numbers[mid] > key){
                return binarySearch(numbers , low , mid - 1 ,key);
            }
            else{
                return binarySearch(numbers , mid + 1 , high , key);
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int data[] = {2,3,4};
        int target = 6;
        Solution solution = new Solution();
        int ret[] = solution.twoSum(data , 6);
        System.out.println(ret[0] + " " + ret[1]);
    }
}