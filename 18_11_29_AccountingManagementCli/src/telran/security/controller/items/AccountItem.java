package telran.security.controller.items;

import org.springframework.http.HttpHeaders;

import telran.security.service.IAccountingManagement;
import telran.view.InputOutput;
import telran.view.Item;


public abstract class AccountItem implements Item {
    protected InputOutput io;
    protected IAccountingManagement service;
    
    public AccountItem(InputOutput io, IAccountingManagement service) {
        this.io = io;
        this.service = service;
    }
}
