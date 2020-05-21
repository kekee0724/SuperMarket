package org.kekee.entity;

import lombok.Data;

/**
 * @author cocoa
 */
@Data
public class Page {
    private int size;//每页信息条数
    private int totalCount;//总信息条数
    private int totalPage;//总页数
    private int currentpage;//当前页

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        if (totalCount % size == 0) {
            this.totalPage = totalCount / size;
        } else {
            this.totalPage = totalCount / size + 1;
        }
    }
}
