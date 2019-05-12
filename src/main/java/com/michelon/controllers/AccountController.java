package com.michelon.controllers;

import com.michelon.dto.request.CreateAccountRequestDto;
import com.michelon.dto.response.CreateAccountResponseDto;
import com.michelon.dto.response.ResponseDto;
import com.michelon.model.Account;
import com.michelon.service.AccountService;

import spark.Request;

public class AccountController implements PostController<CreateAccountRequestDto> {

    public static final String ROUTE = "/account";
    
	private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
	
    @Override
	public ResponseDto<CreateAccountResponseDto> handle(Request request, CreateAccountRequestDto newAccountDto) {
		validate(newAccountDto);
		Account account = accountService.createNewAccount(newAccountDto);
		return new ResponseDto<>(201, new CreateAccountResponseDto(account.getId()));
	}

	private void validate(CreateAccountRequestDto newAccountDto) {
		if (newAccountDto.getInitialBalance() == null) {
    		throw new IllegalArgumentException("initialBalance must be not null.");
		}
	}
	
	@Override
	public Class<CreateAccountRequestDto> getBodyClass() {
		return CreateAccountRequestDto.class;
	}

	@Override
	public String getRoute() {
		return ROUTE;
	}
	
}
