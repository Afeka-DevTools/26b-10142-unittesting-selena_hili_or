package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class AppTest {

    @ParameterizedTest(name = "add({0}, {1}) = {2}")
    @CsvSource({
            "0, 0, 0",
            "2, 3, 5",
            "-4, 9, 5",
            "-7, -8, -15"
    })
    void add_returnsSumForPositiveNegativeAndZeroInputs(int left, int right, int expected) {
        // Arrange

        // Act
        int result = App.add(left, right);

        // Assert
        assertEquals(expected, result);
    }

    @ParameterizedTest(name = "isPrime({0}) = {1}")
    @MethodSource("primeCases")
    void isPrime_identifiesPrimesCompositesAndValuesBelowTwo(int value, boolean expected) {
        // Arrange

        // Act
        boolean result = App.isPrime(value);

        // Assert
        if (expected) {
            assertTrue(result);
        } else {
            assertFalse(result);
        }
    }

    private static Stream<Arguments> primeCases() {
        return Stream.of(
                Arguments.of(-5, false),
                Arguments.of(0, false),
                Arguments.of(1, false),
                Arguments.of(2, true),
                Arguments.of(3, true),
                Arguments.of(4, false),
                Arguments.of(17, true),
                Arguments.of(21, false)
        );
    }

    @ParameterizedTest(name = "reverse(\"{0}\") = \"{1}\"")
    @MethodSource("reverseCases")
    void reverse_handlesEmptySingleCharacterAndMultiCharacterStrings(String input, String expected) {
        // Arrange

        // Act
        String result = App.reverse(input);

        // Assert
        assertEquals(expected, result);
    }

    private static Stream<Arguments> reverseCases() {
        return Stream.of(
                Arguments.of("", ""),
                Arguments.of("a", "a"),
                Arguments.of("abcde", "edcba"),
                Arguments.of("race car", "rac ecar")
        );
    }

    @ParameterizedTest(name = "factorial({0}) = {1}")
    @CsvSource({
            "0, 1",
            "1, 1",
            "5, 120",
            "6, 720"
    })
    void factorial_returnsExpectedValuesForZeroAndPositiveInputs(int input, int expected) {
        // Arrange

        // Act
        int result = App.factorial(input);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    void factorial_throwsForNegativeInput() {
        // Arrange

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> App.factorial(-1));
    }

    @ParameterizedTest(name = "isPalindrome(\"{0}\") = {1}")
    @MethodSource("palindromeCases")
    void isPalindrome_ignoresPunctuationAndCase(String input, boolean expected) {
        // Arrange

        // Act
        boolean result = App.isPalindrome(input);

        // Assert
        if (expected) {
            assertTrue(result);
        } else {
            assertFalse(result);
        }
    }

    private static Stream<Arguments> palindromeCases() {
        return Stream.of(
                Arguments.of("RaceCar", true),
                Arguments.of("A man, a plan, a canal: Panama!", true),
                Arguments.of("No lemon, no melon", true),
                Arguments.of("OpenAI", false)
        );
    }

    @ParameterizedTest(name = "fibonacciUpTo({0}) = {1}")
    @MethodSource("fibonacciCases")
    void fibonacciUpTo_returnsSequenceUpToInclusiveBound(int input, List<Integer> expected) {
        // Arrange

        // Act
        List<Integer> result = App.fibonacciUpTo(input);

        // Assert
        assertEquals(expected, result);
    }

    private static Stream<Arguments> fibonacciCases() {
        return Stream.of(
                Arguments.of(0, List.of(0)),
                Arguments.of(1, List.of(0, 1, 1)),
                Arguments.of(8, List.of(0, 1, 1, 2, 3, 5, 8)),
                Arguments.of(20, List.of(0, 1, 1, 2, 3, 5, 8, 13))
        );
    }

    @Test
    void fibonacciUpTo_throwsForNegativeInput() {
        // Arrange

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> App.fibonacciUpTo(-1));
    }

    @Test
    void charFrequency_countsRepeatedCharacters() {
        // Arrange
        String input = "aabccc";

        // Act
        Map<Character, Integer> result = App.charFrequency(input);

        // Assert
        assertEquals(Map.of('a', 2, 'b', 1, 'c', 3), result);
    }

    @Test
    void charFrequency_returnsEmptyMapForEmptyString() {
        // Arrange

        // Act
        Map<Character, Integer> result = App.charFrequency("");

        // Assert
        assertEquals(Map.of(), result);
    }

    @ParameterizedTest(name = "isAnagram(\"{0}\", \"{1}\") = {2}")
    @MethodSource("anagramCases")
    void isAnagram_handlesSpacesCaseDifferencesAndNonAnagrams(String left, String right, boolean expected) {
        // Arrange

        // Act
        boolean result = App.isAnagram(left, right);

        // Assert
        if (expected) {
            assertTrue(result);
        } else {
            assertFalse(result);
        }
    }

    private static Stream<Arguments> anagramCases() {
        return Stream.of(
                Arguments.of("Dormitory", "Dirty room", true),
                Arguments.of("The eyes", "They see", true),
                Arguments.of("listen", "silent", true),
                Arguments.of("binary", "brainy", true),
                Arguments.of("hello", "world", false)
        );
    }

    @ParameterizedTest(name = "average({0}) = {1}")
    @MethodSource("averageCases")
    void average_returnsExpectedValueForPositiveNegativeMixes(int[] input, double expected) {
        // Arrange

        // Act
        double result = App.average(input);

        // Assert
        assertEquals(expected, result, 0.000001);
    }

    private static Stream<Arguments> averageCases() {
        return Stream.of(
                Arguments.of(new int[] {1, 2, 3, 4}, 2.5),
                Arguments.of(new int[] {-3, 3}, 0.0),
                Arguments.of(new int[] {-10, 5, 15}, 3.3333333333333335)
        );
    }

    @Test
    void average_throwsForEmptyArray() {
        // Arrange

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> App.average(new int[0]));
    }

    @ParameterizedTest(name = "filterEvens({0}) = {1}")
    @MethodSource("filterEvensCases")
    void filterEvens_returnsOnlyEvenNumbers(List<Integer> input, List<Integer> expected) {
        // Arrange

        // Act
        List<Integer> result = App.filterEvens(input);

        // Assert
        assertEquals(expected, result);
    }

    private static Stream<Arguments> filterEvensCases() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), List.of(2, 4, 6)),
                Arguments.of(List.of(2, 4, 8), List.of(2, 4, 8)),
                Arguments.of(List.of(1, 3, 5, 7), List.of()),
                Arguments.of(List.of(), List.of())
        );
    }

    @Test
    void mostCommonWord_returnsMostFrequentWordIgnoringPunctuationAndCase() {
        // Arrange
        String input = "Apple! banana, apple; BANANA? apple.";

        // Act
        String result = App.mostCommonWord(input);

        // Assert
        assertEquals("apple", result);
    }
}
