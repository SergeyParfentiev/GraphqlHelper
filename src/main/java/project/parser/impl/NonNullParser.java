package project.parser.impl;

import static project.parser.inter.OutputParser.parse;

import graphql.schema.GraphQLNonNull;
import project.ParserContext;
import project.parser.inter.AbstractParser;

public class NonNullParser extends AbstractParser<GraphQLNonNull> {

  @Override
  public void parseOutput(GraphQLNonNull type, ParserContext context) {
    notNull = true;
    parse(type.getOriginalWrappedType(), context);
  }
}
