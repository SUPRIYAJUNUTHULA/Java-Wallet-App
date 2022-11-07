package com.wallet.app.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.wallet.app.dto.Wallet;
import com.wallet.app.exception.WalletException;
import com.wallet.app.service.WalletService;
import com.wallet.app.service.WalletServiceImpl;

public class WalletServiceTest {

	WalletService walletService = new WalletServiceImpl();

	@BeforeAll
	public static void setupTestData() {
		System.out.println("Create all test data");
	}

	@Test
	public void registerWalletTest() throws SQLException, WalletException {

		Wallet wallet = walletService.registerWallet(new Wallet(10, "test name1", 1000.0, "test1"));
		assertNotNull(wallet);
		assertEquals(10, wallet.getId());

	}

//@Test
//public void registerWalletTestException() throws WalletException, SQLException {
//	try {
//	Wallet wallet =walletService.registerWallet(new Wallet(20, "test name1", 1000.0, "test1"));
//	assertThrows(WalletException.class,()->  walletService.registerWallet(new Wallet(20, "test name1", 1000.0, "test1")));
//	//assertThrows(WalletException.class,()-> walletService.login(17,"test8"));
//}


	@Test
	public void showWalletBalanceTest() throws SQLException, WalletException {

		Wallet wallet = walletService.registerWallet(new Wallet(11, "test name2", 1000.0, "test2"));

		assertEquals(1000.0, walletService.showWalletBalance(11));
	}

	@Test
	public void loginTest() throws SQLException, WalletException {

		Wallet wallet = walletService.registerWallet(new Wallet(12, "test name2", 1000.0, "test4"));
		assertNotNull(wallet);
		assertEquals(true, walletService.login(12, "test4"));

	}

	@Test
	public void fundTransferTest() throws WalletException, SQLException {
		int fromWalletId = 0;
		int toWalletId = 0;

		Wallet fromwallet = walletService.registerWallet(new Wallet(14, "test name2", 1000.0, "test5"));
		Wallet towallet = walletService.registerWallet(new Wallet(15, "test name2", 1000.0, "test6"));
		fromWalletId = fromwallet.getId();
		assertNotNull(fromwallet);
		assertEquals(14, fromwallet.getId());
		toWalletId = fromwallet.getId();
		assertNotNull(towallet);
		assertEquals(15, towallet.getId());
		assertEquals(true, walletService.fundTransfer(fromWalletId, toWalletId, 500.0));

	}
//	@Test
//	public void ReceivernotExistsTest() {
//		
//		try {
//			Wallet fromwallet =walletService.registerWallet(new Wallet(14, "test name2", 1000.0, "test5"));
//			//Wallet towallet =walletService.registerWallet(new Wallet(20, "test name2", 1000.0, "test6"));
//		assertThrows(WalletException.class,()->walletService.fundTransfer(14,null,500.0));
//		} catch (WalletException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	    
//	}

	@Test
	public void addFundsToWalletTest() throws WalletException, SQLException {
		Wallet wallet = walletService.registerWallet(new Wallet(16, "test name2", 1000.0, "test6"));
		assertEquals(2000.0, walletService.addFundsToWallet(16, 1000.0));

	}

	@Test
	public void loginExceptionTest() throws SQLException, WalletException {
		Wallet wallet = walletService.registerWallet(new Wallet(17, "test name2", 1000.0, "test7"));
		assertThrows(WalletException.class, () -> walletService.login(17, "test8"));
		assertThrows(WalletException.class, () -> walletService.login(null, "test9"));

	}

//	@Test
//public void registerExceptionTest() throws  SQLException,WalletException {
//		try {
//			Wallet wallet =walletService.registerWallet(new Wallet(17, "test name2", 1000.0, "test7"));
//	assertThrows(WalletException.class,()-> walletService.registerWallet(new Wallet(17, "test name2", 1000.0, "test7")));
//		} catch (WalletException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	
//	}
	@Test

	public void fundTransferInsufficientException() throws WalletException, SQLException {

		Wallet fromwallet = walletService.registerWallet(new Wallet(18, "test name2", 1000.0, "test5"));
		Wallet towallet = walletService.registerWallet(new Wallet(19, "test name2", 1000.0, "test6"));

		assertThrows(WalletException.class, () -> walletService.fundTransfer(18, 19, 1500.0));
	}
//	@Test
//	public void showWalletBalanceExceptionTest() throws SQLException {
//		try {
//			Wallet wallet =walletService.registerWallet(new Wallet(103, "test name1", 1000.0, "123"));
//			assertThrows(WalletException.class,()-> walletService.showWalletBalance(200));
//		} catch (WalletException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

//	}
	@Test
	public void unregisterWalletException() throws WalletException, SQLException {
		Wallet wallet = walletService.registerWallet(new Wallet(25, "test name2", 1000.0, "test5"));

		Wallet walletobedeleted = walletService.unRegisterWallet(25, "test5");
		assertEquals(25, walletobedeleted.getId());

	}

	@Test
	public void unregisterExceptionPasswordTest() throws SQLException {

		try {
			walletService.registerWallet(new Wallet(9000, "name9000", 9000.0, "test9000"));
			walletService.unRegisterWallet(9000, "abc");

		} catch (WalletException ex) {
			assertEquals("Password doesn't match", ex.getMessage());
		}
	}

	@Test
	public void fundTransferReceivernotExistException() throws SQLException {
		try {
			walletService.registerWallet(new Wallet(1000, "name1000", 1000.0, "test1000"));
			walletService.registerWallet(new Wallet(2000, "name2000", 2000.0, "test2000"));
			walletService.fundTransfer(1000, null, 5000.0);

		} catch (WalletException ex) {
			assertEquals("Reciever wallet id doesn't exist", ex.getMessage());
		}
	}

//	@Test
//	public void fundTransferInsufficientBalanceException() throws SQLException {
//		try {
//			walletService.registerWallet(new Wallet(3000, "name3000", 3000.0, "test3000"));
//			walletService.registerWallet(new Wallet(4000, "name4000", 4000.0, "test4000"));
//			walletService.fundTransfer(3000, 4000, 5000.0);
//
//		} catch (WalletException ex) {
//			assertEquals("Insufficent Balance", ex.getMessage());
//		}
//	}

	@Test
	public void fundTransferAmountGreaterThanZero() throws SQLException {
		try {
			walletService.registerWallet(new Wallet(77, "name3000", 3000.0, "test3000"));
			walletService.registerWallet(new Wallet(78, "name4000", 4000.0, "test4000"));
			walletService.fundTransfer(3000, 4000, 0.0);

		} catch (WalletException ex) {
			assertEquals("Amount transferred should be greater than Rs.0", ex.getMessage());
		}
	}
	

	@Test
	public void AddAmountGreaterThanZero() throws SQLException {
		try {
			walletService.registerWallet(new Wallet(150, "name3000", 3000.0, "test3000"));
			walletService.addFundsToWallet(150, 0.0);

		} catch (WalletException ex) {
			assertEquals("Amount Should be greater than zero", ex.getMessage());
		}
	}

	@Test
	public void validateTest() throws WalletException, SQLException {
		walletService.registerWallet(new Wallet(101, "name3000", 3000.0, "test3000"));
		assertEquals(true, walletService.Validate(101));
		assertEquals(false, walletService.Validate(105));

	}

	@AfterAll
	public static void destroyTestData() {
		System.out.println("Clear all test data");
	}
}