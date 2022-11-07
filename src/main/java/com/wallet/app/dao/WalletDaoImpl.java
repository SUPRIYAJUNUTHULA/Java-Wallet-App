package com.wallet.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.wallet.app.dto.Wallet;
import com.wallet.app.exception.WalletException;

public class WalletDaoImpl implements WalletDao {

	// Create collection to store the Wallet information.
	Map<Integer, Wallet> wallets = new HashMap<>();

//sql connectivity
	public WalletDaoImpl() {
	}

	Connection connection = MySqlUtility.getConnectionToMySQL();
	/*
	 * public WalletDaoImpl(Connection connection) { super(); this.connection =
	 * connection; }
	 */
//	WalletDao walletDao = new
//			 WalletDaoImpl(connection);

	public Wallet addWallet(Wallet newWallet) throws WalletException, SQLException {

//		 this.wallets.put(newWallet.getId(), newWallet);
//		 return this.wallets.get(newWallet.getId());
		String sql = "INSERT INTO Wallet_Information(id,name,amount,password) VALUES(?,?,?,?)";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, newWallet.getId());
		preparedStatement.setString(2, newWallet.getName());
		preparedStatement.setDouble(3, newWallet.getBalance());
		preparedStatement.setString(4, newWallet.getPassword());
		int count = preparedStatement.executeUpdate();
		if (count == 1) {
			System.out.println("Wallet added successfully to DB.");
		}
//			else {
//				throw new WalletException("Wallet is not registered");
//			}
		return newWallet;
	}

	public Wallet getWalletById(Integer walletId) throws WalletException, SQLException {
		// TODO Auto-generated method stub

		String get = "select * from Wallet_Information where id=" + walletId;
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery(get);
		if (result.isBeforeFirst()) {
			result.next();
			Wallet wallet = new Wallet();
			wallet.setId(result.getInt(1));
			wallet.setName(result.getString(2));
			wallet.setBalance(result.getDouble(3));
			wallet.setPassword(result.getString(4));
			return wallet;
		} else {
			return null;
		}
	}

	public Wallet updateWallet(Integer id, Double amount) throws WalletException, SQLException {
		// TODO Auto-generated method stub
		Wallet updatedWallet = getWalletById(id);
		String update = "update Wallet_Information set amount=" + amount + "where id=" + id;
		Statement statement = connection.createStatement();
		statement.executeUpdate(update);
		return getWalletById(id);
	}

	public Wallet deleteWalletById(Integer walletID) throws WalletException, SQLException {
		// TODO Auto-generated method stub
		Wallet walletDeleted = new Wallet();
		walletDeleted = getWalletById(walletID);
		String delete = "delete from Wallet_Information where id=" + walletID;
		Statement statement = connection.createStatement();

		int count = statement.executeUpdate(delete);

		// walletobeDeleted=getWalletById(walletID);
		// wallets.remove(walletID);
		// System.out.println(wallets);
		return walletDeleted;

	}

}