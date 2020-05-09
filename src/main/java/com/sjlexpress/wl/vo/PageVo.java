package com.sjlexpress.wl.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Min;

import com.github.pagehelper.Page;
import com.sjlexpress.wl.param.PageParam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 分页数据
 *
 * @author zhicheng.lai
 */
@ApiModel(value = "com.sjlexpress.wl.vo.PageParam", description = "分页实体")
@Data
public class PageVo<T> implements Serializable {

    @ApiModelProperty(hidden = true, value = "返回数据集合")
    private List<T> list;

    @ApiModelProperty(name = "page_num", value = "当前页码，默认1")
    @Min(value = 0, message = "请选择大于0的页码")
    private Integer pageNum = 1;

    @ApiModelProperty(name = "page_size", value = "每页大小，默认10")
    @Min(value = 1, message = "一页最少1条数据")
    private Integer pageSize = 10;

    @ApiModelProperty(value = "总数据量,如果传了则后台不再重复查询总数据量，已有的都最好传一下")
    @Min(value = 0, message = "总数量大于0")
    private Long total = 0L;

    public PageVo(PageVo pageVo) {
        this.pageNum = pageVo.getPageNum();
        this.pageSize =  pageVo.getPageSize();
        this.total = pageVo.getTotal();
    }

    /**
     * 分页数据初始化
     */
    public PageVo(List<T> rows, PageParam pageParam) {
        this.setPageSize(pageParam.getPageSize());
        this.setPageNum(pageParam.getPageNum());
        if (rows instanceof com.github.pagehelper.Page) {
            Page<T> page = (Page<T>) rows;
            if (this.total == 0) {
                this.pageSize = page.getPageSize();
                this.pageNum = page.getPageNum();
                this.total = page.getTotal();
            }
            this.list = page.getResult();
        } else {
            this.list = rows;
        }
    }




}
