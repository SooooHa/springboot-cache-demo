package cn.cache.api;

import cn.cache.entity.Department;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RestController;

/**
 * 使用Swagger 注解
 *
 */
@Api(value = "Depart的测试用法")
@RestController
public interface DepartmentControllerApi {
    @ApiOperation(value = "根据id查询部门",notes = "测试")
    @ApiImplicitParam(paramType = "query",name = "id",value = "部门编号",required = true,dataType = "Integer")
    Department selectOne(Integer id);
}
