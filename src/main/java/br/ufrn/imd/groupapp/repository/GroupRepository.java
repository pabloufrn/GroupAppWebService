package br.ufrn.imd.groupapp.repository;

import br.ufrn.imd.groupapp.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
}