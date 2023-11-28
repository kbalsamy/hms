package com.hygieia.app.Services.Commands;

import com.hygieia.app.Services.Interfaces.ICommand;

public class ButtonClickInvoker {

    private ICommand command;

    public void setCommand(ICommand command) {
        this.command = command;
    }

    public void clickButton() {
        command.execute();
    }
    
}
