import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.List;

public class StringSorter {
	
	@SuppressWarnings("rawtypes")
	Queue[] arrOfQ = new Queue[26];
	
	public StringSorter() {
		
		arrOfQ = new Queue[26];
		for (int i = 0; i < arrOfQ.length; i++) {
			arrOfQ[i] = new LinkedList<String>();
		}
	}
	
	public ArrayList<String> sort(ArrayList<String> s) {

		this.distribute(s, 0);
		ArrayList<String> retList = this.collect(0);
		return retList;
	}
	
	@SuppressWarnings("unchecked")
	private void distribute(ArrayList<String> s, int currentPosition) {
		
		Character[] letters = new Character[] {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		Integer[] positions = new Integer[] {0, 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25};
		HashMap<Character,Integer> lettersAndPositions = new HashMap<Character, Integer>(26);
		for (Integer i : positions) {
			lettersAndPositions.put(letters[i], i);
		}
		
		for (String str : s) {
			Character key = str.charAt(currentPosition);
			arrOfQ[lettersAndPositions.get(key)].add(str);
		}
	}
	
	
	@SuppressWarnings("unchecked")
	private ArrayList<String> collect(int i){
		
		ArrayList<String> retList = new ArrayList<String>();
		
		for( Queue<String> q : arrOfQ) {
			
			//base case: the Queue has one or less entries
			if (q.size() == 1) {
				retList.add(q.remove());
			}
			
			if (q.size() > 1) {
				StringSorter ss = new StringSorter();
				ArrayList<String> thisQ = new ArrayList<String>();
				for (String s : q) {
					if (s.length() == 1) {
						retList.add(s);
					}
					else {
						thisQ.add(s);
					}
				}
				
				ss.distribute(thisQ, i + 1);
				
				for (String p : ss.collect(i+1)) {
					retList.add(p);
				}
			}
		}
		
		
		
		return retList;
	}
	
	
	@SuppressWarnings("rawtypes")
	public Queue[]getArrOfQ() {
		return arrOfQ;
	}

	@SuppressWarnings("rawtypes")
	public void setArrOfQ(Queue[] arrOfQ) {
		this.arrOfQ = arrOfQ;
	}


	public static void main(String args[]) {
		ArrayList<String> test1 = new ArrayList<String>();
		test1.add("cab");
		test1.add("abc");
		test1.add("dab");
		test1.add("ac");
		test1.add("abab");
		
		StringSorter ss = new StringSorter();
		ArrayList<String> test1Sorted = ss.sort(test1);
		System.out.println(test1Sorted);
		
		ArrayList<String> test2 = new ArrayList<String>();
		test2.add("zebra");
		test2.add("zyw");
		test2.add("baaaaaaa");
		test2.add("zya");
		
		ArrayList<String> test2Sorted = ss.sort(test2);
		System.out.println(test2Sorted);
	}
}