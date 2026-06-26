package org.example;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class AppTest {

    @Test
    void addReturnsTheSumOfTwoIntegers() {
        assertEquals(5, App.add(2, 3));
        assertEquals(-1, App.add(2, -3));
        assertNotEquals(6, App.add(2, 3));
    }

    @Test
    void isPrimeIdentifiesPrimeAndCompositeNumbers() {
        assertTrue(App.isPrime(2));
        assertTrue(App.isPrime(17));
        assertFalse(App.isPrime(1));
        assertFalse(App.isPrime(18));
    }

    @Test
    void reverseReturnsTheReversedString() {
        assertEquals("cba", App.reverse("abc"));
        assertEquals("", App.reverse(""));
        assertNotEquals("abc", App.reverse("abc"));
    }

    @Test
    void factorialReturnsExpectedValuesAndRejectsNegatives() {
        assertEquals(1, App.factorial(0));
        assertEquals(120, App.factorial(5));
        assertThrows(IllegalArgumentException.class, () -> App.factorial(-1));
    }

    @Test
    void isPalindromeHandlesPalindromesAndNonPalindromes() {
        assertTrue(App.isPalindrome("racecar"));
        assertTrue(App.isPalindrome("A man, a plan, a canal: Panama"));
        assertFalse(App.isPalindrome("hello"));
    }

    @Test
    void fibonacciUpToReturnsExpectedSequenceAndRejectsNegatives() {
        assertIterableEquals(List.of(0, 1, 1, 2, 3, 5), App.fibonacciUpTo(5));
        assertThrows(IllegalArgumentException.class, () -> App.fibonacciUpTo(-1));
    }

    @Test
    void charFrequencyCountsCharactersCorrectly() {
        Map<Character, Integer> frequency = App.charFrequency("banana");

        assertNotNull(frequency);
        assertEquals(3, frequency.size());
        assertAll(
                "frequency checks",
                () -> assertEquals(3, frequency.get('a')),
                () -> assertEquals(2, frequency.get('n')),
                () -> assertEquals(1, frequency.get('b')));
    }

    @Test
    void isAnagramDetectsAnagramsAndNonAnagrams() {
        assertTrue(App.isAnagram("listen", "silent"));
        assertTrue(App.isAnagram("A gentleman", "elegant man"));
        assertFalse(App.isAnagram("hello", "world"));
    }

    @Test
    void averageCalculatesMeanAndRejectsEmptyArray() {
        assertEquals(3.0, App.average(new int[] {1, 2, 3, 4, 5}));
        assertEquals(2.5, App.average(new int[] {1, 4}), 0.0001);
        assertThrows(IllegalArgumentException.class, () -> App.average(new int[] {}));
    }

    @Test
    void filterEvensReturnsOnlyEvenValues() {
        assertIterableEquals(List.of(2, 4, 6), App.filterEvens(List.of(1, 2, 3, 4, 5, 6)));
        assertTrue(App.filterEvens(List.of(1, 3, 5)).isEmpty());
    }

    @Test
    void mostCommonWordReturnsTheMostFrequentWord() {
        assertEquals("three", App.mostCommonWord("one two two three three three four"));
        assertEquals("hello", App.mostCommonWord("Hello, hello, world!"));
    }
}
