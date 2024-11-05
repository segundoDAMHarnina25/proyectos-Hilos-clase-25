package v3mapinsteadlist;

import java.util.Objects;

public class Reference {
	private char row;
	private int colum;
	
	public Reference(char row, int colum) {
		super();
		this.row = row;
		this.colum = colum;
	}

	@Override
	public int hashCode() {
		return Objects.hash(colum, row);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reference other = (Reference) obj;
		return colum == other.colum && row == other.row;
	}
	
	
}
