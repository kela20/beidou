package com.beidou.gateway.service;


import com.beidou.common.entity.SysLog;
import com.github.pagehelper.PageInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(value = "logger")
public interface SysLogService {

    @GetMapping(value="/syslog/{id}")
    public SysLog getById(@PathVariable("id")Integer id);


    @GetMapping(value="/syslogs")
    public PageInfo getAllList(@RequestParam(value = "pageNum", defaultValue = "1")Integer pageNum ,
                               @RequestParam(value = "pageSize", defaultValue = "10")Integer pageSize);


    @GetMapping(value="/userSysLogs")
    public PageInfo getMyList(@RequestParam(value = "pageNum", defaultValue = "1")Integer pageNum ,
                              @RequestParam(value = "pageSize", defaultValue = "10")Integer pageSize,
                              @RequestParam(value = "username")String username
                              );


    @GetMapping(value="/syslog/searchByUsername")
    public PageInfo searchByUsername(@RequestParam(value = "pageNum", defaultValue = "1")Integer pageNum,
                                     @RequestParam(value = "pageSize", defaultValue = "10")Integer pageSize,
                                     @RequestParam(value = "username")String username);
}
