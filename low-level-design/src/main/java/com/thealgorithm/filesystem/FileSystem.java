package com.thealgorithm.filesystem;

public abstract class FileSystem {
  public abstract FileNode root();

  public abstract FileNode currentNode();

  public abstract void execute(FileCommand command);
}
