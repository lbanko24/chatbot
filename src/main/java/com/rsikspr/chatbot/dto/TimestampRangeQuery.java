package com.rsikspr.chatbot.dto;

import lombok.Data;

import java.util.Date;

@Data
public class TimestampRangeQuery {
    private Date start;
    private Date end;
}
