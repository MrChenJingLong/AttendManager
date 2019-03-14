package com.hjkj.cloud.attend.ui.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hjkj.cloud.attend.infrastructure.annotation.Auth;
import com.hjkj.cloud.attend.ui.dto.ReqMsg;
import com.hjkj.cloud.attend.ui.dto.RetMsg;
import com.hjkj.cloud.attend.ui.dto.RetMsgGenerator;
import com.hjkj.cloud.attend.ui.dto.terminal.DutyDto;
import com.hjkj.cloud.attend.ui.dto.terminal.FileDataDto;
import com.hjkj.cloud.attend.ui.dto.terminal.TerminalDto;
import com.hjkj.cloud.attend.ui.dto.terminal.UserDto;
import com.hjkj.cloud.attend.application.service.TerminalService;
import hjkj.springframework.boot.doc.annotation.JLApiParam;
import hjkj.springframework.boot.doc.annotation.PropType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@JLApiParam(value = "com.hjkj.cloud.attend.ui.dto.ReqMsg",type = PropType.JSON)
public class TerminalController {

    @Autowired
    private TerminalService terminalService;

    // 查询所有终端
    @JLApiParam(name = "content",value = "com.hjkj.cloud.attend.ui.dto.terminal.TerminalDto",type = PropType.JSON)
    @RequestMapping("/attend/tm/find-all")
    public RetMsg queryAllTerminal(@RequestBody String body) {
        List<TerminalDto> allTerminal = terminalService.findAllTerminal();
        return RetMsgGenerator.genSuccessRetMsg("查询所有终端",allTerminal);
    }

    // 分页查询终端人员
    @JLApiParam(name = "content",value = "com.hjkj.cloud.attend.ui.dto.terminal.TerminalDto",type = PropType.JSON)
    @RequestMapping("/attend/tm/query-users")
    public RetMsg queryUsers(@RequestBody String body) {
        ReqMsg reqMsg = JSON.parseObject(body, ReqMsg.class);
        TerminalDto terminalInfo = reqMsg.getContentObject(TerminalDto.class);
        Page<UserDto> userDtoPage = terminalService.queryUsersOfTerminal(terminalInfo.getId(), terminalInfo.getPage(), terminalInfo.getSize());
        return RetMsgGenerator.genSuccessRetMsg("分页查询终端人员",userDtoPage);
    }

    //终端注册
    @JLApiParam(name = "content",value = "com.hjkj.cloud.attend.ui.dto.terminal.TerminalDto",type = PropType.JSON)
    @RequestMapping("/attend/tm/register-terminal")
    public RetMsg register(@RequestBody String body) {
        ReqMsg reqMsg = JSON.parseObject(body, ReqMsg.class);
        TerminalDto terminalInfo = reqMsg.getContentObject(TerminalDto.class);
        terminalService.registry(terminalInfo);
        return RetMsgGenerator.genSuccessRetMsg();
    }


    // 批量删除终端信息
    @JLApiParam(name = "content",value = "{\"terminal_ids\":[\"终端Id1\",\"终端Id2\"]}",type = PropType.STRING)
    @RequestMapping("/attend/tm/delete-terminals")
    @Auth(posts = {"经理","老板"})
    public RetMsg deleteTerminals(@RequestBody String body) {
        ReqMsg reqMsg = JSON.parseObject(body, ReqMsg.class);
        JSONArray terminal_ids = JSON.parseObject(reqMsg.getContent()).getJSONArray("terminal_ids");
        terminalService.deleteTerminals(terminal_ids.toJavaList(String.class));
        return RetMsgGenerator.genSuccessRetMsg("批量删除终端信息");
    }

    // 批量分配人员
    @JLApiParam(name = "content",value = "{\"user_ids\":[\"用户Id1\",\"用户Id2\"],\"terminal_ids\":[\"终端Id1\",\"终端Id2\"]}",type = PropType.STRING)
    @RequestMapping("/attend/tm/allot-users")
    @Auth(posts = {"经理","老板"})
    public RetMsg allotUsers(@RequestBody String body) {
        ReqMsg reqMsg = JSON.parseObject(body, ReqMsg.class);
        JSONArray user_ids = JSON.parseObject(reqMsg.getContent()).getJSONArray("user_ids");
        JSONArray terminal_ids = JSON.parseObject(reqMsg.getContent()).getJSONArray("terminal_ids");
        terminalService.allotUsers(user_ids.toJavaList(String.class),terminal_ids.toJavaList(String.class));
        return RetMsgGenerator.genSuccessRetMsg("批量删除终端信息");
    }

    @JLApiParam(name = "content",value = "{\"terminal_ids\":[\"终端Id\"],\"file_type\":\"1001\"}",type = PropType.STRING)
    @RequestMapping("/attend/tm/get-file")
    @Auth(posts = {"经理","老板"})
    public RetMsg getTerminalFile(@RequestBody String body) {
        ReqMsg reqMsg = JSON.parseObject(body, ReqMsg.class);
        JSONArray terminalIds = JSON.parseObject(reqMsg.getContent()).getJSONArray("terminal_ids");
        String fileType = JSON.parseObject(reqMsg.getContent()).getString("file_type");
        terminalService.getTerminalDataByType(terminalIds.toJavaList(String.class),fileType);
        return RetMsgGenerator.genSuccessRetMsg("获取终端数据文件");
    }

    // 批量删除终端人员
    @JLApiParam(name = "content",value = "{\"terminal_id\":\"终端Id\",\"user_ids\":[\"用户Id1\",\"用户Id2\"],\"is_clear\":false}",type = PropType.STRING)
    @RequestMapping("/attend/tm/delete-users")
    @Auth(posts = {"经理","老板"})
    public RetMsg deleteTerminalUsers(@RequestBody String body) {
        ReqMsg reqMsg = JSON.parseObject(body, ReqMsg.class);
        String tmId = JSON.parseObject(reqMsg.getContent()).getString("terminal_id");
        Boolean isClear = JSON.parseObject(reqMsg.getContent()).getBoolean("is_clear");
        if (isClear == null) {
            isClear = false;
        }
        JSONArray userIds = JSON.parseObject(reqMsg.getContent()).getJSONArray("user_ids");
        terminalService.deleteUsersBitch(tmId,userIds.toJavaList(String.class),isClear);
        return RetMsgGenerator.genSuccessRetMsg("批量删除终端信息");
    }

    // 从其他设备拷贝人员数据
    @JLApiParam(name = "content",value = "{\"source\":\"终端Id\",\"others\":[\"终端Id1\",\"终端Id2\"]}",type = PropType.STRING)
    @RequestMapping("/attend/tm/copy-users")
    @Auth(posts = {"经理","老板"})
    public RetMsg allotUsersFromTerminals(@RequestBody String body) {
        ReqMsg reqMsg = JSON.parseObject(body, ReqMsg.class);
        String sourceTmId = JSON.parseObject(reqMsg.getContent()).getString("source");
        JSONArray otherTmIds = JSON.parseObject(reqMsg.getContent()).getJSONArray("others");
        terminalService.copyUserFromOtherTerminal(sourceTmId,otherTmIds.toJavaList(String.class));
        return RetMsgGenerator.genSuccessRetMsg("从其他设备拷贝人员数据");
    }

    //下发人员数据到终端
    @JLApiParam(name = "content",value = "{\"terminal_id\":\"终端Id\",\"user_ids\":[\"用户Id1\",\"用户Id2\"]}",type = PropType.STRING)
    @RequestMapping("/attend/tm/dispatch-users")
    @Auth(posts = {"经理","老板"})
    public RetMsg dispatchUsers(@RequestBody String body) {
        ReqMsg reqMsg = JSON.parseObject(body, ReqMsg.class);
        String tmId = JSON.parseObject(reqMsg.getContent()).getString("terminal_id");
        JSONArray userIds = JSON.parseObject(reqMsg.getContent()).getJSONArray("user_ids");
        terminalService.dispatchUsers(tmId,userIds.toJavaList(String.class));
        return RetMsgGenerator.genSuccessRetMsg("下发人员数据到终端");
    }

    //检测用户是否已注册
    @JLApiParam(name = "content",value = "{\"card_num\":\"工号\",\"mac_addr\":\"mac地址\"}",type = PropType.STRING)
    @RequestMapping("/attend/tm/check-user-id")
    public RetMsg checkUserId(@RequestBody String body) {
        ReqMsg reqMsg = JSON.parseObject(body, ReqMsg.class);
        String cardNum = JSON.parseObject(reqMsg.getContent()).getString("card_num");
        String macAddr = JSON.parseObject(reqMsg.getContent()).getString("mac_addr");
        boolean result = terminalService.isExistUser(cardNum,macAddr);
        return result ? RetMsgGenerator.genFailedRetMsg("终端:" + macAddr + "已注册用户[" + cardNum + "]") : RetMsgGenerator.genSuccessRetMsg("终端:" + macAddr + "未注册用户[" + cardNum + "]");
    }

    //用户注册
    @JLApiParam(name = "content",value = "com.hjkj.cloud.attend.ui.dto.terminal.UserDto",type = PropType.JSON)
    @RequestMapping("/attend/tm/upload-user")
    public RetMsg registerUser(@RequestBody String body) {
        ReqMsg reqMsg = JSON.parseObject(body, ReqMsg.class);
        UserDto userDto = reqMsg.getContentObject(UserDto.class);
        terminalService.addUserToTerminal(userDto);
        return RetMsgGenerator.genSuccessRetMsg("用户注册成功");
    }

    //删除用户
    @JLApiParam(name = "content",value = "com.hjkj.cloud.attend.ui.dto.terminal.UserDto",type = PropType.JSON)
    @RequestMapping("/attend/tm/del-user")
    public RetMsg delUser(@RequestBody String body) {
        ReqMsg reqMsg = JSON.parseObject(body, ReqMsg.class);
        UserDto userDto = reqMsg.getContentObject(UserDto.class);
        terminalService.delUserOfTerminal(userDto.getUser_id(),userDto.getMac_addr(),userDto.isIs_del_rel(),userDto.isIs_del_data());
        return RetMsgGenerator.genSuccessRetMsg("用户删除成功");
    }

    //上传考勤记录
    @JLApiParam(name = "content",value = "com.hjkj.cloud.attend.ui.dto.terminal.DutyDto",type = PropType.JSON)
    @RequestMapping("/attend/tm/upload-record")
    public RetMsg uploadRecord(@RequestBody String body) {
        ReqMsg reqMsg = JSON.parseObject(body, ReqMsg.class);
        DutyDto dutyDto = reqMsg.getContentObject(DutyDto.class);
        terminalService.addDutyLog(dutyDto);
        return RetMsgGenerator.genSuccessRetMsg("考勤记录上传成功");
    }


}
