package com.bins.springcloud.shop.user.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SecurityUtils {

	public static String getUsername() {
		Authentication authentication = getAuthentication();
		return getUsername(authentication);
	}

	public static String getUsername(Authentication authentication) {
		if (authentication == null) {
			return null;
		}
		CustomUserDetails customUser = (CustomUserDetails) authentication.getPrincipal();
		return customUser.getUsername();
	}

	public static Long getUserId(Authentication authentication) {
		if (authentication == null) {
			return null;
		}
		CustomUserDetails customUser = (CustomUserDetails) authentication.getPrincipal();
		return customUser.getUserId();
	}

	public static Long getUserId() {
		Authentication authentication = getAuthentication();
		return getUserId(authentication);
	}

	public static Authentication getAuthentication() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		if (securityContext == null) {
			return null;
		}
		Authentication authentication = securityContext.getAuthentication();
		if (authentication == null) {
			return null;
		}
		return authentication;
	}

	public static String getBcryptPassword(String password) {
		BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
		return bcryptPasswordEncoder.encode(password);
	}

	public static boolean validatePassword(String originPassword, String password) {
		BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
		return bcryptPasswordEncoder.matches(originPassword, password);
	}

}
