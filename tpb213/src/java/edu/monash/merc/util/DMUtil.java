/*
 * Copyright (c) 2011-2012, Monash e-Research Centre
 * (Monash University, Australia)
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 	* Redistributions of source code must retain the above copyright
 * 	  notice, this list of conditions and the following disclaimer.
 * 	* Redistributions in binary form must reproduce the above copyright
 * 	  notice, this list of conditions and the following disclaimer in the
 * 	  documentation and/or other materials provided with the distribution.
 * 	* Neither the name of the Monash University nor the names of its
 * 	  contributors may be used to endorse or promote products derived from
 * 	  this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package edu.monash.merc.util;

import edu.monash.merc.exception.DMException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.EmailValidator;

import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Simon Yu
 * @version 1.0
 * @email Xiaoming.Yu@monash.edu
 * <p/>
 * Date: 27/02/12
 * Time: 1:19 PM
 */
public class DMUtil {
    private static final Object lock = new Object();

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static final String DATE_YYYYMMDD_FORMAT = "yyyy-MM-dd";

    private static final String DATE_FORMAT2 = "yyyyMMddHHmmss";

    private static final String DATE_DDMMYYYY_FORMAT = "dd-MM-yyyy";

    private static final String DATE_UTC_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    private static final String COMMA_SEPARATOR = ",";

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static Map<String, String> sortByValue(Map<String, String> map) {
        List list = new LinkedList(map.entrySet());

        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o1)).getValue()).compareTo(((Map.Entry) (o2)).getValue());
            }
        });

        Map<String, String> result = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext(); ) {
            Map.Entry entry = (Map.Entry) it.next();
            result.put((String) entry.getKey(), (String) entry.getValue());
        }
        return result;
    }

    public static boolean notGTFixedLength(final String str, int length) {
        if (StringUtils.isBlank(str)) {
            return true;
        }
        if (str.trim().length() > length) {
            return false;
        }
        return true;
    }

    public static Date formatDate(final String dateStr) {
        Date date = null;
        DateFormat df = new SimpleDateFormat(DATE_FORMAT);
        try {
            date = df.parse(dateStr);
        } catch (ParseException e) {
            throw new DMException(e.getMessage());
        }
        return date;
    }

    public static Date formatYMDDate(final String dateStr) {
        Date date = null;
        DateFormat df = new SimpleDateFormat(DATE_YYYYMMDD_FORMAT);
        try {
            date = df.parse(dateStr);
        } catch (ParseException e) {
            throw new DMException(e.getMessage());
        }
        return date;
    }

    public static String formatDateToUTC(final Date date) {
        DateFormat simpleDateFormat = new SimpleDateFormat(DATE_UTC_FORMAT, Locale.US);
        return simpleDateFormat.format(date) + "Z";
    }

    public static String genCurrentTimestamp() {
        Date date = GregorianCalendar.getInstance().getTime();
        DateFormat df = new SimpleDateFormat(DATE_FORMAT2);
        return df.format(date);
    }

    public static boolean isToday(Date dateTime) {
        Calendar someCa = GregorianCalendar.getInstance();
        someCa.setTime(dateTime);

        int syear = someCa.get(Calendar.YEAR);
        int smonth = someCa.get(Calendar.MONTH);
        int sday = someCa.get(Calendar.DAY_OF_YEAR);

        Calendar currentCa = GregorianCalendar.getInstance();

        int cyear = currentCa.get(Calendar.YEAR);
        int cmonth = currentCa.get(Calendar.MONTH);
        int cday = currentCa.get(Calendar.DAY_OF_YEAR);
        if (syear == cyear && smonth == cmonth && sday == cday) {
            return true;
        }
        return false;
    }

    public static String dateToYYYYMMDDStr(Date dateTime) {
        SimpleDateFormat sdfDestination = new SimpleDateFormat("yyyy-MM-dd");
        return sdfDestination.format(dateTime);
    }

    public static String dateToDDMMYYYYStr(Date dateTime) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_DDMMYYYY_FORMAT);
        return simpleDateFormat.format(dateTime);
    }

    public static String generateIdBasedOnTimeStamp() {
        String suffix = null;
        synchronized (lock) {
            try {
                Thread.sleep(10);
            } catch (Exception e) {
                // ignore whatever
            }
            // Long time = new Long("12219292800000");
            Long time = System.currentTimeMillis();
            long currentTime = new Date().getTime() + time.longValue();
            suffix = encode(currentTime);
        }
        return suffix;
    }

    private static String encode(long num) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < 9; i++) {
            buf.append(removeVowels(num % 31));
            num = num / 31;
        }
        return buf.toString().toLowerCase();
    }

    /**
     * Remove the vowels.
     *
     * @param num - The integer value.
     * @return a char.
     */
    private static char removeVowels(long num) {
        char formattedChar = 0;

        // System.out.println("num: = " + num);
        if (num < 10) {
            formattedChar = (char) (num + '0');
        } else if (num < 13) {
            formattedChar = (char) (num - 10 + 'B');
        } else if (num < 16) {
            formattedChar = (char) (num - 9 + 'B');
        } else if (num < 21) {
            formattedChar = (char) (num - 8 + 'B');
        } else if (num < 26) {
            formattedChar = (char) (num - 7 + 'B');
        } else {
            formattedChar = (char) (num - 6 + 'B');
        }
        return formattedChar;
    }

    public static String normalizePath(String path) {
        if (StringUtils.endsWith(path, "/")) {
            return StringUtils.removeEnd(path, "/");
        } else {
            return path;
        }
    }

    public static String genUUIDWithPrefix(String prefix) {
        UUID uuid = UUID.randomUUID();
        return prefix + uuid.toString();
    }

    public static String genUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public static String pathEncode(String fileName) throws Exception {
        String encodedStr = URLEncoder.encode(fileName, "UTF-8");
        return encodedStr;
    }

    public static String replaceURLAmpsands(String url) {
        return StringUtils.replace(url, "&", "&amp;");
    }

    /**
     * Validate the email adress.
     *
     * @param email The email address.
     * @return true if it is a valid email address.
     */
    public static boolean validateEmail(String email) {
        EmailValidator validator = EmailValidator.getInstance();
        return validator.isValid(email);
    }

    public static List<String> splitAnzsrcCode(String anzsrcCodes) {
        String[] codes = StringUtils.split(anzsrcCodes, COMMA_SEPARATOR);
        return Arrays.asList(codes);
    }

    public static String[] splitStrByDelim(String inputStr, String delimiter) {
        String[] temps = StringUtils.split(inputStr, delimiter);
        for (int i = 0; i < temps.length; i++) {
            temps[i] = temps[i].trim();
        }
        return temps;
    }

    public static String[] splitByDelims(String inputStr, String... delims) {
        String delimiters = "[";
        for (String delim : delims) {
            delimiters += delim;
        }
        delimiters += "]";

        String[] temps = StringUtils.split(inputStr, delimiters);
        List<String> finalStr = new ArrayList<String>();
        for (int i = 0; i < temps.length; i++) {
            if (StringUtils.isNotBlank(temps[i])) {
                finalStr.add(temps[i].trim());
            }
        }
        return finalStr.toArray(new String[finalStr.size()]);
    }

    public static String replaceAllDelimsByNewDelim(String inputStr, String newDelim, String[] oldDelims) {
        String[] delimRemovedStr = splitByDelims(inputStr, oldDelims);
        int i = 0;
        String temp = "";
        for (String strElement : delimRemovedStr) {
            if (StringUtils.isNotBlank(strElement)) {
                temp += strElement;
                if (i < delimRemovedStr.length - 1) {
                    temp += newDelim;
                }
            }
            i++;
        }
        return temp.trim();
    }

    public static String replaceSpace(String spaceStr) {
        return StringUtils.remove(spaceStr, " ");
    }

    public static String[] split(String str) {
        if (StringUtils.isBlank(str)) {
            return new String[]{""};
        } else {
            String[] tokens = str.split("");
            List<String> tmpList = new ArrayList<String>();
            for (String token : tokens) {
                if (StringUtils.isNotBlank(token)) {
                    tmpList.add(token);
                }
            }
            return tmpList.toArray(new String[tmpList.size()]);
        }
    }

    public static void main(String[] args) {
        Date date = GregorianCalendar.getInstance().getTime();
        System.out.println("============ date: " + date);
        long time = date.getTime();
        time -= 24 * 3600 * 1000;
        date.setTime(time);
        System.out.println("=========== date -1: " + date);

        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        Date yesterdayMidnight = cal.getTime();

        System.out.println("============== yesterday midnight: " + yesterdayMidnight);

        String s = "1000";
        String[] words = DMUtil.split(s);
        System.out.println("==== words size: " + words.length);
        for (String string : words) {
            if (StringUtils.isNotBlank(string)) {
                System.out.println(">" + string + "<");
            }
        }
        char[] validchars = new char[]{'1', '0'};
        boolean valid = StringUtils.containsOnly(s, validchars);
        System.out.println(" is valid: " + valid);
    }
}
