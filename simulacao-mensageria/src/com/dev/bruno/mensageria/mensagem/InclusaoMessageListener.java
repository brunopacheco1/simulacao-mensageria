package com.dev.bruno.mensageria.mensagem;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(mappedName="mensageriaInclusaoQueue", activationConfig =  { @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"), @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"), @ActivationConfigProperty(propertyName="destination", propertyValue="java:jboss/queue/mensageria/inclusao")})
public class InclusaoMessageListener implements MessageListener {

	@Inject
	private MensagemService mensagemService;
	
	@Override
	public void onMessage(Message message) {
		try {
			MensagemDTO dto = message.getBody(MensagemDTO.class);
			
			mensagemService.add(dto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
