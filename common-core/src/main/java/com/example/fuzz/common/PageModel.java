package com.example.fuzz.common;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 通用分页类
 *
 * @author kevin
 * @version 0.1
 * @since 2020-07-04
 */
@Slf4j
@Data
public class PageModel {

//    Logger log = LoggerFactory.getLogger(this.getClass());
    // 分页查询时，每页显示最大数量，默认为500
    private static final int MAX_PAGE_SIZE = 500;

    @ApiModelProperty("页码")
    private int pageNum;                // 页码,默认1
    @ApiModelProperty("每页显示数量")
    private int pageSize;               // 每页显示数量,默认10
    @ApiModelProperty(hidden = true)
    private boolean count = true;       // 是否进行count查询
    @ApiModelProperty(hidden = true)
    private boolean pageable = true;    // 是否可分页

    public <E> PageResult<E> startPage() {
        try {
            if (this.pageable) {
                if (this.pageSize > MAX_PAGE_SIZE || this.pageSize < 1)
                    this.pageSize = 10;
                if (this.pageNum < 1) this.pageNum = 1;
                return PageResultHelper.startPage(this.pageNum, this.pageSize, this.count);
            } else {
                log.warn("分页被禁止");
                return null;
            }
        } catch (Exception e) {
            log.error("分页失败：{}", e.getMessage());
            throw new RuntimeException("分页失败：" + e.getMessage());
        }
    }
}
