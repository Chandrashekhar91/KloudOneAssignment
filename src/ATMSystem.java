import java.util.HashMap;
import java.util.Scanner;

class ATMSystem
{
	private String accno;
	private String name;
	private long balance;
	private String pincode;
	Scanner KB=new Scanner(System.in);
	private static final HashMap<String, String> accPinMap = new HashMap<>();
	
	//method to open an account
	void openAccount()
	{ 
		System.out.print("Enter Account No: ");
		accno=KB.next();
		System.out.print("Enter Name: ");
		name=KB.next();
		System.out.print("Enter Balance: ");
		balance=KB.nextLong();
		System.out.print("Enter ATM Pincode: ");
		pincode=KB.next();		
		savePincode(accno , pincode);
	}
	
	//method to map account no with pincode
	void savePincode(String acc , String pin)
	{ 
		accPinMap.put(acc, pin);
	}

	//method to display account details
	void showAccount()
	{ 
		System.out.println("--------------------------------------");
		System.out.println("| "+accno+" | "+name+" | "+balance+" | ");
		System.out.println("--------------------------------------");
	}

	//method to deposit money
	void deposit()
	{
		long amt;
		System.out.println("Enter Amount you want to Deposit : ");
		amt=KB.nextLong();
		balance=balance+amt;
		System.out.println("Amount deposited successfully , Available Balance : "+balance);
	}

	//method to withdraw money
	void withdrawal()
	{
		long amt;
		System.out.println("Enter Amount you want to withdraw : ");
		amt=KB.nextLong();
		if(balance>=amt)
		{ 
			balance=balance-amt;
			System.out.println("Amount withdraw successfully , Available Balance : "+balance);
		}
		else
		{
			System.out.println("Less Balance..Transaction Failed..");
		}
	}

	//method to search an account number
	boolean search(String acn)
	{ 
		if(accno.equals(acn))
		{ 
			showAccount();
			return(true);
		}
		return(false);
	}
	
	//method to change ATM Pincode
	void changePin(String acn)
	{
		if (accno.equals(acn)) {
			System.out.println("Enter New Pincode : ");
			String pin = KB.next();
			if (accPinMap.containsKey(acn)) {
				accPinMap.put(acn, pin);
				System.out.println("Pincode changed successfully ");
			}
		}
	}
	
	//method to validate PinCode of particular Account
	boolean validatePin(String acn)
	{ 
		if (accno.equals(acn)) {
			System.out.println("Enter Pincode : ");
			String pin = KB.next();
			if (accPinMap.containsKey(acn)) {
				if(pin.equalsIgnoreCase(accPinMap.get(acn)))
					return true;
			}	
		}
		return false;
	}
}	

class ExecuteATMSystem
{  
	public static void main(String arg[])
	{
		Scanner in=new Scanner(System.in);
		
		//create initial accounts
		System.out.print("How Many Customer you Want to Input : ");
		int n=in.nextInt();
		ATMSystem  C[]=new ATMSystem[n];
		for(int i=0;i<C.length;i++)
		{   
			C[i]=new ATMSystem();
			C[i].openAccount();
		}
		
		//run loop until menu 6 is not pressed
		int ch;
		do
		{
			System.out.println("Main Menu\n"+
			"1.Display All\n" +
			"2.Search By Account\n"+
			"3.Deposit\n" +
			"4.Withdrawal\n" +
			"5.Change Pincode\n" +
			"6.Exit");
			System.out.println("Your Choice :");
			ch=in.nextInt();
			switch(ch)
			{ 
				case 1:
					System.out.println(" ------------------------------------------------- ");
					System.out.println(" | Account Number | Account Holder Name | balance | ");
					System.out.println(" ------------------------------------------------- ");
					for(int i=0;i<C.length;i++)
					{
						C[i].showAccount();
					}
					break;

				case 2:
					System.out.print("Enter Account No You Want to Search: ");
					String acn=in.next();
					boolean found=false;
					for(int i=0;i<C.length;i++)
					{  
						found=C[i].search(acn);
						if(found)
						{
							break;
						}
					}
					if(!found)
					{
						System.out.println("Search Failed..Account Not Exist..");
					}
					break;

				case 3:
					System.out.print("Enter Account No : ");
					acn=in.next();
					boolean pinVal = false;
					found=false;
					for(int i=0;i<C.length;i++)
					{  
						found=C[i].search(acn);
						if(found)
						{
							pinVal = C[i].validatePin(acn);
							if(pinVal)
							{
								C[i].deposit();
								break;
							} else {
								System.out.println("Invalid Pin");
								break;
							}
						}
					}
					if(!found)
					{
						System.out.println("Search Failed..Account Not Exist..");
					}
					break;

				case 4:
					System.out.print("Enter Account No : ");
					acn=in.next();
					found=false;
					for(int i=0;i<C.length;i++)
					{  
						found=C[i].search(acn);
						if(found)
						{
							pinVal = C[i].validatePin(acn);
							if(pinVal)
							{
								C[i].withdrawal();
								break;
							} else {
								System.out.println("Invalid Pin");
								break;
							}
						}
					}
					if(!found)
					{
						System.out.println("Search Failed..Account Not Exist..");
					}
					break;

				case 5:
					System.out.print("Enter Account No : ");
					acn=in.next();
					found=false;
					for(int i=0;i<C.length;i++)
					{  
						found=C[i].search(acn);
						if(found)
						{
							C[i].changePin(acn);
							break;
						}
					}
					if(!found)
					{
						System.out.println("Search Failed..Account Not Exist..");
					}
					break;
					
				case 6:
					System.out.println("Good Bye..");
					break;
			}
		}
		while(ch!=6);
	}
}
