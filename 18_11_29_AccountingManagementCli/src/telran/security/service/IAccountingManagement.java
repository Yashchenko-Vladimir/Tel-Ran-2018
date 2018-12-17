package telran.security.service;

import telran.security.dto.AccountDto;

/**
 * Created by Сергей on 28.11.2018.
 */
public interface IAccountingManagement {
    boolean addAccount(AccountDto account);

    boolean removeAccount(String username);

    boolean addRole(String username, String role);

    boolean removeRole(String username, String role);

    boolean updatePassword(String username, String password);

    boolean revokeAccount(String username);

    boolean activateAccount(String username);
    
    boolean login(String username, String password);
}
