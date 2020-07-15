package com.nosach.ccl;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import org.junit.BeforeClass;
import org.junit.Test;

import com.nosach.ccl.core.CodeLineProcessor;
import com.nosach.ccl.core.CodeLineProcessorImpl;

/**
 * Unit test for CodeLineProcessor methods implementation
 * 
 */
public class CodeLineProcessorImplTest {

  private CodeLineProcessor codeLineProcessor = new CodeLineProcessorImpl();
  private static Stream<String> streamLines1;
  private static Stream<String> streamLines2;

  @BeforeClass
  public static void init() throws IOException {
    String filePath = TestHelper.getPath("/folder1/Dave.java");
    streamLines1 = Files.lines(Path.of(filePath));
    filePath = TestHelper.getPath("/folder1/folder2/Hello.java");
    streamLines2 = Files.lines(Path.of(filePath));
  }

  /**
   * Test method for {@link com.nosach.ccl.core.CodeLineProcessorImpl#processFileLines(Stream)}
   * 
   */
  @Test
  public void testProcessFileLines() {
    int result = codeLineProcessor.processFileLines(streamLines1);
    assertEquals(3, result);
    result = codeLineProcessor.processFileLines(streamLines2);
    assertEquals(5, result);
  }
}
