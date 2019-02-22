
public enum Color {
RED("#"), YELLOW("*");
	
private String sign;	
Color(String sign) {
	this.sign = sign;
}

public String getSign() {
	return sign;
	
}
}
