package com.example.fuzz.util;

import java.util.List;

/**
 * 字符串工具类
 *
 * @author kevin
 * @since 2020-07-03
 */
public class StringUtils extends org.springframework.util.StringUtils {


    /**
     * 替换最后一个匹配字符串
     * <p/>
     * 大小写敏感
     *
     * @param text        原字符串
     * @param regex       被替换的字符串正则表达式
     * @param replacement 替换字符串
     * @return 替换后的新字符串
     */
    public static String replaceLast(final String text, final String regex, final String replacement) {
        return text.replaceFirst("(?s)" + regex + "(?!.*?" + regex + ")", replacement);
    }

    /**
     * 获取锚点字符之前的最后一个字符串
     * <p/>
     * 例如：<p>
     * 在设定锚点字符为点<code>.</code>的情况下：<p>
     * <strong>1、</strong>原字符串为<code>abc.xyz.xxx.text</code>时, 返回<code>xxx</code>;<p>
     * <strong>2、</strong>原字符串为<code>abc.xyz_xxx.text</code>时, 返回<code>xyz_xxx</code>;<p>
     * <strong>3、</strong>原字符串为<code>xyz_xxx.text</code>时, 返回<code>xyz_xxx</code>;<p>
     * <strong>4、</strong>原字符串为<code>xyz_xxx_text</code>时, 返回<code>null</code>, 即没有匹配值;<p>
     * <strong>5、</strong>原字符串为<code>xyz_xxx.</code>时, 返回<code>xyz_xxx</code>;<p>
     * <strong>6、</strong>原字符串为<code>xyz_xxx..</code>时, 返回<code>''</code>, 即空字符串;<p>
     *
     * @param text  原字符串
     * @param regex 需要匹配的锚点字符
     * @return 最后一个锚点前的字符串
     */
    public static String subLastMatchPre(final String text, final String regex) {
        if (!text.contains(regex)) return null;
        String tmp = text.substring(0, text.lastIndexOf(regex));
        if (tmp.contains(regex))
            return tmp.substring(tmp.lastIndexOf(regex) + 1);
        else return tmp;
    }

    /**
     * 把string list转成字符串
     * @param list
     * @return
     */
    public static String listToStr(List<String> list) {
        if(list == null || list.size() == 0) return "";
        String result = "";
        for(String val : list) {
            result += val;
        }
        return result;
    }

    public static boolean isNotEmpty(String source) {
        return !isEmpty(source);
    }
}
