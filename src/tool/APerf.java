package tool;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class APerf {
	static String content;
	static List<Row> rows = new ArrayList<Row>();
	static int id = 22880713;

	public static void main(String[] args) {
		fetch(id);
		read();
		outputPerformance(id);
	}

	public static void fetch(int id) {
		content = null;
		URLConnection connection = null;
		try {
			connection = new URL(
					"http://community.topcoder.com/tc?module=BasicData&c=dd_rating_history&cr="
							+ id).openConnection();
			Scanner scanner = new Scanner(connection.getInputStream());
			scanner.useDelimiter("\\Z");
			content = scanner.next();
			scanner.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter(id + "_rating_history.xml")));
			out.println(content);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void read() {
		rows.clear();
		while (true) {
			int start = content.indexOf("<row>");
			int end = content.indexOf("</row>");
			if (start >= 0 && end >= 0) {
				String seg = content.substring(start + 5, end);
				content = content.substring(end + 6);
				Row row = new Row(seg);
				rows.add(row);
			} else
				break;
		}
		Collections.sort(rows);
	}

	public static void outputPerformance(int id) {
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter(id + "_performance.txt")));
			for (int i = 0; i < rows.size(); i++)
				out.println(rows.get(i).getDate() + "\t"
						+ rows.get(i).getPerformance(i));
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class Row implements Comparable<Row> {
	private String handle;
	private String coder_id;
	private String round_id;
	private String short_name;
	private String date;
	private String old_rating;
	private String new_rating;
	private String volatility;
	private String rank;
	private String percentile;
	private String rating_order;

	public Row(String seg) {
		super();
		int start = seg.indexOf("<short_name>");
		int end = seg.indexOf("</short_name>");
		short_name = seg.substring(start + 12, end);

		start = seg.indexOf("<date>");
		end = seg.indexOf("</date>");
		date = seg.substring(start + 6, end);

		start = seg.indexOf("<old_rating>");
		end = seg.indexOf("</old_rating>");
		old_rating = seg.substring(start + 12, end);

		start = seg.indexOf("<new_rating>");
		end = seg.indexOf("</new_rating>");
		new_rating = seg.substring(start + 12, end);
	}

	public String getHandle() {
		return handle;
	}

	public void setHandle(String handle) {
		this.handle = handle;
	}

	public String getCoder_id() {
		return coder_id;
	}

	public void setCoder_id(String coder_id) {
		this.coder_id = coder_id;
	}

	public String getRound_id() {
		return round_id;
	}

	public void setRound_id(String round_id) {
		this.round_id = round_id;
	}

	public String getShort_name() {
		return short_name;
	}

	public void setShort_name(String short_name) {
		this.short_name = short_name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getOld_rating() {
		return old_rating;
	}

	public void setOld_rating(String old_rating) {
		this.old_rating = old_rating;
	}

	public String getNew_rating() {
		return new_rating;
	}

	public void setNew_rating(String new_rating) {
		this.new_rating = new_rating;
	}

	public String getVolatility() {
		return volatility;
	}

	public void setVolatility(String volatility) {
		this.volatility = volatility;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getPercentile() {
		return percentile;
	}

	public void setPercentile(String percentile) {
		this.percentile = percentile;
	}

	public String getRating_order() {
		return rating_order;
	}

	public void setRating_order(String rating_order) {
		this.rating_order = rating_order;
	}

	public int compareTo(Row r) {
		return date.compareTo(r.date);
	}

	public int performance(int Rating, int NewRating, int timesPlayed) {
		double Weight = 1 / (1 - (0.42 / (timesPlayed + 1) + 0.18)) - 1;
		if (Rating >= 2500)
			Weight *= 0.8;
		else if (Rating >= 2000)
			Weight *= 0.9;
		double PerfAs = (NewRating * (1 + Weight) - Rating) / Weight;
		return (int) PerfAs;
	}

	public int getPerformance(int timesPlayed) {
		int Rating = Integer.parseInt(old_rating);
		int NewRating = Integer.parseInt(new_rating);
		return performance(Rating, NewRating, timesPlayed);
	}
}
