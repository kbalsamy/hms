package com.hygieia.app.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hygieia.app.Services.ApiResponse;
import com.hygieia.app.Services.Commands.ButtonClickInvoker;
import com.hygieia.app.Services.Commands.MuteCommand;
import com.hygieia.app.Services.Commands.TurnOffCameraCommand;
import com.hygieia.app.Services.Commands.TurnOnCameraCommand;
import com.hygieia.app.Services.Commands.UnmuteCommand;
import com.hygieia.app.Services.Commands.VideoChatReceiver;

@RestController
@RequestMapping("api/v1/videochat")
public class VideoChatController {

    private ButtonClickInvoker buttonClickInvoker;
    private VideoChatReceiver videoChatReceiver;

    public VideoChatController() {
        videoChatReceiver = new VideoChatReceiver();
        buttonClickInvoker = new ButtonClickInvoker();
    }


    // mute
    @GetMapping("/mute")
    public ResponseEntity<ApiResponse> mute() {
        try{
            buttonClickInvoker.setCommand(new MuteCommand(videoChatReceiver));
            buttonClickInvoker.clickButton();           
            return ResponseEntity.ok(new ApiResponse(true, "Muted", null));
        }catch(Exception e){
            return ResponseEntity.ok(new ApiResponse(false, "Mute failed", null));
        }
    }

    // unmute
    @GetMapping("/unmute")
    public ResponseEntity<ApiResponse> unmute() {
        try{
            buttonClickInvoker.setCommand(new UnmuteCommand(videoChatReceiver));
            buttonClickInvoker.clickButton();           
            return ResponseEntity.ok(new ApiResponse(true, "Unmuted", null));
        }catch(Exception e){
            return ResponseEntity.ok(new ApiResponse(false, "Unmute failed", null));
        }
    }

    // turn off camera
    @GetMapping("/turnoffcamera")
    public ResponseEntity<ApiResponse> turnOffCamera() {
        try{
            buttonClickInvoker.setCommand(new TurnOffCameraCommand(videoChatReceiver));
            buttonClickInvoker.clickButton();           
            return ResponseEntity.ok(new ApiResponse(true, "Camera turned off", null));
        }catch(Exception e){
            return ResponseEntity.ok(new ApiResponse(false, "Camera turn off failed", null));
        }
    }

    // turn on camera
    @GetMapping("/turnoncamera")
    public ResponseEntity<ApiResponse> turnOnCamera() {
        try{
            buttonClickInvoker.setCommand(new TurnOnCameraCommand(videoChatReceiver));
            buttonClickInvoker.clickButton();           
            return ResponseEntity.ok(new ApiResponse(true, "Camera turned on", null));
        }catch(Exception e){
            return ResponseEntity.ok(new ApiResponse(false, "Camera turn on failed", null));
        }
    }


    
    
}
