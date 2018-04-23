package Primary;
import java.io.*;
import Primary.primaryNumber;

public class DismatlingPrimaryNumbers {

	private int number;          // the number that user insert
	private primaryNumber arr[];  // indicate the Number =  P1^S1 * P2^S2 * P3^S3..
	private int size;            // the size of the arr
	private int prr[];            // the array of primary number until the number that uset insert
	private int sizep;           // the size of the prr array
	private boolean isPrimaryNumber;// indicate if the number that user insert is primary number
	private int sumVariousDivides; // the sum of all the variuos divides


	// Ctor
	public DismatlingPrimaryNumbers(int number){
		this.number = number;
		this.size = 1;
		this.arr = null;
		this.sizep = 0;
		this.prr = null;
		this.isPrimaryNumber = false;
		
		// if is invalid number 
		if (number <= 1)
		{
			primaryNumber p = new primaryNumber(1);
			this.number = 1;
			arr = new primaryNumber[size];
			arr[0] = p;
		}
		else
		{
			// initial the prr array and his size
			getAllPrimaryNumberUntilNumber(number);

			// initial variable
			int temp = number;
			int i = 0, k = 0;
			// check on the number that the user insert
			while (temp != 1 && i < sizep)
			{
				// if the number temp is divide by the primary number in index i
				if (((float)temp) / prr[i] - ((int)temp) / prr[i] == 0)
				{
					if (arr == null)  // if the array not initial
					{
						primaryNumber p = new primaryNumber(prr[i]);
						size = 1;
						arr = new primaryNumber[size];
						arr[0] = p;
					}
					else if (arr[k].getPrimary() == prr[i]) // if the last primary still divide the current update number 
					{
						arr[k].addPow();
					}
					else  // if we in the next primary number
					{
						primaryNumber p = new primaryNumber(prr[i]);
						add(p);
						k++;
					}
					temp = temp / prr[i];  //  update the current number 
				}
				else // move the next primary
				{
					i++;
				}
			}
			// if the number was the same number - in other hand is primary itself
			if (temp == number) 
			{
				//System.out.println("is primary number ! ");
				this.isPrimaryNumber = true;
				primaryNumber p = new primaryNumber(number);
				arr = new primaryNumber[size];
				arr[0] = p;
			}
		}
	}

	// add new number to the array number result
	public void add(primaryNumber p){
		primaryNumber[] res = new primaryNumber[size + 1];
		for (int i = 0; i < size; i++){
			res[i] = arr[i];
		}
		res[size] = p;
		size++;
		arr = res;
	}

	public int toNumber(){
		int res = 0;
		for (int i = 0; i < size; i++){
			res += arr[i].toNumber();
		}
		return res;
	};

	// initial the prr array and his size
	public void getAllPrimaryNumberUntilNumber(int number){
		sizep = 0;
		for (int i = 0; i < number; i++){
			if (isPrimay(i))
				addToAArrayPrr(i);
		} 
	}

	// // Check if the number i is primary number
	public boolean isPrimay(int i){
		if (i <= 1) return false;
		for (int j = 2; j < Math.sqrt(i); j++){
			if (i % j == 0)
				return false;
		}
		return true;
	}

	// add primary number to the prr array
	public void addToAArrayPrr(int i){
		if (prr == null) {
			sizep = 1;
			prr = new int[sizep];
			prr[0] = i;
		}
		else{
			int[] res = new int[sizep + 1];
			for (int j = 0; j < sizep; j++)
				res[j] = prr[j];

			res[sizep] = i;
			
			sizep++;
			prr = res;
		}
	}

	// print the P1^S1 * P2^S2 * P3^S3..
	public String print(DismatlingPrimaryNumbers d){
		String str = "" + number + " = ";
		for (int i = 0; i < d.size; i++)
			if (i != d.size-1)
				str += "" + d.arr[i].print() + " • ";
			else 
				str += "" + d.arr[i].print();

		str += "\n";
		return str;
	}
	
	// print the P1^S1 * P2^S2 * P3^S3..
		public String getGeneralSolution(DismatlingPrimaryNumbers d){
			String str = "";
			for (int i = 0; i < d.size; i++)
				if (i != d.size-1)
					str += "" + d.arr[i].printGeneral() + " • ";
				else 
					str += "" + d.arr[i].printGeneral();

			str += "\n";
			return str;
		}
		
	// return the amount of the various divides
	public int amountDivides(){
		
		int res = 1; 
		
		if (arr == null){
			isPrimaryNumber = true;
			return 2;
		}

		for (int i = 0; i < size; i++){
			res *= arr[i].getPow() + 1;
		}
		
		if (res <= 2) 
			isPrimaryNumber = true;

		return res;
	}
	
	public String amountDividesStr(){
		
		int res = 1; 
		String str = "";
		
		if (arr == null){
			isPrimaryNumber = true;
			str += "(<strong>1</strong> + 1) = 2";
			return str;
		}

		for (int i = 0; i < size; i++){
			str += "(<strong>"+ arr[i].getPow() +"</strong> + 1)";
			res *= arr[i].getPow() + 1;
		}
		
		if (res <= 2) 
			isPrimaryNumber = true;
		str += " = " + res;
		return str;
	}
	
				
	public String Divides2(){
		String str = "";
		sumVariousDivides = 0;
		for(int i = 1, k = 0; i <= number; i++){
			if(i < number)
			{
				if(number % i == 0){
					str += " " + i + " ,";
					sumVariousDivides += i;
				}
			}
			else{
				if(number % i == 0){
					str += " " + i + "\n";
					sumVariousDivides += i;
				}
			}
		}
		return str;
		
	}
	// print all the various divides
	public String Divides(){
		String str = "";
		int sized = amountDivides(); // amount od the dividers
		int[] div = new int[sized]; // initial array dividers
		for (int i = 0; i < sized; i++) // reset to 1
			div[i] = 1;

		// temp to get all the (A)(B)(C)... 
		int[][] temp = new int[size][];
		
		// initial the temp DoArray
		for (int i = 0;  i < size; i++)
			temp[i] = new int[(arr[i].getPow()+1)];
		
		// set the number in the temp array
		for (int i = 0, j = 0; i < size; i++)
		{
			for (int m = 0; m < (arr[i].getPow() + 1); m++){
				temp[i][j++] = arr[i].toNumber(m);
			}
			j = 0;
		}

		// order the various divides in the array div 

		int until = arr[0].getPow() + 1;

		for (int i = 0; i < until; i++){
			div[i] = temp[0][i];
		}

		int k = arr[0].getPow() + 1;
		
		for (int i = 1 ; i < size; i++)
		{		
			for (int j = 1; j < (arr[i].getPow() + 1) && k < sized; j++)
			{
				for (int l = 0; l < until; l++)
				{
					div[k++] = div[l] * temp[i][j];
				}
			}
			until *=  arr[i].getPow() + 1;
			k = until;
		}

				
		// sort
		for (int i = 0; i < sized; i++)
		{
			for (int j = 0; j < sized; j++)
			{
				if (div[i] < div[j])
				{
					int  t = div[i];
					div[i] = div[j];
					div[j] = t;
				}
			}
		}
		
		// compute the summerize of all the defferent various divides
		sumVariousDivides = 0;
		for (int i = 0; i < sized; i++)
		{
			sumVariousDivides += div[i];
		}


		// print the dividers
		str = printdiv(div, sized);
		return str;
	}

	
	// print the array div 
	public String printdiv(int[] div,int sized){
		String str = "";
		for (int i = 0; i < sized; i++){
			if (i != sized - 1)
				str += "" + div[i] + ", ";
			else{
				str += "" + div[i] + "\n";
			}
		}
		return str;
	}
	
	
	// print the array prr - (2^0, 2^1 , 2^2 ...) or (1, 2 , 4 , ...)
	public String printArr(int[][] temp,  boolean flag){
		String str = "";
		// print the numbers as (A)*(B)*(C)
		if (flag){
			for (int i = 0; i < size; i++)
			{
				for (int j = 0; j < arr[i].getPow() + 1; j++){

					if (i != 0 && j == 0)
						str += "*(";
					else if (j == 0)
						str += "(";

					if (j != arr[i].getPow())
						str += "" + arr[i].getPrimary() + "<sup><small>" + j + "</small></sup> ,";
					else
						str += "" + arr[i].getPrimary() + "<sup><small>" + j + "</small></sup>";
				}
			}
			str += "\n";
			
		}
		else{
			for (int i = 0; i < size; i++)
			{
				for (int j = 0; j < arr[i].getPow() + 1; j++){

					if (i != 0 && j == 0)
						str += "*(";
					else if (j == 0)
						str += "(";

					if (j != arr[i].getPow())
						str += "" + temp[i][j] + ",";
					else
						str += "" + temp[i][j];
				}
			}
			str += "\n";
		}	
		
		return str;
	}

	// Check if is perfect number - Is a natural number equal to its all natural divisors except the number itself.
	public String PerfectNumber(){
		String str = "זה לא מספר משוכלל";
		if(isPrimaryNumber){
			str = "! זהו מספר ראשוני !";
		}
		else if (sumVariousDivides - number > number){
			str = "+ זהו מספר עודף +";
		}
		else if(sumVariousDivides - number < number){
			str = "- זהו מספר מחוסר -";
		}
		else{
			str = "* זהו מספר משוכלל *";
		}
		
		return str;
	}

	public int sumDivides(){
		return sumVariousDivides;
	}
	
	public int sumDivides_wo(){
		return sumVariousDivides - number;
	}
	
}
