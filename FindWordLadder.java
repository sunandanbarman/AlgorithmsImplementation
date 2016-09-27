import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
/*
* Reference Question :
* https://leetcode.com/problems/word-ladder/
*/
public class FindWordLadder {
    public static int ladderLength(String beginWord, String endWord, Set<String> wordList) {
        if (beginWord == null || endWord == null ) {
            return 0;
        }
        if (beginWord.length() != endWord.length()) {
            return 0;
        }
        if (beginWord.trim().length() == 0 || endWord.trim().length() == 0) {
            return 0;
        }
        if (beginWord.equals(endWord)) {
            return 0;
        }
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        char[] chArr = new char[beginWord.length()];
        wordList.add(endWord);
        
        LinkedList<String> list = new LinkedList<>();
        list.add(beginWord);
        int levels = 1;
        while(!list.isEmpty()) {
            int size = list.size();
            for(int k=0; k < size; k++) {
                String sTemp = list.removeFirst();
                chArr = sTemp.toCharArray();
                for(int i=0; i< chArr.length; i++) {
                    chArr = sTemp.toCharArray();
                    
                    for(char c='a'; c <= 'z'; c++) {
                        chArr[i] = c;
                        String s = new String(chArr);
                        if (s.equals(endWord)) {
                        	levels++;
                            return levels;
                        }
                        if (wordList.contains(s) && !visited.contains(s)) {
                            visited.add(s);
                            list.add(s);
                        }
                    }
                }            	
            }
            levels++;
            
        }
        
        return 0;
    }    
	public static void main(String[] args) 
	{
		Set<String> wordList = new HashSet<>();

		wordList.add("hot");
		wordList.add("cog");
		wordList.add("dot");
		wordList.add("dog");		
		wordList.add("hit");
		wordList.add("lot");
		wordList.add("log");
		System.out.println(ladderLength("hit","cog",wordList));
		
	}
}