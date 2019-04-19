package ${my.Manager};

import ${package.Entity}.${entity};
import ${my.superManagerClassPackage};

/**
 * <p>
 * ${table.comment!} 服务封装类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${my.managerName} : ${my.superManagerClass}<${entity}, ${my.entityIdClass}>
<#else>
public interface ${my.managerName} 
    extends ${my.superManagerClass}<${entity}, ${my.entityIdClass}>
{
    
}
</#if>

