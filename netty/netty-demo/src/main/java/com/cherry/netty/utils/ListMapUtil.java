package com.cherry.netty.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ListMapUtil {
    /**
     * 将数组转变成list
     * 
     * @param objects
     * @return
     */
    public static <T> List<T> array2List(T[] objects) {
        List<T> list = null;
        if (objects != null && objects.length > 0) {
            list = new ArrayList<T>();
            for (int i = 0; i < objects.length; i++)
                list.add(objects[i]);

        }
        return list;
    }

    public static <T> Set<T> array2Set(T[] objects) {
        Set<T> set = null;
        if (objects != null && objects.length > 0) {
            set = new HashSet<T>();
            for (int i = 0; i < objects.length; i++)
                set.add(objects[i]);

        }
        return set;
    }

    /**
     * 将两个Collection对象合并（取交集） 注：将拷贝对象中的与源中不重复的拷贝到源中
     * 
     * @param cs
     *            源
     * @param cf
     *            拷贝对象
     */
    public static void mergeList(Collection<Object> cs, Collection<Object> cf) {
        if (cs != null && cf != null)
            for (Iterator<Object> iter = cf.iterator(); iter.hasNext();) {
                Object obj = iter.next();
                if (!cs.contains(obj)) {
                    cs.add(obj);
                }
            }
    }

    /**
     * 从一个list中减去另外一个list
     * 
     * @param fromList
     *            减数
     * @param minuendList
     *            被减数
     * @return list
     */
    public static List<Object> minusList(Collection<Object> fromList,
            Collection<Object> minuendList) {
        if (fromList != null && minuendList != null)
            for (Iterator<Object> iter = minuendList.iterator(); iter.hasNext();) {
                Object obj = iter.next();

                if (fromList.contains(obj)) {
                    fromList.remove(obj);
                }
            }

        return new ArrayList<Object>(fromList);
    }

    /**
     * 是否空list
     * 
     * @param list
     * @return
     */
    public static boolean isEmptyList(List<?> list) {
        return list == null || list.size() < 1;
    }

    /**
     * 是否空list
     * 
     * @param list
     * @return
     */
    public static <T> List<T> copyList(List<T> list) {
        List<T> ls = new ArrayList<T>();
        if (ListMapUtil.isEmptyList(list))
            return null;
        for (int i = 0; i < list.size(); i++) {
            ls.add(list.get(i));
        }
        return ls;
    }

    /**
     * 按逗号分隔将其转为LIST
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> getTlist(String str) {
        String strreplace = str.replaceAll("\'", "");
        List<T> list = new ArrayList<T>();
        if (!StringUtil.empty(strreplace)) {
            T[] args = (T[]) strreplace.split(",");
            for (int n = 0; args != null && args.length > n; n++) {
                list.add(args[n]);
            }
        }
        return list;
    }

    /**
     * LIST将其转为按逗号分隔
     * 
     * @return
     */
    public static <T> String getTlistToString(List<T> list) {
        StringBuffer strBuffer = new StringBuffer("");
        for (int i = 0; list != null && list.size() > i; i++) {
            strBuffer.append(list.get(i));
            if (list.size() > i + 1) {
                strBuffer.append(",");
            }
        }
        return strBuffer.toString();
    }

    /**
     * 把一个list 字符转换成 sql 中 in 语句
     * 
     * @param list
     * @return
     * @return
     */
    public static <T> String getSelectInStr(List<T> list) {
        StringBuffer strBuffer = new StringBuffer("");
        for (int i = 0; list != null && list.size() > i; i++) {
            strBuffer.append("'").append(list.get(i)).append("'");
            if (list.size() > i + 1) {
                strBuffer.append(",");
            }
        }
        return strBuffer.toString();
    }
}
