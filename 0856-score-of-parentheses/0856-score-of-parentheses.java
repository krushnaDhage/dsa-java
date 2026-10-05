class Solution {
    public int scoreOfParentheses(String s) {

        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                stack.push(0);
            } 
            else {
                int inside = stack.pop();

                int score = (inside == 0) ? 1 : 2 * inside;

                int previous = stack.pop();
                stack.push(previous + score);
            }
        }

        return stack.peek();
    }
}