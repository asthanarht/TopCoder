public class SpamChecker {

	public String spamCheck(String judgeLog, int good, int bad) {
		int score = 0;
		for (int i = 0; i < judgeLog.length(); i++)
			if (judgeLog.charAt(i) == 'x') {
				score -= bad;
				if (score < 0)
					return "SPAM";
			} else
				score += good;
		return "NOT SPAM";
	}

}
