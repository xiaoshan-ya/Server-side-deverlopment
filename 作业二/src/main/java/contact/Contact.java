package contact;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component //在spring启动的时候就被创建为Bean
public class Contact { //保存的信息是一个二维数组，contact是外维

	private List<Message> messages = new ArrayList<>();

	public void addMessage(Message message) {
		this.messages.add(message);
	}
}
