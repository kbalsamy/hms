package com.hygieia.app.Controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.hygieia.app.DTO.SessionDto;
import com.hygieia.app.DTO.UserLogInDto;
import com.hygieia.app.Models.SessionData;
import com.hygieia.app.Services.ApiResponse;
import com.hygieia.app.Services.TokenService;
import com.hygieia.app.Services.Commands.ButtonClickInvoker;
import com.hygieia.app.Services.Commands.MuteCommand;
import com.hygieia.app.Services.Commands.TurnOffCameraCommand;
import com.hygieia.app.Services.Commands.TurnOnCameraCommand;
import com.hygieia.app.Services.Commands.UnmuteCommand;
import com.hygieia.app.Services.Commands.VideoChatReceiver;
import com.hygieia.app.Services.VideoMeetingSessionManager;

@RestController
@CrossOrigin
@RequestMapping("api/v1/videochat")
public class VideoChatController {

    private ButtonClickInvoker buttonClickInvoker;
    private VideoChatReceiver videoChatReceiver;

    @Autowired
    private VideoMeetingSessionManager sessionManager;

    @Autowired
    private TokenService tokenService;

    public VideoChatController() {
        videoChatReceiver = new VideoChatReceiver();
        buttonClickInvoker = new ButtonClickInvoker();
    }

    // mute
    @GetMapping("/mute")
    public ResponseEntity<ApiResponse> mute() {
        try {
            buttonClickInvoker.setCommand(new MuteCommand(videoChatReceiver));
            buttonClickInvoker.clickButton();
            return ResponseEntity.ok(new ApiResponse(true, "Muted", null));
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse(false, "Mute failed", null));
        }
    }

    // unmute
    @GetMapping("/unmute")
    public ResponseEntity<ApiResponse> unmute() {
        try {
            buttonClickInvoker.setCommand(new UnmuteCommand(videoChatReceiver));
            buttonClickInvoker.clickButton();
            return ResponseEntity.ok(new ApiResponse(true, "Unmuted", null));
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse(false, "Unmute failed", null));
        }
    }

    // turn off camera
    @GetMapping("/turnoffcamera")
    public ResponseEntity<ApiResponse> turnOffCamera() {
        try {
            buttonClickInvoker.setCommand(new TurnOffCameraCommand(videoChatReceiver));
            buttonClickInvoker.clickButton();
            return ResponseEntity.ok(new ApiResponse(true, "Camera turned off", null));
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse(false, "Camera turn off failed", null));
        }
    }

    // turn on camera
    @GetMapping("/turnoncamera")
    public ResponseEntity<ApiResponse> turnOnCamera() {
        try {
            buttonClickInvoker.setCommand(new TurnOnCameraCommand(videoChatReceiver));
            buttonClickInvoker.clickButton();
            return ResponseEntity.ok(new ApiResponse(true, "Camera turned on", null));
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse(false, "Camera turn on failed", null));
        }
    }

    @GetMapping("/join")
    public ResponseEntity<SessionDto> joinMeeting(@RequestBody UserLogInDto userlog) {

        try {
            tokenService.GenerateToken(userlog);

            String sessionId = generateSessionId();

            SessionData session = sessionManager.createSession(sessionId, userlog.getUserName());

            return new ResponseEntity<>(new SessionDto(sessionId, session.getHostUsername() + " joined meeting"),
                    HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity<>(new SessionDto(null, "Cannot join meeting"), HttpStatus.UNAUTHORIZED);

        }
    }

    private String generateSessionId() {

        return String.valueOf(UUID.randomUUID());
    }

    @RequestMapping(value = "/leave", method = RequestMethod.GET)
    //@ResponseBody
    public ResponseEntity<SessionDto> leaveMeeting(@RequestParam String sessionId) {

        try {
            String username = sessionManager.getUsernameBySessionId(sessionId);

            sessionManager.removeSession(sessionId);
            return new ResponseEntity<>(new SessionDto(sessionId, username + " left meeting"),
                    HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(new SessionDto(null, "Session ID not valid"), HttpStatus.UNAUTHORIZED);

        }

    }

}
