package com.start.helm.domain.helm.chart.customizers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TemplateStringPatcher {

  public static int indexOfString(List<String> list, String string) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).contains(string)) {
        return i;
      }
    }
    return -1;
  }

  public static String insertAfter(String original, String marker, String patch, int leadingSpaces) {
    final List<String> originalLines = new ArrayList<>(Arrays.asList(original.split("\n")));
    final int index = indexOfString(originalLines, marker);
    final String[] split = patch.split("\n");
    final String leadingWhitespace = leadingSpaces > 0 ? " ".repeat(leadingSpaces) : "";

    if (index != -1) {
      int nextIndex = index + 1;
      int newElements = split.length;

      for (int i = 0; i < newElements; i++) {
        originalLines.add(nextIndex + i, leadingWhitespace + split[i]);
      }
    }
    return String.join("\n", originalLines);
  }

}
