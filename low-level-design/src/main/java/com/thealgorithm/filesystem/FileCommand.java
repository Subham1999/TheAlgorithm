package com.thealgorithm.filesystem;

/**
 * @author: Subham Santra
 */
public abstract class FileCommand {
  abstract String command();

  abstract FileNode execute(FileNode fileNode) throws NotSupportedException;
}
