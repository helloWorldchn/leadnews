package com.example.search.controller.v1;

import com.example.model.common.dto.ResponseResult;
import com.example.model.search.dto.UserSearchDto;
import com.example.search.service.ApAssociateWordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/associate")
public class ApAssociateWordsController {

    @Autowired
    private ApAssociateWordsService apAssociateWordsService;

    @PostMapping("/search")
    public ResponseResult search(@RequestBody UserSearchDto dto){
        return apAssociateWordsService.search(dto);
    }
}
