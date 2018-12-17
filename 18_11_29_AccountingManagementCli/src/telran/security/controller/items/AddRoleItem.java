package telran.security.controller.items;

import telran.security.dto.AccountDto;
import telran.security.service.IAccountingManagement;
import telran.view.InputOutput;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Сергей on 28.11.2018.
 */
public class AddRoleItem extends AccountItem {
    public AddRoleItem(InputOutput io, IAccountingManagement service) {
        super(io, service);
    }

    @Override
    public String displayedName() {
        return "Add role";
    }

    @Override
    public void perform() {
        String userName = io.inputString("Enter user name");
        if (userName == null)
            return;
        String role = io.inputString("Enter role");
        if (role == null)
            return;
        io.outputLine(service.addRole(userName, role));
    }
}
