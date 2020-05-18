package practise;

import java.util.*;

/**
 * Created by amitsharma on 19/5/20.
 */
public class AnagramInString {

  public static void main(String []args) {
    String s = "cbaebabacd";
    String p = "abc";
    System.out.println(findAnagramsFinal(s,p));
  }

  /** Approach 3 : Windowing techqinue
   *
   *  Don't compute substring map every time after m char in string s just add one count for new char and
   *  minus for i-m
   *
   *
   */
  public static List<Integer> findAnagramsFinal(String s, String p) {
    int n=s.length();
    int m=p.length();
    if(n<m)
      return new ArrayList();

    int[] pfreq= new int[26];
    int[] sfreq= new int[26];
    for(int i=0;i<m;i++)
    {
      pfreq[p.charAt(i)-'a']++;
      sfreq[s.charAt(i)-'a']++;
    }

    List<Integer> result = new ArrayList();
    for(int i=m;i<n;i++)
    {
      if(Arrays.equals(pfreq,sfreq))
      {
        result.add(i-m);
      }
      sfreq[s.charAt(i)-'a']++;
      sfreq[s.charAt(i-m)-'a']--;

    }
    if(Arrays.equals(pfreq,sfreq))
    {
      result.add(n-m);
    }
    return result;
  }

  /** Approach 2 : Time Limited Exceed
   *  Count Map for String p and Maintain count map for each substring of s with m length
   *  then checked
   *
   */
  public static List<Integer> findAnagramsApproach2(String s, String p) {
    List<Integer> finalList = new ArrayList<>();
    if(s == null || s.isEmpty()) {
      return finalList;
    }


    Map<Character, Integer> countMap = new HashMap<>();
    for(int i = 0 ; i < p.length() ; i++) {
      Character c = p.charAt(i);
      if(countMap.containsKey(c)) {
        countMap.put(c, countMap.get(c) + 1);
      } else {
        countMap.put(c, 1);
      }
    }

    for(int i = 0 ; i <= s.length() - p.length();i++) {
      if(!countMap.containsKey(s.charAt(i))) continue;
      String subString = s.substring(i, i+p.length());
      boolean checkAnagram = check(subString, countMap);
      if(checkAnagram) {
        finalList.add(i);
      }
    }
    return finalList;
  }

  public static boolean check(String subString, Map<Character, Integer> countMap) {
    Map<Character, Integer> stringMap = new HashMap<>();
    for(int i = 0 ; i < subString.length() ; i++) {
      Character c = subString.charAt(i);
      if(stringMap.containsKey(c)) {
        stringMap.put(c, stringMap.get(c) + 1);
      } else {
        stringMap.put(c, 1);
      }
    }

    for(int i = 0 ; i < subString.length() ; i++) {
      Character t = subString.charAt(i);
      if(!stringMap.get(t).equals(countMap.get(t)))
        return false;
    }

    return true;

  }

  /** Approach 1 : Cons - Memory Limited Exceeded because of Stacks
   *
   *  Complexity to find anagram is O(m*m!) if m is length of string p
   *  Total time complexity O(m*m!) + O(n*m)
   */
  public static List<Integer> findAnagramsApproach1(String s, String p) {
    List<Integer> finalList = new ArrayList<>();
    if(s == null || s.isEmpty()) {
      return finalList;
    }


    List<String> anagramList = new ArrayList<>();
    findAllAnagram(p.toCharArray(), 0, anagramList);
    for(int i = 0 ; i <= s.length() - p.length();i++) {
      String subString = s.substring(i, i+p.length());
      if(anagramList.contains(subString)) {
        finalList.add(i);
      }
    }
    return finalList;

  }

  public static void findAllAnagram(char[] str, int low, List<String> anagramList) {
    if(low == str.length -1) {
      anagramList.add(new String(str));
    }

    for(int i = low; i < str.length ; i++) {
      swap(str, low, i);
      findAllAnagram(str,low+1, anagramList);
      swap(str,i, low);
    }
  }

  public static void swap(char str[], int i, int j) {
    char temp = str[i];
    str[i] = str[j];
    str[j] = temp;
  }

}
