Roman Numeral
=============

This is a Java implementation of a Roman numeral class that supports parsing of Roman numeral strings, the conversion 
between Roman numeral string and Arabic decimal integer representation as well as basic arithmetic and comparison 
operations.

Here is a quick usage example:

```java
import de.sebastianzander.romannumerals.RomanNumeral;

public class Application
{
    public void main(String[] args)
    {
        // construct from either a Roman numeral string or an Arabic decimal integer
        RomanNumeral a = new RomanNumeral(1987);
        RomanNumeral b = new RomanNumeral("XXXIV");
        RomanNumeral c = RomanNumeral.fromRomanNumeralString("IV");

        // calculate sum of two RomanNumeral objects
        RomanNumeral s = a.add(b);

        // calculate sum of Iterable of RomanNumeral objects
        RomanNumeral t = RomanNumeral.sum(Arrays.asList(a, b, c));

        System.out.printf("%s + %s = %s\n", a, b, s);
        System.out.printf("%d + %d = %d\n", a.arabicDecimal(), b.arabicDecimal(), s.arabicDecimal());
    }
}
```

The above example should create the following output:

```
MCMLXXXVII + XXXIV = MMXXI
1987 + 34 = 2021
```
