package com.thealgorithm.filesystem;

import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Subham Santra
 */
public abstract class SimpleAbstractFileNode implements FileNode {
  private final String name;
  private final Timestamp createTs;
  private final List<FileNode> fileNodes;
  private final FileNode parent;
  private final boolean isDirectory;

  public SimpleAbstractFileNode(
      String name,
      Timestamp createTs,
      List<FileNode> fileNodes,
      FileNode parent,
      boolean isDirectory) {

    this.name = name;
    this.createTs = createTs;
    this.fileNodes = fileNodes;
    this.parent = parent;
    this.isDirectory = isDirectory;
  }

  @Override
  public FileNode parent() {
    return parent;
  }

  @Override
  public String name() {
    return this.name;
  }

  @Override
  public FileNode cd(String nextNodeName) throws FileNotFoundException {
    if (nextNodeName.equals("..")) {
      return parent();
    }

    return fileNodes.stream()
        .filter(fn -> fn.isDirectory() && fn.name().equals(nextNodeName))
        .findFirst()
        .orElseThrow(() -> new FileNotFoundException(nextNodeName));
  }

  @Override
  public List<FileNode> pwd() {
    List<FileNode> workingDir = new ArrayList<>();
    FileNode fn = this;
    while (fn != null) {
      workingDir.addLast(fn);
      fn = fn.parent();
    }
    return workingDir;
  }

  @Override
  public boolean isDirectory() {
    return isDirectory;
  }

  @Override
  public Timestamp createTs() {
    return createTs;
  }

  protected List<FileNode> fileNodes() {
    return fileNodes;
  }
}
