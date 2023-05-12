package project2;

public class KMP {
  
  public int match(String T, String P) {
    /** Your code goes here */
    int n = T.length();
    int m = P.length();
    int[] F = new int[m];
    F = failureFunction(P);
    int i = 0;
    int j = 0;

    while(i < n){
      if(T.charAt(i) == P.charAt(j)){
        if(j == m - 1){
          return i - j;
        }
        else{
          i += 1;
          j += 1;
        }
      }
      else{
        if(j > 0){
          j = F[j-1];
        }
        else{
          i += 1;
        }
      }
    }

    return -1;
  }

  public int[] failureFunction(String P){
    int m = P.length();
    int[] F = new int[m];
    F[0] = 0;
    int i = 1;
    int j = 0;

    while(i < m){
      if(P.charAt(i) == P.charAt(j)){
        F[i] = j + 1;
        i += 1;
        j += 1;
      }
      else if(j > 0){
        j = F[j - 1];
      }
      else{
        F[i] = 0;
        i += 1;
      }
    }
    return F;
  }

}