package ${package.Mapper};

import ${package.Entity}.${entity};
import ${superMapperClassPackage};

/**
 * <p>
 * ${table.comment!} Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${table.mapperName} : ${superMapperClass}<${entity}, ${my.entityIdClass}>
<#else>
public interface ${table.mapperName} extends ${superMapperClass}<${entity}, ${my.entityIdClass}> {

}
</#if>
