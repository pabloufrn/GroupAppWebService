package br.ufrn.imd.groupapp.repository;

import br.ufrn.imd.groupapp.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByGroupIdAndDateAfter(Long groupId, Date date);
}
