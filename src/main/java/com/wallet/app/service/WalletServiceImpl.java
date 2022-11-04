package com.wallet.app.service;

import java.sql.SQLException;

import com.wallet.app.dao.WalletDao;
import com.wallet.app.dao.WalletDaoImpl;
import com.wallet.app.dto.Wallet;
import com.wallet.app.exception.WalletException;

public class WalletServiceImpl implements WalletService {

	private WalletDao walletRepository = new WalletDaoImpl();
	
	
	public Wallet registerWallet(Wallet newWallet) throws WalletException, SQLException {
		
		return this.walletRepository.addWallet(newWallet);
		
	}
    
	private Wallet WallettobeChecked;
	public Boolean login(Integer walletId, String password) throws WalletException, SQLException {
		// TODO Auto-generated method stub
		     WallettobeChecked= this.walletRepository.getWalletById(walletId);
		     
		     if(WallettobeChecked !=null) {
		    	 if(WallettobeChecked.getPassword().equals(password)) {
		    		 return true;
		    	 }
		    	 else {
		    		 
		    		 throw new WalletException("Invalid credentails");
		    	 }
		     }
		    	 else {
		    		 throw new WalletException("Wallet id not found");
		    	 }
		     
	}
		
		
	

	public Double addFundsToWallet(Integer walletId, Double amount) throws WalletException, SQLException {
		// TODO Auto-generated method stub
		
		Double totalAmount=this.walletRepository.getWalletById(walletId).getBalance()+amount;
		
		WallettobeChecked=this.walletRepository.updateWallet(walletId, totalAmount);
	      
		return WallettobeChecked.getBalance();
	}

	public Double showWalletBalance(Integer walletId) throws WalletException, SQLException {
		// TODO Auto-generated method stub
		WallettobeChecked= this.walletRepository.getWalletById(walletId);
		return WallettobeChecked.getBalance();
	}

	 Wallet fromWallet;
	 Wallet toWallet;
	public Boolean fundTransfer(Integer fromId, Integer toId, Double amount) throws WalletException, SQLException {
		// TODO Auto-generated method stub
		    fromWallet=this.walletRepository.getWalletById(fromId);
		    toWallet=this.walletRepository.getWalletById(toId);
		    if(fromWallet!=null && toWallet!=null){
		    	
		    	Double  fromBalance=fromWallet.getBalance();
		    	Double  toBalance=toWallet.getBalance();
		    	if(fromBalance>amount) {
		    	
		    		this.walletRepository.updateWallet(fromWallet.getId(),fromBalance-amount);
		    		this.walletRepository.updateWallet(toWallet.getId(),toBalance+amount);
		    		return true;
		    		
		    	}
		    	else {
		    		throw new WalletException("Insufficent Balance");
		    	}
		    	
		    }
		    else if(toWallet==null) {
		    	throw new WalletException("Reciever wallet id doesn't exist");
		    }
	    		 
	      return false; 	 
		   
		    
		    
	}
	Wallet Wallettobedeleted;

	public Wallet unRegisterWallet(Integer walletId, String password) throws WalletException, SQLException {
		// TODO Auto-generated method stub
		Wallettobedeleted=this.walletRepository.getWalletById(walletId);
		 if(Wallettobedeleted.getPassword().equals(password)) {
			 this.walletRepository.deleteWalletById(walletId);
		 }
		return Wallettobedeleted;
	}




	//@Override
//	public boolean Validate(Integer walletId) throws WalletException, SQLException {
//		// TODO Auto-generated method stub
//		 Wallet Validatewallet=this.walletRepository.getWalletById(walletId);
//		if(Validatewallet==null) {
//			return true;
//		}
//		else return false;
//	}

}
