package com.example.fuzz.common;

import com.github.pagehelper.Page;

/**
 * 本系统使用的分页数据模型
 *
 * @author kevin
 * @version 0.1
 * @since 2020-07-03
 */
public class PageResult<E> extends Page<E> {

    public PageResult() {
    }

    public PageResult(int pageNum, int pageSize) {
        super(pageNum, pageSize);
    }

    public PageResult(int pageNum, int pageSize, boolean count) {
        super(pageNum, pageSize, count);
    }

    public static <E> PageResult.PageResultBuilder<E> builder() {
        return new PageResult.PageResultBuilder<>();
    }

    public static class PageResultBuilder<E> {
        private int pageNum;
        private int pageSize;
        private boolean count = true;
        private String orderBy;

        public PageResultBuilder<E> pageNum(int pageNum) {
            this.pageNum = pageNum;
            return this;
        }

        public PageResultBuilder<E> pageSize(int pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public PageResultBuilder<E> count(boolean count) {
            this.count = count;
            return this;
        }

        public PageResultBuilder<E> orderBy(String orderBy) {
            this.orderBy = orderBy;
            return this;
        }

        public PageResult<E> build() {
            PageResult<E> pageResult = new PageResult<>(this.pageNum, this.pageSize, this.count);
            pageResult.setOrderBy(this.orderBy);
            return pageResult;
        }
    }
}
