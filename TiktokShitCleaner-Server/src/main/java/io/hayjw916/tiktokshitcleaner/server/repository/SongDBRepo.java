package io.hayjw916.tiktokshitcleaner.server.repository;

import io.hayjw916.tiktokshitcleaner.server.model.SongDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**Repository that keeps songs from the SongDB class*/
@Repository
public interface SongDBRepo extends JpaRepository<SongDB, String> { }