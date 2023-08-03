/**
 * 
You are given a list of alphabets with a dependency constraint. The alphabets need to be sorted in such a way that if there is a dependency between two alphabets, the dependent alphabet should come after the one it depends on. You need to implement a function String sortAlphabetsWithDependency(List<String> alphabets, List<String[]> dependencies) that returns a sorted string representing the alphabets in the required order.

Input:

alphabets: A list of strings representing the unique alphabets (A to Z) that need to be sorted. The alphabets may appear in any order in the list.
dependencies: A list of string arrays representing the dependencies between alphabets. Each string array will contain two elements, where the first element depends on the second element. For example, if the string array is ["B", "A"], it means that "B" depends on "A" and "A" should come before "B" in the sorted order.
Output:

Return a string containing the sorted alphabets in the order satisfying the dependency constraint.
Example:

Input: 
alphabets = ["B", "A", "C", "D"]
dependencies = [["B", "A"], ["C", "A"], ["D", "C"]]

Output:
"ACBD"
Note:
    The alphabets will be represented using uppercase English letters from 'A' to 'Z'.
    There may be multiple valid solutions for the sorting order, and you can return any of them as long as it satisfies the dependency constraint.
    The input will be valid, and there won't be any circular dependencies between alphabets.

Constraints:
    The number of alphabets will be in the range [1, 26].
    The number of dependencies will be in the range [0, 100].

**/

import java.util.*;

public class SortingAlphabetsWithDependency {

    public static String sortAlphabetsWithDependency(List<String> alphabets, List<String[]> dependencies) {
        Map<String, List<String>> graph = new HashMap<>();
        Map<String, Integer> inDegree = new HashMap<>();

        // Initialize the graph and in-degree map
        for (String alphabet : alphabets) {
            graph.put(alphabet, new ArrayList<>());
            inDegree.put(alphabet, 0);
        }

        // Build the graph and in-degree map based on dependencies
        for (String[] dependency : dependencies) {
            String dependent = dependency[0];
            String dependencyOn = dependency[1];
            graph.get(dependencyOn).add(dependent);
            inDegree.put(dependent, inDegree.get(dependent) + 1);
        }

        Queue<String> queue = new LinkedList<>();
        for (String alphabet : alphabets) {
            if (inDegree.get(alphabet) == 0) {
                queue.add(alphabet);
            }
        }

        StringBuilder result = new StringBuilder();
        while (!queue.isEmpty()) {
            String current = queue.poll();
            result.append(current);

            for (String dependent : graph.get(current)) {
                inDegree.put(dependent, inDegree.get(dependent) - 1);
                if (inDegree.get(dependent) == 0) {
                    queue.add(dependent);
                }
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        List<String> alphabets = Arrays.asList("B", "A", "C", "D");
        List<String[]> dependencies = Arrays.asList(
                new String[]{"B", "A"},
                new String[]{"C", "A"},
                new String[]{"D", "C"}
        );

        String sortedAlphabets = sortAlphabetsWithDependency(alphabets, dependencies);
        System.out.println(sortedAlphabets); // Output: "ACBD"
    }
}
