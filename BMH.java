package project2;

import java.util.HashMap;
import java.util.HashSet;

public class BMH {
  
  public int match(String T, String P) {
    /** Your code goes here */
    int n = T.length();
    int m = P.length();
    HashSet<Character> set = new HashSet<>();
    for(int a = 0; a < T.length(); a++){
      set.add(T.charAt(a));
    }
    HashMap<Character, Integer> L = lastOccurenceFunction(P, set);
    if(L == null){
      return -1;
    }
    int i = m - 1;
    int j = m - 1;
    while(i < n){
      if(T.charAt(i) == P.charAt(j)){
        if(j == 0){
          return i;
        }
        else{
          i -= 1;
          j -= 1;
        }
      }
      else{
        int l = L.get(T.charAt(i));
        i = i + m - Math.min(j, 1 + l);
        j = m - 1;
      }
    }

    return -1;
  }

  public HashMap<Character, Integer> lastOccurenceFunction(String P, HashSet<Character> set){
    HashMap<Character, Integer> map = new HashMap<>();
    for(char x : set){
      map.put(x, -1);
    }
    int m = P.length();
    for(int i = 0; i < m; i++){
      if(!map.containsKey(P.charAt(i))){
        return null;
      }
      else{
        map.replace(P.charAt(i), i);
      }
    }
    return map;
  }

}
