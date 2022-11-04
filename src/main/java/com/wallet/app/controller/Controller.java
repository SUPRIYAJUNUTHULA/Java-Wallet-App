
package com.wallet.app.controller;

import java.util.Scanner;

//import com.employee.app.dao.EmployeeDAO;
//import com.employee.app.dao.EmployeeDaoImpl;
import com.wallet.app.dao.MySqlUtility;
import com.wallet.app.dao.WalletDao;
import com.wallet.app.dao.WalletDaoImpl;
import com.wallet.app.dto.Wallet;
import com.wallet.app.exception.WalletException;
import com.wallet.app.service.WalletService;
import com.wallet.app.service.WalletServiceImpl;

public class Controller {

	public static void main(String[] args) {
		
		WalletService walletService = new WalletServiceImpl();
		//SQL Connectivity 
//		WalletDao walletDao = new
//				 WalletDaoImpl(MySqlUtility.getConnectionToMySQL());
		
		
		Scanner scan=new Scanner(System.in);
		System.out.println("Welcome to Wallet-App");
	    System.out.println("Choose any of the below services");
		System.out.println("1.Create/Register , 2.Login if account already exists");
		int Choice=scan.nextInt();
		
		
	
	     try {
//	    	 Wallet wallet1 = walletService.registerWallet(new Wallet(1, "Ford", 1000.0, "123"));
//				Wallet wallet2 = walletService.registerWallet(new Wallet(2, "paytm", 5000.0, "456"));
//				Wallet wallet3 = walletService.registerWallet(new Wallet(3, "Phone pe", 7000.0, "789"));
//				Wallet wallet4 = walletService.registerWallet(new Wallet(4, "gpay", 10000.0, "1011"));
	     switch(Choice) {
	     case 1:
	    	 System.out.println("Enter a Wallet id");
	    	 scan=new Scanner(System.in);
	    	 int WalletId=scan.nextInt();
	    	 //if(walletService.Validate(WalletId)) {
	    		 System.out.println("Enter a Wallet name");
		    	 scan=new Scanner(System.in);
		    	 String WalletName=scan.nextLine();
		    	 System.out.println("Enter a Ammount");
		    	 scan=new Scanner(System.in);
		    	 Double Amount=scan.nextDouble();
		    	 
		    	 System.out.println("Enter password");
		    	 scan=new Scanner(System.in);
		    	 String Password=scan.nextLine();
		    	 
			
				
				Wallet wallet5 = walletService.registerWallet(new Wallet(WalletId, WalletName, Amount, Password));
				System.out.println("User registered succesfully"); 
	    	 
	    	
			break;
			case 2:
				
				scan=new Scanner(System.in);
		    	 System.out.println("Enter Wallet Id");
				 scan=new Scanner(System.in);
				 int WalletIdlogin=scan.nextInt();
				// if(!(walletService.Validate(WalletIdlogin))) {
		    	 System.out.println("Enter password");
		    	 scan=new Scanner(System.in);
		    	 String Passwordlogin=scan.nextLine();
		    	 
				
			if(walletService.login(WalletIdlogin,Passwordlogin )) {
				System.out.println("choose from below services ");
				System.out.println("1.Show your balance , 2.add Funds to wallet , 3.Transfer funds , 4.Unregister wallet1");
				int Services=scan.nextInt();
				switch(Services) {
				case 1:System.out.println("Balance in your id is "+walletService.showWalletBalance(1));
				break;
				 
				case 2:
					System.out.println("Enter Wallet id");
			    	 scan=new Scanner(System.in);
			    	 int  walletid=scan.nextInt();
					
					System.out.println("Enter Amount to be deposited");
			    	 scan=new Scanner(System.in);
			    	 Double depositAmount=scan.nextDouble();
			    	 if(depositAmount>0) {
					System.out.println("added funds succesfully and the balance is "+walletService.addFundsToWallet(walletid,depositAmount));
			    	 }
			    	 else {
			    		 throw new WalletException("Entered Amount should be greater than zero");
			    	 }
					break;
				case 3:
					System.out.println("Enter Wallet Id of wallet from which you want to transfer");
					 scan=new Scanner(System.in);
					 int FromWalletId=scan.nextInt();
					 System.out.println("Enter Wallet Id of wallet to which you want to transfer");
					 scan=new Scanner(System.in);
					 int ToWalletId=scan.nextInt();
					 System.out.println("Enter Amount to be transferred");
					 scan=new Scanner(System.in);
					 Double TransferAmount=scan.nextDouble();
					 if(TransferAmount>0){
					
					if(walletService.fundTransfer(FromWalletId,ToWalletId,TransferAmount)) {
					  
					  System.out.println("Funds transferred succesfully "+"balances from  account is "+walletService.showWalletBalance(FromWalletId)+" balances  to account is "+walletService.showWalletBalance(ToWalletId));
					  
					}
					 
					else {
						System.out.println("Transfer Failed");
					}}
					 else {
						 throw new WalletException("Entered Amount should be greater than zero");
					 }
					
				break;
				case 4:
					System.out.println("Enter Wallet Id of wallet from which you want to transfer");
					 scan=new Scanner(System.in);
					 int walletIdtobeunregistered=scan.nextInt();
					 System.out.println("Enter Wallet Id of wallet to which you want to transfer");
					 scan=new Scanner(System.in);
					 String password=scan.nextLine();
					
					System.out.println("Wallet "+walletService.unRegisterWallet(walletIdtobeunregistered, password)+" deleted successfully");
					break;
				}
				//System.out.println("User logged in Successfully");
				//System.out.println("Balance in your id is "+walletService.showWalletBalance(1));
				//System.out.println("added funds succesfully and the balance is "+walletService.addFundsToWallet(2,2000.0));
//				if(walletService.fundTransfer(1,6,1500.0)) {
//				  System.out.println("Funds transferred succesfully "+"balances from toaccount is "+walletService.showWalletBalance(1)+" balances from toaccount is "+walletService.showWalletBalance(2));
//				  
//				}
//				else {
//					System.out.println("Transfer Failed");
//				}
				//System.out.println("Wallet "+walletService.unRegisterWallet(3, "456")+" deleted successfully");
				
			}
			
				}
			//	 else throw new WalletException("User doesn't exist");
	  //   }
	     }
				
	     
		
	     
	     
	
			//System.out.println(wallet2);
		 catch (WalletException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	     catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
	
	     }
}
