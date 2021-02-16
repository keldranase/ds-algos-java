public class ComparableDate implements Comparable<ComparableDate> {
	private final int month, day, year;

	public ComparableDate(int m, int d, int y) {
		month = m;
		day = d;
		year = y;
	}

	public int compareTo(ComparableDate that) {
		if (this.year < that.year) return -1;
		if (this.year > that.year) return +1;
		if (this.month < that.month) return -1;
		if (this.month > that.month) return +1;
		if (this.day < that.day) return -1;
		if (this.day > that.day) return +1;
		return 0;
	}

	public static void main(String args[]) {
		ComparableDate d1 = new ComparableDate(2, 16, 2021);
		ComparableDate d2 = new ComparableDate(9, 17, 2020);
		System.out.println(d1.compareTo(d2));
	}
}
		





		
