package project3;

import java.util.HashMap;
import java.util.HashSet;

public class Bitap {
  
  public int match(String T, String P) {
    /** Your code goes here */
    int m = P.length();
    int n = T.length();
    int Mask = 1;
    for(int i = 0; i < m - 1; i++){
      Mask = (Mask << 1) + 1;
    }
    //System.out.println(Mask);

    HashSet<Character> alphabet = new HashSet<>();
    for(int i = 0; i < m; i++){
      alphabet.add(P.charAt(i));
    }
    for(int j = 0; j < n; j++){
      alphabet.add(T.charAt(j));
    }

    HashMap<Character, Integer> map = new HashMap<>();
    for(char a : alphabet){
      map.put(a,0);
    }

    for(int i = m - 1; i >= 0; i--){
      for(char a: alphabet){
        if(i != 0) {
          if (P.charAt(i) == a) {
            int temp = map.get(a);
            temp = temp << 1;
            map.put(a, temp);
          } else {
            int temp = map.get(a);
            temp = (temp + 1) << 1;
            map.put(a, temp);
          }
        }
        else{
          if (P.charAt(i) == a) {
            int temp = map.get(a);
            map.put(a, temp);
          } else {
            int temp = map.get(a);
            temp = temp + 1;
            map.put(a, temp);
          }
        }
      }
    }
    /*System.out.println(map.get('a'));
    System.out.println(map.get('b'));
    System.out.println(map.get('c'));*/
    //System.out.println(map.get(''));

    int x = 0;
    int num = (int) Math.pow(2,m-1);
    //System.out.println(num);
    for(int j = 0; j < n; j++){
      x = ((x << 1) & Mask) | map.get(T.charAt(j));
      if((x < num) && (j >= m-1)){
        //System.out.println(x);
        return j - m + 1;
      }
    }
    //System.out.println(60 | 29);
    return -1;
  }

}
