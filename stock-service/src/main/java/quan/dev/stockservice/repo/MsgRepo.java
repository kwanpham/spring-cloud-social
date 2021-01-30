package quan.dev.stockservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import quan.dev.stockservice.model.MessageEntity;

public interface MsgRepo extends JpaRepository<MessageEntity , Long> {
}
