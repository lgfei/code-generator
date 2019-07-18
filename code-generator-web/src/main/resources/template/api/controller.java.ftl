package ${package.Controller};
 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;

import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};

<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>
 
/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Api(tags = {"${table.comment!}"})
@Controller
@RequestMapping("/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
public class  ${table.controllerName} extends ${superControllerClass}<${table.serviceName},${entity}, ${my.entityIdClass}> {
    
    @Override
    protected ${entity} newEntity() {
        return new ${entity}();
	}
}