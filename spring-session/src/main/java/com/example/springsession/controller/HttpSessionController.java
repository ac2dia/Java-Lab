package com.example.springsession.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/session")
public class HttpSessionController {

  @GetMapping
  public String getSession(HttpSession httpSession, HttpServletRequest httpServletRequest) {
    log.debug(httpSession.getId());

    httpSession.setAttribute("name", "shlee");
    httpSession.setAttribute("email", "ac2dia@gmail.com");
    httpSession.setAttribute("company", "innogrid");

    return "Get Session";
  }

}
