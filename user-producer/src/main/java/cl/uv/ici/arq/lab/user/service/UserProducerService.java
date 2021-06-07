package cl.uv.ici.arq.lab.user.service;

import cl.uv.ici.arq.lab.user.dto.UserDTO;

public interface UserProducerService {
	public void sendMessage(UserDTO user);

}
