package com.wallet.app.dao;

import java.sql.SQLException;

import com.wallet.app.dto.Wallet;
import com.wallet.app.exception.WalletException;

public interface WalletDao {
	//CRUD
	Wallet addWallet(Wallet newWallet)throws WalletException,SQLException;
	Wallet getWalletById(Integer walletId)throws WalletException,SQLException;
	Wallet updateWallet(Integer id,Double amount)throws WalletException,SQLException;
	Wallet deleteWalletById(Integer walletID)throws WalletException,SQLException;
	
}