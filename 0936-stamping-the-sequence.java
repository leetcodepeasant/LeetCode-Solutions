class Solution {
    public int[] movesToStamp(String stamp, String target) {
        char[] S = stamp.toCharArray();
        char[] T = target.toCharArray();
        List<Integer> ans = new ArrayList<>();
        boolean[] visited = new boolean[T.length - S.length + 1];
        int questions = 0;
        
        while (questions < T.length) {
            boolean canSolve = false;
            for (int i = 0; i <= T.length - S.length; i++) {
                // i-th winwodw: starts at index i with length of S.length.
                if (!visited[i] && canSolveWindow(S, T, i)) {
                    // visited[i] indicates whether i-th window has been replaced with all ?.
                    questions = solveWindow(S, T, i, questions);
                    ans.add(i);
                    visited[i] = true;
                    canSolve = true;
                    if (questions == T.length) {
                        break;
                    }
                }
            }
            if (!canSolve) {
                // If after checking all the windows and still find no solvable window, then the sequence is not possible to stamp. Early return, otherwise infinite loop.
                return new int[0];
            }
        }
        // actual stamping sequence the is reverse of ans
        int[] ansArray = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            ansArray[i] = ans.get(ans.size() - i - 1);
        }
        return ansArray;
    }
    
    private boolean canSolveWindow(char[] S, char[] T, int p) {
        // Check if p-th window of T can be replaced with all ?.
        for (int i = 0; i < S.length; i++) {
            if (T[i + p] != '?' && T[i + p] != S[i]) {
                return false;
            }
        }
        return true;
    }
    
    private int solveWindow(char[] S, char[] T, int p, int cnt) {
        // Replace p-th window of T with all ? and return the total number of ?.
        for (int i = 0; i < S.length; i++) {
            if (T[i + p] != '?') {
                T[i + p] = '?';
                cnt++;
            }
        }
        return cnt;
    }
}
