package project3;

import java.util.HashSet;

public class RK {
  
  public int match(String T, String P) {
    /** Your code goes here */
    int len_p = P.length();
    int len_T = T.length();
    int h = 1;

    HashSet<Character> alpha_count = new HashSet<>();
    for(int i = 0; i < len_T; i++){
      alpha_count.add(T.charAt(i));
    }
    for(int j = 0; j < len_p; j++){
      alpha_count.add(P.charAt(j));
    }
    int b = alpha_count.size();

    int hash_p = hash_function(P, len_p, b);
    int hash_t = hash_function(T, len_p, b);

    for (int i = 0; i < len_p - 1; i++) {
      h = h * b;
    }

    for(int i = 0; i <= len_T - len_p; i++){
      if(hash_p == hash_t){
        int j;
        for(j = 0; j < len_p; j++){
          if(T.charAt(i + j) != P.charAt(j)){
            break;
          }
        }
          if(j == len_p){
            return i;
          }
      }
      if(i < len_T - len_p){
        hash_t = b * (hash_t - T.charAt(i) * h) + T.charAt(i + len_p);
      }
    }
    return -1;
  }

  public int hash_function(String input, int len_p, int b){
    int result = 0;
    for(int i = 0; i < len_p; i++){
      result = b * result + input.charAt(i);
    }
    return result;
  }

}
