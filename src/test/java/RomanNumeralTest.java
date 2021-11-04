import de.sebastianzander.romannumerals.RomanNumeral;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class RomanNumeralTest
{
    /*  ------------------------------------------------------------------------------------------------------------ *
     *  Initialization and member setting                                                                            *
     *  ------------------------------------------------------------------------------------------------------------ */

    @Test
    public void defaultConstructedShouldReturnEmptyString()
    {
        RomanNumeral romanNumeral = new RomanNumeral();
        assertEquals("Default constructed Roman numeral should return ''", "", romanNumeral.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void decimal4000ShouldThrowIllegalArgumentException()
    {
        new RomanNumeral(4000);
    }

    @Test(expected = IllegalArgumentException.class)
    public void decimalNegative1ShouldThrowIllegalArgumentException()
    {
        new RomanNumeral(-1);
    }

    @Test
    public void copyConstructedShouldHoldSameValue()
    {
        RomanNumeral romanNumeral = new RomanNumeral(1);
        RomanNumeral copy = new RomanNumeral(romanNumeral);
        assertEquals(1, copy.arabicDecimal());
    }

    @Test
    public void shouldReturnValueAsInitialized()
    {
        RomanNumeral romanNumeral = new RomanNumeral(1);
        assertEquals(1, romanNumeral.arabicDecimal());
    }

    @Test
    public void shouldBeSettable()
    {
        RomanNumeral romanNumeral = new RomanNumeral();

        romanNumeral.setArabicDecimal(1);
        assertEquals(1, romanNumeral.arabicDecimal());

        romanNumeral.setRomanNumeralString("II");
        assertEquals("II", romanNumeral.romanNumeral());
    }

    /*  ------------------------------------------------------------------------------------------------------------ *
     *  Equality and hash codes                                                                                      *
     *  ------------------------------------------------------------------------------------------------------------ */

    @Test
    public void shouldNotBeEqualToNull()
    {
        RomanNumeral romanNumeral = new RomanNumeral(1);
        assertFalse(romanNumeral.equals(null));
    }

    @Test
    public void shouldBeEqualToItself()
    {
        RomanNumeral romanNumeral = new RomanNumeral(1);
        assertTrue(romanNumeral.equals(romanNumeral));
    }

    @Test
    public void sameDecimalValuesShouldBeEqual()
    {
        RomanNumeral a = new RomanNumeral(1);
        RomanNumeral b = new RomanNumeral("I");
        assertTrue(a.equals(b));
    }

    @Test
    public void sameDecimalValuesButDifferentRomanStringsShouldBeEqual()
    {
        RomanNumeral a = new RomanNumeral("IV");
        RomanNumeral b = new RomanNumeral("IIII");
        assertTrue(a.equals(b));
    }

    @Test
    public void sameDecimalValuesShouldGiveSameHashCode()
    {
        RomanNumeral a = new RomanNumeral(1);
        RomanNumeral b = new RomanNumeral("I");
        assertTrue(a.hashCode() == b.hashCode());
    }

    /*  ------------------------------------------------------------------------------------------------------------ *
     *  Decimal to Roman conversion                                                                                  *
     *  ------------------------------------------------------------------------------------------------------------ */

    @Test
    public void decimal0ShouldReturnEmpty()
    {
        testDecimalToRoman(0, "");
    }

    @Test
    public void decimal1ShouldReturnI()
    {
        testDecimalToRoman(1, "I");
    }

    /**
     * Simplest test using standard additive notation
     */
    @Test
    public void decimal3ShouldReturnIII()
    {
        testDecimalToRoman(3, "III");
    }

    /**
     * Simple concatenation omitting subtractive notation
     */
    @Test
    public void decimal66ShouldReturnLXVI()
    {
        testDecimalToRoman(66, "LXVI");
    }

    /**
     * Simplest test using subtractive notation
     */
    @Test
    public void decimal4ShouldReturnIV()
    {
        testDecimalToRoman(4, "IV");
    }

    @Test
    public void decimal9ShouldReturnIX()
    {
        testDecimalToRoman(9, "IX");
    }

    @Test
    public void decimal944ShouldReturnCMXLIV()
    {
        testDecimalToRoman(944, "CMXLIV");
    }

    /**
     * Largest possible decimal representable by standard form Roman numerals
     */
    @Test
    public void decimal3999ShouldReturnMMMCMXCIX()
    {
        testDecimalToRoman(3999, "MMMCMXCIX");
    }

    /*  ------------------------------------------------------------------------------------------------------------ *
     *  Roman to Decimal conversion                                                                                  *
     *  ------------------------------------------------------------------------------------------------------------ */

    @Test
    public void romanEmptyStringShouldReturn0()
    {
        testRomanToDecimal("", 0);
    }

    @Test
    public void romanIShouldReturn1()
    {
        testRomanToDecimal("I", 1);
    }

    @Test
    public void romanIIIShouldReturn3()
    {
        testRomanToDecimal("III", 3);
    }

    /**
     * Additive form for 4
     */
    @Test
    public void romanIIIIShouldReturn4()
    {
        testRomanToDecimal("IIII", 4);
    }

    @Test
    public void romanIVShouldReturn4()
    {
        testRomanToDecimal("IV", 4);
    }

    /**
     * Rare additive form for 9
     */
    @Test
    public void romanVIIIIShouldReturn9()
    {
        testRomanToDecimal("VIIII", 9);
    }

    @Test
    public void romanMCMLXXXVIIShouldReturn1987()
    {
        testRomanToDecimal("MCMLXXXVII", 1987);
    }

    /**
     * Largest possible standard form Roman numeral
     */
    @Test
    public void romanMMMCMXCIXShouldReturnDecimal3999()
    {
        testRomanToDecimal("MMMCMXCIX", 3999);
    }

    @Test(expected = IllegalArgumentException.class)
    public void romanAShouldThrowIllegalArgumentException()
    {
        new RomanNumeral("A");
    }

    /*  ------------------------------------------------------------------------------------------------------------ *
     *  Arithmetic                                                                                                   *
     *  ------------------------------------------------------------------------------------------------------------ */

    @Test
    public void decimal1AddDefaultConstructedShouldReturn1()
    {
        RomanNumeral a = new RomanNumeral(1);
        RomanNumeral b = new RomanNumeral();
        assertEquals(1, a.add(b).arabicDecimal());
    }

    @Test
    public void decimal1Add2ShouldReturn3()
    {
        RomanNumeral a = new RomanNumeral(1);
        RomanNumeral b = new RomanNumeral(2);
        assertEquals(3, a.add(b).arabicDecimal());
    }

    @Test
    public void decimal1Add3ShouldReturnRomanIV()
    {
        RomanNumeral a = new RomanNumeral(1);
        assertEquals("IV", a.add(3).romanNumeral());
    }

    @Test
    public void decimal3AddNegative2ShouldReturn1()
    {
        RomanNumeral a = new RomanNumeral(3);
        assertEquals(1, a.add(-2).arabicDecimal());
    }

    @Test(expected = IllegalArgumentException.class)
    public void decimal1AddNegative2ShouldThrowIllegalArgumentException()
    {
        RomanNumeral a = new RomanNumeral(1);
        a.add(-2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void decimal3999Add1ShouldThrowIllegalArgumentException()
    {
        RomanNumeral a = new RomanNumeral(3999);
        a.add(1);
    }

    @Test
    public void decimal1SubtractDefaultConstructedShouldReturn1()
    {
        RomanNumeral a = new RomanNumeral(1);
        RomanNumeral b = new RomanNumeral();
        assertEquals(1, a.subtract(b).arabicDecimal());
    }

    @Test
    public void decimal4Subtract1ShouldReturn3()
    {
        RomanNumeral a = new RomanNumeral(4);
        RomanNumeral b = new RomanNumeral(1);
        assertEquals(3, a.subtract(b).arabicDecimal());
    }

    @Test(expected = IllegalArgumentException.class)
    public void decimal4Subtract5ShouldThrowIllegalArgumentException()
    {
        RomanNumeral a = new RomanNumeral(4);
        a.subtract(5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void decimal3999SubtractNegative1ShouldThrowIllegalArgumentException()
    {
        RomanNumeral a = new RomanNumeral(3999);
        a.subtract(-1);
    }

    @Test
    public void addIterableOfRomanNumeralsTogether()
    {
        RomanNumeral a = RomanNumeral.sum(Arrays.asList(
            RomanNumeral.fromArabicDecimalInteger(2),
            RomanNumeral.fromArabicDecimalInteger(3),
            RomanNumeral.fromArabicDecimalInteger(5),
            RomanNumeral.fromArabicDecimalInteger(8)
        ));

        assertEquals(18, a.arabicDecimal());
    }

    @Test
    public void subtractIterableOfRomanNumeralsFromAMinuend()
    {
        RomanNumeral a = RomanNumeral.difference(RomanNumeral.fromArabicDecimalInteger(18), Arrays.asList(
            RomanNumeral.fromArabicDecimalInteger(2),
            RomanNumeral.fromArabicDecimalInteger(3),
            RomanNumeral.fromArabicDecimalInteger(8)
        ));

        assertEquals(5, a.arabicDecimal());
    }

    /*  ------------------------------------------------------------------------------------------------------------ *
     *  Test helper methods                                                                                          *
     *  ------------------------------------------------------------------------------------------------------------ */

    private static void testDecimalToRoman(final int givenDecimal, final String expectedRoman)
    {
        RomanNumeral romanNumeral = new RomanNumeral(givenDecimal);
        assertEquals("Decimal " + givenDecimal + " should return Roman '" + expectedRoman + "'", expectedRoman,
            romanNumeral.romanNumeral());
    }

    private static void testRomanToDecimal(final String givenRoman, final int expectedDecimal)
    {
        RomanNumeral romanNumeral = new RomanNumeral(givenRoman);
        assertEquals("Roman '" + givenRoman + "' should return decimal " + expectedDecimal, expectedDecimal,
            romanNumeral.arabicDecimal());
    }
}
