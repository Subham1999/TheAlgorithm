package com.thealgorithm.filesystem;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Subham Santra
 */
public class SimpleDirectory extends SimpleAbstractFileNode {
  public SimpleDirectory(
      String name,
      Timestamp createTs,
      List<FileNode> fileNodes,
      FileNode parent,
      boolean isDirectory) {
    super(name, createTs, fileNodes, parent, isDirectory);
  }

  @Override
  public FileNode mkdir(String name) {
    SimpleDirectory simpleDirectory =
        new SimpleDirectory(
            name, new Timestamp(System.currentTimeMillis()), new ArrayList<>(), this, true);
    this.fileNodes().add(simpleDirectory);
    return simpleDirectory;
  }

  @Override
  public FileNode rm(String name) {
    this.fileNodes().removeIf(fn -> fn.name().equalsIgnoreCase(name));
    return this;
  }

  @Override
  public void ls() {
    for (FileNode fileNode : this.fileNodes()) {
      System.out.printf("%s %s %s\n", name(), createTs(), isDirectory() ? "dir" : "file");
    }
  }
}
