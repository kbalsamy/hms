package com.hygieia.app.Services.Commands;

import com.hygieia.app.Services.Interfaces.ICommand;

public class TurnOffCameraCommand implements ICommand  {
    
        private VideoChatReceiver videoChatReceiver;
    
        public TurnOffCameraCommand(VideoChatReceiver videoChatReceiver) {
            this.videoChatReceiver = videoChatReceiver;
        }
    
        @Override
        public void execute() {
            videoChatReceiver.turnOffCamera();
        }
    
}
