package telran.security.controller.items;

import telran.security.service.IAccountingManagement;
import telran.view.InputOutput;

/**
 * Created by Сергей on 28.11.2018.
 */
public class UpdatePasswordItem extends AccountItem {
    public UpdatePasswordItem(InputOutput io, IAccountingManagement service) {
        super(io, service);
    }

    @Override
    public String displayedName() {
        return "Update password";
    }

    @Override
    public void perform() {
        String userName = io.inputString("Enter user name");
        if (userName == null)
            return;
        String password = io.inputString("Enter password");
        if (password == null)
            return;
        io.outputLine(service.updatePassword(userName, password));
    }
}
