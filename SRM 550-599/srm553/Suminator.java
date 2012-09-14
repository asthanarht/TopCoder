package srm553;

import java.util.Stack;

public class Suminator {
	public Stack<Long> stack = new Stack<Long>();

	public int findMissing(int[] program, int wantedResult) {
		long result = run(program, 0);
		if (result == wantedResult)
			return 0;
		result = run(program, 1);
		if (result <= wantedResult)
			if (run(program, wantedResult - result + 1) == (long) wantedResult)
				return (int) (wantedResult - result + 1);
		return -1;
	}

	public long run(int[] program, long check) {
		for (int i = 0; i < program.length; i++) {
			long cur = program[i];
			if (cur == -1)
				cur = check;
			if (cur == 0) {
				long a = pop(), b = pop();
				push(a + b);
			} else
				push(cur);
		}
		return pop();
	}

	public long pop() {
		if (stack.size() > 0)
			return stack.pop();
		return 0;
	}

	public void push(long num) {
		stack.push(num);
	}
}
