package com.cherry.netty.utils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class StringUtil {

    static Logger logger = Logger.getLogger(StringUtil.class);
    /**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE = "^((13[0-9])|(14[5,7,9])|(15[^4,\\D])|(18[0-9])|(17[0,1,3,5-9])|(88[0-9]))\\d{8}$";

    public StringUtil() {
    }

    public static boolean isEmoji(String source) {
        if (source != null) {
            Pattern emoji = Pattern.compile(
                    "[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
                    Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
            Matcher emojiMatcher = emoji.matcher(source);
            if (emojiMatcher.find()) {
                source = emojiMatcher.replaceAll("");
                return true;
            }
            return false;
        }
        return false;
    }

    public static boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (isNum.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    public static String replaceBlank(String str,String regex) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }
    /**
     * 按长度分割字符串
     * 
     * @param content
     * @param len
     * @return
     */
    public static String[] split(String content, int len) {
        if (content == null || content.equals("")) {
            return null;
        }
        int len2 = content.length();
        if (len2 <= len) {
            return new String[] { content };
        } else {
            int i = len2 / len + 1;
            System.out.println("i:" + i);
            String[] strA = new String[i];
            int j = 0;
            int begin = 0;
            int end = 0;
            while (j < i) {
                begin = j * len;
                end = (j + 1) * len;
                if (end > len2)
                    end = len2;
                strA[j] = content.substring(begin, end);
                // System.out.println(strA[j]+"<br/>");
                j = j + 1;
            }
            return strA;
        }
    }

    public static boolean emailFormat(String email) {
        boolean tag = true;
        final String pattern1 = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        final Pattern pattern = Pattern.compile(pattern1);
        final Matcher mat = pattern.matcher(email);
        if (!mat.find()) {
            tag = false;
        }
        return tag;
    }

    /**
     * 验证是不是EMAIL
     * 
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        boolean retval = false;
        String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(email);
        retval = matcher.matches();
        return retval;
    }

    /**
     * 验证汉字为true
     * 
     * @param s
     * @return
     */
    public static boolean isLetterorDigit(String s) {
        if (s.equals("") || s == null) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isLetterOrDigit(s.charAt(i))) {
                // if (!Character.isLetter(s.charAt(i))){
                return false;
            }
        }
        // Character.isJavaLetter()
        return true;
    }

    /**
     * 判断某字符串是否为null,如果长度大于256,则返回256长度的子字符串,反之返回原串
     * 
     * @param str
     * @return
     */
    public static String checkStr(String str) {
        if (str == null) {
            return "";
        } else if (str.length() > 256) {
            return str.substring(256);
        } else {
            return str;
        }
    }

    /**
     * 验证是不是Int validate a int string
     * 
     * @param str
     *            the Integer string.
     * @return true if the str is invalid otherwise false.
     */
    public static boolean validateInt(String str) {
        if (str == null || str.trim().equals(""))
            return false;

        char c;
        for (int i = 0, l = str.length(); i < l; i++) {
            c = str.charAt(i);
            if (!((c >= '0') && (c <= '9')))
                return false;
        }

        return true;
    }

    /**
     * 字节码转换成16进制字符串 内部调试使用
     * 
     * @param b
     * @return
     */
    public static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;
            if (n < b.length - 1)
                hs = hs + ":";
        }
        return hs.toUpperCase();
    }

    /**
     * 字节码转换成自定义字符串 内部调试使用
     * 
     * @param b
     * @return
     */
    public static String byte2string(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;
            // if (n<b.length-1) hs=hs+":";
        }
        return hs.toUpperCase();
    }

    public static byte[] string2byte(String hs) {
        byte[] b = new byte[hs.length() / 2];
        for (int i = 0; i < hs.length(); i = i + 2) {
            String sub = hs.substring(i, i + 2);
            byte bb = new Integer(Integer.parseInt(sub, 16)).byteValue();
            b[i / 2] = bb;
        }
        return b;
    }

    /**
     * 验证字符串是否为空
     * 
     * @param param
     * @return
     */
    public static boolean empty(String param) {
        return param == null || param.trim().length() < 1 || "null".equals(param)
                || "".equals(param);
    }

    /**
     * 验证字符串是否为空
     * 
     * @param param
     * @return
     */
    public static boolean empty(Date param) {
        return param == null;
    }

    /**
     * 验证数字是否为空
     * 
     * @param param
     * @return
     */
    public static boolean empty(Integer param) {
        return param == null || "".equals(param);
    }

    /**
     * 验证浮点是否为空
     * 
     * @param param
     * @return
     */
    public static boolean empty(BigDecimal param) {
        return param == null || "".equals(param);
    }

    /**
     * 验证浮点是否为空
     * 
     * @param param
     * @return
     */
    public static boolean empty(Double param) {
        return param == null || "".equals(param);
    }

    // 验证英文字母或数据
    public static boolean isLetterOrDigit(String str) {
        java.util.regex.Pattern p = null; // 正则表达式
        java.util.regex.Matcher m = null; // 操作的字符串
        boolean value = true;
        try {
            p = java.util.regex.Pattern.compile("[^0-9A-Za-z]");
            m = p.matcher(str);
            if (m.find()) {

                value = false;
            }
        } catch (Exception e) {
        }
        return value;
    }

    /**
     * 验证是否是小写字符 串
     */
    @SuppressWarnings("unused")
    private static boolean isLowerLetter(String str) {
        java.util.regex.Pattern p = null; // 正则表达式
        java.util.regex.Matcher m = null; // 操作的字符串
        boolean value = true;
        try {
            p = java.util.regex.Pattern.compile("[^a-z]");
            m = p.matcher(str);
            if (m.find()) {
                value = false;
            }
        } catch (Exception e) {
            logger.error("log" + e.toString());
        }
        return value;
    }

    public static String encode(String str, String code) {
        try {
            return URLEncoder.encode(str, code);
        } catch (Exception e) {
            return "";
        }
    }

    public static String decode(String str, String code) {
        try {
            return URLDecoder.decode(str, code);
        } catch (Exception e) {
            return "";
        }
    }

    public static String nvl(String param) {
        return param != null ? param.trim() : "";
    }

    public static int parseInt(String param, int d) {
        int i = d;
        try {
            i = Integer.parseInt(param);
        } catch (Exception e) {
        }
        return i;
    }

    public static int parseInt(String param) {
        return parseInt(param, 0);
    }

    public static long parseLong(String param) {
        long l = 0L;
        try {
            l = Long.parseLong(param);
        } catch (Exception e) {
        }
        return l;
    }

    public static double parseDouble(String param) {
        return parseDouble(param, 1.0);
    }

    public static double parseDouble(String param, double t) {
        double tmp = 0.0;
        try {
            tmp = Double.parseDouble(param.trim());
        } catch (Exception e) {
            tmp = t;
        }
        return tmp;
    }

    public static boolean parseBoolean(String param) {
        if (empty(param))
            return false;
        switch (param.charAt(0)) {
        case 49: // '1'
        case 84: // 'T'
        case 89: // 'Y'
        case 116: // 't'
        case 121: // 'y'
            return true;

        }
        return false;
    }

    /**
     * public static String replace(String mainString, String oldString, String
     * newString) { if(mainString == null) return null; int i =
     * mainString.lastIndexOf(oldString); if(i < 0) return mainString;
     * StringBuffer mainSb = new StringBuffer(mainString); for(; i >= 0; i =
     * mainString.lastIndexOf(oldString, i - 1)) mainSb.replace(i, i +
     * oldString.length(), newString);
     * 
     * return mainSb.toString(); }
     * 
     */

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static final String[] split(String str, String delims) {
        StringTokenizer st = new StringTokenizer(str, delims);
        ArrayList list = new ArrayList();
        for (; st.hasMoreTokens(); list.add(st.nextToken()))
            ;
        return (String[]) list.toArray(new String[list.size()]);
    }

    public static String substring(String str, int length) {
        if (str == null)
            return null;

        if (str.length() > length)
            return (str.substring(0, length - 2) + "...");
        else
            return str;
    }

    public static boolean validateDouble(String str) throws RuntimeException {
        if (str == null)
            // throw new RuntimeException("null input");
            return false;
        char c;
        int k = 0;
        for (int i = 0, l = str.length(); i < l; i++) {
            c = str.charAt(i);
            if (!((c >= '0') && (c <= '9')))
                if (!(i == 0 && (c == '-' || c == '+')))
                    if (!(c == '.' && i < l - 1 && k < 1))
                        // throw new RuntimeException("invalid number");
                        return false;
                    else
                        k++;
        }
        return true;
    }

    public static boolean validateMobile(String str, boolean includeUnicom) {
        if (str == null || str.trim().equals(""))
            return false;
        str = str.trim();

        if (str.length() != 11 || !validateInt(str))
            return false;

        if (includeUnicom
                && (str.startsWith("130") || str.startsWith("133") || str.startsWith("131")))
            return true;

        if (!(str.startsWith("139") || str.startsWith("138") || str.startsWith("137")
                || str.startsWith("136") || str.startsWith("135")))
            return false;
        return true;
    }

    public static boolean validateMobile(String str) {
        return validateMobile(str, false);
    }

    /**
     * delete file
     * 
     * @param fileName
     * @return -1 exception,1 success,0 false,2 there is no one directory of the
     *         same name in system
     */
    public static int deleteFile(String fileName) {
        File file = null;
        int returnValue = -1;
        try {
            file = new File(fileName);
            if (file.exists())
                if (file.delete())
                    returnValue = 1;
                else
                    returnValue = 0;
            else
                returnValue = 2;

        } catch (Exception e) {
            System.out.println("Exception:e=" + e.getMessage());
        } finally {
            file = null;
            // return returnValue;
        }
        return returnValue;
    }

    public static String gbToIso(String s) throws UnsupportedEncodingException {
        return covertCode(s, "GB2312", "ISO8859-1");
    }

    public static String covertCode(String s, String code1, String code2)
            throws UnsupportedEncodingException {
        if (s == null)
            return null;
        else if (s.trim().equals(""))
            return "";
        else
            return new String(s.getBytes(code1), code2);
    }

    public static String transCode(String s0) throws UnsupportedEncodingException {
        if (s0 == null || s0.trim().equals(""))
            return null;
        else {
            s0 = s0.trim();
            return new String(s0.getBytes("GBK"), "ISO8859-1");
        }
    }

    public static String GBToUTF8(String s) {
        try {
            StringBuffer out = new StringBuffer("");
            byte[] bytes = s.getBytes("unicode");
            for (int i = 2; i < bytes.length - 1; i += 2) {
                out.append("\\u");
                String str = Integer.toHexString(bytes[i + 1] & 0xff);
                for (int j = str.length(); j < 2; j++) {
                    out.append("0");
                }
                out.append(str);
                String str1 = Integer.toHexString(bytes[i] & 0xff);
                for (int j = str1.length(); j < 2; j++) {
                    out.append("0");
                }

                out.append(str1);
            }
            return out.toString();
        } catch (UnsupportedEncodingException e) {
            logger.error("log" + e.toString());
            return null;
        }
    }

    @SuppressWarnings("unused")
    public static final String[] replaceAll(String[] obj, String oldString, String newString) {
        if (obj == null) {
            return null;
        }
        int length = obj.length;
        String[] returnStr = new String[length];
        String str = null;
        for (int i = 0; i < length; i++) {
            returnStr[i] = replaceAll(obj[i], oldString, newString);
        }
        return returnStr;
    }

    public static String replaceAll(String s0, String oldstr, String newstr) {
        if (s0 == null || s0.trim().equals(""))
            return null;
        StringBuffer sb = new StringBuffer(s0);
        int begin = 0;
        // int from = 0;
        begin = s0.indexOf(oldstr);
        while (begin > -1) {
            sb = sb.replace(begin, begin + oldstr.length(), newstr);
            s0 = sb.toString();
            begin = s0.indexOf(oldstr, begin + newstr.length());
        }
        return s0;
    }

    public static String toHtml(String str) {
        String html = str;
        if (str == null || str.length() == 0) {
            return str;
        }
        html = replaceAll(html, "&", "&amp;");
        html = replaceAll(html, "<", "&lt;");
        html = replaceAll(html, ">", "&gt;");
        html = replaceAll(html, "\r\n", "\n");
        html = replaceAll(html, "\n", "<br>\n");
        html = replaceAll(html, "\t", "         ");
        html = replaceAll(html, " ", "&nbsp;");
        return html;
    }

    public static final String replace(String line, String oldString, String newString) {
        if (line == null) {
            return null;
        }
        int i = 0;
        if ((i = line.indexOf(oldString, i)) >= 0) {
            char[] line2 = line.toCharArray();
            char[] newString2 = newString.toCharArray();
            int oLength = oldString.length();
            StringBuffer buf = new StringBuffer(line2.length);
            buf.append(line2, 0, i).append(newString2);
            i += oLength;
            int j = i;
            while ((i = line.indexOf(oldString, i)) > 0) {
                buf.append(line2, j, i - j).append(newString2);
                i += oLength;
                j = i;
            }
            buf.append(line2, j, line2.length - j);
            return buf.toString();
        }
        return line;
    }

    public static final String replaceIgnoreCase(String line, String oldString, String newString) {
        if (line == null) {
            return null;
        }
        String lcLine = line.toLowerCase();
        String lcOldString = oldString.toLowerCase();
        int i = 0;
        if ((i = lcLine.indexOf(lcOldString, i)) >= 0) {
            char[] line2 = line.toCharArray();
            char[] newString2 = newString.toCharArray();
            int oLength = oldString.length();
            StringBuffer buf = new StringBuffer(line2.length);
            buf.append(line2, 0, i).append(newString2);
            i += oLength;
            int j = i;
            while ((i = lcLine.indexOf(lcOldString, i)) > 0) {
                buf.append(line2, j, i - j).append(newString2);
                i += oLength;
                j = i;
            }
            buf.append(line2, j, line2.length - j);
            return buf.toString();
        }
        return line;
    }

    public static final String escapeHTMLTags(String input) {
        // Check if the string is null or zero length -- if so, return
        // what was sent in.
        if (input == null || input.length() == 0) {
            return input;
        }
        // Use a StringBuffer in lieu of String concatenation -- it is
        // much more efficient this way.
        StringBuffer buf = new StringBuffer(input.length());
        char ch = ' ';
        for (int i = 0; i < input.length(); i++) {
            ch = input.charAt(i);
            if (ch == '<') {
                buf.append("&lt;");
            } else if (ch == '>') {
                buf.append("&gt;");
            } else {
                buf.append(ch);
            }
        }
        return buf.toString();
    }

    /**
     * Returns a random String of numbers and letters of the specified length.
     * The method uses the Random class that is built-in to Java which is
     * suitable for low to medium grade security uses. This means that the
     * output is only pseudo random, i.e., each number is mathematically
     * generated so is not truly random.
     * <p>
     * 
     * For every character in the returned String, there is an equal chance that
     * it will be a letter or number. If a letter, there is an equal chance that
     * it will be lower or upper case.
     * <p>
     * 
     * The specified length must be at least one. If not, the method will return
     * null.
     * 
     * @param length
     *            the desired length of the random String to return.
     * @return a random String of numbers and letters of the specified length.
     */

    private static Random randGen = null;
    private static Object initLock = new Object();
    private static char[] numbersAndLetters = null;

    public static final String randomString(int length) {
        if (length < 1) {
            return null;
        }
        // Init of pseudo random number generator.
        if (randGen == null) {
            synchronized (initLock) {
                if (randGen == null) {
                    randGen = new Random();
                    // Also initialize the numbersAndLetters array
                    numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz"
                            + "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
                }
            }
        }
        // Create a char buffer to put random letters and numbers in.
        char[] randBuffer = new char[length];
        for (int i = 0; i < randBuffer.length; i++) {
            randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
        }
        return new String(randBuffer);
    }

    public static String getSubstring(String content, int i) {
        int varsize = 10;
        String strContent = content;
        if (strContent.length() < varsize + 1) {
            return strContent;
        } else {
            int max = (int) Math.ceil((double) strContent.length() / varsize);
            if (i < max - 1) {
                return strContent.substring(i * varsize, (i + 1) * varsize);
            } else {
                return strContent.substring(i * varsize);
            }
        }
    }

    public static String now(String pattern) {
        return dateToString(new Date(), pattern);
    }

    public static String dateToString(Date date, String pattern) {
        if (date == null) {
            return "";
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            return sdf.format(date);
        }
    }

    public static synchronized String getNow() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return sdf.format(new Date());
    }

    public static java.sql.Date stringToDate(String strDate, String pattern) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = simpleDateFormat.parse(strDate);
        long lngTime = date.getTime();
        return new java.sql.Date(lngTime);
    }

    public static java.util.Date stringToUtilDate(String strDate, String pattern)
            throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.parse(strDate);
    }

    public static String formatHTMLOutput(String s) {
        if (s == null)
            return null;

        if (s.trim().equals(""))
            return "";

        String formatStr;
        formatStr = replaceAll(s, " ", "&nbsp;");
        formatStr = replaceAll(formatStr, "\n", "<br />");

        return formatStr;
    }

    /*
     * 4舍5入 @param num 要调整的数 @param x 要保留的小数位
     */
    public static double myround(double num, int x) {
        BigDecimal b = new BigDecimal(num);
        return b.setScale(x, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /*
     * public static String getSubstring(String content,int i){ int varsize=10;
     * String strContent=content; if(strContent.length()<varsize+1){ return
     * strContent; }else{ int
     * max=(int)Math.ceil((double)strContent.length()/varsize); if(i<max-1){
     * return strContent.substring(i*varsize,(i+1)*varsize); }else{ return
     * strContent.substring(i*varsize); } } }
     */

    public static int parseLongInt(Long param, int d) {
        int i = d;
        try {
            i = Integer.parseInt(String.valueOf(param));
        } catch (Exception e) {
        }
        return i;
    }

    public static int parseLongInt(Long param) {
        return parseLongInt(param, 0);
    }

    public static String returnString(Object obj) {
        if (obj == null) {
            return "";
        } else {
            return obj.toString();
        }
    }

    /**
     * 修改敏感字符编码
     * 
     * @param value
     * @return
     */
    public static String htmlEncode(String value) {
        String re[][] = { { "<", "&lt;" }, { ">", "&gt;" }, { "\"", "&quot;" },
                { "\\′", "&acute;" }, { "&", "&amp;" } };

        for (int i = 0; i < 4; i++) {
            value = value.replaceAll(re[i][0], re[i][1]);
        }
        return value;
    }

    /**
     * 防SQL注入
     * 
     * @param str
     * @return
     */
    public static boolean sql_inj(String str) {
        String inj_str = "'|and|exec|insert|select|delete|update|count|*|%|chr|mid|master|truncate|char|declare|;|or|-|+|,";
        String inj_stra[] = inj_str.split("|");
        for (int i = 0; i < inj_stra.length; i++) {
            if (str.indexOf(inj_stra[i]) >= 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 防止前台js脚本注入
     * 
     * @param str
     * @return
     */
    public static boolean url_js_inj(String str) {
        String inj_str = "%|onmouseover|svg|script|alert|onload";
        String inj_stra[] = inj_str.split("\\|");
        for (int i = 0; i < inj_stra.length; i++) {
            if (str.indexOf(inj_stra[i]) >= 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 读取一个时间戳的文件名
     * 
     * @return
     */
    public static String getAnnexFileStr() {
        SimpleDateFormat sDateFormat;
        Random r = new Random();
        int rannum = (int) (r.nextDouble() * (99999 - 1000 + 1)) + 10000;
        sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return sDateFormat.format(new Date()) + rannum;
    }

    /**
     * 获取随机数
     * 
     * @param length
     * @return
     */
    public static String getRandomNum(int length) {
        String str = "";
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            str += String.valueOf(random.nextInt(10));
        }
        return str;
    }

    /**
     * 根据显示路径获取保存路径.
     * 
     * @param dir
     *            。
     * @return result。
     */
    public static String[] getSaveDir(final String[] dir, String type) {

        if (dir != null) {
            String[] result = new String[dir.length];
            for (int i = 0; i < dir.length; i++) {
                result[i] = getSaveUlr(dir[i], type);
            }
            return result;
        }
        return null;
    }

    public static String getMutilSaveUlr(final String urlStr, String type) {
        if (empty(urlStr)) {
            return "";
        }
        List<String> list = ListMapUtil.getTlist(urlStr);
        List<String> resultList = new ArrayList<String>();
        for (String s : list) {
            resultList.add(getSaveUlr(s, type));
        }
        return ListMapUtil.getTlistToString(resultList);

    }

    public static String parseNullToStr(String str) {
        return str == null ? "" : str;
    }

    /**
     * 获取 图片保存地址
     * 
     * @param viewUrl
     * @return
     */
    public static String getSaveUlr(String viewUrl, String type) {
        if (empty(viewUrl)) {
            return "";
        }
        if ("image".equals(type)) {
            return viewUrl.substring(viewUrl.indexOf("images/") + "images/".length(),
                    viewUrl.length());
        } else if ("voice".equals(type)) {
            return viewUrl.substring(viewUrl.indexOf("voice/") + "voice/".length(),
                    viewUrl.length());

        }
        return "";
    }

    public static String getImgSizeUrl(String originalImgUrl, String size) {
        if (empty(originalImgUrl)) {
            return "";
        }
        return originalImgUrl.substring(0, originalImgUrl.lastIndexOf(".")) + "_" + size
                + originalImgUrl.substring(originalImgUrl.lastIndexOf("."),
                        originalImgUrl.length());
    }

    public static String getUUid() {
        UUID uuid = UUID.randomUUID();
        // 得到对象产生的ID
        String a = uuid.toString();
        a = a.toUpperCase();
        return a = a.replaceAll("-", "");
    }

    public static String getTradeOutNo() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = simpleDateFormat.format(new Date());
        return date + getRandomNum(9);
    }

    public static String substringBetween(String base64Url, String startStr, String endStr) {
        return base64Url.substring(startStr.length(), base64Url.indexOf(endStr));
    }

    /**
     * 校验手机号
     * 
     * @param mobile
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isMobile(String mobile) {
        return Pattern.matches(REGEX_MOBILE, mobile);
    }

    public static String getEntyPerNo(String parperNo) {
        if (empty(parperNo)) {
            return "";
        }
        if (parperNo.length() <= 4) {
            return parperNo;
        }
        String fontStr = parperNo.substring(0, 2);
        String lastStr = parperNo.substring(parperNo.length() - 2, parperNo.length());
        String sd = "*";
        for (int i = 0; i < parperNo.length() - 5; i++) {
            sd += "*";
        }

        return fontStr + sd + lastStr;
    }

    public static String getEntyAccountNo(String accountNo) {
        if (empty(accountNo)) {
            return "";
        }
        String app = "";
        if (accountNo.length() <= 7) {
            return accountNo;
        }
        if (accountNo.length() == 11) {
            String fontStr = accountNo.substring(0, 3);
            String lastStr = accountNo.substring(accountNo.length() - 4, accountNo.length());
            String sd = "*";
            for (int i = 0; i < (accountNo.length()) - 8; i++) {
                sd += "*";
            }

            return fontStr + sd + lastStr + app;
        }
        if (accountNo.indexOf("@") != -1) {
            app = accountNo.substring(accountNo.lastIndexOf("."), accountNo.length());
            accountNo = accountNo.substring(0, accountNo.lastIndexOf("."));

            String fontStr = accountNo.substring(0, 2);
            String sd = "*";
            for (int i = 0; i < (((accountNo.length()) - 3) > 5 ? 5
                    : ((accountNo.length()) - 3)); i++) {
                sd += "*";
            }

            return fontStr + sd + app;
        }
        return accountNo;

    }

    public static int getFirstIndex(String content) {
        int j = 0;
        for (int i = 0; i < content.length(); i++) {
            char ch = content.charAt(i);
            if (String.valueOf(ch).equals(":") && j == 0) {
                return i + 1;
            }
        }
        return j;
    }

    public static int getMsgType(String content) {
        int j = 0;
        for (int i = 0; i < content.length(); i++) {
            char ch = content.charAt(i);
            if (String.valueOf(ch).equals("]") && j == 0) {
                return i;
            }
        }
        return j;
    }

    public static String getPrivideCode(String areaCode) {
        if (!empty(areaCode) && areaCode.length() >= 4) {
            return areaCode.substring(0, 4);
        }
        return "99999";
    }

    public static String getCityCode(String areaCode) {
        if (!empty(areaCode) && areaCode.length() >= 8) {
            return areaCode.substring(0, 8);
        }
        return "99999";
    }

    public static String subZeroAndDot(String s) {
        if (s.indexOf(".") > 0) {
            s = s.replaceAll("0+?$", "");// 去掉多余的0
            s = s.replaceAll("[.]$", "");// 如最后一位是.则去掉
        }
        return s;
    }

    /**
     * 如果比上个版本小 返回 true 等于 返回false 不会有大于最新版本的
     * 
     * @param oldVersion
     * @param newVersion
     * @param sysTypr
     * @return
     */
    public static boolean vervionComer(String systemType, String oldAppVersion,
            String newAppversion) {
        if (empty(systemType)) {
            return false;
        }
        if (empty(newAppversion)) {
            return false;
        }

        if ("1".equals(systemType)) {// 安卓
            String newVersinon = newAppversion.substring(1, newAppversion.length());
            String oldVersion = oldAppVersion.substring(1, oldAppVersion.length());
            if (Integer.parseInt(newVersinon) > Integer.parseInt(oldVersion)) {
                return true;
            }
        } else if ("2".equals(systemType)) {// ios2.2.0
            String[] newVerArray = newAppversion.split("\\.");
            String[] oldVerArray = oldAppVersion.split("\\.");
            for (int i = 0; i < newVerArray.length; i++) {
                if (Integer.parseInt(newVerArray[i]) > Integer.parseInt(oldVerArray[i])) {
                    return true;
                }
            }

        }
        return false;
    }

    // public static void main(String[] args) {
    // // String s="邹贝贝";
    // // System.out.println(isEmoji(s));
    // // System.out.println(String.format("%04d", 123));
    // // String sd="否慎";
    // // System.out.println(sd.indexOf("慎"));
    /// * String tel="17333333333";
    // System.out.println(isMobile(tel));*/
    //
    // // System.out.println(getPrivideCode("0031"));
    /// * String saveFile="voice/2016/1020/182.92.67.4/2016102017112899857.amr";
    // System.out.println(saveFile.replace("amr", "mp3"));
    // String
    // contents="【小明大夫】:http://182.92.67.4/easydoctorv2-ws/voice/2016/1027/182.92.67.4/2016102713370821462.mp3";
    // System.out.println(contents.substring(StringUtil.getFirstIndex(contents),contents.length()));*/
    // double s=2.00;
    // System.out.println(subZeroAndDot(String.valueOf(s)));
    // System.out.println(getEntyAccountNo("dsk115@163.com"));
    // System.out.println(vervionComer("2","2.2.0","2.2.1"));
    // }

}
