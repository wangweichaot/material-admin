package com.sjlexpress.wl.param;

import com.github.pagehelper.PageHelper;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;

import java.io.Serializable;

/**
 * 分页数据
 *
 * @author zhicheng.lai
 */
@ApiModel(value = "com.sjlexpress.wl.param.PageParam", description = "分页参数")
@Data
public class PageParam implements Serializable {

    @ApiModelProperty(value = "当前页码，默认1")
    @Min(value = 0, message = "请选择大于0的页码")
    private Integer pageNum = 1;

    @ApiModelProperty(value = "每页大小，默认10")
    @Min(value = 0, message = "一个最少0条数据")
    private Integer pageSize = 10;

    @ApiModelProperty(name = "sortName", value = "排序字段名，没有排序功能的不要传")
    private String sortName;

    @ApiModelProperty(value = "排序方式，true顺序，false倒叙")
    private Boolean sort = false;

    /**
     * 处理分页
     */
    public void pageHelper() {
        //是否查询总数量
        if (this.pageNum > 1) {
            PageHelper.startPage(this.pageNum, this.pageSize, true);
        } else {
            PageHelper.startPage(this.pageNum, this.pageSize);
        }
        //顺序
        if (StringUtils.isNotBlank(sortName)) {
//            PageHelper.orderBy(this.sortName);
        	String sortStr =(sort == true)?"asc":"desc";
        	PageHelper.orderBy(this.sortName + sortStr);
        } else {
            
        }
    }

}
