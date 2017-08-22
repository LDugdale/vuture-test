import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Testing the methods of the WordUtils class
 *
 * @author Laurie Dugdale
 */
public class WordUtilsTest {

    private WordUtils w;

    @Before
    public void setUp() {
        w = new WordUtils();
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    /**************************** Task 1 test ****************************/

    /**
     * Task 1 test
     * countLetterInString
     *
     * normal behaviour
     */
    @Test
    public void letterCountTest1(){
        // setup
        char c = 'e';
        String s = "I have some cheese";
        // expected
        int expected = 5;
        // actual
        int actual = w.countLetterInString(c, s);
        // test
        assertEquals(expected, actual);
    }

    /**
     * Task 1 test
     * countLetterInString
     *
     * no matches
     */
    @Test
    public void letterCountTest2(){
        // setup
        char c = 'y';
        String s = "I have some cheese";
        // expected
        int expected = 0;
        // actual
        int actual = w.countLetterInString(c, s);
        // test
        assertEquals(expected, actual);
    }

    /**
     * Task 1 test
     * countLetterInString
     *
     * empty string
     */
    @Test
    public void letterCountTest3(){
        // setup
        char c = 'y';
        String s = "";
        // expected
        int expected = 0;
        // actual
        int actual = w.countLetterInString(c, s);
        // test
        assertEquals(expected, actual);
    }

    /**
     * Task 1 test
     * countLetterInString
     *
     * null string
     */
    @Test
    public void letterCountTest4(){
        // exception setup
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("text String was null");
        // setup
        char c = 'y';
        String s = null;
        // expected
        int expected = 0;
        // actual
        int actual = w.countLetterInString(c, s);
        // test
        assertEquals(expected, actual);
    }

    /**
     * Task 1 test
     * countLetterInString
     *
     * all matches
     */
    @Test
    public void letterCountTest5(){
        // setup
        char c = 'a';
        String s = "aaaaaaaaaa";
        // expected
        int expected = 10;
        // actual
        int actual = w.countLetterInString(c, s);
        // test
        assertEquals(expected, actual);
    }

    /**************************** Task 2 test ****************************/

    /**
     * Task 2 test
     * isPalindrome
     *
     * normal false behaviour
     */
    @Test
    public void isPalindromeTest1(){
        // setup
        String s = "I have some cheese";
        // test
        assertFalse(w.isPalindrome(s));
    }

    /**
     * Task 2 test
     * isPalindrome
     *
     * normal true behaviour
     */
    @Test
    public void isPalindromeTest2(){
        // setup
        String s = "God saved Eva’s dog";
        // test
        assertTrue(w.isPalindrome(s));
    }

    /**
     * Task 2 test
     * isPalindrome
     *
     * null value
     */
    @Test
    public void isPalindromeTest3(){
        // setup
        String s = null;
        // test
        assertTrue(w.isPalindrome(s));
    }

    /**
     * Task 2 test
     * isPalindrome
     *
     * empty String
     */
    @Test
    public void isPalindromeTest4(){
        // setup
        String s = "";
        // test
        assertTrue(w.isPalindrome(s));
    }

    /**
     * Task 2 test
     * isPalindrome
     *
     * unecessary characters
     */
    @Test
    public void isPalindromeTest5(){
        // setup
        String s = "//@~!£$%^&*()_+-+={[}]~#':;?/>.<,|\\¬`c.i.v.i./c/;/";
        // test
        assertTrue(w.isPalindrome(s));
    }

    /**************************** Task 3a test ****************************/

    /**
     * Task 3a test
     * countCensoredWords
     *
     * normal behaviour with matches
     */
    @Test
    public void countCensoredWords1(){
        // setup
        String [] a = {"dog", "cat", "large"};
        String s = "I have a cat named Meow and a dog name Woof. I love the dog a lot. He is larger than a small horse.";
        // expected
        String expected = "cat: 1, dog: 2, large: 1, total: 4";
        // actual
        String actual = w.countCensoredWords(a, s);
        // test
        assertEquals(expected, actual);
    }

    /**
     * Task 3a test
     * countCensoredWords
     *
     * normal behaviour with no matches
     */
    @Test
    public void countCensoredWords2(){
        // setup
        String [] a = {"hello", "goodbye", "bonsoir"};
        String s = "I have a cat named Meow and a dog name Woof. I love the dog a lot. He is larger than a small horse.";
        // expected
        String expected = "total: 0";
        // actual
        String actual = w.countCensoredWords(a, s);
        // test
        assertEquals(expected, actual);
    }

    /**
     * Task 3a test
     * countCensoredWords
     *
     * all matches
     */
    @Test
    public void countCensoredWords3(){
        // setup
        String [] a = {"hello", "goodbye", "bonsoir"};
        String s = "hello goodbye bonsoir hello goodbye bonsoir hello goodbye bonsoir";
        // expected
        String expected = "bonsoir: 3, goodbye: 3, hello: 3, total: 9";
        // actual
        String actual = w.countCensoredWords(a, s);
        // test
        assertEquals(expected, actual);
    }

    /**
     * Task 3a test
     * countCensoredWords
     *
     * null array
     */
    @Test
    public void countCensoredWords4(){
        // exception setup
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("censored array was null");
        // setup
        String [] a = null;
        String s = "hello goodbye bonsoir hello goodbye bonsoir hello goodbye bonsoir";
        // expected
        String expected = "bonsoir: 3, goodbye: 3, hello: 3, total: 9";
        // actual
        String actual = w.countCensoredWords(a, s);
        // test
        assertEquals(expected, actual);
    }

    /**
     * Task 3a test
     * countCensoredWords
     *
     * null String
     */
    @Test
    public void countCensoredWords5(){
        // exception setup
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("text String was null");
        // setup
        String [] a = {"hello", "goodbye", "bonsoir"};
        String s = null;
        // expected
        String expected = "bonsoir: 3, goodbye: 3, hello: 3, total: 9";
        // actual
        String actual = w.countCensoredWords(a, s);
        // test
        assertEquals(expected, actual);
    }

    /**************************** Task 3b test ****************************/

    /**
     * Task 3b test
     * censorWords
     *
     * null array
     */
    @Test
    public void censorWords1(){
        // exception setup
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("censored array was null");
        // setup
        String [] a = null;
        String s = "hello goodbye bonsoir hello goodbye bonsoir hello goodbye bonsoir";
        // expected
        String expected = "bonsoir: 3, goodbye: 3, hello: 3, total: 9";
        // actual
        String actual = w.censorWords(a, s);
        // test
        assertEquals(expected, actual);
    }

    /**
     * Task 3b test
     * censorWords
     *
     * null String
     */
    @Test
    public void censorWords2(){
        // exception setup
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("text String was null");
        // setup
        String [] a = {"hello", "goodbye", "bonsoir"};
        String s = null;
        // expected
        String expected = "bonsoir: 3, goodbye: 3, hello: 3, total: 9";
        // actual
        String actual = w.censorWords(a, s);
        // test
        assertEquals(expected, actual);
    }


    /**
     * Task 3b test
     * censorWords
     *
     * normal behaviour with matches
     */
    @Test
    public void censorWords3(){
        // setup
        String [] a = {"meow", "woof"};
        String s = "I have a cat named Meow and a dog name Woof. I love the dog a lot. He is larger than a small horse.";
        // expected
        String expected = "I have a cat named M$$w and a dog name W$$f. I love the dog a lot. He is larger than a small horse.";
        // actual
        String actual = w.censorWords(a, s);
        // test
        assertEquals(expected, actual);
    }

    /**
     * Task 3b test
     * censorWords
     *
     * normal behaviour with no matches
     */
    @Test
    public void censorWords4(){
        // setup
        String [] a = {"baa", "tweet"};
        String s = "I have a cat named Meow and a dog name Woof. I love the dog a lot. He is larger than a small horse.";
        // expected
        String expected = "I have a cat named Meow and a dog name Woof. I love the dog a lot. He is larger than a small horse.";
        // actual
        String actual = w.censorWords(a, s);
        // test
        assertEquals(expected, actual);
    }


    /**
     * Task 3b test
     * censorWords
     *
     * extra characters
     */
    @Test
    public void censorWords5(){
        // setup
        String [] a = {"meow", "woof"};
        String s = "I have a cat named Meow's and a dog name pleaseWooflater. I love the dog a lotmoreifitwouldwoofless. He is larger than a small horse.";
        // expected
        String expected = "I have a cat named M$$$$s and a dog name p$$$$$$$$$$$$$r. I love the dog a l$$$$$$$$$$$$$$$$$$$$$$s. He is larger than a small horse.";
        // actual
        String actual = w.censorWords(a, s);
        // test
        assertEquals(expected, actual);
    }

    /**
     * Task 3b test
     * censorWords
     *
     * everything matched
     */
    @Test
    public void censorWords6(){
        // setup
        String [] a = {"meow", "woof"};
        String s = "meow woof meow woof meow woof meow woof";
        // expected
        String expected = "m$$w w$$f m$$w w$$f m$$w w$$f m$$w w$$f";
        // actual
        String actual = w.censorWords(a, s);
        // test
        assertEquals(expected, actual);
    }

    /**************************** Task 3c test ****************************/

    /**
     * Task 3c test
     * censorSingleWordPalindromes
     *
     * normal behaviour
     */
    @Test
    public void censorSingleWordPalindromes1(){
        // setup
        String s = "Anna went to vote in the election to fulfil her civic duty";
        // expected
        String expected = "A$$a went to vote in the election to fulfil her c$$$c duty";
        // actual
        String actual = w.censorSingleWordPalindromes(s);
        // test
        assertEquals(expected, actual);
    }

    /**
     * Task 3c test
     * censorSingleWordPalindromes
     *
     * normal behaviour no matches
     */
    @Test
    public void censorSingleWordPalindromes2(){
        // setup
        String s = "Alina went to vote in the election to fulfil her very important duty";
        // expected
        String expected = "Alina went to vote in the election to fulfil her very important duty";
        // actual
        String actual = w.censorSingleWordPalindromes(s);
        // test
        assertEquals(expected, actual);
    }

    /**
     * Task 3c test
     * censorSingleWordPalindromes
     *
     * all matches
     */
    @Test
    public void censorSingleWordPalindromes3(){
        // setup
        String s = "bob anna civic powop libertyytrebil freedomodeerf";
        // expected
        String expected = "b$b a$$a c$$$c p$$$p l$$$$$$$$$$$$l f$$$$$$$$$$$f";
        // actual
        String actual = w.censorSingleWordPalindromes(s);
        // test
        assertEquals(expected, actual);
    }

    /**
     * Task 3c test
     * censorSingleWordPalindromes
     *
     * all matches
     */
    @Test
    public void censorSingleWordPalindromes4(){
        // setup
        String s = "bob anna civic powop libertyytrebil freedomodeerf";
        // expected
        String expected = "b$b a$$a c$$$c p$$$p l$$$$$$$$$$$$l f$$$$$$$$$$$f";
        // actual
        String actual = w.censorSingleWordPalindromes(s);
        // test
        assertEquals(expected, actual);
    }

    /**
     * Task 3c test
     * censorSingleWordPalindromes
     *
     * extra characters matches
     */
    @Test
    public void censorSingleWordPalindromes5(){
        // setup
        String s = "bob anna civic spowop's liberty&ytrebil freedomodeerf setup-putes";
        // expected
        String expected = "b$b a$$a c$$$c s$$$$$$s l$$$$$$$$$$$$$l f$$$$$$$$$$$f s$$$$$$$$$s";
        // actual
        String actual = w.censorSingleWordPalindromes(s);
        // test
        assertEquals(expected, actual);
    }

    /**
     * Task 3c test
     * censorSingleWordPalindromes
     *
     * testing null String
     */
    @Test
    public void censorSingleWordPalindromes6(){
        // exception setup
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("text String was null");
        // setup
        String s = null;
        // expected
        String expected = "b$b a$$a c$$$c s$$$$$$s l$$$$$$$$$$$$$l f$$$$$$$$$$$f s$$$$$$$$$s";
        // actual
        String actual = w.censorSingleWordPalindromes(s);
        // test
        assertEquals(expected, actual);
    }
}

