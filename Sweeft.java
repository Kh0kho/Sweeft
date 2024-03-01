import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.HashSet;
import java.util.Set;

public class Sweeft {

    public int evaluateExpression(String expression) {
        int result = 0;
        int start = 0;
        int length = expression.length();

        for (int i = 0; i < length; i++) {
            char currentChar = expression.charAt(i);

            if (currentChar == '+' || currentChar == '-') {
                if(i==0) continue;
                String numberStr = expression.substring(start, i);
                result += Integer.parseInt(numberStr);
                start = i;
            }
        }

        String lastNumberStr = expression.substring(start);
        result += Integer.parseInt(lastNumberStr);

        return result;
    }

    public int numberOfHappyStrings(List<String> strings) {
        int result = 0;
        for (int i = 0; i < strings.size(); i++) {
            if (valid(strings.get(i))) result++;
        }
        return result;
    }

    private boolean valid(String s) {
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) return false;
        }
        return true;
    }

    public class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        ListNode next;

        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        return prev;
    }

    public int[] findIntersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> commonSet = new HashSet<>();

        for (int num : nums1) {
            set1.add(num);
        }

        for (int num : nums2) {
            if (set1.contains(num)) {
                commonSet.add(num);
            }
        }

        return commonSet.stream().mapToInt(Integer::intValue).toArray();
    }


    public static int lenOfLongSubarr(int[] array, int k) {
    	int[] used = new int[array.length];

        return maxLen(array, k, 0, used, 0);
    }

    
    private static int maxLen(int[] array, int k, int currentSum, int[] used, int answer) {
    	if ( currentSum == k){
    		return answer;
    	}
    	int ans = 0;
    	for(int i = 0; i < array.length; i++){
    		if (used[i] == 0){
    			used[i] = 1;
    			answer ++;
    			ans = Math.max(ans, maxLen(array, k, currentSum + array[i], used, answer));
    			used[i] = 0;
    			answer --;
    		}
    	}
    	return ans;
    }

    
    public static boolean isValidSequence(int[] array, int[] sequence) {
        int arrayIndex = 0;
        int sequenceIndex = 0;

        while (arrayIndex < array.length && sequenceIndex < sequence.length) {
            if (array[arrayIndex] == sequence[sequenceIndex]) {
                sequenceIndex++;
            }
            arrayIndex++;
        }

        return sequenceIndex == sequence.length;
    }
    
    
    public static void printReversedList(ListNode node) {
        if (node == null) {
            return;
        }
    
        if (node.next != null) {
            printReversedList(node.next);
            System.out.print("<-");
        }
    
        System.out.print(node.val);
    }
    
	public static void main(String[] args) {
        Sweeft sw = new Sweeft();

        //expression
        String expression = "5+20-8+5";
        int result = sw.evaluateExpression(expression);
        System.out.println(result);

        // happy string
        List<String> strings = List.of("abbcc", "abc", "abcabc", "cabcbb");
        int happyStrings = sw.numberOfHappyStrings(strings);
        System.out.println(happyStrings);

        
        
        // linkd list
        ListNode head = sw.new ListNode(1);
        head.next = sw.new ListNode(2);
        head.next.next = sw.new ListNode(3);
        head.next.next.next = sw.new ListNode(4);
        head.next.next.next.next = sw.new ListNode(5);

        ListNode reversedHead = sw.reverseList(head);

        printReversedList(reversedHead);
        System.out.println();



        //findIntersection
        int[] nums1 = {1, 2, 3, 3, 4, 5};
        int[] nums2 = {3, 4, 4, 5, 6, 7};


        int[] intersection = sw.findIntersection(nums1, nums2);

        System.out.println(Arrays.toString(intersection));

        
        //lenOfLongSubarr
        int[] array = {6, 2, 2, 3, 4, 1};
        int k = 8;

        result = sw.lenOfLongSubarr(array, k);
        System.out.println(result);
        
        //ValidSequence
        int[] arr = {5, 1, 22, 25, 6, -1, 8, 10};
        int[] sequence = {1, 6, -1, 10};

        boolean res = isValidSequence(arr, sequence);
        System.out.println(res);
    }
}
