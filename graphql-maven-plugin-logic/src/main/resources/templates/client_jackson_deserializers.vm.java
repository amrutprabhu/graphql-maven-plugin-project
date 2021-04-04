/** Generated by the default template from graphql-java-generator */
package ${packageUtilName};

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.graphql_java_generator.customscalars.CustomScalarRegistryImpl;
import com.graphql_java_generator.client.response.AbstractCustomJacksonDeserializer;
import com.graphql_java_generator.customscalars.GraphQLScalarTypeDate;
import com.graphql_java_generator.exception.GraphQLRequestExecutionException;

import graphql.schema.GraphQLScalarType;

#foreach($import in $imports)
import $import;
#end

/**
 * This class is a standard Deserializer for Jackson. It uses the {@link GraphQLScalarType} that is implemented by the project for this scalar
 */
public class CustomJacksonDeserializers {
	
#foreach ($customDeserializer in $customDeserializers)
	public static class ${customDeserializer.classSimpleName} extends AbstractCustomJacksonDeserializer<${customDeserializer.javaClassFullName}> {
		private static final long serialVersionUID = 1L;
		public ${customDeserializer.classSimpleName}() {
			super(
#if (${customDeserializer.itemCustomDeserializer})
				new ${customDeserializer.itemCustomDeserializer.classSimpleName}(),
#else
				null,
#end
				#if($customDeserializer.listDepth>0)true#else false#end,
				${customDeserializer.itemJavaClassFullName}.class,
#if (${customDeserializer.customScalarDefinition})  ## if(customScalarDefinition not null)
#if (${customDeserializer.customScalarDefinition.graphQLScalarTypeClass})
				new ${customDeserializer.customScalarDefinition.graphQLScalarTypeClass}()
#elseif (${customDeserializer.customScalarDefinition.graphQLScalarTypeStaticField})
				${customDeserializer.customScalarDefinition.graphQLScalarTypeStaticField}
#elseif (${customDeserializer.customScalarDefinition.graphQLScalarTypeGetter})
				${customDeserializer.customScalarDefinition.graphQLScalarTypeGetter}
#else

			OUPS!
			
			${object.javaName} : you must define one of graphQLScalarTypeClass, graphQLScalarTypeStaticField or graphQLScalarTypeGetter (in the POM parameters for CustomScalars)
			//
			See https://graphql-maven-plugin-project.graphql-java-generator.com/customscalars.html
#end
#else
				null
#end
			);
		}
	}

#end	
}
