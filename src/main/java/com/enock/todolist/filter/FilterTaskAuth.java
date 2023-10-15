package com.enock.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.enock.todolist.user.IUserRepository;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

	@Autowired
	private IUserRepository userRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// pegar a autenticaçao, usuario e senha
		var authorization = request.getHeader("Authorization");

		var auth_encoded = authorization.substring("Basic".length()).trim(); // separa o basic da criptografia

		byte[] authDecode = Base64.getDecoder().decode(auth_encoded); // traduz a criptografia pra base64
		var authString = new String(authDecode); // traduz da base64 para string

		String[] credentials = authString.split(":");
		String username = credentials[0];
		String password = credentials[1];

		System.out.println(username);
		System.out.println(password);

		// validar usuario
		var user = this.userRepository.findByUsername(username);
		if (user == null) {
			response.sendError(401);
		} else {
			// validar senha
			var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
			if(passwordVerify.verified) {
				filterChain.doFilter(request, response);
			}else {
				response.sendError(401);
			}

			// aceito
		}
		

	}

}