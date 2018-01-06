package com.altarix.services;

import dtos.security.ReCaptchaResponseDto;
import lombok.NonNull;

public interface ReCaptchaApiService {
    Boolean verify(@NonNull String recaptchaResponse);
    ReCaptchaResponseDto getResponse(@NonNull String recaptchaResponse);
}
