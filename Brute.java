package project2;

public class Brute {

  public int match(String T, String P) {
    /** Your code goes here */
    int n = T.length();
    if(n == 0){
      return -1;
    }
    int m = P.length();

    for(int i = 0; i <= n-m; i++){
      int j = 0;
      while(j < m && T.charAt(i + j) == P.charAt(j)){
        j += 1;
      }
      if(j == m){
        return i;
      }
      /*else{
        //break;
      }*/
    }

    return -1;
  }

}
