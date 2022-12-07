package com.sbaars.adventofcode.year22.days;

import com.sbaars.adventofcode.common.Day;
import com.sbaars.adventofcode.year22.Day2022;

import java.io.IOException;
import java.util.*;

import static com.sbaars.adventofcode.common.ReadsFormattedString.readString;

public class Day7 extends Day2022 {

  public Day7() {
    super(7);
  }

  public static void main(String[] args) throws IOException {
    Day d = new Day7();
//    d.downloadIfNotDownloaded();
//    d.downloadExample();
//    d.printParts();
//    System.in.read();
//    d.submitPart1();
    d.submitPart2();
  }

  public record Node(Map<String, Node> children, long size){}
  public record File(long size, String name){}

  @Override
  public Object part1() {
    List<String> commands = Arrays.asList(dayStrings()).stream().toList();
    Node root = findChildren(commands);
    return sumSize(root);
  }

  int i = 1;
  public Node findChildren(List<String> commands) {
    Map<String, Node> children = new HashMap<>();
    for(; i<commands.size(); i++) {
      String c = commands.get(i);
      if (c.charAt(0) == '$') {
        String command = c.substring(2);
        if (command.startsWith("cd")) {
          String folder = command.substring(3);
          if (folder.equals("..")) break;
          i++;
          children.put(folder, findChildren(commands));
        }
      } else {
        if(c.startsWith("dir")) {
          String dirName = c.substring(4);
          children.put(dirName, new Node(new HashMap<>(), 0));
        } else {
          File f = readString(c, "%n %s", File.class);
          children.put(f.name, new Node(new HashMap<>(), f.size));
        }
      }
    }
    return new Node(children, children.values().stream().mapToLong(Node::size).sum());
  }

  public long sumSize(Node n) {
    long total = 0;
    for(Node node : n.children.values()) {
      if(node.size<=100000 && !node.children.isEmpty()) {
        total+=node.size;
      }
      total += sumSize(node);
    }
    return total;
  }

  public List<Long> sumSize(Node n, long sizeRoot) {
    List<Long> total = new ArrayList<>();
    for(Node node : n.children.values()) {
      if(node.size>=sizeRoot-(70000000-30000000) && !node.children.isEmpty()) {
        total.add(node.size);
      }
      total.addAll(sumSize(node, sizeRoot));
    }
    return total;
  }

  @Override
  public Object part2() {
    List<String> commands = Arrays.asList(dayStrings()).stream().toList();
    Node root = findChildren(commands);
    return sumSize(root, root.size()).stream().mapToLong(e -> e).min().getAsLong();
  }
}
