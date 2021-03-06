# AlgorithmsImplementation
Implementation of algorithms of various problems

Algorithms so far implemented are :

1. Optimizing matrix chain multiplication

2. Computation x^y in logn time where y =2^n using DaC strategy

3. Strassen algorithm implementation , with facility to generate log file which can be used to verify the manual calculations with computed values and vice versa. See the description of the file on explanation of how to generate the log file.

4. Longest common subsequence finder between two strings using dynamic programming technique. Note here there is no facility added to generate the log files as I did in Strassens Algorithm. I feel that it is not necessary, but if anyone requests a facility like this, I will be happy to add ( or someone can contribute this facility ! ) . The maximum length is fixed at 3000, it was tested on a 8 GB RAM machine with i7 processor and Windows 8.1. Dependent on machine configuration, the maximum length allowed may need to be reduced, please take care of this before specifying extra-ordinarily long sequences. If the maximum length is not adhered to, <em>stack overflow <b>may</b> occur</em>.

5. 0/1 Knapsack algorithm implementation using dynamic programming technique. Algorithm as described in CLRS book, works in O(n*K) time , where n : number of items, and K: knapsack weight

6. Major sorting algorithms ( Merge Sort, Heapsort, Insertion sort, Selection sort, Radix sort, Bucket sort) . This is an ongoing work right now.

7. Recursive implementation to solve Tower of Hanoi using pegs A, B and C. Peg A is initial , B is final peg, and C is the intermediate peg. 

8. Sieve of Eratosthenes implementation

9. Simple Balanced expression checker in Java using Stack. This was asked to me in coding round for Moziila firefox. I felt it would be be a good practice to do it in Java. I can't give out exact question detail because I don't remember :)

10. 3-way quick sort

11. Non recursive quicksort using stack

The algorithms added/ to be added will/ do cover a variety of areas, such as graph theory, computational geometry etc.
Major inspiration for dynamic algorithms solutions taken from CLRS book
