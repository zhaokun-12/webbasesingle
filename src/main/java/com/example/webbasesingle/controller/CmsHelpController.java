package com.example.webbasesingle.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.webbasesingle.entity.CmsHelp;
import com.example.webbasesingle.exception.ServiceException;
import com.example.webbasesingle.service.CmsHelpService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 帮助表(CmsHelp)表控制层
 *
 * @author makejava
 * @since 2021-06-11 11:17:13
 */
@RestController
@RequestMapping("cmsHelp")
public class CmsHelpController {
    /**
     * 服务对象
     */
    @Resource
    private CmsHelpService cmsHelpService;

    @GetMapping("all")
    public List getAll() {
        return cmsHelpService.list();
    }

    @PostMapping("page")
    public Object getAllPage(Page page) {

        throw new ServiceException("sb");
//        return cmsHelpService.page(page);
    }
}