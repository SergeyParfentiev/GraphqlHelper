package project.parser.impl;

import static project.parser.inter.OutputParser.parse;

import graphql.schema.GraphQLFieldDefinition;
import project.ParserContext;
import project.parser.inter.AbstractParser;

public class FieldDefinitionParser
    extends AbstractParser<GraphQLFieldDefinition> {

  @Override
  public void parseOutput(GraphQLFieldDefinition graphQLFieldDefinition,
      ParserContext context) {

    appendTab(context);
    context.getStructureBuilder().append(graphQLFieldDefinition.getName());

    if (context.isArguments()) {
      context.getStructureBuilder().append(":");
    }

    parse(graphQLFieldDefinition.getType(), context);
  }
}
