package com.yd.JJLin.admin.controller;

import com.yd.JJLin.admin.model.dto.UserDTO;
import com.yd.JJLin.admin.model.entity.UserInfo;
import com.yd.JJLin.admin.service.UserService;
import com.yd.JJLin.common.constant.enums.ResultErrorEnum;
import com.yd.JJLin.common.model.PagerRequestVO;
import com.yd.JJLin.common.model.ResultGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户相关接口
 *
 * @author wangyuandong
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
        int total = userService.getUserCount();
        return ResultGenerator.genSuccessPager(userList, total);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.ALL_VALUE)
    public String createUser(@RequestBody UserInfo userInfo) {
        boolean existsUser = userService.existsUser(userInfo.getUsername());
        if (existsUser) {
            return ResultGenerator.genErrorResult(ResultErrorEnum.USERNAME_EXISTS_ERROR);
        }
        UserInfo newUserInfo = userService.createUser(userInfo);
        return ResultGenerator.genSuccessResult(newUserInfo);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String login(@RequestBody UserInfo userInfo) {
        UserDTO user =  userService.queryByUserName(userInfo.getUsername(), userInfo.getPassword());
        if (user == null) {
            return ResultGenerator.genErrorResult(ResultErrorEnum.PASSWORD_ERROR);
        }
        if (user.getStatus() != 1) {
            return ResultGenerator.genErrorResult(ResultErrorEnum.STATUS_ERROR);
        }

//        user.setUid(user.getId().toString());
        return ResultGenerator.genSuccessResult(user);
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String info(@RequestParam Long id) {
        UserDTO user =  userService.queryByUserId(id);
        if (user == null) {
            return ResultGenerator.genErrorResult(ResultErrorEnum.USER_ERROR);
        }
        return ResultGenerator.genSuccessResult(user);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String update(@RequestBody UserInfo userInfo) {
        UserDTO user =  userService.queryByUserId(userInfo.getId());
        if (user == null) {
            return ResultGenerator.genErrorResult(ResultErrorEnum.USER_ERROR);
        }
        userService.update(userInfo);
        return ResultGenerator.genSuccessResult();
    }
}
