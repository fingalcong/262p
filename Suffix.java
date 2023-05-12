import java.util.ArrayList;
import java.util.Arrays;
    class GFG
    {
        public static class Suffix implements Comparable<Suffix>
        {
            int index;
            int rank;
            int next;

            public Suffix(int ind, int r, int nr)
            {
                index = ind;
                rank = r;
                next = nr;
            }

            public int compareTo(Suffix S)
            {
                if (rank != S.rank) return Integer.compare(rank, S.rank);
                return Integer.compare(next, S.next);
            }
        }

        public static ArrayList<Integer> suffixArray(String S)
        {
            int n = S.length();
            Suffix[] su = new Suffix[n];

            for (int i = 0; i < n; i++)
            {
                su[i] = new Suffix(i, S.charAt(i) - '$', 0);
            }
            for (int i = 0; i < n; i++)
                su[i].next = (i + 1 < n ? su[i + 1].rank : -1);

            Arrays.sort(su);

            int[] ind = new int[n];

            for (int length = 4; length < 2 * n; length <<= 1)
            {
                int rank = 0, prev = su[0].rank;
                su[0].rank = rank;
                ind[su[0].index] = 0;
                for (int i = 1; i < n; i++)
                {
                    if (su[i].rank == prev &&
                            su[i].next == su[i - 1].next)
                    {
                        prev = su[i].rank;
                        su[i].rank = rank;
                    }
                    else
                    {
                        prev = su[i].rank;
                        su[i].rank = ++rank;
                    }
                    ind[su[i].index] = i;
                }

                for (int i = 0; i < n; i++)
                {
                    int nextP = su[i].index + length / 2;
                    su[i].next = nextP < n ?
                            su[ind[nextP]].rank : -1;
                }
                Arrays.sort(su);
            }

            ArrayList<Integer> res = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                res.add(su[i].index);
                //res.set(i, su[i].index);
                System.out.println(su[i].index);
            }
            return res;
        }

        static void printArr(ArrayList<Integer> res, int n)
        {
            for (int i = 0; i < n; i++)
                System.out.print(res.get(i) + " ");
            System.out.println();
        }
        
        public static void main(String[] args)
        {
            String txt = "banana";
            int n = txt.length();
            ArrayList<Integer> suff_arr = suffixArray(txt);
            System.out.println("Following is suffix array for banana:");
            printArr(suff_arr, n);
        }
    }

