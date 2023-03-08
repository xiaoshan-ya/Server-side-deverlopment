package contact.data;

import contact.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository
         extends CrudRepository<Message, String> { //将Message和数据库绑定，将Message的值作为属性存储到数据库中
  
}
