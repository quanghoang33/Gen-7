import java.util.*;

public class MinimumGeneticMutation {

    List<Character> characters = Arrays.asList('A', 'C', 'G', 'T');

    public int minMutation(String startGene, String endGene, String[] bank) {
        if (startGene.length() != endGene.length()) {
            return -1;
        }
        Map<String, Boolean> visited = new HashMap<>();
        for (int i = 0; i < bank.length; i++) {
            visited.put(bank[i], false);
        }
        Queue<String> queue = new ArrayDeque<>();
        queue.add(startGene);
        int res = 0;
        int queueSize;
        while (!queue.isEmpty()) {
            queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                String gene = queue.poll();
                if (endGene.equals(gene)) {
                    return res;
                }
                for (int j = 0; j < gene.length(); j++) {
                    for (int k = 0; k < characters.size(); k++) {
                        String nextGene = gene.substring(0, j) + characters.get(k) + gene.substring(j + 1);
                        if (exist(nextGene, bank, visited)) {
                            queue.add(nextGene);
                            visited.put(nextGene, true);
                        }
                    }
                }
            }

            res++;
        }
        return -1;
    }

    private boolean exist(String s, String[] bank, Map<String, Boolean> visited) {
        for (String value : bank) {
            if (s.equals(value) && !visited.get(s)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        MinimumGeneticMutation test = new MinimumGeneticMutation();
        String[] bank = {"AATTCCGG","AACCTGGG","AACCCCGG","AACCTACC"};
        int res = test.minMutation("AACCTTGG", "AATTCCGG", bank);
        System.out.println(res);
    }

}
