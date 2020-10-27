package br.ufrn.imd.groupapp.repository;

import br.ufrn.imd.groupapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
