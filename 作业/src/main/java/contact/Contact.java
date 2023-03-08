package contact;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Component //在spring启动的时候就被创建为Bean
@Entity //创建一个数据库表实体，连接到数据库表
public class Contact { //保存的信息是一个二维数组，contact是外维

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Message> messages = new ArrayList<>();

	public void addMessage(Message message) {
		this.messages.add(message);
	}
}
