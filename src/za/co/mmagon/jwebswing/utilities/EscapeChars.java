package za.co.mmagon.jwebswing.utilities;

import java.io.*;
import java.net.*;
import java.text.*;
import java.util.regex.*;

/**
 * Convenience methods for escaping special characters related to HTML, XML, and regular expressions.
 * <p>
 * &lt;P&gt;To keep you safe by default, WEB4J goes to some effort to escape characters in your data when appropriate, such that you &lt;em&gt;usually&lt;/em&gt; don't need to think too much about
 * escaping special characters. Thus, you shouldn't need to &lt;em&gt;directly&lt;/em&gt; use the services of this class very often.
 * <p>
 * &lt;P&gt;&lt;span class='highlight'&gt;For Model Objects containing free form user input, it is highly recommended that you use {@link SafeText}, not &lt;tt&gt;String&lt;/tt&gt;&lt;/span&gt;. Free
 * form user input is open to malicious use, such as &lt;a href='http://www.owasp.org/index.php/Cross_Site_Scripting'&gt;Cross Site Scripting&lt;/a&gt; attacks. Using &lt;tt&gt;SafeText&lt;/tt&gt;
 * will protect you from such attacks, by always escaping special characters automatically in its &lt;tt&gt;toString()&lt;/tt&gt; method.
 * <p>
 * &lt;P&gt;The following WEB4J classes will automatically escape special characters for you, when needed : &lt;ul&gt; &lt;li&gt;the {@link SafeText} class, used as a building block class for your
 * application's Model Objects, for modeling all free form user input &lt;li&gt;the {@link Populate} tag used with forms &lt;li&gt;the {@link Report} class used for creating quick reports
 * &lt;li&gt;the {@link Text}, {@link TextFlow}, and {@link Tooltips} custom tags used for translation &lt;/ul&gt;
 */
public final class EscapeChars
{
    private static final Pattern SCRIPT = Pattern.compile(
            "<SCRIPT>", Pattern.CASE_INSENSITIVE
    );
    private static final Pattern SCRIPT_END = Pattern.compile(
            "</SCRIPT>", Pattern.CASE_INSENSITIVE
    );
    
    /**
     * Static only
     */
    private EscapeChars()
    {
        //empty - prevent construction
    }

    /**
     * Escape characters for text appearing in HTML markup.
     * <p>
     * &lt;P&gt;This method exists as a defence against Cross Site Scripting (XSS) hacks. The idea is to neutralize control characters commonly used by scripts, such that they will not be executed by
     * the browser. This is done by replacing the control characters with their escaped equivalents. See {link hirondelle.web4j.security.SafeText} as well.
     *
     * @param aText
     *
     * @return
     */
    public static String forHTML(String aText)
    {
        final StringBuilder result = new StringBuilder();
        final StringCharacterIterator iterator = new StringCharacterIterator(aText);
        char character = iterator.current();
        while (character != CharacterIterator.DONE)
        {
            switch (character)
            {
                case '<':
                    result.append("&lt;");
                    break;
                case '>':
                    result.append("&gt;");
                    break;
                case '&':
                    result.append("&amp;");
                    break;
                case '\"':
                    result.append("&quot;");
                    break;
                case '\t':
                    addCharEntity(9, result);
                    break;
                case '!':
                    addCharEntity(33, result);
                    break;
                case '#':
                    addCharEntity(35, result);
                    break;
                case '$':
                    addCharEntity(36, result);
                    break;
                case '%':
                    addCharEntity(37, result);
                    break;
                case '\'':
                    addCharEntity(39, result);
                    break;
                case '(':
                    addCharEntity(40, result);
                    break;
                case ')':
                    addCharEntity(41, result);
                    break;
                case '*':
                    addCharEntity(42, result);
                    break;
                case '+':
                    addCharEntity(43, result);
                    break;
                case ',':
                    addCharEntity(44, result);
                    break;
                case '-':
                    addCharEntity(45, result);
                    break;
                case '.':
                    addCharEntity(46, result);
                    break;
                case '/':
                    addCharEntity(47, result);
                    break;
                case ':':
                    addCharEntity(58, result);
                    break;
                case ';':
                    addCharEntity(59, result);
                    break;
                case '=':
                    addCharEntity(61, result);
                    break;
                case '?':
                    addCharEntity(63, result);
                    break;
                case '@':
                    addCharEntity(64, result);
                    break;
                case '[':
                    addCharEntity(91, result);
                    break;
                case '\\':
                    addCharEntity(92, result);
                    break;
                case ']':
                    addCharEntity(93, result);
                    break;
                case '^':
                    addCharEntity(94, result);
                    break;
                case '_':
                    addCharEntity(95, result);
                    break;
                case '`':
                    addCharEntity(96, result);
                    break;
                case '{':
                    addCharEntity(123, result);
                    break;
                case '|':
                    addCharEntity(124, result);
                    break;
                case '}':
                    addCharEntity(125, result);
                    break;
                case '~':
                    addCharEntity(126, result);
                    break;
                default:
                    //the char is not a special one
                    //add it to the result as is
                    result.append(character);
                    break;
            }
            character = iterator.next();
        }
        return result.toString();
    }

    /**
     * Escape all ampersand characters in a URL.
     *
     * @param aURL
     *
     * @return
     */
    public static String forHrefAmpersand(String aURL)
    {
        return aURL.replace("&", "&amp;");
    }

    /**
     * Formats as UTF-8
     *
     * @param aURLFragment
     *
     * @return
     *
     * @throws java.io.UnsupportedEncodingException
     */
    public static String forURL(String aURLFragment) throws UnsupportedEncodingException
    {
        String result = null;
        try
        {
            result = URLEncoder.encode(aURLFragment, "UTF-8");
        }
        catch (UnsupportedEncodingException ex)
        {
            throw ex;
        }
        return result;
    }

    /**
     * Escape characters for text appearing as XML data, between tags.
     *
     * @param aText
     *
     * @return
     */
    public static String forXML(String aText)
    {
        final StringBuilder result = new StringBuilder();
        final StringCharacterIterator iterator = new StringCharacterIterator(aText);
        char character = iterator.current();
        while (character != CharacterIterator.DONE)
        {
            switch (character)
            {
                case '<':
                    result.append("&lt;");
                    break;
                case '>':
                    result.append("&gt;");
                    break;
                case '\"':
                    result.append("&quot;");
                    break;
                case '\'':
                    result.append("&#039;");
                    break;
                case '&':
                    result.append("&amp;");
                    break;
                default:
                    //the char is not a special one
                    //add it to the result as is
                    result.append(character);
                    break;
            }
            character = iterator.next();
        }
        return result.toString();
    }

    /**
     * Escapes characters for text appearing as data in the
     *
     * @param aText
     *
     * @return
     */
    public static String forJSON(String aText)
    {
        final StringBuilder result = new StringBuilder();
        StringCharacterIterator iterator = new StringCharacterIterator(aText);
        char character = iterator.current();
        while (character != StringCharacterIterator.DONE)
        {
            switch (character)
            {
                case '\"':
                    result.append("\\\"");
                    break;
                case '\\':
                    result.append("\\\\");
                    break;
                case '/':
                    result.append("\\/");
                    break;
                case '\b':
                    result.append("\\b");
                    break;
                case '\f':
                    result.append("\\f");
                    break;
                case '\n':
                    result.append("\\n");
                    break;
                case '\r':
                    result.append("\\r");
                    break;
                case '\t':
                    result.append("\\t");
                    break;
                default:
                    //the char is not a special one
                    //add it to the result as is
                    result.append(character);
                    break;
            }
            character = iterator.next();
        }
        return result.toString();
    }

    /**
     * Returns opening and closing tags replaced with the HTML Equivalents replaced by their escaped equivalents.
     *
     * @param aText
     *
     * @return
     */
    public static String toDisableTags(String aText)
    {
        final StringBuilder result = new StringBuilder();
        final StringCharacterIterator iterator = new StringCharacterIterator(aText);
        char character = iterator.current();
        while (character != CharacterIterator.DONE)
        {
            switch (character)
            {
                case '<':
                    result.append("&lt;");
                    break;
                case '>':
                    result.append("&gt;");
                    break;
                default:
                    //the char is not a special one
                    //add it to the result as is
                    result.append(character);
                    break;
            }
            character = iterator.next();
        }
        return result.toString();
    }

    /**
     * Replace characters having special meaning in regular expressions with their escaped equivalents, preceded by a '\' character.
     *
     * @param aRegexFragment
     *
     * @return
     */
    public static String forRegex(String aRegexFragment)
    {
        final StringBuilder result = new StringBuilder();

        final StringCharacterIterator iterator
                = new StringCharacterIterator(aRegexFragment);
        char character = iterator.current();
        while (character != CharacterIterator.DONE)
        {
            /*
             * All literals need to have backslashes doubled.
             */
            switch (character)
            {
                case '.':
                    result.append("\\.");
                    break;
                case '\\':
                    result.append("\\\\");
                    break;
                case '?':
                    result.append("\\?");
                    break;
                case '*':
                    result.append("\\*");
                    break;
                case '+':
                    result.append("\\+");
                    break;
                case '&':
                    result.append("\\&");
                    break;
                case ':':
                    result.append("\\:");
                    break;
                case '{':
                    result.append("\\{");
                    break;
                case '}':
                    result.append("\\}");
                    break;
                case '[':
                    result.append("\\[");
                    break;
                case ']':
                    result.append("\\]");
                    break;
                case '(':
                    result.append("\\(");
                    break;
                case ')':
                    result.append("\\)");
                    break;
                case '^':
                    result.append("\\^");
                    break;
                case '$':
                    result.append("\\$");
                    break;
                default:
                    //the char is not a special one
                    //add it to the result as is
                    result.append(character);
                    break;
            }
            character = iterator.next();
        }
        return result.toString();
    }

    /**
     * Escape <tt>'$'</tt> and <tt>'\'</tt> characters in replacement strings.
     *
     * @param aInput
     *
     * @return
     */
    public static String forReplacementString(String aInput)
    {
        return Matcher.quoteReplacement(aInput);
    }

    /**
     * Disable all script tags
     *
     * @param aText
     *
     * @return
     */
    public static String forScriptTagsOnly(String aText)
    {
        String result;
        Matcher matcher = SCRIPT.matcher(aText);
        result = matcher.replaceAll("&gt;SCRIPT&lt;");
        matcher = SCRIPT_END.matcher(result);
        result = matcher.replaceAll("&gt;/SCRIPT&lt;");
        return result;
    }

    

    /**
     * Adds padding to string builder
     *
     * @param aIdx
     * @param aBuilder
     */
    private static void addCharEntity(Integer aIdx, StringBuilder aBuilder)
    {
        String padding = "";
        if (aIdx <= 9)
        {
            padding = "00";
        }
        else if (aIdx <= 99)
        {
            padding = "0";
        }
        else
        {
            //no prefix
        }
        String number = padding + aIdx.toString();
        aBuilder.append("&#").append(number).append(";");
    }
}
