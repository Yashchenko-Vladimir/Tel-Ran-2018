package telran.security.controller.items;

import telran.security.dto.AccountDto;
import telran.security.service.IAccountingManagement;
import telran.view.InputOutput;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Сергей on 28.11.2018.
 */
public class RemoveAccountItem extends AccountItem {
    public RemoveAccountItem(InputOutput io, IAccountingManagement service) {
        super(io, service);
    }

    @Override
    public String displayedName() {
        return "Remove account";
    }

    @Override
    public void perform()  {
        String userName = io.inputString("Enter user name");
        if (userName == null)
            return;
        io.outputLine(service.removeAccount(userName));
    }
}
