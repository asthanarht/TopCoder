/**
 * This is a greedy solution.
 * 
 * @author safarisoul
 * 
 */
public class PermutationSignature {
    public int[] reconstruct(String signature) {
        int n = signature.length();
        int[] p = new int[n + 1];
        int min = 1; // next value to be placed in p
        for (int i = 0; i < n; i++) {
            // if the current signature is I, then we can put the minimum
            // available value here
            if (signature.charAt(i) == 'I') {
                p[i] = min++;
                continue;
            }
            // if the current signature is D
            // we find the end of the D sequence
            int count = i; // index of the last element in the D sequence
            while (count < n && signature.charAt(count) == 'D')
                count++;
            // start from the last element towards the first element,
            // assign them the next minimum available value
            for (int j = count; j >= i; j--, min++)
                p[j] = min;
            i = count;
        }
        // if the last char in signature is I, then p[n] is guaranteed to be
        // unsigned up to now;
        // on the other hand, if the last char in signature is D, then p[n]
        // must have been signed by now.
        p[n] = (p[n] == 0 ? min : p[n]);
        return p;
    }
}
