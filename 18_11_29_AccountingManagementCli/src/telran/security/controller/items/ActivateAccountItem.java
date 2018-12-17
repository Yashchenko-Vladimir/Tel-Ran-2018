package telran.security.controller.items;

import telran.security.service.IAccountingManagement;
import telran.view.InputOutput;

/**
 * Created by Сергей on 28.11.2018.
 */
public class ActivateAccountItem extends AccountItem {
    public ActivateAccountItem(InputOutput io, IAccountingManagement service) {
        super(io, service);
    }

    @Override
    public String displayedName() {
        return "Activate account";
    }

    @Override
    public void  perform(){
        String userName = io.inputString("Enter user name");
        if (userName == null)
            return;
        io.outputLine(service.activateAccount(userName));
    }
}
