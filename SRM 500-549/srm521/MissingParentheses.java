public class MissingParentheses {
    public int countCorrections(String par) {
        while (par.contains("()"))
            par = par.replace("()", "");
        return par.length();
    }
}
