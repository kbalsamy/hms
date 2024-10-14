package com.hygieia.app.Services.Commands;

import com.hygieia.app.Services.Interfaces.ICommand;

public class TurnOnCameraCommand implements ICommand{

    private VideoChatReceiver videoChatReceiver;

    public TurnOnCameraCommand(VideoChatReceiver videoChatReceiver) {
        this.videoChatReceiver = videoChatReceiver;
    }

    @Override
    public void execute() {
        videoChatReceiver.turnOnCamera();
    }

    
}
