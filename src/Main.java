public class Main {
	public static void main(String [] args) {
		Match m = new Match();
		m.start();
		while( m.getBattleground().getBool() == false) {	 
			m.play();
		}
	}
}
