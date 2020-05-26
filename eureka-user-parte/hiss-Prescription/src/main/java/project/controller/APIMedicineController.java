package project.controller;

import cn.project.utils.HttpClientHelper;
import cn.project.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("api")
@Api(tags = "负责调用药品控制器")
public class APIMedicineController {
    @Resource
    HttpClientHelper httpClientHelper;

    @ApiOperation(value = "负责调用基本数据模块中的药品控制器(根据条件查询所有药品并分页)")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "prescriptionTypeId",value = "处方类型ID(表示该药品属于(西药、中药、检查项目中的哪一种))",required = true),
            @ApiImplicitParam(paramType = "query",name = "medicineTypeId",value = "药品类型ID"),
            @ApiImplicitParam(paramType = "query",name = "nameOrPinYin",value = "药品名称或拼音"),
            @ApiImplicitParam(paramType = "query",name = "page",value = "当前页",required = true),
            @ApiImplicitParam(paramType = "query",name = "limit",value = "每页显示大小",required = true)
    })
    @GetMapping("getMedicine")
    public Object getMedicine(@RequestParam Integer prescriptionTypeId, @RequestParam(required = false) Integer medicineTypeId, @RequestParam(required = false) String nameOrPinYin, @RequestParam Integer page,@RequestParam Integer limit){
        if(page == null){
            page = 1;
        }
        if(limit == null){
            limit = 2;
        }
        StringBuffer buffer = new StringBuffer("?").append("pageNo=").append(page).append("&pageSize=").append(limit);
        if(prescriptionTypeId!=null) {
            buffer.append("&prescriptionTypeId=").append(prescriptionTypeId);
        }
        if(medicineTypeId!=null) {
            buffer.append("&medicineTypeId=").append(medicineTypeId);
        }
        if(nameOrPinYin!=null) {
            buffer.append("&nameOrPinYin=").append(nameOrPinYin);
        }
        return httpClientHelper.get("http://localhost:8083/medicine/getMedicines"+buffer).getResponseBody();
    }

    @GetMapping("getAllMedicineType")
    @ApiOperation(value = "负责调用基本数据模块中的药品控制器(根据条件查询所有药品类型)")
    @ApiImplicitParam(name = "prescriptionTypeId",value = "处方类型ID(西药、中药、检查项目)",required = true)
    public Response getAllMedicineType(@RequestParam Integer prescriptionTypeId){
        return httpClientHelper.get("http://localhost:8083/medicine/getMedicineType?prescriptionTypeId="+prescriptionTypeId);

    }

    @GetMapping("/report")
    public void report(HttpServletResponse response){
        try {
            response.sendRedirect("http://localhost:8083/medicine/report");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/getMedicineUsage")
    @ApiOperation(value = "负责调用基本数据模块中的药品控制器(获取所有药品用法)")
    public Response getMedicineUsage(){
        return httpClientHelper.get("http://localhost:8083/medicine/getMedicineUsage");
    }
}
