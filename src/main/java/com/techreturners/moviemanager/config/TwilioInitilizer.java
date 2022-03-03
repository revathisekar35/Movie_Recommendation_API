package com.techreturners.moviemanager.config;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class TwilioInitilizer {
	public TwilioInitilizer(String sid, String authId, String toNum, String fromNum, String message) {
		Twilio.init(sid, authId);
		Message.creator(new PhoneNumber(toNum), new PhoneNumber(fromNum), message).create();
	}
}
