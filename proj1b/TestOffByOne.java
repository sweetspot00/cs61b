import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();
    @Test
    public void testequalChars() {
        char a = 'a';
        char b = 'b';
        char c = 's';
        char d = 'q';
        char e = 'A';
        char f = 'b';
        char g = '$';
        char h = '(';
        char i = ')'
        assertTrue(offByOne.equalChars(a, b));
        assertFalse(offByOne.equalChars(c, d));
        assertFalse(offByOne.equalChars(e, f));
        assertFalse(offByOne.equalChars(g, h));
        assertTrue(offByOne.equalChars(h, i));
    }

    // Your tests go here.
}
