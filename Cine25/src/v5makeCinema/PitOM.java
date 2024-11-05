package v5makeCinema;

import java.util.stream.IntStream;

public class PitOM {
	private int rowAmount=15;
	private int columnAmount=20;
	
	public Seat makeFirstSeat() {
		IntStream.range(0,rowAmount).boxed().forEach(
				i->{
					IntStream.range(0,columnAmount).boxed().forEach((j)->{
						i,j
					})
				});
		return new Seat(new Reference((char)1,1));
	}
}
