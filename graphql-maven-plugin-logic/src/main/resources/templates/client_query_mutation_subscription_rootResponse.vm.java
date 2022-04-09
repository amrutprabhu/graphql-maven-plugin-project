/** Generated by the default template from graphql-java-generator */
package ${packageUtilName};

import java.util.List;

#if($configuration.isGenerateJacksonAnnotations())
import com.fasterxml.jackson.annotation.JsonProperty;
#end
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.graphql_java_generator.annotation.GraphQLNonScalar;
import com.graphql_java_generator.client.GraphQLObjectMapper;
import com.graphql_java_generator.client.request.ObjectResponse;
import com.graphql_java_generator.client.response.Error;

#if(${configuration.separateUtilityClasses})
import ${configuration.packageName}.${object.classSimpleName};
#end

@SuppressWarnings("unused")
public class ${object.classSimpleName}RootResponse {

#if($configuration.isGenerateJacksonAnnotations())
	@JsonProperty("data")
#end
	@GraphQLNonScalar(fieldName = "${object.name}", graphQLTypeSimpleName = "${object.javaName}", javaClass = ${object.classFullName}.class)
	${object.classSimpleName} ${object.requestType};

#if($configuration.isGenerateJacksonAnnotations())
	@JsonProperty("errors")
#end
	@JsonDeserialize(contentAs = Error.class)
	public List<Error> errors;

#if($configuration.isGenerateJacksonAnnotations())
	@JsonProperty("extensions")
	public JsonNode extensions;
#end

	// This getter is needed for the Json serialization
	public ${object.classSimpleName} getData() {
		return this.${object.requestType};
	}

	// This setter is needed for the Json deserialization
	public void setData(${object.classSimpleName} ${object.requestType}) {
		this.${object.requestType} = ${object.requestType};
	}

	public List<Error> getErrors() {
		return errors;
	}

	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}

#if($configuration.isGenerateJacksonAnnotations())
	public JsonNode getExtensions() {
		return extensions;
	}

	public void setExtensions(JsonNode extensions) {
		this.extensions = extensions;
	}
#end

}
