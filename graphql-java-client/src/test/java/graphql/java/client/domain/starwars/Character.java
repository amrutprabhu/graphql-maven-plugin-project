package graphql.java.client.domain.starwars;

import java.util.List;

import graphql.java.client.annotation.GraphQLNonScalar;
import graphql.java.client.annotation.GraphQLScalar;

/**
 * @author generated by graphql-maven-plugin
 */
public interface Character {

	@GraphQLScalar(graphqlType = String.class)
	public void setId(String id);

	@GraphQLScalar(graphqlType = String.class)
	public String getId();

	@GraphQLScalar(graphqlType = String.class)
	public void setName(String name);

	@GraphQLScalar(graphqlType = String.class)
	public String getName();

	@GraphQLNonScalar(graphqlType = Character.class)
	public void setFriends(List<Character> friends);

	@GraphQLNonScalar(graphqlType = Character.class)
	public List<Character> getFriends();

	@GraphQLScalar(graphqlType = Episode.class)
	public void setAppearsIn(List<Episode> appearsIn);

	@GraphQLScalar(graphqlType = Episode.class)
	public List<Episode> getAppearsIn();
}
