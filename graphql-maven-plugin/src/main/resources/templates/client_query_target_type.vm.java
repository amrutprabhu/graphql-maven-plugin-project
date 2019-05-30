package ${package};

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * @author generated by graphql-java-generator
 * @See https://github.com/graphql-java-generator/graphql-java-generator
 */
public class ${objectName} {

#if ($query.list  &&  $query.type.class.simpleName == "InterfaceType")
	@JsonDeserialize(contentAs = ${query.type.concreteClassSimpleName}.class)
#elseif (${query.list})
	@JsonDeserialize(contentAs = ${query.type.classSimpleName}.class)
#elseif ($query.type.class.simpleName == "InterfaceType")
	@JsonDeserialize(as = ${query.type.concreteClassSimpleName}.class)
#end
	#if(${query.list})List<#end${query.type.classSimpleName}#if(${query.list})>#end ${query.name};

	public void set${query.pascalCaseName}(#if(${query.list})List<#end${query.type.classSimpleName}#if(${query.list})>#end ${query.name}) {
		this.${query.name} = ${query.name};
	}

	public #if(${query.list})List<#end${query.type.classSimpleName}#if(${query.list})>#end get${query.pascalCaseName}() {
		return ${query.name};
	}
	
    public String toString() {
        return "${objectName} {${query.name}: " + ${query.name} + "}";
    }
}
