package city;

public class Pair<A, B> {
	private final A first;
	private final B second;
	
	public Pair (A first, B second) {
		this.first = first;
		this.second = second;
	}

	public final A getFirst () {
		return first;
	}

	public final B getSecond () {
		return second;
	}
	
	public final boolean equals (Pair<A, B> r) {
		return getFirst().equals(r.getFirst()) &&
			   getSecond().equals(r.getSecond());
	}
	
	@Override
	public final String toString () {
		return "(" + getFirst().toString() +
			   ", " + getSecond().toString() + ")";
	}
}
