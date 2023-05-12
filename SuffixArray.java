package project5;

import java.util.*;

class SuffixArray {
  public static class Suffix implements Comparable<Suffix>
  {
    int num;
    int amount;
    int following;

    public Suffix(int numm, int am, int foll)
    {
      num = numm;
      amount = am;
      following = foll;
    }

    public int compareTo(Suffix S)
    {
      if (amount != S.amount){
        return Integer.compare(amount, S.amount);
      }
      return Integer.compare(following, S.following);
    }
  }

  public static ArrayList<Integer> construct(String S)
  {
    int n = S.length();
    if(n == 0){
      System.out.println("empty input");
    }
    Suffix[] su = new Suffix[n];
    int lengthofit = su.length;
    if(lengthofit == 0){
      System.out.println("empty su");
    }
    for (int i = 0; i < n; i++)
    {
      su[i] = new Suffix(i, S.charAt(i) - '$', 0);
    }
    for (int i = 0; i < n; i++)
      su[i].following = (i + 1 < n ? su[i + 1].amount : -1);

    Arrays.sort(su);

    int[] numm = new int[n];

    for (int length = 4; length < 2 * n; length <<= 1)
    {

      int amount = 0, prev = su[0].amount;
      su[0].amount = amount;
      numm[su[0].num] = 0;
      for (int i = 1; i < n; i++)
      {
        if (su[i].amount == prev &&
                su[i].following == su[i - 1].following)
        {
          prev = su[i].amount;
          su[i].amount = amount;
        }
        else
        {
          prev = su[i].amount;
          su[i].amount = ++amount;
        }
        numm[su[i].num] = i;
      }

      for (int i = 0; i < n; i++)
      {
        int nextP = su[i].num + length / 2;
        su[i].following = nextP < n ?
                su[numm[nextP]].amount : -1;
      }

      Arrays.sort(su);
    }

    ArrayList<Integer> res = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      res.add(su[i].num);
      //res.set(i, su[i].num);
      //System.out.println(su[i].num);
    }
    return res;
  }
  /*public static void main(String[] args) {
    String S = "mississippi";
    ArrayList<Integer> res = construct(S);
  }*/
}
