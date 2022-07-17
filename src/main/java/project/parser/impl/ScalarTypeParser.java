package project.parser.impl;

import graphql.schema.GraphQLScalarType;
import project.ParserContext;
import project.parser.inter.AbstractParser;

public class ScalarTypeParser extends AbstractParser<GraphQLScalarType> {

  @Override
  public void parseOutput(GraphQLScalarType graphQLScalarType, ParserContext context) {
 if (context.isArguments()) {
      context.getStructureBuilder().append(" ").append(graphQLScalarType.getName());

      if (notNull) {
        context.getStructureBuilder().append("!");
      }
    }

    context.getStructureBuilder().append("\n");
  }
}
