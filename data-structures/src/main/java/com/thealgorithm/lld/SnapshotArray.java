package com.thealgorithm.lld;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;

/**
 * @author: Subham Santra
 */
public class SnapshotArray {
  static class Pair {
    int snap;
    int val;

    public Pair(int s, int v) {
      this.snap = s;
      this.val = v;
    }

    public String toString() {
      return String.format("[%d @%d]", val, snap);
    }
  }

  private int snapId;
  private final Map<Integer, TreeSet<Pair>> snapshots;

  public SnapshotArray(int length) {
    snapshots = new HashMap<>();
    for (int i = 0; i < length; ++i) {
      snapshots.put(i, new TreeSet<>(Comparator.comparingInt(p -> p.snap)));
    }
  }

  public void set(int index, int val) {
    TreeSet<Pair> change = snapshots.get(index);
    if (!change.isEmpty() && change.getLast().snap == snapId) {
      change.getLast().val = val;
    } else {
      change.add(new Pair(snapId, val));
    }
    print();
  }

  public int snap() {
    return snapId++;
  }

  public int get(int index, int snap_id) {
    TreeSet<Pair> change = snapshots.get(index);
    NavigableSet<Pair> pairs = change.headSet(new Pair(snap_id, 0), true);
    if (pairs.isEmpty()) return 0;
    return pairs.getLast().val;
  }

  public void print() {
    System.out.println(snapshots);
  }

  /**
   * Your SnapshotArray object will be instantiated and called as such: SnapshotArray obj = new
   * SnapshotArray(length); obj.set(index,val); int param_2 = obj.snap(); int param_3 =
   * obj.get(index,snap_id);
   */
  public static void main(String[] args) {
    SnapshotArray snapshotArray = new SnapshotArray(1);

    snapshotArray.set(0, 15);
    System.out.println(snapshotArray.snap());
    System.out.println(snapshotArray.snap());
    System.out.println(snapshotArray.snap());
    System.out.println(snapshotArray.get(0, 2));
    System.out.println(snapshotArray.snap());
    System.out.println(snapshotArray.snap());
    System.out.println(snapshotArray.get(0, 0));
  }
}
