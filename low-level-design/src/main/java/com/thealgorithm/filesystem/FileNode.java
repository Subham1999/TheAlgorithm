package com.thealgorithm.filesystem;

import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author: Subham Santra
 */
public interface FileNode {

  String name();

  FileNode mkdir(String name) throws NotSupportedException;

  FileNode rm(String name) throws NotSupportedException;

  FileNode cd(String nextNodeName) throws FileNotFoundException;

  List<FileNode> pwd();

  void ls() throws NotSupportedException;

  boolean isDirectory();

  Timestamp createTs();

  FileNode parent();
}
