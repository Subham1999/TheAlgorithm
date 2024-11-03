package com.thealgorithm.filesystem;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author: Subham Santra
 */
public class SimpleFile extends SimpleAbstractFileNode {
  public SimpleFile(
      String name,
      Timestamp createTs,
      List<FileNode> fileNodes,
      FileNode parent,
      boolean isDirectory) {
    super(name, createTs, fileNodes, parent, isDirectory);
  }

  @Override
  public FileNode mkdir(String name) throws NotSupportedException {
    throw new NotSupportedException("operation is not supported");
  }

  @Override
  public FileNode rm(String name) throws NotSupportedException {
    throw new NotSupportedException("operation is not supported");
  }

  @Override
  public void ls() throws NotSupportedException {
    throw new NotSupportedException("operation is not supported");
  }
}
