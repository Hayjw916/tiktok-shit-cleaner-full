package io.hayjw916.tiktokshitcleaner.server.repo;

import io.hayjw916.tiktokshitcleaner.server.model.SongModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongDBRepo extends JpaRepository<SongModel, String> {
}
