import java.util.*;

class Solution {

          private Map<String, Map<String, List<String>>> groupStore = new HashMap<>();
          private Set<String> groupNames = new HashSet<>();
          private List<Group> results = new ArrayList<>();

          public List<Group> findGroups(Map<String, Map<String, List<String>>> groups) {

                    for (String groupName : groups.keySet()) {
                              groupNames.add(groupName);
                    }

                    for (Map.Entry<String, Map<String, List<String>>> entry : groups.entrySet()) {
                              String currName = entry.getKey();
                              List<String> currMembersList = new ArrayList<>();
                              List<String> currentDeptList = new ArrayList<>();

                              List<String> members = entry.getValue().get("members");
                              for (String member : members) {
                                        if (groupNames.contains(member)) {
                                                  currentDeptList.add(member);
                                        } else {
                                                  currMembersList.add(member);
                                        }
                              }

                              Map<String, List<String>> temp = new HashMap<>();
                              temp.put("members", currMembersList);
                              temp.put("depts", currentDeptList);
                              groupStore.put(currName, temp);
                    }

                    // Create result Group
                    Group result = new Group();
                    result.setName("root");
                    result.setMembers(new ArrayList<>());

                    // Expand members for each group iteratively
                    for (String groupName : groupNames) {

                              Set<String> currentMember = new HashSet<>();
                              // System.out.println("Processing group: " + groupName);

                              List<String> currGroupMember = groupStore.get(groupName).get("members");
                              if (currGroupMember != null) {
                                        currGroupMember.forEach(currentMember::add);
                              }
                              // System.out.println("Direct members of " + groupName + ": " + currentMember);

                              List<String> currGroup = groupStore.get(groupName).get("depts");
                              if (currGroup != null) {
                                        for (String group : currGroup) {
                                                  groupStore.get(group).get("members").forEach(currentMember::add);
                                                  // System.out.println("Adding members of nested group " + group + ": "
                                                  // + groupStore.get(group).get("members"));
                                        }
                              }

                              result.setName(groupName);
                              result.setMembers(new ArrayList<>(currentMember));

                              results.add(result);
                              System.out.println("curr group: " + result.getName() + ": " + result.getMembers());
                    }

                    return results;
          }

          public static void main(String[] args) {
                    Map<String, Map<String, List<String>>> groups = new HashMap<>();
                    groups.put("group1", new HashMap<>() {
                              {
                                        put("members", Arrays.asList("user1", "user2", "group2"));
                              }
                    });
                    groups.put("group2", new HashMap<>() {
                              {
                                        put("members", Arrays.asList("user1", "user3", "group1"));
                              }
                    });
                    groups.put("group3", new HashMap<>() {
                              {
                                        put("members", Arrays.asList("group1", "group2", "group3"));
                              }
                    });
                    groups.put("group4", new HashMap<>() {
                              {
                                        put("members", Arrays.asList("group1", "group2", "group3", "group4"));
                              }
                    });

                    Solution solution = new Solution();
                    List<Group> results = solution.findGroups(groups);

                    results.forEach(group -> System.out.println(group.getName() + ": " + group.getMembers()));
          }

          class User {
                    String name;
          }

          class Group {
                    String name;
                    List<String> members;

                    public Group() {
                    }

                    public Group(String name) {
                              this.name = name;
                    }

                    public String getName() {
                              return name;
                    }

                    public void setName(String name) {
                              this.name = name;
                    }

                    public List<String> getMembers() {
                              return members;
                    }

                    public void setMembers(List<String> members) {
                              this.members = members;
                    }
          }
}
