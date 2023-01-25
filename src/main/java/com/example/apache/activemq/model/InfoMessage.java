package com.example.apache.activemq.model;

import java.io.Serial;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class InfoMessage implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;
  private String topic;
  private String message;

}
