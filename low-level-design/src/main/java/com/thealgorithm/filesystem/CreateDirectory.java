package com.thealgorithm.filesystem;

/**
 * @author: Subham Santra
 */
public class CreateDirectory extends FileCommand {
  @Override
  String command() {
    return "mkdir";
  }

  @Override
  FileNode execute(FileNode fileNode) throws NotSupportedException {
    return fileNode.mkdir(command());
  }
}
