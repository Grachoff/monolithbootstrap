package com.altarix.services.security;

import com.altarix.dtos.security.ReCaptchaResponseDto;
import lombok.NonNull;

public interface ReCaptchaApiService {
    Boolean verify(@NonNull String recaptchaResponse);
    ReCaptchaResponseDto getResponse(@NonNull String recaptchaResponse);
}
