package com.wallet.app.service;

import java.sql.SQLException;

import com.wallet.app.dto.Wallet;
import com.wallet.app.exception.WalletException;

public interface WalletService {

	Wallet registerWallet(Wallet newWallet) throws WalletException,SQLException;
	
	Boolean login(Integer walletId,String password)throws WalletException,SQLException;

	Double addFundsToWallet(Integer walletId, Double amount)throws WalletException,SQLException;

	Double showWalletBalance(Integer walletId)throws WalletException,SQLException;

	Boolean fundTransfer(Integer fromId, Integer toId, Double amount)throws WalletException,SQLException,SQLException;
	
	Wallet unRegisterWallet(Integer walletId,String password)throws WalletException,SQLException;
	boolean Validate(Integer walletId)throws WalletException,SQLException;
}
