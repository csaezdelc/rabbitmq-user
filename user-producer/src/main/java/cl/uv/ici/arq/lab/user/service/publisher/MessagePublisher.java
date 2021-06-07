package cl.uv.ici.arq.lab.user.service.publisher;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cl.uv.ici.arq.lab.user.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class MessagePublisher {
	

	@Autowired
	AmqpTemplate rabbitTemplate;

	@Value("${rabbitmq.exchange}")
	private String exchange;
	
	@Value("${application.id}")
	private String applicationId;


	public void sendMessage(UserDTO user) {

		Random generator = new Random(new Date().getTime());
		Integer num = generator.nextInt();
		num = num < 0 ? num * -1 : num;
		
		MessageProperties props = MessagePropertiesBuilder.newInstance()
				.setContentType(MessageProperties.CONTENT_TYPE_JSON)
				.setMessageId(UUID.randomUUID().toString())
				.setAppId(applicationId)
				.setCorrelationIdIfAbsent(applicationId + "_" + num).build();

		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = null;
		try {
			jsonInString = mapper.writeValueAsString(user);
			Message message = new Message(jsonInString.getBytes(), props);
			rabbitTemplate.convertAndSend(exchange, null, message);
			log.info("Message from Publisher: " + message.getMessageProperties().getCorrelationId());
		} catch (JsonProcessingException e) {
			log.error("JsonProcessingException , Root Cause : " + e);
		}

	}
}