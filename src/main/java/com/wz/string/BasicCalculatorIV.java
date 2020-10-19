package com.wz.string;

import java.util.*;

/**
 * Given an expression such as expression = "e + 8 - a + 5" and an evaluation map such as {"e": 1}
 * (given in terms of evalvars = ["e"] and evalints = [1]), return a list of tokens representing the simplified expression, such as ["-1*a","14"]
 * 1. An expression alternates chunks and symbols, with a space separating each chunk and symbol.
 * 2. A chunk is either an expression in parentheses, a variable, or a non-negative integer.
 * 3. A variable is a string of lowercase letters (not including digits.) Note that variables can be multiple letters,
 *    and note that variables never have a leading coefficient or unary operator like "2x" or "-x".
 * Expressions are evaluated in the usual order: brackets first, then multiplication, then addition and subtraction.
 * For example, expression = "1 + 2 * 3" has an answer of ["7"].
 *
 * The format of the output is as follows:
 * 1. For each term of free variables with non-zero coefficient, we write the free variables within a term in
 *    sorted order lexicographically. For example, we would never write a term like "b*a*c", only "a*b*c".
 * 2. Terms have degree equal to the number of free variables being multiplied, counting multiplicity.
 *    (For example, "a*a*b*c" has degree 4.) We write the largest degree terms of our answer first,
 *    breaking ties by lexicographic order ignoring the leading coefficient of the term.
 * 3. The leading coefficient of the term is placed directly to the left with an asterisk separating it from the variables
 *    (if they exist.)  A leading coefficient of 1 is still printed.
 * 4. An example of a well formatted answer is ["-2*a*a*a", "3*a*a*b", "3*b*b", "4*a", "5*c", "-6"]
 * 5. Terms (including constant terms) with coefficient 0 are not included.  For example, an expression of "0" has an output of [].
 *
 * Examples:
 *
 * Input: expression = "e + 8 - a + 5", evalvars = ["e"], evalints = [1]
 * Output: ["-1*a","14"]
 *
 * Input: expression = "e - 8 + temperature - pressure",
 * evalvars = ["e", "temperature"], evalints = [1, 12]
 * Output: ["-1*pressure","5"]
 *
 * Input: expression = "(e + 8) * (e - 8)", evalvars = [], evalints = []
 * Output: ["1*e*e","-64"]
 *
 * Input: expression = "7 - 7", evalvars = [], evalints = []
 * Output: []
 *
 * Input: expression = "a * b * c + b * a * c * 4", evalvars = [], evalints = []
 * Output: ["5*a*b*c"]
 *
 * Note:
 * 1. expression will have length in range [1, 250].
 * 2. evalvars, evalints will have equal lengths in range [0, 100].
 */
public class BasicCalculatorIV {
    public static void main(String[] args) {
        System.out.println(basicCalculatorIV("e + 8 - a + 5", new String[]{"e"}, new int[]{1}));
    }

    public static List<String> basicCalculatorIV(String expression, String[] evalvars, int[] evalints) {
        Map<String, Integer> map = new HashMap<>();
        Stack<Token> stack = new Stack<>();
        Stack<List<Token>> calc = new Stack<>();
        Queue<Token> q = new LinkedList<>();

        int i;
        // build the map for evaluation variables
        for (i = 0; i < evalvars.length; i++) {
            map.put(evalvars[i], evalints[i]);
        }

        // convert to reverse polish notation
        i = 0;
        while (i < expression.length()) {
            char c = expression.charAt(i);
            if (c == '(') {
                Token token = new Token(0, "(", "");
                stack.push(token);
                i++;
            } else if (c == ')') {
                while (!stack.isEmpty() && !stack.peek().operator.equals("(")) {
                    q.add(stack.pop());
                }
                stack.pop();
                i++;
            } else if ((c == '+' || c == '-' || c == '*')) {
                Token token = new Token(0, "" + c, "");
                while (!stack.isEmpty() && !stack.peek().operator.equals("(") &&
                        !((stack.peek().operator.equals("+") || stack.peek().operator.equals("-")) && c == '*')) {
                    q.add(stack.pop());
                }
                stack.push(token);
                i++;
            } else if (c == ' ') {
                i++;
            } else {
                int coefficient = 1, j = i + 1;
                Token token;
                if (Character.isDigit(c)) {
                    // If this is a number
                    while (j < expression.length() && Character.isDigit(expression.charAt(j))) {
                        j++;
                    }
                    coefficient = Integer.parseInt(expression.substring(i, j));
                    token = new Token(coefficient, "", "");
                } else {
                    // Else this is a variable
                    while (j < expression.length() && expression.charAt(j) >= 'a' && expression.charAt(j) <= 'z') {
                        j++;
                    }

                    // Check if we can swap with a number
                    if (!map.containsKey(expression.substring(i, j))) {
                        token = new Token(coefficient, "", expression.substring(i, j));
                    } else {
                        token = new Token(map.get(expression.substring(i, j)), "", "");
                    }
                }

                i = j;
                q.add(token);
            }
        }

        while (!stack.isEmpty()) {
            q.add(stack.pop());
        }

        // Evaluate the reverse polish notation
        while (!q.isEmpty()) {
            Token t = q.poll();
            if (t.isOperator()) {
                List<Token> op2 = calc.pop();
                List<Token> op1 = calc.pop();

                if (t.operator.equals("+")) {
                    op1.addAll(op2);
                    calc.push(op1);
                } else if (t.operator.equals("-")) {
                    for (Token token : op2) {
                        token.coefficient = -token.coefficient;
                        op1.add(token);
                    }
                    calc.push(op1);
                } else {
                    List<Token> nlist = new ArrayList<>();
                    for (Token t1 : op1) {
                        for (Token t2 : op2) {
                            Token nt = new Token(t1.coefficient * t2.coefficient, "", "");
                            nt.terms.addAll(t1.terms);
                            nt.terms.addAll(t2.terms);
                            nlist.add(nt);
                        }
                    }
                    calc.push(nlist);
                }
            } else {
                List<Token> list = new ArrayList<>();
                list.add(t);
                calc.push(list);
            }
        }

        List<Token> res = calc.pop();
        Map<String, Integer> map_coef = new HashMap<>();
        Map<Integer, Set<String>> degree = new TreeMap<>(Collections.reverseOrder());

        // Aggregate the coefficients for the same term
        for (Token token : res) {
            String key = token.getKey();
            int c = map_coef.getOrDefault(key, 0) + token.coefficient;
            map_coef.put(key, c);

            int d = token.terms.size();
            Set<String> s = degree.getOrDefault(d, new TreeSet<>());
            s.add(key);

            degree.put(d, s);
        }


        // Output the results
        List<String> result = new ArrayList<>();
        for (Integer d : degree.keySet()) {
            for (String term : degree.get(d)) {
                int coef = map_coef.get(term);
                if (coef != 0) {
                    result.add(coef + (term.isEmpty() ? "" : "*") + term);
                }
            }
        }

        return result;
    }

    private static class Token {
        int coefficient;
        List<String> terms;
        String operator;

        public Token(int c, String op, String var) {
            coefficient = c;
            operator = op;
            terms = new LinkedList<>();
            if (!var.isEmpty()) terms.add(var);
        }

        public boolean isOperator() {
            return operator.equals("+") || operator.equals("-") || operator.equals("*");
        }

        public String getKey() {
            StringBuilder builder = new StringBuilder();
            Collections.sort(terms);
            for (String term : terms) {
                if (builder.length() > 0) builder.append("*");
                builder.append(term);
            }
            return builder.toString();
        }
    }
}
