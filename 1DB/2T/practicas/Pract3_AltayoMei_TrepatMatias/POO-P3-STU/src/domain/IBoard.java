package domain;

public interface IBoard {

	public int getRows();
	
	public int getColumns();
	
	public boolean isCellEmpty(int row, int col);
}
