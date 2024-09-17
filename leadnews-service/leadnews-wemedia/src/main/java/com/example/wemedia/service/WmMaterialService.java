package com.example.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.model.common.dto.ResponseResult;
import com.example.model.wemedia.dto.WmMaterialDto;
import com.example.model.wemedia.pojo.WmMaterial;
import org.springframework.web.multipart.MultipartFile;

public interface WmMaterialService extends IService<WmMaterial> {

    /**
     * 图片上传
     * @param multipartFile
     * @return
     */
    public ResponseResult uploadPicture(MultipartFile multipartFile);

    /**
     * 素材列表查询
     * @param dto
     * @return
     */
    public ResponseResult findList(WmMaterialDto dto);

}