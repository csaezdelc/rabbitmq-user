package cl.uv.ici.arq.lab.consumer.service.listener;

import java.io.IOException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import cl.uv.ici.arq.lab.consumer.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MessageSubcriber {

	@RabbitListener(queues = { "${rabbitmq.queues[0]}" })
	public void recievedMessage1(Message msg) {

		String bodyMessage = new String(msg.getBody());
		log.info("Message: " + bodyMessage);

		ObjectMapper mapper = new ObjectMapper();
		UserDTO user = null;
		try {
			user = mapper.readValue(bodyMessage, UserDTO.class);
		} catch (IOException e) {
			log.error("IOException , Root Cause : " + e);		
		}

		// TODO IMPLEMENT SUSCRIBER LOGICS
		log.info("User: " + user.toString());
		log.info("CorrelationId: " + msg.getMessageProperties().getCorrelationId());
	}

	@RabbitListener(queues = { "${rabbitmq.queues[1]}" })
	public void recievedMessage2(Message msg) {

		log.info("Message 2: " + msg);
	}

	@RabbitListener(queues = { "${rabbitmq.queues[2]}" })
	public void recievedMessag3(Message msg) {

		log.info("Message 3: " + msg);
	}

}