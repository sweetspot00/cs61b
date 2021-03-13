import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }
    @Test
    public void testisPalindrome() {
        String a = "";
        String b = "b";
        String c = "asdsa";
        String d = "aaal";
        String e = "AaAa";
        String f = "acca";
        assertFalse(palindrome.isPalindrome(d));
        assertFalse(palindrome.isPalindrome(e));
        assertTrue(palindrome.isPalindrome(b));
        assertTrue(palindrome.isPalindrome(a));
        assertTrue(palindrome.isPalindrome(c));
        assertTrue(palindrome.isPalindrome(f));
    }
    @Test
    public void testisPalindrome1() {
        CharacterComparator cc = new OffByOne();
        String a = "flake";
        String b = "arqb";
        String c = "";
        String d = "s";
        String e = "aaaa";
        assertTrue(palindrome.isPalindrome(a, cc));
        assertTrue(palindrome.isPalindrome(b, cc));
        assertTrue(palindrome.isPalindrome(c, cc));
        assertTrue(palindrome.isPalindrome(d, cc));
        assertFalse(palindrome.isPalindrome(e, cc));

    }
}
