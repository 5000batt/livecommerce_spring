package com.example.livecommerce.live;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LiveHistoryRepository extends JpaRepository<LiveHistory, Long> {
}
