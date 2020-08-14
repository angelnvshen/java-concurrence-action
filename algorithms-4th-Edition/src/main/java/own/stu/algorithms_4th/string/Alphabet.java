package own.stu.algorithms_4th.string;

public class Alphabet {

    private char[] alphabet;     // the characters in the alphabet

    private final int R;         // the radix of the alphabet

    private int[] inverse;

    public Alphabet(String s) {

        boolean[] check = new boolean[Character.MAX_VALUE];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (check[i]) {
                throw new IllegalArgumentException("Illegal alphabet: repeated character = '" + c + "'");
            }
            check[i] = true;
        }
        R = s.length();
        alphabet = s.toCharArray();

        inverse = new int[Character.MAX_VALUE];
        for (int i = 0; i < inverse.length; i++) {
            inverse[i] = -1;
        }

        for (int i = 0; i < R; i++) {
            inverse[alphabet[i]] = i;
        }

    }

    private Alphabet(int radix) {
        R = radix;
        alphabet = new char[R];
        inverse = new int[R];

        for (int i = 0; i < R; i++) {
            alphabet[i] = (char) i;
            inverse[i] = i;
        }
    }

    public char toChar(int index) {
        if (index < 0 || index >= R) {
            throw new IllegalArgumentException("index must be between 0 and " + R + ": " + index);
        }
        return alphabet[index];
    }

    public int toIndex(char c) {
        if (c >= inverse.length || inverse[c] == -1) {
            throw new IllegalArgumentException("Character " + c + " not in alphabet");
        }

        return inverse[c];
    }

    public boolean contains(char c) {
        return toIndex(c) != -1;
    }

    public int R() {
        return R;
    }

    public int lgR() {
        int lgR = 0;
        for (int r = R; r > 0; r = r / 2) {
            lgR++;
        }
        return lgR;
    }

    public int[] toIndices(String s) {
        char[] chars = s.toCharArray();
        int result[] = new int[s.length()];
        for (int i = 0; i < chars.length; i++) {
            result[i] = toIndex(chars[i]);
        }
        return result;
    }

    public String toChars(int[] indices) {

        StringBuilder sb = new StringBuilder(indices.length);
        for (int i = 0; i < indices.length; i++) {
            sb.append(toChar(indices[i]));
        }
        return sb.toString();
    }

    /**
     * The binary alphabet { 0, 1 }.
     */
    public static final Alphabet BINARY = new Alphabet("01");

    /**
     * The octal alphabet { 0, 1, 2, 3, 4, 5, 6, 7 }.
     */
    public static final Alphabet OCTAL = new Alphabet("01234567");

    /**
     * The decimal alphabet { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 }.
     */
    public static final Alphabet DECIMAL = new Alphabet("0123456789");

    /**
     * The hexadecimal alphabet { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, A, B, C, D, E, F }.
     */
    public static final Alphabet HEXADECIMAL = new Alphabet("0123456789ABCDEF");

    /**
     * The DNA alphabet { A, C, T, G }.
     */
    public static final Alphabet DNA = new Alphabet("ACGT");

    /**
     * The lowercase alphabet { a, b, c, ..., z }.
     */
    public static final Alphabet LOWERCASE = new Alphabet("abcdefghijklmnopqrstuvwxyz");

    /**
     * The uppercase alphabet { A, B, C, ..., Z }.
     */

    public static final Alphabet UPPERCASE = new Alphabet("ABCDEFGHIJKLMNOPQRSTUVWXYZ");

    /**
     * The protein alphabet { A, C, D, E, F, G, H, I, K, L, M, N, P, Q, R, S, T, V, W, Y }.
     */
    public static final Alphabet PROTEIN = new Alphabet("ACDEFGHIKLMNPQRSTVWY");

    /**
     * The base-64 alphabet (64 characters).
     */
    public static final Alphabet BASE64 = new Alphabet("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/");

    /**
     * The ASCII alphabet (0-127).
     */
    public static final Alphabet ASCII = new Alphabet(128);

    /**
     * The extended ASCII alphabet (0-255).
     */
    public static final Alphabet EXTENDED_ASCII = new Alphabet(256);

    /**
     * The Unicode 16 alphabet (0-65,535).
     */
    public static final Alphabet UNICODE16 = new Alphabet(65536);

    public static void main(String[] args) {
        Alphabet alphabet = Alphabet.DECIMAL;
        System.out.println(alphabet.toChar(1));
        System.out.println(alphabet.toIndex('1'));
        for(int i : alphabet.toIndices("455823")){
            System.out.println(i);
        }
    }
}
