package com.thealgorithm.filesystem;

/**
 * @author: Subham Santra
 */
public class CreateFile extends FileCommand {
  @Override
  String command() {
    return "create_file";
  }

  @Override
  FileNode execute(FileNode fileNode) {
    return null;
  }
}
