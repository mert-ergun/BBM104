public class Constants {

	// This class keeps all default values of characters and two maps to direct moving/damaging/healing.
	
	public static final int orkAP = 30;
	public static final int orkHP = 200;
	public static final int orkHealPoints = 10;
	public static final int orkMaxMove = 1;
	
	public static final int trollAP = 20;
	public static final int trollHP = 150;
	public static final int trollMaxMove = 1;
	
	public static final int goblinAP = 10;
	public static final int goblinHP = 80;
	public static final int goblinMaxMove = 4;
	
	public static final int humanAP = 30;
	public static final int humanHP = 100;
	public static final int humanMaxMove = 3;
	
	public static final int elfAP = 15;
	public static final int elfHP = 70;
	public static final int elfRangedAP = 15;
	public static final int elfMaxMove = 4;
	
	public static final int dwarfAP = 20;
	public static final int dwarfHP = 120;
	public static final int dwarfMaxMove = 2;

	public static final int[][] map = {{0,1}, {1,1}, {1,0}, {1,-1}, {0, -1}, {-1,-1}, {-1, 0}, {-1, 1}};
	public static final int[][] largeMap = {{-2, -2}, {-2, -1}, {-2, 0}, {-2, 1}, {-2, 2},
											{-1, -2}, {-1, -1}, {-1, 0}, {-1, 1}, {-1, 2},
											{0, -2}, {0, -1}, {0, 1}, {0, 2},
											{1, -2}, {1, -1}, {1, 0}, {1, 1}, {1, 2},
											{2, -2}, {2, -1}, {2, 0}, {2, 1}, {2, 2}};
	
}