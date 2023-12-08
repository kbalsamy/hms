package com.hygieia.app.Services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.hygieia.app.Models.SessionData;
import com.hygieia.app.Repositories.SessionRepository;

@Component
public class VideoMeetingSessionManager {

        @Autowired
        private SessionRepository sessionRepository;

        public SessionData createSession(String sessionId, String hostUsername) {
            SessionData sessionEntity = new SessionData();
            sessionEntity.setSessionId(sessionId);
            sessionEntity.setHostUsername(hostUsername);
            sessionRepository.save(sessionEntity);
            return sessionEntity;
        }

        public SessionData getSession(String sessionId) {
            return sessionRepository.findById(sessionId).orElse(null);
        }

        public void removeSession(String sessionId) {
            sessionRepository.deleteById(sessionId);
        }

        public String getUsernameBySessionId(String sessionId) {
            SessionData sessionEntity = sessionRepository.findById(sessionId).orElse(null);
            return (sessionEntity != null) ? sessionEntity.getHostUsername() : null;
        }

        public String getSessionIdByUsername(String username) {
            Optional<SessionData> sessionEntity = sessionRepository.findByHostUsername(username);
            return sessionEntity.map(SessionData::getSessionId).orElse(null);
        }
    }

