package com.example.webbasesingle.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.webbasesingle.dao.CmsHelpDao;
import com.example.webbasesingle.entity.CmsHelp;
import com.example.webbasesingle.service.CmsHelpService;
import org.springframework.stereotype.Service;

/**
 * 帮助表(CmsHelp)表服务实现类
 *
 * @author makejava
 * @since 2021-06-11 11:17:12
 */
@Service("cmsHelpService")
public class CmsHelpServiceImpl extends ServiceImpl<CmsHelpDao, CmsHelp> implements CmsHelpService {

}