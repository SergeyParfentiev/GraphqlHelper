package project.parser.impl;

import graphql.schema.GraphQLOutputType;
import project.ParserContext;
import project.parser.inter.AbstractParser;

public class OutputTypeParser extends AbstractParser<GraphQLOutputType> {

  @Override
  public void parseOutput(GraphQLOutputType type, ParserContext context) {
    System.out.println();
  }
}
