package kz.bitlab.m3_ch1.repository;

import kz.bitlab.m3_ch1.entities.Sport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface SportRepository extends JpaRepository<Sport, Long> {
}
