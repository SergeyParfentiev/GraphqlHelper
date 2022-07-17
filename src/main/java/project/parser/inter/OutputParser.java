package project.parser.inter;

import graphql.schema.GraphQLSchemaElement;
import java.util.List;
import project.ParserContext;
import project.parser.impl.ArgumentParser;
import project.parser.impl.EnumTypeParser;
import project.parser.impl.FieldDefinitionParser;
import project.parser.impl.InputObjectFieldParser;
import project.parser.impl.InputObjectTypeParser;
import project.parser.impl.ListParser;
import project.parser.impl.NonNullParser;
import project.parser.impl.ObjectTypeParser;
import project.parser.impl.OutputTypeParser;
import project.parser.impl.ScalarTypeParser;

public interface OutputParser<T extends GraphQLSchemaElement> {

  List<OutputParser> parsers = List.of(new ArgumentParser(), new InputObjectTypeParser(),
      new InputObjectFieldParser(),
      new FieldDefinitionParser(), new OutputTypeParser(), new NonNullParser(), new ListParser(),
      new ObjectTypeParser(), new EnumTypeParser(), new ScalarTypeParser());

  boolean isSupports(GraphQLSchemaElement type);

  void parseOutput(T t, ParserContext context);

  void parseSupportsOutput(GraphQLSchemaElement type, ParserContext context);

  static void parse(GraphQLSchemaElement type, ParserContext context) {
    parsers.stream()
        .filter(parser -> parser.isSupports(type))
        .findFirst()
        .orElseThrow(() -> new RuntimeException("Type [" + type.toString() + "] not resolved."))
        .parseSupportsOutput(type, context);
  }
}
