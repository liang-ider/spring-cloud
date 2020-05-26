package project.controller;

import cn.project.utils.HttpClientHelper;
import cn.project.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@Api(tags = "负责调用模板接口")
@RequestMapping("/api")
public class APITemplateController {
    @Resource
    HttpClientHelper httpClientHelper;
    @ApiOperation(value = "负责调用基本数据模块中的模板控制器(根据条件查询所有模板并分页)")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "prescriptionTypeId",value = "处方类型ID(表示该药品属于(西药、中药、检查项目中的哪一种))"),
            @ApiImplicitParam(paramType = "query",name = "templatePermission",value = "模板权限(0:私有,1:公有)"),
            @ApiImplicitParam(paramType = "query",name = "templateNoOrName",value = "模板名称或拼音"),
            @ApiImplicitParam(paramType = "query",name = "pageNo",value = "当前页",required = true),
            @ApiImplicitParam(paramType = "query",name = "pageSize",value = "每页显示大小",required = true)
    })
    @GetMapping("/getAllTemplate")
    public Response getAllTemplate(@RequestParam Integer pageNo, @RequestParam Integer pageSize, @RequestParam(required = false) Integer prescriptionTypeId, @RequestParam(required = false) Integer templatePermission, @RequestParam(required = false) String templateNoOrName){
        if(pageNo == null) pageNo = 1;
        if(pageSize == null) pageSize = 3;
        StringBuffer buffer = new StringBuffer("?").append("pageNo=").append(pageNo).append("&pageSize=").append(pageSize);
        if(prescriptionTypeId!=null) {
            buffer.append("&prescriptionTypeId=").append(prescriptionTypeId);
        }
        if(templatePermission!=null) {
            buffer.append("&templatePermission=").append(templatePermission);
        }
        if(templateNoOrName!=null) {
            buffer.append("&templateNoOrName=").append(templateNoOrName);
        }
        return httpClientHelper.get("http://localhost:8082/template/getAllTemplate"+buffer);
    }

    @GetMapping("/getTemplateDetails")
    @ApiOperation(value = "负责调用基本数据模块中的模板控制器(根据模板ID获取模板下的处方详情)")
    @ApiImplicitParam(paramType = "query",value = "模板ID",name = "id",required = true)
    public Response getTemplateDetails(@RequestParam Integer id){
        return httpClientHelper.get("http://localhost:8082/template/"+id);
    }
    @PostMapping("/add")
    public Response add(String name){
        Map<String,Object> map = new HashMap<>();
        map.put("name",name);
        return httpClientHelper.postForResponse("http://localhost:8083/medicine/abc",map);
    }

}
