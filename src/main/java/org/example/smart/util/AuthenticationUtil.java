package org.example.smart.util;

import org.example.smart.security.model.CustomUserDetails;
import org.springframework.security.core.Authentication;

public class AuthenticationUtil {
	public static Long getSoldierId(Authentication authentication) {
		CustomUserDetails customUserDetails = (CustomUserDetails)authentication.getPrincipal();
		return customUserDetails.getSoldierId();
	}
}
