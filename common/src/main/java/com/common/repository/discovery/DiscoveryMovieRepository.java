package com.common.repository.discovery;

import com.common.domain.Item.Movie;
import com.common.domain.discovery.DiscoveryMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscoveryMovieRepository extends JpaRepository<DiscoveryMovie, Long> {
}
