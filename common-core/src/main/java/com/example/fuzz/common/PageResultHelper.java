package com.example.fuzz.common;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * @author kevin
 * @version 0.1
 * @since 2020-07-03
 */
public class PageResultHelper extends PageHelper {

    public static <E> PageResult<E> startPage(int pageNum, int pageSize, boolean count) {
        return startPage(pageNum, pageSize, count, false, false);
    }

    public static <E> PageResult<E> startPage(int pageNum, int pageSize, boolean count, Boolean reasonable, Boolean pageSizeZero) {
        PageResult<E> page = new PageResult<E>(pageNum, pageSize, count);
        page.setReasonable(reasonable);
        page.setPageSizeZero(pageSizeZero);
        //当已经执行过orderBy的时候
        Page<E> oldPage = getLocalPage();
        if (oldPage != null && oldPage.isOrderByOnly()) {
            page.setOrderBy(oldPage.getOrderBy());
        }
        setLocalPage(page);
        return page;
    }
}
