package project.parser.inter;

import graphql.schema.GraphQLSchemaElement;
import java.lang.reflect.ParameterizedType;
import project.ParserContext;

public abstract class AbstractParser<T extends GraphQLSchemaElement> implements
    OutputParser<T> {

  protected final Class<T> tClass;
  protected static int tabIndex = 0;
  protected static boolean notNull;

  public AbstractParser() {
    tClass = (Class<T>) ((ParameterizedType) getClass()
        .getGenericSuperclass()).getActualTypeArguments()[0];
  }

  @Override
  public void parseSupportsOutput(GraphQLSchemaElement type, ParserContext context) {
    parseOutput((T) type, context);
    notNull = false;
  }

  protected void appendTab(ParserContext context) {
    for (int i = 0; i < tabIndex; i++) {
      context.getStructureBuilder().append("\t");
    }

    if (!context.isArguments()) {
      context.getStructureBuilder().append("\t");
    }
  }

  @Override
  public boolean isSupports(GraphQLSchemaElement type) {
    return type.getClass() == tClass;
  }
}
