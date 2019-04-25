package com.beidou.user.controller;

import com.beidou.common.annotation.SysLogger;
import com.beidou.common.entity.ResponseMsg;
import com.beidou.common.util.StringUtil;
import com.beidou.gateway.entity.RoleRule;
import com.beidou.user.service.RoleRuleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Api(value = "RoleRuleController|角色-权限管理管理操作")
@RestController
public class RoleRuleController {

    @Autowired
    private RoleRuleService roleRuleService;


    
    @ApiOperation(value="添加角色-权限管理信息", notes="添加角色-权限管理信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色ID", required = true, dataType = "int", paramType="query"),
            @ApiImplicitParam(name = "ruleIds", value = "权限id（用逗号隔开）", required = true, dataType = "String", paramType="query")
    })
    @PostMapping(value = "/roleRule")
    public ResponseMsg insert(@RequestParam(value = "roleId")Integer roleId, @RequestParam("ruleIds")String ruleIds){
        ResponseMsg responseMsg;
        if(!StringUtil.isEmpty(ruleIds)){
            //批量删除
            if(ruleIds.contains(",")){
                List<Integer> add_ids = new ArrayList();
                String[] str_ids = ruleIds.split(",");
                //组装id的集合
                for (String string : str_ids) {
                    add_ids.add(Integer.parseInt(string));
                }
                responseMsg=roleRuleService.batchInsert(roleId,add_ids);
            }else{
                Integer id = Integer.parseInt(ruleIds);
                RoleRule roleRule=new RoleRule(roleId,id);
                responseMsg=roleRuleService.insert(roleRule);
            }
            return responseMsg;
        }else{
            return ResponseMsg.Error("请选择要添加的权限");
        }
    }


    
    @ApiOperation(value="更新id对应的角色-权限管理信息", notes="更新id对应的角色-权限管理信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色ID", required = true, dataType = "int", paramType="query"),
            @ApiImplicitParam(name = "ruleIds", value = "权限id（用逗号隔开）", required = true, dataType = "String", paramType="query")
    })
    @PutMapping(value="/roleRule")
    public ResponseMsg updateByRoleId(@RequestParam("roleId")Integer roleId,@RequestParam("ruleIds")String ruleIds){
        ResponseMsg responseMsg;
        if(!StringUtil.isEmpty(ruleIds)){
            //批量更新
            if(ruleIds.contains(",")){
                List<Integer> add_ids = new ArrayList();
                String[] str_ids = ruleIds.split(",");
                //组装id的集合
                for (String string : str_ids) {
                    add_ids.add(Integer.parseInt(string));
                }
                responseMsg=roleRuleService.updateByRoleIdBatch(roleId,add_ids);
            }else{
                Integer id = Integer.parseInt(ruleIds);
                RoleRule roleRule=new RoleRule(roleId,id);
                responseMsg=roleRuleService.updateByRoleId(roleRule);
            }
            return responseMsg;
        }else{
            return ResponseMsg.Error("请选择要添加的权限");
        }
    }
}
