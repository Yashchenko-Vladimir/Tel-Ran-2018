package telran.security.controller.items;

import telran.security.dto.AccountDto;
import telran.security.service.IAccountingManagement;
import telran.view.InputOutput;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Сергей on 28.11.2018.
 */
public class AddAccountItem extends AccountItem {
    public AddAccountItem(InputOutput io, IAccountingManagement service) {
        super(io, service);
    }

    @Override
    public String displayedName() {
        return "Add account";
    }

    @Override
    public void perform() {
        String userName = io.inputString("Enter user name");
        if (userName == null)
            return;
        String password = io.inputString("Enter password");
        if (password == null)
            return;
        String role = io.inputString("Enter role");
        HashSet<String> roles = new HashSet<>();
        while (role != null) {
            roles.add(role);
            role = io.inputString("Enter next role");
        }
        io.outputLine(service.addAccount(new AccountDto(userName, password, roles)));
    }
}
