package ${my.ManagerImpl};

import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
import ${my.Manager}.${my.managerName};
import ${my.superManagerImplClassPackage};

import org.springframework.stereotype.Service;

/**
 * <p>
 * ${table.comment!} 服务实现封装类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service
<#if kotlin>
open class ${my.managerImplName} : ${my.superManagerImplClass}<${table.serviceName}, ${entity}, ${my.entityIdClass}>(), ${table.serviceName} 
{

}
<#else>
public class ${my.managerImplName} 
	extends ${my.superManagerImplClass}<${table.serviceName}, ${entity}, ${my.entityIdClass}> 
	implements ${my.managerName} 
{
    @Override
    protected ${entity} newEntity()
    {
        return new ${entity}();
    }
}
</#if>

