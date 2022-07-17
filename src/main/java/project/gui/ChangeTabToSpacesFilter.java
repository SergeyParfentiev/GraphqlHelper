package project.gui;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class ChangeTabToSpacesFilter extends DocumentFilter {

  private String spaces = "";

  public ChangeTabToSpacesFilter(int spaceCount) {
    for (int i = 0; i < spaceCount; i++) {
      spaces += " ";
    }
  }

  @Override
  public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
      throws BadLocationException {
    string = string.replace("\t", spaces);
    super.insertString(fb, offset, string, attr);
  }

  @Override
  public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
    super.remove(fb, offset, length);
  }

  @Override
  public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
      throws BadLocationException {
    text = text.replace("\t", spaces);
    super.replace(fb, offset, length, text, attrs);
  }

}
