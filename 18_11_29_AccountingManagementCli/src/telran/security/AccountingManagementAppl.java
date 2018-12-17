package telran.security;

import telran.security.controller.items.*;
import telran.security.service.AccountingManagementImpl;
import telran.security.service.IAccountingManagement;
import telran.view.*;


public class AccountingManagementAppl {
    private static final String DEFAULT_HOSTNAME = "http://localhost:";
    private static final int DEFAULT_PORT = 8080;
    private static IAccountingManagement service;
    private static InputOutput io;

    public static void main(String[] args) {
        String hostname = args.length > 0 ? args[0] : DEFAULT_HOSTNAME;
        int port = args.length > 1 ? Integer.parseInt(args[1]) : DEFAULT_PORT;
        io = new ConsoleInputOutput();
        service= new AccountingManagementImpl(hostname, port);
        Item[] items = getMenuItems();
        Menu menu = new MenuWithExit(io, items);
        menu.runMenu();
    }

    private static Item[] getMenuItems() {
        return new Item[]{new LoginItem(io, service), new AddAccountItem(io, service), new RemoveAccountItem(io, service), 
        		new UpdatePasswordItem(io, service),  new AddRoleItem(io, service), new RemoveRoleItem(io, service), 
        		new RevokeAccountItem(io, service), new ActivateAccountItem(io, service)};
    }
}
