import java.io.*;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class PracticeCode {
	
	public static void printPermutationIndexes(String smaller, String bigger) {
		if (smaller == null || bigger == null) {
			System.out.println("Null strings are not accepted");
			return;
		}
		if (smaller.length() > bigger.length()) {
			System.out.println("Cannot find permutation of a bigger string within smaller string");
			return;
		}
		int substringIdx = 0;
		int smallerLen   = smaller.length();
		ArrayList<HashMap<String,Integer>> list = new ArrayList<>();
		for(int i=0; i  < bigger.length(); i++) {
			HashMap<String,Integer> hashMap;

			if (i == smallerLen * substringIdx) {
				hashMap = new HashMap<>();
				list.add(hashMap);
			} else {
				
				hashMap = list.get(substringIdx);
			}
			String key  = Character.toString(bigger.charAt(i));
			if (hashMap.containsKey(key)) {
				hashMap.put(key,hashMap.get(key) + 1);
			} else {
				hashMap.put(key,1);
			}
			if ( (( i+1) % smallerLen ) == 0) {
				substringIdx ++;
			}
		}

		/*Put smaller string in a hashmap too now*/
		HashMap<String,Integer> smallerMap = new HashMap<>();
		for(int i=0; i < smaller.length(); i++) {
			String key = Character.toString(smaller.charAt(i));
			if (smallerMap.containsKey(key)) {
				smallerMap.put(key,smallerMap.get(key) + 1);
			} else {
				smallerMap.put(key,1);
			}
		}

		/*Now check for any permutation possibility within the bigger string*/
		int index = 0;
		for(HashMap<String,Integer> hashMap : list) {
			boolean isPerm = true;
			if (smallerMap.size() != hashMap.size()) {
				continue; /*if unequal number of characters, we can easily skip it.
				This can happen in case, where bigger string has some 1-2 characters left in the end*/
			}
			Set<String> keySet = hashMap.keySet();
			for(String s : keySet) {
				if (!smallerMap.containsKey(s)) {
					isPerm = false;
					break; /*Not a permutation, break out & check next possible string*/
				}
				if (smallerMap.get(s) != hashMap.get(s)) { /*Number of characters are different*/
					isPerm = false;
					break; /*Not a permutation, break out & check next possible string*/
				}
			}
			if (isPerm) {
				System.out.println("permutation at " + String.valueOf(index  * smallerLen));
			}
			index++;
		}
	}
	/**************************************************************************/
	private static HashSet<String> merge(String other,HashSet<String> strList) {
		HashSet<String> mergeList = new HashSet<>();
		
		for(String str : strList) {
			int i;

			for(i=0; i < str.length(); i++) {
				StringBuilder sb = new StringBuilder();
				sb = sb.append(str.substring(0,i))
				.append(other)
				.append(str.substring(i));					
				mergeList.add(sb.toString());
			}
			StringBuilder sb = new StringBuilder();
			sb = sb.append(str.substring(0,i)).append(other);
			mergeList.add(sb.toString());
		}


		return mergeList;
	}
	
	
	/*Generate all permutations of a given string*/
	public static HashSet<String> generatePermutationsOfGivenString(String str) {
		if (str == null) {
			return null;
		}
		if (str.length() == 0) {
			HashSet<String> hashSet = new HashSet<>();
			hashSet.add("");
			return hashSet;
		}
		if (str.length() == 1) {
			HashSet<String> hashSet = new HashSet<>();
			hashSet.add(str);
			return hashSet;
		}
		return PracticeCode.merge(Character.toString(str.charAt(str.length()-1)),
					generatePermutationsOfGivenString(str.substring(0,str.length()-1) ) );
	}

	public static void printPermutations(String str) {
		HashSet<String> hashSet = PracticeCode.generatePermutationsOfGivenString(str);
		for(String perm : hashSet) {
			System.out.println(perm);
		}
	}
	
	
	public static void main(String[] args) {
	  /*Print the indexes of all the permutation of smaller string within given string
	  Happens in O(biggerLen) time*/
		String smaller = "abbc";
		String bigger  = "cbabadcbbabbcbabaabccbabc";
		PracticeCode.printPermutationIndexes(smaller,bigger);
		
		/*Print all possible permutation of given string*/
		PracticeCode.printPermutations("abcd");		
	}
}	
