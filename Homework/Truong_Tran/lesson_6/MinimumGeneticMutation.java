import java.util.*;

public class MinimumGeneticMutation {

    public int minMutation(String startGene, String endGene, String[] bank) {
        if (startGene.length() != endGene.length()) {
            return -1;
        }
        Map<String, Boolean> visited = new HashMap<>();
        for (int i = 0; i < bank.length; i++) {
            visited.put(bank[i], false);
        }
        List<Integer> indexs = preProcessing(startGene, endGene);
        Queue<String> queue = new ArrayDeque<>();
        queue.add(startGene);
        int depth = 0;
        while (!queue.isEmpty()) {
            List<String> next = new ArrayList<>();
            while (!queue.isEmpty()) {
                String gene = queue.poll();
                if (endGene.equals(gene)) {
                    return depth;
                }
                for (Integer i : indexs) {
                    Character c = gene.charAt(i);
                    switch (c) {
                        case 'A':
                            String nextGene2 = gene.substring(0, i) + 'C' + gene.substring(i + 1);
                            if (exist(nextGene2, bank, visited)) {
                                next.add(nextGene2);
                                visited.put(nextGene2, true);
                            }

                            String nextGene3 = gene.substring(0, i) + 'G' + gene.substring(i + 1);
                            if (exist(nextGene3, bank, visited)) {
                                next.add(nextGene3);
                                visited.put(nextGene3, true);
                            }
                            String nextGene4 = gene.substring(0, i) + 'T' + gene.substring(i + 1);
                            if (exist(nextGene4, bank, visited)) {
                                next.add(nextGene4);
                                visited.put(nextGene4, true);
                            }
                        case 'C':
                            nextGene2 = gene.substring(0, i) + 'A' + gene.substring(i + 1);
                            if (exist(nextGene2, bank, visited)) {
                                next.add(nextGene2);
                                visited.put(nextGene2, true);
                            }

                            nextGene3 = gene.substring(0, i) + 'G' + gene.substring(i + 1);
                            if (exist(nextGene3, bank, visited)) {
                                next.add(nextGene3);
                                visited.put(nextGene3, true);
                            }
                            nextGene4 = gene.substring(0, i) + 'T' + gene.substring(i + 1);
                            if (exist(nextGene4, bank, visited)) {
                                next.add(nextGene4);
                                visited.put(nextGene4, true);
                            }
                        case 'G':
                            nextGene2 = gene.substring(0, i) + 'A' + gene.substring(i + 1);
                            if (exist(nextGene2, bank, visited)) {
                                next.add(nextGene2);
                                visited.put(nextGene2, true);
                            }

                            nextGene3 = gene.substring(0, i) + 'C' + gene.substring(i + 1);
                            if (exist(nextGene3, bank, visited)) {
                                next.add(nextGene3);
                                visited.put(nextGene3, true);
                            }
                            nextGene4 = gene.substring(0, i) + 'T' + gene.substring(i + 1);
                            if (exist(nextGene4, bank, visited)) {
                                next.add(nextGene4);
                                visited.put(nextGene4, true);
                            }
                        case 'T':
                            nextGene2 = gene.substring(0, i) + 'A' + gene.substring(i + 1);
                            if (exist(nextGene2, bank, visited)) {
                                next.add(nextGene2);
                                visited.put(nextGene2, true);
                            }

                            nextGene3 = gene.substring(0, i) + 'G' + gene.substring(i + 1);
                            if (exist(nextGene3, bank, visited)) {
                                next.add(nextGene3);
                                visited.put(nextGene3, true);
                            }
                            nextGene4 = gene.substring(0, i) + 'C' + gene.substring(i + 1);
                            if (exist(nextGene4, bank, visited)) {
                                next.add(nextGene4);
                                visited.put(nextGene4, true);
                            }

                    }
                }
            }
            queue.addAll(next);
            depth++;
        }
        return -1;
    }

    public List<Integer> preProcessing(String startGene, String endGene) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < startGene.length(); i++) {
            if (startGene.charAt(i) != endGene.charAt(i)) {
                list.add(i);
            }
        }
        return list;
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

        int i = test.minMutation("AACCTTGG", "AATTCCGG", new String[]{"AATTCCGG", "AACCTGGG", "AACCCCGG", "AACCTACC"});
    }
}
