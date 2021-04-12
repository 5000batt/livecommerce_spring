package com.example.livecommerce.boradcast;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BroadcastFileRepository extends JpaRepository<BroadcastFile, Long> {
	
}
