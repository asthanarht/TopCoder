package srm574;

public class TheNumberGame {
    public String determineOutcome(int A, int B) {
        String a = "" + A;
        String b = "" + B;
        String aa = new StringBuffer(a).reverse().toString();
        if (a.indexOf(b) >= 0 || aa.indexOf(b) >= 0)
            return "Manao wins";
        return "Manao loses";
    }
}
