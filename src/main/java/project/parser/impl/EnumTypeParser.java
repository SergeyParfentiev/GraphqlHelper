package project.parser.impl;

import graphql.schema.GraphQLEnumType;
import project.ParserContext;
import project.parser.inter.AbstractParser;

public class EnumTypeParser extends AbstractParser<GraphQLEnumType> {

  @Override
  public void parseOutput(GraphQLEnumType graphQLEnumType, ParserContext context) {
   if (context.isArguments()) {
      context.getStructureBuilder().append(" ").append(graphQLEnumType.getName());

      if (notNull) {
        context.getStructureBuilder().append("!");
      }
    }

    context.getStructureBuilder().append("\n");
  }
}
