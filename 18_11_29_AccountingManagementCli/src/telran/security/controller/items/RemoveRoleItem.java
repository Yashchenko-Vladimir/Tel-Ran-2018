package telran.security.controller.items;

import telran.security.service.IAccountingManagement;
import telran.view.InputOutput;

/**
 * Created by Сергей on 28.11.2018.
 */
public class RemoveRoleItem extends AccountItem {
    public RemoveRoleItem(InputOutput io, IAccountingManagement service) {
        super(io, service);
    }

    @Override
    public String displayedName() {
        return "Remove role";
    }

    @Override
    public void perform()  {
        String userName = io.inputString("Enter user name");
        if (userName == null)
            return;
        String role = io.inputString("Enter role");
        if (role == null)
            return;
        io.outputLine(service.removeRole(userName, role));
    }
}
