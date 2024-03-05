package e1;


public class LogicsImpl implements Logics {

	private final RandomizePositionGenerator randomizePositionGenerator = new SafetyPositionRandomizer();
	private final Pair<Integer, Integer> pawn;
	private Pair<Integer, Integer> knight;
	private final int size;

	public LogicsImpl(int size) {
		this.size = size;
		this.pawn = this.randomizePositionGenerator.getRandomPosition(size);
		this.knight = this.randomizePositionGenerator.getRandomPosition(size);
	}

	//Only for test
	public LogicsImpl(int size, Pair<Integer, Integer> pawnPosition, Pair<Integer, Integer> knightPosition){
		this.size = size;
		this.pawn = pawnPosition;
		this.knight = knightPosition;
	}


	private boolean canMove(int row, int col){
		int x = row - this.knight.getX();
		int y = col - this.knight.getY();
		return (x != 0 && y != 0 && Math.abs(x) + Math.abs(y) == 3) ? true : false;
	};
	
	@Override
	public void hit(int row, int col) {
		if (row < 0 || col < 0 || row >= this.size || col >= this.size) {
			throw new IndexOutOfBoundsException();
		}
		// Below a compact way to express allowed moves for the knight
		if (this.canMove(row, col)) {
			this.knight = new Pair<>(row, col);
		}
	}



	@Override
	public boolean hasKnight(int row, int col) {
		return this.knight.equals(new Pair<>(row, col));
	}

	@Override
	public boolean hasPawn(int row, int col) {
		return this.pawn.equals(new Pair<>(row, col));
	}

	@Override
	public boolean checkWin() {
		return this.pawn.equals(this.knight);
	}


}
