public class Search {
    public static void main(String[] args) {
        int[] nums = {11,12,22,23,33,34,44,45,55,56,66,67,69,77,78,88,89,90,91,92};
        int target = 69;
        int linearSearchResult = linearSearch(nums,target);
        System.out.println("Linear Search Result: " +linearSearchResult);
        int binarySearchResult = binarySearch(nums,target);
        System.out.println("Binary Search Result: " +binarySearchResult);

        System.out.println();

        int []arr = new int [1024];
        System.out.println("Linear Search Result: " +linearSearch(arr,target));
        System.out.println("Binary Search Result: " +binarySearch(arr,target));


    }
    public static int linearSearch(int[] nums, int target) {
        int steps = 0;
        for(int i = 0; i < nums.length; i++){
            steps++;
            if(nums[i] == target){
                System.out.println("Steps taken by Linear Search: " +steps);
                return i;
            }
        }
        System.out.println("Steps taken by Linear Search: " +steps);
        return -1;
    }

    public static int binarySearch(int[] nums, int target) {
        int steps = 0;
        int start = 0;
        int end = nums.length - 1;
        while(start <= end){
            steps++;
            int mid = start + (end - start)/2;
            if(nums[mid] == target){
                System.out.println("Steps taken by Binary Search: " +steps);
                return mid;
            }
            else if(nums[mid] < target)
                start = mid + 1;
            else if(nums[mid] > target)
                end = mid - 1;
        }
        System.out.println("Steps taken by Binary Search: " +steps);
        return -1;
    }
}
