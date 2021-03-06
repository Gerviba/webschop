package hu.gerviba.webschop.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.gerviba.webschop.model.OpeningEntity;

@Repository
public interface OpeningRepository extends JpaRepository<OpeningEntity, Long> {

    List<OpeningEntity> findAllByOrderByDateStart();
    
    List<OpeningEntity> findAllByDateEndGreaterThanAndDateEndLessThanOrderByDateStart(long now, long weekFromNow);
    
    Optional<OpeningEntity> findFirstByCircle_IdOrderByDateStart(Long circle);
    
}
