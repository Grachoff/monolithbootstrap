package com.altarix.services.security;

import com.altarix.dtos.security.ReCaptchaResponseDto;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class ReCaptchaApiServiceImpl implements ReCaptchaApiService {
    @Value("${app.reCaptcha.apiUrl}")
    private String reCaptchaApiUrl;
    @Value("${app.reCaptcha.secretKey}")
    private String secretKey;

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public Boolean verify(@NonNull String recaptchaResponse) {
        return getResponse(recaptchaResponse).isSuccess();
    }

    @Override
    public ReCaptchaResponseDto getResponse(@NonNull String recaptchaResponse) {
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("secret", secretKey);
        form.add("response", recaptchaResponse);
        ReCaptchaResponseDto reCaptchaResponse = restTemplate.postForObject(reCaptchaApiUrl, form, ReCaptchaResponseDto.class);
        log.info("{}", reCaptchaResponse);
        return reCaptchaResponse;
    }

}