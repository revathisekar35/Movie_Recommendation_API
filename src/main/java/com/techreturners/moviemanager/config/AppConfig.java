package com.techreturners.moviemanager.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "twilio")
@Data
public class AppConfig {
	public String sid;
	public String authId;
	public String fromNumber;
	public String toNumber;
}
