package com.hygieia.app.Services.Commands;

import com.hygieia.app.Services.Interfaces.ICommand;

public class MuteCommand implements ICommand {

    private VideoChatReceiver videoChatReceiver;

    public MuteCommand(VideoChatReceiver videoChatReceiver) {
        this.videoChatReceiver = videoChatReceiver;
    }

    @Override
    public void execute() {
        videoChatReceiver.mute();
    }
    
}
