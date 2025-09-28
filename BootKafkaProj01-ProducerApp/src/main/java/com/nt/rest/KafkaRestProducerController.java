package com.nt.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nt.consumer.MessageStore;
import com.nt.producer.KafkaProducer;

@RestController
@RequestMapping("/kafka")
public class KafkaRestProducerController {
	
	@Autowired
	private KafkaProducer pro;
	@Autowired
	private MessageStore store;
	
	
	@GetMapping("/send")
	public String sendMessage(@RequestParam("msg")String msg) {
		String status=pro.sendMessage(msg);
		return "<h1>"+status+"</h1>";
	}
	
	@GetMapping("/readAll")
	public String fetchMessage() {
		return "<h1>"+store.getAllMessage()+"</h1>";
	}
	

}
