
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
		// SQL Connectivity
//		WalletDao walletDao = new
//				 WalletDaoImpl(MySqlUtility.getConnectionToMySQL());

		Scanner scan = new Scanner(System.in);
		System.out.println("Welcome to Wallet-App");
		System.out.println("Choose any of the below services");
		System.out.println("1.Create/Register , 2.Login if account already exists");
		int Choice = scan.nextInt();

		try {
			switch (Choice) {
			case 1:
				System.out.println("Enter a Wallet id");
				scan = new Scanner(System.in);
				int WalletId = scan.nextInt();
				if (walletService.Validate(WalletId)) {
					throw new WalletException("Wallet with id " + WalletId + " already exists");
				}

				System.out.println("Enter a Wallet name");
				scan = new Scanner(System.in);
				String WalletName = scan.nextLine();
				System.out.println("Enter a Ammount");
				scan = new Scanner(System.in);
				Double Amount = scan.nextDouble();

				System.out.println("Enter password");
				scan = new Scanner(System.in);
				String Password = scan.nextLine();

				Wallet wallet5 = walletService.registerWallet(new Wallet(WalletId, WalletName, Amount, Password));

				break;
			case 2:

				scan = new Scanner(System.in);
				System.out.println("Enter Wallet Id");
				scan = new Scanner(System.in);
				int WalletIdlogin = scan.nextInt();
				if (walletService.Validate(WalletIdlogin)) {
					System.out.println("Enter password");
					scan = new Scanner(System.in);
					String Passwordlogin = scan.nextLine();

					if (walletService.login(WalletIdlogin, Passwordlogin)) {
						System.out.println("User logged in Successfully");
						System.out.println("choose from below services ");
						System.out.println(
								"1.Show your balance , 2.add Funds to wallet , 3.Transfer funds , 4.Unregister wallet1");

						int Services = scan.nextInt();
						switch (Services) {
						case 1:
							System.out
									.println("Balance in your id is " + walletService.showWalletBalance(WalletIdlogin));
							break;

						case 2:
//					System.out.println("Enter Wallet id");
//			    	 scan=new Scanner(System.in);
//			    	 int  walletid=scan.nextInt();

							System.out.println("Enter Amount to be deposited");
							scan = new Scanner(System.in);
							Double depositAmount = scan.nextDouble();
							//System.out.println("Before Balance is "+ walletService.showWalletBalance(WalletIdlogin));
							
							System.out.println("added funds succesfully and the balance is "
									+ walletService.addFundsToWallet(WalletIdlogin, depositAmount));
							

							break;
						case 3:
//					System.out.println("Enter Wallet Id of wallet from which you want to transfer");
//					 scan=new Scanner(System.in);
//					 int FromWalletId=scan.nextInt();
							System.out.println("Enter Wallet Id of wallet to which you want to transfer");
							scan = new Scanner(System.in);
							int ToWalletId = scan.nextInt();
							System.out.println("Enter Amount to be transferred");
							scan = new Scanner(System.in);
							Double TransferAmount = scan.nextDouble();

							if (walletService.fundTransfer(WalletIdlogin, ToWalletId, TransferAmount)) {

								System.out.println("Funds transferred succesfully");

								System.out.println("Sender Balance is "+ walletService.showWalletBalance(WalletIdlogin));
								System.out.println("Reciever Balance is "+ walletService.showWalletBalance(ToWalletId));
							}

//					else {
//						System.out.println("Transfer Failed");
//					}
//					 

							break;
						case 4:
//					System.out.println("Enter Wallet Id of wallet from which you want to transfer");
//					 scan=new Scanner(System.in);
//					 int walletIdtobeunregistered=scan.nextInt();
							System.out.println("Enter password of wallet to which you want to unregister");
							scan = new Scanner(System.in);
							String password = scan.nextLine();
							walletService.unRegisterWallet(WalletIdlogin, password);
							System.out.println("Wallet deleted successfully");
							break;
						}

					}

				} else {
					throw new WalletException("Id doesn't exist");
				}
				// }
			}
		}

		catch (WalletException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

	}
}
