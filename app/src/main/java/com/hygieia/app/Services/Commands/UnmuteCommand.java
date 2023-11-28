package com.hygieia.app.Services.Commands;

import com.hygieia.app.Services.Interfaces.ICommand;

public class UnmuteCommand  implements ICommand{

    private VideoChatReceiver videoChatReceiver;

    public UnmuteCommand(VideoChatReceiver videoChatReceiver) {
        this.videoChatReceiver = videoChatReceiver;
    }

    @Override
    public void execute() {
        videoChatReceiver.unmute();
    }
    
}
