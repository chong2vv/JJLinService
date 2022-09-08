package com.ry.time.admin.controller;

import com.ry.time.admin.model.dto.UserDTO;
import com.ry.time.admin.model.entity.UserInfo;
import com.ry.time.admin.service.UserService;
import com.ry.time.common.constant.enums.ResultErrorEnum;
import com.ry.time.common.model.PagerRequestVO;
import com.ry.time.common.model.ResultGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户相关接口
 *
 * @author gongjiguang
 * @date 2022/9/2
 */
@RestController
@RequestMapping("/vue-admin-template/user/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getUserList(PagerRequestVO pagerRequestVO) {
        List<UserDTO> userList = userService.getUserList(pagerRequestVO);
        return ResultGenerator.genSuccessResult(userList);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String createUser(@RequestBody UserInfo userInfo) {
        boolean existsUser = userService.existsUser(userInfo.getUsername());
        if (existsUser) {
            return ResultGenerator.genErrorResult(ResultErrorEnum.USERNAME_EXISTS_ERROR);
        }
        userService.createUser(userInfo);
        return ResultGenerator.genSuccessResult();
    }
}
