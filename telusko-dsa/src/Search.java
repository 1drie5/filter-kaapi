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
        System.out.println("Recursive Binary Search Result: " +recursiveBinarySearch(arr,target,0,arr.length-1,0));
    }

    public static int linearSearch(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            System.out.println("Invalid or empty array.");
            return -1;
        }
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
        if (nums == null || nums.length == 0) {
            System.out.println("Invalid or empty array.");
            return -1;
        }
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
            else
                end = mid - 1;
        }
        System.out.println("Steps taken by Binary Search: " +steps);
        return -1;
    }

    public static int recursiveBinarySearch(int[] nums, int target, int start, int end, int steps) {
        if (nums == null || nums.length == 0) {
            System.out.println("Invalid or empty array.");
            return -1;
        }
        if (start > end) {
            System.out.println("Steps taken by Recursive Binary Search: " + steps);
            return -1;
        }
        steps++;
        int mid = start + (end - start) / 2;

        if (nums[mid] == target) {
            System.out.println("Steps taken by Recursive Binary Search: " + steps);
            return mid;
        }
        else if (nums[mid] > target) {
            return recursiveBinarySearch(nums, target, start, mid - 1, steps);
        }
        else {
            return recursiveBinarySearch(nums, target, mid + 1, end, steps);
        }
    }
}
