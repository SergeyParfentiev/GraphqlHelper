package project.parser.impl;

import static project.parser.inter.OutputParser.*;

import graphql.schema.GraphQLArgument;
import project.ParserContext;
import project.parser.inter.AbstractParser;

public class ArgumentParser extends AbstractParser<GraphQLArgument> {

  @Override
  public void parseOutput(GraphQLArgument graphQLArgument, ParserContext context) {
    tabIndex++;
    appendTab(context);
    context.getStructureBuilder().append(graphQLArgument.getName()).append(":");

    parse(graphQLArgument.getType(), context);

    tabIndex--;
  }
}
