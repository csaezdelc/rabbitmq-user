package cl.uv.ici.arq.lab.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.uv.ici.arq.lab.user.dto.UserDTO;
import cl.uv.ici.arq.lab.user.service.UserProducerService;
import cl.uv.ici.arq.lab.user.service.publisher.MessagePublisher;

@Service("userProducerService")
public class UserProducerServiceImpl implements UserProducerService {

	@Autowired
	MessagePublisher publisher;
	
	@Override
	public void sendMessage(UserDTO user) {
		publisher.sendMessage(user);
	}

}
