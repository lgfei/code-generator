package ${package.Controller};
 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;

import ${package.Entity}.${entity};
import ${my.Manager}.${my.managerName};

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
@RequestMapping("<#if my.artifactId??>/${my.artifactId}-api</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
public class  ${table.controllerName} 
	extends ${superControllerClass}<${my.managerName},${entity}, ${my.entityIdClass}> 
{

}