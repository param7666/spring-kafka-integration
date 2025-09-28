package com.nt.consumer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class MessageStore {

	private List<String> listMsg=new ArrayList<>();
	
	public void addMessage(String message) {
		listMsg.add(message);
	}
	
	public String getAllMessage() {
		return listMsg.toString();
	}
}
