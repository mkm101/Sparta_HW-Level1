package Repository;

import Entity.Name;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Repository extends JpaRepository<Name,Long> {
   List<Name> findAllByOrderByModifiedAtDesc();
   List<Name> findAll();
}
