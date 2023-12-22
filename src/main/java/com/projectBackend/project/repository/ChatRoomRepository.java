package com.projectBackend.project.repository;

import com.projectBackend.project.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, String> {

}
