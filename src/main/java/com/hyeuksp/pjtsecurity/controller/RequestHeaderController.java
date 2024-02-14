package com.hyeuksp.pjtsecurity.controller;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Slf4j
@RestController
public class RequestHeaderController {
    private static final Logger logger = LoggerFactory.getLogger(RequestHeaderController.class);

    @GetMapping(value = "/headers")
    public String headers(
            HttpServletRequest request,
            HttpServletResponse response,
            HttpMethod httpMethod,
            Locale locale,
            @RequestHeader MultiValueMap<String, String> headerMap,
            @RequestHeader("host") String host,
            @CookieValue(value="myCookie", required = false) String cookie
    ) {
        logger.info("request={}", request);
        logger.info("response={}", response);
        logger.info("httpMethod={}", httpMethod);
        logger.info("locale={}", locale);
        logger.info("headerMap={}", headerMap);
        logger.info("header host={}", host);
        logger.info("myCookie={}", cookie);

        return "ok";
    }
}