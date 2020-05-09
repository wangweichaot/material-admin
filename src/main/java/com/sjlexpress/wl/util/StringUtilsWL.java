package com.sjlexpress.wl.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 继承自Spring util的工具类，减少jar依赖
 * @author wangweichao
 */
public class StringUtilsWL extends org.springframework.util.StringUtils {
    /**
     * Check whether the given {@code CharSequence} contains actual <em>text</em>.
     * <p>More specifically, this method returns {@code true} if the
     * {@code CharSequence} is not {@code null}, its length is greater than
     * 0, and it contains at least one non-whitespace character.
     * <p><pre class="code">
     * StringUtils.isBlank(null) = true
     * StringUtils.isBlank("") = true
     * StringUtils.isBlank(" ") = true
     * StringUtils.isBlank("12345") = false
     * StringUtils.isBlank(" 12345 ") = false
     * </pre>
     * @param str the {@code CharSequence} to check (may be {@code null})
     * @return {@code true} if the {@code CharSequence} is not {@code null},
     * its length is greater than 0, and it does not contain whitespace only
     * @see Character#isWhitespace
     */
    public static boolean isBlank(final CharSequence cs) {
        return !StringUtilsWL.isNotBlank(cs);
    }

    /**
     * <p>Checks if a CharSequence is not empty (""), not null and not whitespace only.</p>
     *
     * <pre>
     * StringUtils.isNotBlank(null)      = false
     * StringUtils.isNotBlank("")        = false
     * StringUtils.isNotBlank(" ")       = false
     * StringUtils.isNotBlank("bob")     = true
     * StringUtils.isNotBlank("  bob  ") = true
     * </pre>
     *
     * @param cs  the CharSequence to check, may be null
     * @return {@code true} if the CharSequence is
     *  not empty and not null and not whitespace
     * @see Character#isWhitespace
     */
    public static boolean isNotBlank(final CharSequence cs) {
        return StringUtilsWL.hasText(cs);
    }

    /**
     * Convert a {@code Collection} into a delimited {@code String} (e.g. CSV).
     * <p>Useful for {@code toString()} implementations.
     * @param coll the {@code Collection} to convert
     * @param delim the delimiter to use (typically a ",")
     * @return the delimited {@code String}
     */
    public static String join(Collection<?> coll, String delim) {
        return StringUtilsWL.collectionToDelimitedString(coll, delim);
    }

    /**
     * Convert a {@code String} array into a delimited {@code String} (e.g. CSV).
     * <p>Useful for {@code toString()} implementations.
     * @param arr the array to display
     * @param delim the delimiter to use (typically a ",")
     * @return the delimited {@code String}
     */
    public static String join(Object[] arr, String delim) {
        return StringUtilsWL.arrayToDelimitedString(arr, delim);
    }

    /**
     * 驼峰格式字符串转换为下划线格式字符串
     * @param param
     * @return
     */
    public static String camelToUnderline(String param) {
        if (param == null || "".equals(param.trim())) { return ""; }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append("_");
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 下划线格式字符串转换为驼峰格式字符串
     * @param param
     * @return
     */
    public static String underlineToCamel(String param) {
        if (param == null || "".equals(param.trim())) { return ""; }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (c == '_') {
                if (++i < len) { // 防止是最后一个,让下一个字母变为大写
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    private static final String CHARSET_NAME = "UTF-8";

    /**
     * 获取byte[]类型Key
     * @param key
     * @return
     */
    public static byte[] getBytesKey(Object object) {
        if (object instanceof String) {
            return getBytes((String) object);
        } else {
            return serialize(object);
        }
    }

    /**
     * byte[]型转换Object
     * @param key
     * @return
     */
    public static Object toObject(byte[] bytes) {
        return unserialize(bytes);
    }

    /**
     * 转换为字节数组
     * @param str
     * @return
     */
    public static byte[] getBytes(String str) {
        if (str != null) {
            try {
                return str.getBytes(CHARSET_NAME);
            } catch (final UnsupportedEncodingException e) {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Object转换byte[]类型
     * @param key
     * @return
     */
    public static byte[] toBytes(Object object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            if (object != null) {
                baos = new ByteArrayOutputStream();
                oos = new ObjectOutputStream(baos);
                oos.writeObject(object);
                return baos.toByteArray();
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 序列化对象
     * @param object
     * @return
     */
    public static byte[] serialize(Object object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            if (object != null) {
                baos = new ByteArrayOutputStream();
                oos = new ObjectOutputStream(baos);
                oos.writeObject(object);
                return baos.toByteArray();
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 反序列化对象
     * @param bytes
     * @return
     */
    public static Object unserialize(byte[] bytes) {
        ByteArrayInputStream bais = null;
        try {
            if (bytes != null && bytes.length > 0) {
                bais = new ByteArrayInputStream(bytes);
                final ObjectInputStream ois = new ObjectInputStream(bais);
                return ois.readObject();
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
	 * <br />
	 * @Description: 判断字符串不为空（加固处理）
	 * <br />
	 * @param source
	 * <br />
	 * @return boolean  
	 * <br />
	 * @author ZuoJun
	 * <br />
	 * @date 2016-7-27 15:43
	 * <br />
	 */
	public static boolean isNotEmpty(String source){
		if(source != null && !"".equals(source) && !"null".equalsIgnoreCase(source) && !"undefined".equalsIgnoreCase(source)){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 
	 * @param source
	 * @return
	 */
	public static String nullToBlank(String source){
		if(source != null && !"null".equalsIgnoreCase(source) && !"undefined".equalsIgnoreCase(source)){
			return source;
		}else{
			return "";
		}
	}

	
	/**
	 * <br />
	 * @Description: 判断字符串为空（加固处理）
	 * <br />
	 * @param source
	 * <br />
	 * @return boolean  
	 * <br />
	 * @author ZuoJun
	 * <br />
	 * @date 2016-7-27 15:44
	 * <br />
	 */
	public static boolean isEmpty(String source){
		return !isNotEmpty(source);
	}
	
	/**
	 * 判断字符串不为空字符串
	 * @param source
	 * @return
	 */
	public static boolean isNotBlank(String source){
		return isNotEmpty(source) && isNotEmpty(source.trim());
	}
	
	/**
	 * 判断字符串为空字符串
	 * @param source
	 * @return
	 */
	public static boolean isBlank(String source){
		return !isNotBlank(source);
	}

	public static String getString(Object o) {
		return o == null?null:(o instanceof String?(String)o:o.toString());
	}
	
	
	/**
	 * java去除字符串中的空格、回车、换行符、制表符
	 * @param str
	 * @return
	 */
	public static String replaceBlank(String str) {
		String dest = "";
		if (str!=null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}
}
