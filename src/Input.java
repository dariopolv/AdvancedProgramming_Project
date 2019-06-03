import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Input {

	private int input_int;
	private String input_string;

	public Input()  {
		this.input_int = 0;
		this.input_string = "";
	}

	public void insertString() {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		try {
			this.input_string = read.readLine(); 
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void insertInt() {
		BufferedReader readint = new BufferedReader(new InputStreamReader(System.in));
		try {
			this.input_int = Integer.parseInt(readint.readLine());
		}

		catch(IOException e) {
			e.printStackTrace();
		}
	}

	public int getInputInt() {
		return this.input_int;
	}

	public String getInputString() {
		return this.input_string;
	}
}