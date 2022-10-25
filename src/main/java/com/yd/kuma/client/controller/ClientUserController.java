package com.yd.kuma.client.controller;

import com.yd.kuma.admin.model.dto.UserDTO;
import com.yd.kuma.admin.model.entity.UserInfo;
import com.yd.kuma.client.service.ClientUserService;
import com.yd.kuma.common.constant.enums.ResultErrorEnum;
import com.yd.kuma.common.model.ResultGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: wangyuandong
 * @Date: 2022/10/25 13:39
 */
@RestController
@RequestMapping("/client/user/")
@RequiredArgsConstructor
public class ClientUserController {

    private final ClientUserService clientUserService;

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String login(@RequestBody UserInfo userInfo) {
        UserDTO user =  clientUserService.queryByUserName(userInfo.getUsername(), userInfo.getPassword());
        if (user == null) {
            return ResultGenerator.genErrorResult(ResultErrorEnum.PASSWORD_ERROR);
        }
        if (user.getStatus() != 1) {
            return ResultGenerator.genErrorResult(ResultErrorEnum.STATUS_ERROR);
        }
        user.setUid(user.getId().toString());
        return ResultGenerator.genSuccessResult(user);
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String info(@RequestParam Long id) {
        UserDTO user =  clientUserService.queryByUserId(id);
        user.setUid(user.getId().toString());
        if (user == null) {
            return ResultGenerator.genErrorResult(ResultErrorEnum.USER_ERROR);
        }
        return ResultGenerator.genSuccessResult(user);
    }
}
