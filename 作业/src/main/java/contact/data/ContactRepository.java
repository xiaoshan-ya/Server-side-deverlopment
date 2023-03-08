package contact.data;

import contact.Contact;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository
         extends CrudRepository<Contact, Long> { //将Contact和数据库绑定，将Contact中的值作为属性存储到数据库中

}
