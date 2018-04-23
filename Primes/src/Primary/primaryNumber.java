package Primary;

public class primaryNumber {
	
	private int primary;
	private int _pow;
	
		
	public primaryNumber()
	{ 
		this.primary = 1; 
		this._pow = 1;
	}
		
	public primaryNumber(int primary, int pow)
	{ 
		this.primary = primary; 
		this._pow = pow;
	}
	
	public primaryNumber(int primary)
	{ 
		this.primary = primary; 
		this._pow = 1;
	}
	
	
	public int toNumber(){ 
		return (int)(Math.pow(primary, _pow)); 
	}
	
	public int toNumber(int p){
		if (p < _pow)
			return (int)(Math.pow(primary, p));
		else 
			return (int)(Math.pow(primary, _pow));
	}
	
	public void addPow(){
		_pow++;
	}
	public int getPrimary(){ 
		return primary; 
	}
	
	public int getPow(){
		return _pow; 
	}
	public String printGeneral(){
		String str;
		str = "" + primary + "<sup><small>" + "[0-" + _pow + "]</small></sup>";
		return str;
	}
	
	public String print(){
		String str;
		str = "" + primary + "<sup><small>" + _pow + "</small></sup>";
		return str;
	}
}
