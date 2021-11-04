package de.sebastianzander.romannumerals;

import java.util.*;

public final class RomanNumeral
{
    private record RomanValue(String numeral, int value) {}

    private static final String ORDERED_NUMERALS = "IVXLCDM";
    private static final List<RomanValue> ROMAN_VALUES = Arrays.asList
    (
        new RomanValue( "M", 1000),
        new RomanValue("CM",  900),
        new RomanValue( "D",  500),
        new RomanValue("CD",  400),
        new RomanValue( "C",  100),
        new RomanValue("XC",   90),
        new RomanValue( "L",   50),
        new RomanValue("XL",   40),
        new RomanValue( "X",   10),
        new RomanValue("IX",    9),
        new RomanValue( "V",    5),
        new RomanValue("IV",    4),
        new RomanValue( "I",    1)
    );

    private int m_arabicDecimal = 0;
    private String m_romanNumeralString = "";

    /*  ------------------------------------------------------------------------------------------------------------ *
     *  Construction                                                                                                 *
     *  ------------------------------------------------------------------------------------------------------------ */

    public RomanNumeral() {}

    public RomanNumeral(final RomanNumeral other)
    {
        m_arabicDecimal = other.m_arabicDecimal;
        m_romanNumeralString = other.m_romanNumeralString;
    }

    public RomanNumeral(final int arabicDecimal)
        throws IllegalArgumentException
    {
        setArabicDecimal(arabicDecimal);
    }

    public RomanNumeral(final String romanNumeralString)
        throws IllegalArgumentException
    {
        setRomanNumeralString(romanNumeralString);
    }

    /*  ------------------------------------------------------------------------------------------------------------ *
     *  Getters and setters                                                                                          *
     *  ------------------------------------------------------------------------------------------------------------ */

    /**
     * Sets the Arabic decimal integer representation and updates its Roman numeral string representation.
     * @param arabicDecimal The new Arabic decimal integer representation
     */
    public void setArabicDecimal(final int arabicDecimal)
    {
        m_arabicDecimal = arabicDecimal;
        m_romanNumeralString = convertArabicDecimalToRomanNumeralString(arabicDecimal);
    }

    /**
     * Gets the Arabic decimal integer representation of this RomanNumeral.
     * @return The Arabic decimal integer representation
     */
    public int getArabicDecimal()
    {
        return m_arabicDecimal;
    }

    /**
     * Gets the Arabic decimal integer representation of this RomanNumeral.
     * @return The Arabic decimal integer representation
     */
    public int arabicDecimal()
    {
        return m_arabicDecimal;
    }

    /**
     * Sets the Roman numeral string representation and updates its Arabic decimal integer representation.
     * @param romanNumeralString The new Roman numeral string representation
     */
    public void setRomanNumeralString(final String romanNumeralString)
    {
        m_romanNumeralString = romanNumeralString;
        m_arabicDecimal = convertRomanNumeralStringToArabicDecimal(romanNumeralString);
    }

    /**
     * Gets the Roman numeral string representation of this RomanNumeral.
     * @return The Roman numeral string representation
     */
    public String getRomanNumeralString()
    {
        return m_romanNumeralString;
    }

    /**
     * Gets the Roman numeral string representation of this RomanNumeral.
     * @return The Roman numeral string representation
     */
    public String romanNumeral()
    {
        return m_romanNumeralString;
    }

    /*  ------------------------------------------------------------------------------------------------------------ *
     *  Object overloads                                                                                             *
     *  ------------------------------------------------------------------------------------------------------------ */

    /**
     * Compares this and another Roman numeral object in terms of their underlying Arabic decimal integer values.
     * Their Roman numeral string representations do not have to be equal.
     * @param other The other Roman numeral object to compare with
     * @return True if this' and other's underlying Arabic decimal integer representations are equal
     */
    @Override
    public boolean equals(Object other)
    {
        if(other == null || !(other instanceof RomanNumeral))
            return false;

        if(other == this)
            return true;

        RomanNumeral romanNumeralString = (RomanNumeral)other;
        return m_arabicDecimal == romanNumeralString.m_arabicDecimal;
    }

    @Override
    public int hashCode()
    {
        return m_arabicDecimal;
    }

    /**
     * Gives the Roman numeral as a string.
     * @return The Roman numeral
     */
    @Override
    public String toString()
    {
        return m_romanNumeralString;
    }

    /*  ------------------------------------------------------------------------------------------------------------ *
     *  Arithmetic                                                                                                   *
     *  ------------------------------------------------------------------------------------------------------------ */

    /**
     * Adds other RomanNumeral <code>addend</code> and this RomanNumeral together and returns the sum as a new
     * RomanNumeral.
     * @param addend The RomanNumeral to add to this
     * @return The sum as a new RomanNumeral
     */
    public RomanNumeral add(final RomanNumeral addend)
        throws IllegalArgumentException
    {
        int sum = m_arabicDecimal + addend.m_arabicDecimal;
        return new RomanNumeral(sum);
    }

    /**
     * Adds Arabic decimal integer <code>addend</code> and this RomanNumeral together and returns their sum as a
     * RomanNumeral.
     * @param addend The Arabic decimal integer to add to this; may be negative
     * @return The sum as a RomanNumeral
     */
    public RomanNumeral add(final int addend)
        throws IllegalArgumentException
    {
        int sum = m_arabicDecimal + addend;
        return new RomanNumeral(sum);
    }

    /**
     * Adds Roman numeral string <code>addend</code> and this RomanNumeral together and returns their sum as a
     * RomanNumeral.
     * @param addend The Roman numeral string to add to this
     * @return The sum as a RomanNumeral
     */
    public RomanNumeral add(final String addend)
        throws IllegalArgumentException
    {
        int sum = m_arabicDecimal + convertRomanNumeralStringToArabicDecimal(addend);
        return new RomanNumeral(sum);
    }

    /**
     * Adds RomanNumerals in <code>addends</code> to this RomanNumeral and returns their sum as a RomanNumeral.
     * @param addends An Iterable of RomanNumeral objects to add together
     * @return The sum as a RomanNumeral
     */
    public RomanNumeral add(final Iterable<RomanNumeral> addends)
        throws IllegalArgumentException
    {
        int sum = m_arabicDecimal;
        for(RomanNumeral addend : addends)
            sum += addend.m_arabicDecimal;

        return new RomanNumeral(sum);
    }

    /**
     * Adds RomanNumerals <code>augend</code> and <code>addend</code> together and returns their sum as a RomanNumeral.
     * @param augend The left-hand operand
     * @param addend The right-hand operand
     * @return The sum as a RomanNumeral
     */
    public static RomanNumeral sum(final RomanNumeral augend, final RomanNumeral addend)
    {
        int sum = augend.m_arabicDecimal + addend.m_arabicDecimal;
        return new RomanNumeral(sum);
    }

    /**
     * Adds multiple RomanNumerals together and returns their sum as a RomanNumeral.
     * @param addends An Iterable of RomanNumeral objects to add together
     * @return The sum as a RomanNumeral
     */
    public static RomanNumeral sum(final Iterable<RomanNumeral> addends)
    {
        int sum = 0;
        for(RomanNumeral addend : addends)
            sum += addend.m_arabicDecimal;

        return new RomanNumeral(sum);
    }

    /**
     * Subtracts other RomanNumeral <code>subtrahend</code> from this RomanNumeral and returns the difference as a
     * RomanNumeral.
     * @param subtrahend The RomanNumeral to subtract from this
     * @return The difference as a RomanNumeral
     */
    public RomanNumeral subtract(final RomanNumeral subtrahend)
        throws IllegalArgumentException
    {
        int difference = m_arabicDecimal - subtrahend.m_arabicDecimal;
        return new RomanNumeral(difference);
    }

    /**
     * Subtracts Arabic decimal integer <code>subtrahend</code> from this RomanNumeral and returns the difference as a
     * RomanNumeral.
     * @param subtrahend The Arabic decimal integer to subtract from this; may be negative
     * @return The difference as a RomanNumeral
     */
    public RomanNumeral subtract(final int subtrahend)
        throws IllegalArgumentException
    {
        int difference = m_arabicDecimal - subtrahend;
        return new RomanNumeral(difference);
    }

    /**
     * Subtracts Roman numeral string <code>subtrahend</code> from this RomanNumeral and returns the difference as a
     * RomanNumeral.
     * @param subtrahend The Roman numeral string to subtract from this
     * @return The difference as a RomanNumeral
     */
    public RomanNumeral subtract(final String subtrahend)
        throws IllegalArgumentException
    {
        int difference = m_arabicDecimal - convertRomanNumeralStringToArabicDecimal(subtrahend);
        return new RomanNumeral(difference);
    }

    /**
     * Subtracts RomanNumeral <code>subtrahend</code> from <code>minuend</code> and returns their difference as a
     * RomanNumeral.
     * @param minuend The left-hand operand
     * @param subtrahend The right-hand operand
     * @return The sum as a RomanNumeral
     */
    public static RomanNumeral difference(final RomanNumeral minuend, final RomanNumeral subtrahend)
    {
        int sum = minuend.m_arabicDecimal + subtrahend.m_arabicDecimal;
        return new RomanNumeral(sum);
    }

    /**
     * Subtracts multiple RomanNumerals <code>subtrahend</code> from <code>minuend</code> and returns the difference as
     * a RomanNumeral.
     * @param minuend The left-hand operand and as such the base of multiple subtraction
     * @param subtrahends An Iterable of RomanNumeral objects to subtract from <code>minuend</code>
     * @return The sum as a RomanNumeral
     */
    public static RomanNumeral difference(final RomanNumeral minuend, final Iterable<RomanNumeral> subtrahends)
    {
        int difference = minuend.m_arabicDecimal;
        for(RomanNumeral subtrahend : subtrahends)
            difference -= subtrahend.m_arabicDecimal;

        return new RomanNumeral(difference);
    }

    /*  ------------------------------------------------------------------------------------------------------------ *
     *  Conversion                                                                                                   *
     *  ------------------------------------------------------------------------------------------------------------ */

    /**
     * Converts a Roman numeral string to its Arabic decimal integer representation.
     * @param romanNumeralString The Roman numeral string
     * @return The Arabic decimal integer representation of the given Roman numeral
     */
    public static int convertRomanNumeralStringToArabicDecimal(final String romanNumeralString)
        throws IllegalArgumentException
    {
        int decimal = 0, lastNumeralIndex = -1, lastValue = 0;

        for(final char numeral : romanNumeralString.toCharArray())
        {
            int value = convertRomanNumeralCharToDecimal(numeral);
            decimal += value;

            // indexOf has to return a valid character index here since convertRomanNumeralCharToDecimal did not throw
            int charIndex = ORDERED_NUMERALS.indexOf(numeral);
            int charIndexDiff = charIndex - lastNumeralIndex;

            boolean lastNumeralNeedsSubtracting = lastNumeralIndex >= 0 && (charIndexDiff == 1 || charIndexDiff == 2);
            if(lastNumeralNeedsSubtracting)
            {
                // undo adding of last numeral value AND subtract it, too
                decimal -= lastValue * 2;
            }

            lastNumeralIndex = charIndex;
            lastValue = value;
        }

        return decimal;
    }

    /**
     * Converts a single Roman numeral to its Arabic decimal integer representation.
     * @param romanNumeralString The Roman numeral character
     * @return The Arabic decimal integer representation of the given Roman numeral character
     */
    public static int convertRomanNumeralCharToDecimal(final char romanNumeralString)
        throws IllegalArgumentException
    {
        for(final RomanValue rv : ROMAN_VALUES)
            if(rv.numeral.length() == 1 && rv.numeral.charAt(0) == romanNumeralString)
                return rv.value;

        throw new IllegalArgumentException("The given character '" + romanNumeralString +
            "' does not present a valid standard form roman numeral");
    }

    /**
     * Converts an Arabic decimal integer to its Roman numeral string representation.
     * @param arabicDecimal The Arabic decimal integer
     * @return The Roman numeral string representation of the given Arabic decimal integer
     */
    public static String convertArabicDecimalToRomanNumeralString(final int arabicDecimal)
        throws IllegalArgumentException
    {
        if(arabicDecimal == 0)
            return "";

        if(arabicDecimal < 0 || arabicDecimal > 3999)
            throw new IllegalArgumentException("Decimal number " + arabicDecimal +
                " is not in the valid range of standard form roman numerals");

        StringBuilder string = new StringBuilder();
        int decimal = arabicDecimal;

        for(final RomanValue rv : ROMAN_VALUES) {
            while(decimal >= rv.value && decimal > 0) {
                decimal -= rv.value;
                string.append(rv.numeral);
            }
        }

        return string.toString();
    }

    public static RomanNumeral fromRomanNumeralString(final String romanNumeralString)
        throws IllegalArgumentException
    {
        return new RomanNumeral(romanNumeralString);
    }

    public static RomanNumeral fromArabicDecimalInteger(final int arabicDecimal)
        throws IllegalArgumentException
    {
        return new RomanNumeral(arabicDecimal);
    }
}
