package com.sangeng.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.sangeng.domain.ResponseResult;
import com.sangeng.domain.entity.SgCategory;
import com.sangeng.domain.vo.CategoryListVo;
import com.sangeng.domain.vo.ExcelCategoryVo;
import com.sangeng.enums.AppHttpCodeEnum;
import com.sangeng.service.SgCategoryService;
import com.sangeng.utils.BeanCopyUtils;
import com.sangeng.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author 曼迪卡尔多
 * @ClassName CategoryController
 * @description: TODO
 * @date 2023年08月02日
 * @version: 1.0
 */
@RestController
public class CategoryController {
    @Autowired
    private SgCategoryService categoryService;

    @GetMapping("/content/category/listAllCategory")
    public ResponseResult listAllCategory(){

        return ResponseResult.okResult(categoryService.listAllCategory());
    }
    @PreAuthorize("@ps.hasPermission('content:category:export')")
    @GetMapping("/content/category/export")
    public void export(HttpServletResponse response){

        try {
            //设置下载文件的请求头
            WebUtils.setDownLoadHeader("分类.xlsx",response);
            //获取需要导出的数据
            List<SgCategory> list = categoryService.list();
            List<ExcelCategoryVo> excelCategoryVos = BeanCopyUtils.copyBeanList(list, ExcelCategoryVo.class);
            //把数据写入到Excel中
            EasyExcel.write(response.getOutputStream(), ExcelCategoryVo.class).autoCloseStream(Boolean.FALSE).sheet("分类导出")
                    .doWrite(excelCategoryVos);

        } catch (Exception e) {
            e.printStackTrace();
            //如果出现异常就相应json数据
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
            WebUtils.renderString(response, JSON.toJSONString(result));
        }


    }
}
