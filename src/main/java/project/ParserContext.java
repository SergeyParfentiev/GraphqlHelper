package project;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ParserContext {

  private final boolean arguments;
  private final StringBuilder structureBuilder;
}
